package peru.volcanes.igp.eventos.m_model;

public class reservas {

    String fecha;
    String hora;

    String departamento;
    String departamento_distrito;
    String distrito;
    String fecha_hora;
    String cancha_nombre;
    String local;

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


    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getDepartamento_distrito() {
        return departamento_distrito;
    }

    public void setDepartamento_distrito(String departamento_distrito) {
        this.departamento_distrito = departamento_distrito;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(String fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    public String getCancha_nombre() {
        return cancha_nombre;
    }

    public void setCancha_nombre(String cancha_nombre) {
        this.cancha_nombre = cancha_nombre;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }


    public reservas(String fecha, String hora, String departamento, String departamento_distrito, String distrito, String fecha_hora, String cancha_nombre, String local) {
        this.fecha = fecha;
        this.hora = hora;
        this.departamento = departamento;
        this.departamento_distrito = departamento_distrito;
        this.distrito = distrito;
        this.fecha_hora = fecha_hora;
        this.cancha_nombre = cancha_nombre;
        this.local = local;
    }

    public reservas() {
    }

}