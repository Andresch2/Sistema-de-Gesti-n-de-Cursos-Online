package co.api.ventaCursos.service;


import co.api.ventaCursos.Modelo.Tutor;
import co.api.ventaCursos.Repository.TutorRepository;
import co.api.ventaCursos.error.LocalNotFoundException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TutorService implements ITutorService {

    @Autowired
    private TutorRepository tutorRepository;


    @Override
    public Tutor crearTutor(Tutor tutor) {
        return tutorRepository.save(tutor);
    }

    @Override
    public List<Tutor> obtenerTodosLosTutores() {
        return tutorRepository.findAll();
    }
    

    @Override
    public Optional<Tutor> obtenerTutorPorId(ObjectId id) throws LocalNotFoundException {
        Optional<Tutor> tutor = tutorRepository.findById(id);
        if (!tutor.isPresent()) {
            throw new LocalNotFoundException("Tutor no encontrado con el ID: " + id);
        }
        return tutor;
    }

    @Override
    public void eliminarTutor(ObjectId id) {
        tutorRepository.deleteById(id);
    }

    @Override
    public boolean iniciarSesion(String correo, String contraseña) {
        Tutor tutor = tutorRepository.findByCorreo(correo);
        return tutor != null && tutor.getContraseña().equals(contraseña);
    }
}