package co.api.ventaCursos;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import co.api.ventaCursos.Controller.CursoController;
import co.api.ventaCursos.Modelo.Curso;
import co.api.ventaCursos.error.LocalNotFoundException;
import co.api.ventaCursos.service.CursoService;

class VentaCursosCursoTests {

    @InjectMocks
    private CursoController cursoController;

    @Mock
    private CursoService cursoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCrearCurso() {
        Curso curso = new Curso("Descripcion", "Titulo", "Contacto", 100.0, "Tutor");
        when(cursoService.crearCurso(curso)).thenReturn(curso);

        ResponseEntity<Curso> response = cursoController.crearCurso(curso);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(curso, response.getBody());
    }

    @Test
    void testObtenerTodosLosCursos() {
        List<Curso> cursos = new ArrayList<>();
        cursos.add(new Curso("Descripcion", "Titulo", "Contacto", 100.0, "Tutor"));
        when(cursoService.obtenerTodosLosCursos()).thenReturn(cursos);

        ResponseEntity<List<Curso>> response = cursoController.obtenerTodosLosCursos();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cursos, response.getBody());
    }

    @Test
    void testObtenerCursoPorId_Exito() throws LocalNotFoundException {
        ObjectId id = new ObjectId();
        Curso curso = new Curso("Descripcion", "Titulo", "Contacto", 100.0, "Tutor");
        when(cursoService.obtenerCursoPorId(id)).thenReturn(Optional.of(curso));

        ResponseEntity<Curso> response = cursoController.obtenerCursoPorId(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(curso, response.getBody());
    }

    @Test
    void testObtenerCursoPorId_Fallo() throws LocalNotFoundException {
        ObjectId id = new ObjectId();
        when(cursoService.obtenerCursoPorId(id)).thenReturn(Optional.empty());

        ResponseEntity<Curso> response = cursoController.obtenerCursoPorId(id);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testEliminarCurso() {
        ObjectId id = new ObjectId();
        doNothing().when(cursoService).eliminarCurso(id);

        ResponseEntity<Void> response = cursoController.eliminarCurso(id);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testEditarCurso_Exito() throws LocalNotFoundException {
        ObjectId id = new ObjectId();
        Curso actualizado = new Curso("Nueva Descripcion", "Nuevo Titulo", "Nuevo Contacto", 150.0, "Nuevo Tutor");

        when(cursoService.editarCurso(id, actualizado)).thenReturn(actualizado);

        ResponseEntity<Curso> response = cursoController.editarCurso(id, actualizado);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(actualizado, response.getBody());
    }

    @Test
    void testEditarCurso_Fallo() throws LocalNotFoundException {
        ObjectId id = new ObjectId();
        Curso actualizado = new Curso("Nueva Descripcion", "Nuevo Titulo", "Nuevo Contacto", 150.0, "Nuevo Tutor");

        when(cursoService.editarCurso(id, actualizado)).thenThrow(new LocalNotFoundException("Curso no encontrado"));

        ResponseEntity<Curso> response = cursoController.editarCurso(id, actualizado);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
    
}
