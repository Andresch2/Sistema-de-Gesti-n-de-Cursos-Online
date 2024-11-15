package co.api.ventaCursos.Modelo;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public abstract class Persona {
    
	
    @Length(min=10, max=80)
    @Email(message = "El correo electrónico debe tener un formato válido")
    private String correo;
    
    @Length(min=8, max=50)
    private String contraseña;

    public Persona(String correo, String contraseña) {
        this.correo = correo;
        this.contraseña = contraseña;
    }

    public boolean iniciarSesion(String contraseña, String correo) {
        return this.contraseña.equals(contraseña) && this.correo.equals(correo);
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    @Override
    public String toString() {
        return "Persona [correo=" + correo + ", contraseña=" + contraseña + "]";
    }

}