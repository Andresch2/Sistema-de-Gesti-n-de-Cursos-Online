package co.api.ventaCursos.Repository;
import co.api.ventaCursos.Modelo.Tutor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorRepository extends MongoRepository<Tutor, ObjectId> {

	Tutor findByCorreo(String correo);
}