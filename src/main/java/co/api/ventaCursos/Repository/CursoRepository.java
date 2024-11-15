package co.api.ventaCursos.Repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import co.api.ventaCursos.Modelo.Curso;

public interface CursoRepository extends MongoRepository<Curso, ObjectId> {
	

}