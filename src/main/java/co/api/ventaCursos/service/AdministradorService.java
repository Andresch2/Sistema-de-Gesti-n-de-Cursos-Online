package co.api.ventaCursos.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.api.ventaCursos.Modelo.Administrador;
import co.api.ventaCursos.Repository.AdministradorRepository;
import co.api.ventaCursos.error.LocalNotFoundException;

@Service
public class AdministradorService implements IAdministradorService {

    @Autowired
    private AdministradorRepository administradorRepository;

    @Override
    public Administrador crearAdministrador(Administrador administrador) {
        return administradorRepository.save(administrador);
    }

    @Override
    public List<Administrador> obtenerTodosLosAdministradores() {
        return administradorRepository.findAll();
    }

    @Override
    public Optional<Administrador> obtenerAdministradorPorId(ObjectId id) throws LocalNotFoundException {
        Optional<Administrador> administrador = administradorRepository.findById(id);
        if (!administrador.isPresent()) {
            throw new LocalNotFoundException("Administrador no  encontrado con el ID: " + id);
        }
        return administrador;
    }

    @Override
    public void eliminarAdministrador(ObjectId id) {
        administradorRepository.deleteById(id);
    }

    @Override
    public boolean iniciarSesion(String correo, String contraseña) {
        Administrador administrador = administradorRepository.findByCorreo(correo);
        return administrador != null && administrador.getContraseña().equals(contraseña);
    }
}