package co.api.ventaCursos.Modelo;

public class Administrador extends Persona {

    public Administrador(String correo, String contraseña) {
        super(correo, contraseña);
    }

    public void eliminarTutor() {

    }
    // subiendo a repositorio

    @Override
    public boolean iniciarSesion(String contraseña, String correo) {

        Administrador adminEncontrado = buscarAdministradorPorCorreo(correo);
        if (adminEncontrado != null) {
            return adminEncontrado.getContraseña().equals(contraseña) && adminEncontrado.getCorreo().equals(correo);
        }
        return false;
    }

    private Administrador buscarAdministradorPorCorreo(String correo) {
        return null;
    }

    @Override
    public String toString() {
        return "Administrador [getCorreo()=" + getCorreo() + ", getContraseña()=" + getContraseña() + "]";
    }
}
