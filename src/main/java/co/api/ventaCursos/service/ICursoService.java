package co.api.ventaCursos.service;

import co.api.ventaCursos.Modelo.Curso;
import co.api.ventaCursos.error.LocalNotFoundException;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface ICursoService {
    Curso crearCurso(Curso curso);
    List<Curso> obtenerTodosLosCursos();
    Optional<Curso> obtenerCursoPorId(ObjectId id) throws LocalNotFoundException;
    void eliminarCurso(ObjectId id);
    Curso editarCurso(ObjectId id, Curso cursoActualizado) throws LocalNotFoundException;
}