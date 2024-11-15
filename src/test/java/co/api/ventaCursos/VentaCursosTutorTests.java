package co.api.ventaCursos;


import co.api.ventaCursos.Controller.TutorController;
import co.api.ventaCursos.Modelo.Tutor;
import co.api.ventaCursos.error.LocalNotFoundException;
import co.api.ventaCursos.service.TutorService;
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

class VentaCursosTutorTests {

    @InjectMocks
    private TutorController tutorController;

    @Mock
    private TutorService tutorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCrearTutor() {
        Tutor tutor = new Tutor("correo@example.com", "contraseña", "Nombre Completo", "30");
        when(tutorService.crearTutor(tutor)).thenReturn(tutor);

        ResponseEntity<Tutor> response = tutorController.crearTutor(tutor);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(tutor, response.getBody());
    }

    @Test
    void testObtenerTodosLosTutores() {
        List<Tutor> tutores = new ArrayList<>();
        tutores.add(new Tutor("correo@example.com", "contraseña", "Nombre Completo", "30"));
        when(tutorService.obtenerTodosLosTutores()).thenReturn(tutores);

        ResponseEntity<List<Tutor>> response = tutorController.obtenerTodosLosTutores();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tutores, response.getBody());
    }

    @Test
    void testObtenerTutorPorId_Exito() throws LocalNotFoundException {
        ObjectId id = new ObjectId();
        Tutor tutor = new Tutor("correo@example.com", "contraseña", "Nombre Completo", "30");
        when(tutorService.obtenerTutorPorId(id)).thenReturn(Optional.of(tutor));

        ResponseEntity<Tutor> response = tutorController.obtenerTutorPorId(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tutor, response.getBody());
    }

    @Test
    void testObtenerTutorPorId_Fallo() throws LocalNotFoundException {
        ObjectId id = new ObjectId();
        when(tutorService.obtenerTutorPorId(id)).thenReturn(Optional.empty());

        ResponseEntity<Tutor> response = tutorController.obtenerTutorPorId(id);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
    
    @Test
    void testEliminarTutor() {
        ObjectId id = new ObjectId();
        doNothing().when(tutorService).eliminarTutor(id);

        ResponseEntity<Void> response = tutorController.eliminarTutor(id);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

}