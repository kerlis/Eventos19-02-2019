package peru.volcanes.igp.eventos.m_model;

public class reservas {

    String fecha;
    String hora;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public reservas(String fecha, String hora) {
        this.fecha = fecha;
        this.hora = hora;
    }

    public reservas() {
    }

}