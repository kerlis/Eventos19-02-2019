package peru.volcanes.igp.eventos.m_model;

public class canchas {
    String canchaid;
    String descripcion;
    String distrito;
    String estacionamiento;
    String foto;
    String incluye;
    String local;
    String local_distrito;
    String localid;
    String nombre;
    String servicioshigienicos;
    String tribuna;
    public String getCanchaid() {
        return canchaid;
    }

    public void setCanchaid(String canchaid) {
        this.canchaid = canchaid;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getEstacionamiento() {
        return estacionamiento;
    }

    public void setEstacionamiento(String estacionamiento) {
        this.estacionamiento = estacionamiento;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getIncluye() {
        return incluye;
    }

    public void setIncluye(String incluye) {
        this.incluye = incluye;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getLocal_distrito() {
        return local_distrito;
    }

    public void setLocal_distrito(String local_distrito) {
        this.local_distrito = local_distrito;
    }

    public String getLocalid() {
        return localid;
    }

    public void setLocalid(String localid) {
        this.localid = localid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public canchas(String canchaid, String descripcion, String distrito, String estacionamiento, String foto, String incluye, String local, String local_distrito, String localid, String nombre, String servicioshigienicos, String tribuna) {
        this.canchaid = canchaid;
        this.descripcion = descripcion;
        this.distrito = distrito;
        this.estacionamiento = estacionamiento;
        this.foto = foto;
        this.incluye = incluye;
        this.local = local;
        this.local_distrito = local_distrito;
        this.localid = localid;
        this.nombre = nombre;
        this.servicioshigienicos = servicioshigienicos;
        this.tribuna = tribuna;
    }

    public canchas() {
    }
}
