package co.api.ventaCursos.Modelo;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class Tutor extends Persona {
	
	@Length(min=5, max=30)
	@NotBlank(message="Espacio obligatorio")
	private String nombreCompleto;
	
	@Positive(message="El numero debe ser positivo")
	@NotBlank(message="Espacio obligatorio")
	private String edad;

    public Tutor(String correo, String contraseña, String nombreCompleto, String edad) {
    	super(correo, contraseña);
    	this.nombreCompleto = nombreCompleto;
    	this.edad = edad;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public void registrar() {

    }

    public void agregarCurso() {

    }

    public void editarCurso() {

    }

    public void eliminarCurso() {

    }

    public void recuperarContraseña() {

    }

    @Override
    public boolean iniciarSesion(String contraseña, String correo) {
        Tutor tutorEncontrado = buscarTutorPorCorreo(correo);
        if (tutorEncontrado != null) {
            return tutorEncontrado.getContraseña().equals(contraseña) &&
                   tutorEncontrado.getCorreo().equals(correo);
        }
        return false;
    }

    private Tutor buscarTutorPorCorreo(String correo) {
        return null; 
    }

	@Override
	public String toString() {
		return "Tutor [nombreCompleto=" + nombreCompleto + ", edad=" + edad + ", getCorreo()=" + getCorreo()
				+ ", getContraseña()=" + getContraseña() + "]";
	}
}