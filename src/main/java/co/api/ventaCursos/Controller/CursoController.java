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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.api.ventaCursos.Modelo.Curso;
import co.api.ventaCursos.error.LocalNotFoundException;
import co.api.ventaCursos.service.CursoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/cursos")

public class CursoController {

    @Autowired
    private CursoService cursoService;

    @PostMapping("/crear")
    public ResponseEntity<Curso> crearCurso(@Valid @RequestBody Curso curso) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.crearCurso(curso));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/obtener-todos")
    public ResponseEntity<List<Curso>> obtenerTodosLosCursos() {
        try {
            return ResponseEntity.ok().body(cursoService.obtenerTodosLosCursos());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/obtener-por-id/{id}")
    public ResponseEntity<Curso> obtenerCursoPorId(@PathVariable ObjectId id){
        try {
            Optional<Curso> curso = cursoService.obtenerCursoPorId(id);
            return curso.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

        @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarCurso(@PathVariable ObjectId id) {
        try {
            cursoService.eliminarCurso(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Curso> editarCurso(@PathVariable ObjectId id, @Valid @RequestBody Curso cursoActualizado) {
        try {
            Curso cursoEditado = cursoService.editarCurso(id, cursoActualizado);
            return ResponseEntity.ok().body(cursoEditado);
        } catch (LocalNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}