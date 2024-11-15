package co.api.ventaCursos.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;

import co.api.ventaCursos.Modelo.Administrador;
import co.api.ventaCursos.error.LocalNotFoundException;

public interface IAdministradorService {
    Administrador crearAdministrador(Administrador administrador);

    List<Administrador> obtenerTodosLosAdministradores();

    Optional<Administrador> obtenerAdministradorPorId(ObjectId id) throws LocalNotFoundException;

    void eliminarAdministrador(ObjectId id);

    boolean iniciarSesion(String correo, String contraseña);
}