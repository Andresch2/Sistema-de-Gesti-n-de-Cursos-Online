package co.api.ventaCursos;

import co.api.ventaCursos.Controller.EstudianteController;
import co.api.ventaCursos.Modelo.Estudiante;
import co.api.ventaCursos.error.LocalNotFoundException;
import co.api.ventaCursos.service.EstudianteService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VentaCursosEstudianteTests {

    @InjectMocks
    private EstudianteController estudianteController;

    @Mock
    private EstudianteService estudianteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCrearEstudiante() throws LocalNotFoundException {
        Estudiante estudiante = new Estudiante("correo@example.com", "contraseña", "Nombre Completo", 20);
        when(estudianteService.crearEstudiante(estudiante)).thenReturn(estudiante);

        ResponseEntity<Estudiante> response = estudianteController.crearEstudiante(estudiante);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(estudiante, response.getBody());
    }

    @Test
    void testObtenerTodosLosEstudiantes() {
        List<Estudiante> estudiantes = new ArrayList<>();
        estudiantes.add(new Estudiante("correo@example.com", "contraseña", "Nombre Completo", 20));
        when(estudianteService.obtenerTodosLosEstudiantes()).thenReturn(estudiantes);

        ResponseEntity<List<Estudiante>> response = estudianteController.obtenerTodosLosEstudiantes();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(estudiantes, response.getBody());
    }

    @Test
    void testObtenerEstudiantePorId_Exito() throws LocalNotFoundException {
        ObjectId id = new ObjectId();
        Estudiante estudiante = new Estudiante("correo@example.com", "contraseña", "Nombre Completo", 20);
        when(estudianteService.obtenerEstudiantePorId(id)).thenReturn(Optional.of(estudiante));

        ResponseEntity<Estudiante> response = estudianteController.obtenerEstudiantePorId(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(estudiante, response.getBody());
    }

    @Test
    void testObtenerEstudiantePorId_Fallo() throws LocalNotFoundException {
        ObjectId id = new ObjectId();
        when(estudianteService.obtenerEstudiantePorId(id)).thenReturn(Optional.empty());

        ResponseEntity<Estudiante> response = estudianteController.obtenerEstudiantePorId(id);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testEliminarEstudiante() {
        ObjectId id = new ObjectId();
        doNothing().when(estudianteService).eliminarEstudiante(id);

        ResponseEntity<Void> response = estudianteController.eliminarEstudiante(id);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testIniciarSesion_Exito() {
        String correo = "correo@example.com";
        String contraseña = "contraseña";
        Estudiante estudiante = new Estudiante(correo, contraseña, "Nombre Completo", 20);
        when(estudianteService.iniciarSesion(correo, contraseña)).thenReturn(true);

        ResponseEntity<String> response = estudianteController.iniciarSesion(estudiante);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Inicio de sesión exitoso", response.getBody());
    }
    
    @Test
    void testIniciarSesion_Fallo() {
        String correo = "correo@example.com";
        String contraseña = "contraseña";
        Estudiante estudiante = new Estudiante(correo, contraseña, "Nombre Completo", 20);
        when(estudianteService.iniciarSesion(correo, contraseña)).thenReturn(false);

        ResponseEntity<String> response = estudianteController.iniciarSesion(estudiante);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Credenciales incorrectas", response.getBody());
    }

}