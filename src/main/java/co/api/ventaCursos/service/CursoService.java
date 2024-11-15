package co.api.ventaCursos.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.api.ventaCursos.Modelo.Curso;
import co.api.ventaCursos.Repository.CursoRepository;
import co.api.ventaCursos.error.LocalNotFoundException;

@Service
public class CursoService implements ICursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Override
    public Curso crearCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    @Override
    public List<Curso> obtenerTodosLosCursos() {
        return cursoRepository.findAll();
    }

    @Override
    public Optional<Curso> obtenerCursoPorId(ObjectId id) throws LocalNotFoundException {
        Optional<Curso> curso = cursoRepository.findById(id);
        if (curso.isEmpty()) {
            throw new LocalNotFoundException("Curso no encontrado");
        }
        return curso;
    }

    @Override
    public void eliminarCurso(ObjectId id) {
        cursoRepository.deleteById(id);
    }

    @Override
    public Curso editarCurso(ObjectId id, Curso cursoActualizado) throws LocalNotFoundException {
        Optional<Curso> cursoExistente = cursoRepository.findById(id);
        if (cursoExistente.isPresent()) {
            Curso curso = cursoExistente.get();
            curso.setDescripcion(cursoActualizado.getDescripcion());
            curso.setTitulo(cursoActualizado.getTitulo());
            curso.setContacto(cursoActualizado.getContacto());
            curso.setPrecio(cursoActualizado.getPrecio());
            curso.setNombreTutor(cursoActualizado.getNombreTutor());
            return cursoRepository.save(curso);
        } else {
            throw new LocalNotFoundException("Curso no encontrado");
        }
    }
}

//Subiendo Services