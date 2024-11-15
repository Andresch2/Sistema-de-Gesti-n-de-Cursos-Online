package co.api.ventaCursos.service;

import co.api.ventaCursos.Modelo.Estudiante;
import co.api.ventaCursos.error.LocalNotFoundException;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface IEstudianteService {
    Estudiante crearEstudiante(Estudiante estudiante);
    List<Estudiante> obtenerTodosLosEstudiantes();
    Optional<Estudiante> obtenerEstudiantePorId(ObjectId id) throws LocalNotFoundException;
    void eliminarEstudiante(ObjectId id);
    boolean iniciarSesion(String correo, String contraseña);
}