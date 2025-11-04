package model.entities;

import java.sql.Time;
import java.sql.Date;
import model.enums.EstadoJornadaVacunacion;

public class JornadaVacunacion {

    private int id;
    private String nombre;
    private Date fecha;
    private Time horaInicio;
    private Time horaFin;
    private String ubicacion;
    private String descripcion;
    private int capacidadMaxima;
    private EstadoJornadaVacunacion.Estado estado = EstadoJornadaVacunacion.Estado.PLANIFICADA;

    public JornadaVacunacion() {}

    public JornadaVacunacion(String nombre, Date fecha, Time horaInicio, Time horaFin,
                             String ubicacion, String descripcion, int capacidadMaxima, EstadoJornadaVacunacion.Estado estado) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
        this.capacidadMaxima = capacidadMaxima;
        this.estado = estado;
    }

    public JornadaVacunacion(int id, String nombre, Date fecha, Time horaInicio, Time horaFin,
                             String ubicacion, String descripcion, int capacidadMaxima, EstadoJornadaVacunacion.Estado estado) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
        this.capacidadMaxima = capacidadMaxima;
        this.estado = estado;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }

    public Time getHoraInicio() { return horaInicio; }
    public void setHoraInicio(Time horaInicio) { this.horaInicio = horaInicio; }

    public Time getHoraFin() { return horaFin; }
    public void setHoraFin(Time horaFin) { this.horaFin = horaFin; }

    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public int getCapacidadMaxima() { return capacidadMaxima; }
    public void setCapacidadMaxima(int capacidadMaxima) { this.capacidadMaxima = capacidadMaxima; }

    public EstadoJornadaVacunacion.Estado getEstado() { return estado; }
    public void setEstado(EstadoJornadaVacunacion.Estado estado) { this.estado = estado; }


    @Override
    public String toString() {
        return "JornadaVacunacion{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", fecha=" + fecha +
                ", horaInicio=" + horaInicio +
                ", horaFin=" + horaFin +
                ", ubicacion='" + ubicacion + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", capacidadMaxima=" + capacidadMaxima +
                ", estado=" + estado +
                '}';
    }
}
