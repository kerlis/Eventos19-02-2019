package peru.volcanes.igp.eventos.m_model;

public class usuario {

    String nombreusuario;
    String email;
    String telefono;
    String dni;
    String password;

    public String getNombreusuario() {
        return nombreusuario;
    }

    public void setNombreusuario(String nombreusuario) {
        this.nombreusuario = nombreusuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public usuario(String nombreusuario, String email, String telefono, String dni, String password) {
        this.nombreusuario = nombreusuario;
        this.email = email;
        this.telefono = telefono;
        this.dni = dni;
        this.password = password;
    }

    public usuario() {
    }
}
