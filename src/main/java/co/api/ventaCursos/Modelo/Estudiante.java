package co.api.ventaCursos.Modelo;


import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Positive;

public class Estudiante extends Persona {
	
	@Length(min=5, max=30)
	private String nombreCompleto;
	
	@Positive(message="El numero debe ser positivo")
	private int edad;
	
	public Estudiante(String correo, String contraseña, String nombreCompleto, int edad) {
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

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}


	public void comprarCursos() {
		
	}

	public void registrarse(String nombreCompleto,int edad, String contraseña, String correo) {
	}	

	public void recuperarContraseña() {
	
	}
	
	@Override
	public boolean iniciarSesion(String contraseña, String correo) {
		Estudiante estudianteEncontrado = buscarEstudiantePorCorreo(correo);
		if (estudianteEncontrado != null) {
			return estudianteEncontrado.getContraseña().equals(contraseña) &&
					estudianteEncontrado.getCorreo().equals(correo);
		}
		return false;
	}

	private Estudiante buscarEstudiantePorCorreo(String correo) {
		return null;
	}

	@Override
	public String toString() {
		return "Estudiante [nombreCompleto=" + nombreCompleto + ", edad=" + edad + ", getCorreo()=" + getCorreo()
				+ ", getContraseña()=" + getContraseña() + "]";
	}

}