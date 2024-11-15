package co.api.ventaCursos.Controller;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.api.ventaCursos.Modelo.Administrador;
import co.api.ventaCursos.service.AdministradorService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/administradores")
public class AdministradorController {

    @Autowired
    private AdministradorService administradorService;

    @PostMapping("/crear")
    public ResponseEntity<Administrador> crearAdministrador(@Valid @RequestBody Administrador administrador) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(administradorService.crearAdministrador(administrador));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/obtener-todos")
    public ResponseEntity<List<Administrador>> obtenerTodosLosAdministradores() {
        try {
            return ResponseEntity.ok().body(administradorService.obtenerTodosLosAdministradores());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/obtener-por-id/{id}")
    public ResponseEntity<Administrador> obtenerAdministradorPorId(@PathVariable ObjectId id) {
        try {
            Optional<Administrador> administrador = administradorService.obtenerAdministradorPorId(id);
            return administrador.map(value -> ResponseEntity.ok().body(value))
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarAdministrador(@PathVariable ObjectId id) {
        try {
            administradorService.eliminarAdministrador(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> iniciarSesion(@RequestBody Administrador administrador) {
        try {
            boolean authenticated = administradorService.iniciarSesion(administrador.getCorreo(),
                    administrador.getContraseña());
            if (authenticated) {
                return ResponseEntity.ok().body("Inicio de sesión exitoso");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }
}