package co.api.ventaCursos.Modelo;

import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import jakarta.validation.constraints.Positive;


@Document(collection = "cursos")
public class Curso {
	
    @Id
    @JsonSerialize(using = ObjectIdSerializer.class)
	private ObjectId id;
    
    @Length(min=0, max=800)
    private String descripcion;
    
    @Length(min=10, max=100)
    private String titulo;
    
    @Length(min=5, max=15)
    private String contacto;
   
    @Positive(message="El numero debe ser positivo")
    private double precio;
    
    @Length(min=10, max=50)
    private String nombreTutor;

    public Curso(String descripcion, String titulo, String contacto, double precio, String nombreTutor) {
        this.descripcion = descripcion;
        this.titulo = titulo;
        this.contacto = contacto;
        this.precio = precio;
        this.nombreTutor = nombreTutor;
    }

    public void buscar() {
       
    }

    public void generarCertificado() {
       
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }
    
    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getNombreTutor() {
        return nombreTutor;
    }

    public void setNombreTutor(String nombreTutor) {
        this.nombreTutor = nombreTutor;
    }

    public void agregarCurso() {

    }
    
    public void editarCurso() {
    
    }
    
    public void eliminarCurso() {
    
    }

	@Override
	public String toString() {
		return "Curso [id=" + id + ", descripcion=" + descripcion + ", titulo=" + titulo + ", contacto=" + contacto
				+ ", precio=" + precio + ", nombreTutor=" + nombreTutor + "]";
	}
	
    
}