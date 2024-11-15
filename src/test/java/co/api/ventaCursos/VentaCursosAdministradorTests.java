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

import co.api.ventaCursos.Controller.AdministradorController;
import co.api.ventaCursos.Modelo.Administrador;
import co.api.ventaCursos.error.LocalNotFoundException;
import co.api.ventaCursos.service.AdministradorService;

class VentaCursosAdministradorTests {

    @InjectMocks
    private AdministradorController administradorController;

    @Mock
    private AdministradorService administradorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCrearAdministrador() {
        Administrador administrador = new Administrador("correo@example.com", "contraseña");
        when(administradorService.crearAdministrador(administrador)).thenReturn(administrador);

        ResponseEntity<Administrador> response = administradorController.crearAdministrador(administrador);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(administrador, response.getBody());
    }

    @Test
    void testObtenerTodosLosAdministradores() {
        List<Administrador> administradores = new ArrayList<>();
        administradores.add(new Administrador("correo@example.com", "contraseña"));
        when(administradorService.obtenerTodosLosAdministradores()).thenReturn(administradores);

        ResponseEntity<List<Administrador>> response = administradorController.obtenerTodosLosAdministradores();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(administradores, response.getBody());
    }

    @Test
    void testObtenerAdministradorPorId_Exito() throws LocalNotFoundException {
        ObjectId id = new ObjectId();
        Administrador administrador = new Administrador("correo@example.com", "contraseña");
        when(administradorService.obtenerAdministradorPorId(id)).thenReturn(Optional.of(administrador));

        ResponseEntity<Administrador> response = administradorController.obtenerAdministradorPorId(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(administrador, response.getBody());
    }

    @Test
    void testObtenerAdministradorPorId_Fallo() throws LocalNotFoundException {
        ObjectId id = new ObjectId();
        when(administradorService.obtenerAdministradorPorId(id)).thenReturn(Optional.empty());

        ResponseEntity<Administrador> response = administradorController.obtenerAdministradorPorId(id);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testEliminarAdministrador() {
        ObjectId id = new ObjectId();
        doNothing().when(administradorService).eliminarAdministrador(id);

        ResponseEntity<Void> response = administradorController.eliminarAdministrador(id);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testIniciarSesion_Exito() {
        String correo = "correo@example.com";
        String contraseña = "contraseña";
        Administrador administrador = new Administrador(correo, contraseña);
        when(administradorService.iniciarSesion(correo, contraseña)).thenReturn(true);

        ResponseEntity<String> response = administradorController.iniciarSesion(administrador);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Inicio de sesión exitoso", response.getBody());
    }
    
    @Test
    void testIniciarSesion_Fallo() {
        Administrador administrador = new Administrador("admin@correo.com", "12345");
        when(administradorService.iniciarSesion(administrador.getCorreo(), administrador.getContraseña())).thenReturn(false);

        ResponseEntity<String> response = administradorController.iniciarSesion(administrador);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Credenciales incorrectas", response.getBody());
    }
}
