package co.api.ventaCursos.Repository;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import co.api.ventaCursos.Modelo.Estudiante;

@Repository
public interface EstudianteRepository extends MongoRepository<Estudiante, ObjectId> {

	Estudiante findByCorreo(String correo);

}