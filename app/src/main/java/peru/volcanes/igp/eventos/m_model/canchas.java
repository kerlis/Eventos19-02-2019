package peru.volcanes.igp.eventos.m_model;

public class canchas {

    String distrito;
    String local;
    String nombre;


    String estacionamiento;
    String descripcion;
    String incluye;
    String servicioshigienicos;
    String tribuna;
    String foto;

    public String getEstacionamiento() {
        return estacionamiento;
    }

    public void setEstacionamiento(String estacionamiento) {
        this.estacionamiento = estacionamiento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIncluye() {
        return incluye;
    }

    public void setIncluye(String incluye) {
        this.incluye = incluye;
    }

    public String getServicioshigienicos() {
        return servicioshigienicos;
    }

    public void setServicioshigienicos(String servicioshigienicos) {
        this.servicioshigienicos = servicioshigienicos;
    }

    public String getTribuna() {
        return tribuna;
    }

    public void setTribuna(String tribuna) {
        this.tribuna = tribuna;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public canchas(String distrito, String local, String nombre, String estacionamiento, String descripcion, String incluye, String servicioshigienicos, String tribuna, String foto) {
        this.distrito = distrito;
        this.local = local;
        this.nombre = nombre;
        this.estacionamiento = estacionamiento;
        this.descripcion = descripcion;
        this.incluye = incluye;
        this.servicioshigienicos = servicioshigienicos;
        this.tribuna = tribuna;
        this.foto = foto;
    }

    public canchas() {
    }
}
