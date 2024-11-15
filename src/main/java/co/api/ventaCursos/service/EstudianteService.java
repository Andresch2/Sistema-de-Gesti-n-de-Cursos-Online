package co.api.ventaCursos.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.api.ventaCursos.Modelo.Estudiante;
import co.api.ventaCursos.Repository.EstudianteRepository;
import co.api.ventaCursos.error.LocalNotFoundException;

@Service
public class EstudianteService implements IEstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Override
    public Estudiante crearEstudiante(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    @Override
    public List<Estudiante> obtenerTodosLosEstudiantes() {
        return estudianteRepository.findAll();
    }

    @Override
    public Optional<Estudiante> obtenerEstudiantePorId(ObjectId id) throws LocalNotFoundException {
        Optional<Estudiante> estudiante = estudianteRepository.findById(id);
        if (!estudiante.isPresent()) {
            throw new LocalNotFoundException("Estudiante no encontrado con el ID: " + id);
        }
        return estudiante;
    }

    @Override
    public void eliminarEstudiante(ObjectId id) {
        estudianteRepository.deleteById(id);
    }

    @Override
    public boolean iniciarSesion(String correo, String contraseña) {
        Estudiante estudiante = estudianteRepository.findByCorreo(correo);
        return estudiante != null && estudiante.getContraseña().equals(contraseña);
    }
}