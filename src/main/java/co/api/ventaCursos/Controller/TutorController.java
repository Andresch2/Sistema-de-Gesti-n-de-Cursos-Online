package co.api.ventaCursos.Controller;

import co.api.ventaCursos.Modelo.Tutor;
import co.api.ventaCursos.error.LocalNotFoundException;
import co.api.ventaCursos.service.TutorService;
import jakarta.validation.Valid;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tutores")
public class TutorController {

    @Autowired
    private TutorService tutorService;

    @PostMapping("/crear")
    public ResponseEntity<Tutor> crearTutor(@Valid @RequestBody Tutor tutor){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(tutorService.crearTutor(tutor));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @GetMapping("/obtener-todos")
    public ResponseEntity<List<Tutor>> obtenerTodosLosTutores() {
        try {
            return ResponseEntity.ok().body(tutorService.obtenerTodosLosTutores());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/obtener-por-id/{id}")
    public ResponseEntity<Tutor> obtenerTutorPorId(@PathVariable ObjectId id)throws LocalNotFoundException {
        try {
            Optional<Tutor> tutor = tutorService.obtenerTutorPorId(id);
            return tutor.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarTutor(@PathVariable ObjectId id) {
        try {
            tutorService.eliminarTutor(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}