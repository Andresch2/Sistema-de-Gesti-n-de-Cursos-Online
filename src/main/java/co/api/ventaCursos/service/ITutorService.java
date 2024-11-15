package co.api.ventaCursos.service;

import co.api.ventaCursos.Modelo.Tutor;
import co.api.ventaCursos.error.LocalNotFoundException;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface ITutorService {
    Tutor crearTutor(Tutor tutor);
    List<Tutor> obtenerTodosLosTutores();
    Optional<Tutor> obtenerTutorPorId(ObjectId id) throws LocalNotFoundException;
    void eliminarTutor(ObjectId id);
    boolean iniciarSesion(String correo, String contraseña);
}