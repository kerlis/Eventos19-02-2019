package peru.volcanes.igp.eventos.m_model;

public class canchas {

    String distrito;
    String local;
    String nombre;

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

    public canchas(String distrito, String local, String nombre) {
        this.distrito = distrito;
        this.local = local;
        this.nombre = nombre;
    }

    public canchas() {
    }
}
