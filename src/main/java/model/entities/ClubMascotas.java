package model.entities;

import java.util.Date;

public class ClubMascotas {

    private int id;
    private int duenoId; // FK a duenos
    private int puntosAcumulados;
    private int puntosCanjeados;
    private int puntosDisponibles;
    private String nivel;
    private Date fechaInscripcion;
    private Date fechaUltimaActualizacion;
    private boolean activo;

    public ClubMascotas() {}

    public ClubMascotas(int duenoId, int puntosAcumulados, int puntosCanjeados, int puntosDisponibles,
                        String nivel, Date fechaInscripcion, Date fechaUltimaActualizacion, boolean activo) {
        this.duenoId = duenoId;
        this.puntosAcumulados = puntosAcumulados;
        this.puntosCanjeados = puntosCanjeados;
        this.puntosDisponibles = puntosDisponibles;
        this.nivel = nivel;
        this.fechaInscripcion = fechaInscripcion;
        this.fechaUltimaActualizacion = fechaUltimaActualizacion;
        this.activo = activo;
    }

    public ClubMascotas(int id, int duenoId, int puntosAcumulados, int puntosCanjeados, int puntosDisponibles,
                        String nivel, Date fechaInscripcion, Date fechaUltimaActualizacion, boolean activo) {
        this.id = id;
        this.duenoId = duenoId;
        this.puntosAcumulados = puntosAcumulados;
        this.puntosCanjeados = puntosCanjeados;
        this.puntosDisponibles = puntosDisponibles;
        this.nivel = nivel;
        this.fechaInscripcion = fechaInscripcion;
        this.fechaUltimaActualizacion = fechaUltimaActualizacion;
        this.activo = activo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDuenoId() {
        return duenoId;
    }

    public void setDuenoId(int duenoId) {
        this.duenoId = duenoId;
    }

    public int getPuntosAcumulados() {
        return puntosAcumulados;
    }

    public void setPuntosAcumulados(int puntosAcumulados) {
        this.puntosAcumulados = puntosAcumulados;
    }

    public int getPuntosCanjeados() {
        return puntosCanjeados;
    }

    public void setPuntosCanjeados(int puntosCanjeados) {
        this.puntosCanjeados = puntosCanjeados;
    }

    public int getPuntosDisponibles() {
        return puntosDisponibles;
    }

    public void setPuntosDisponibles(int puntosDisponibles) {
        this.puntosDisponibles = puntosDisponibles;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public Date getFechaUltimaActualizacion() {
        return fechaUltimaActualizacion;
    }

    public void setFechaUltimaActualizacion(Date fechaUltimaActualizacion) {
        this.fechaUltimaActualizacion = fechaUltimaActualizacion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "ClubMascotas{" +
                "id=" + id +
                ", duenoId=" + duenoId +
                ", puntosAcumulados=" + puntosAcumulados +
                ", puntosCanjeados=" + puntosCanjeados +
                ", puntosDisponibles=" + puntosDisponibles +
                ", nivel='" + nivel + '\'' +
                ", fechaInscripcion=" + fechaInscripcion +
                ", fechaUltimaActualizacion=" + fechaUltimaActualizacion +
                ", activo=" + activo +
                '}';
    }
}
