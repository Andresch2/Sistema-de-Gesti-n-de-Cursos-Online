package co.api.ventaCursos.Repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import co.api.ventaCursos.Modelo.Administrador;

@Repository
public interface AdministradorRepository extends MongoRepository<Administrador, ObjectId> {

    Administrador findByCorreo(String correo);

}