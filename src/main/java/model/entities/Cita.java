package model.entities;

import java.sql.Timestamp;

public class Cita {

    private int id;
    private int mascotaId;
    private int veterinarioId;
    private Timestamp fechaHora;
    private String motivo;
    private int estadoId;
    private String observaciones;
    private Timestamp fechaCreacion;
    
    public Cita(){}
    
    public Cita(int id, int mascotaId, int veterinarioId, Timestamp fechaHora, 
            String motivo, int estadoId, String observaciones, 
            Timestamp fechaCreacion){
        this.id = id;
        this.mascotaId = mascotaId;
        this.veterinarioId = veterinarioId;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.estadoId = estadoId;
        this.observaciones = observaciones;
        this.fechaCreacion = fechaCreacion;
    }
    
    public Cita(int mascotaId, int veterinarioId, Timestamp fechaHora, 
            String motivo, int estadoId, String observaciones, 
            Timestamp fechaCreacion){
        this.mascotaId = mascotaId;
        this.veterinarioId = veterinarioId;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.estadoId = estadoId;
        this.observaciones = observaciones;
        this.fechaCreacion = fechaCreacion;
    }
    
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public int getMascotaId() {return mascotaId;}
    public void setMascotaId(int mascotaId) {this.mascotaId = mascotaId;}

    public int getVeterinarioId() {return veterinarioId;}
    public void setVeterinarioId(int veterinarioId) {this.veterinarioId = veterinarioId;}

    public Timestamp getFechaHora() {return fechaHora;}
    public void setFechaHora(Timestamp fechaHora) {this.fechaHora = fechaHora;}

    public String getMotivo() {return motivo;}
    public void setMotivo(String motivo) {this.motivo = motivo;}

    public int getEstadoId() {return estadoId;}
    public void setEstadoId(int estadoId) {this.estadoId = estadoId;}

    public String getObservaciones() {return observaciones;}
    public void setObservaciones(String observaciones) {this.observaciones = observaciones;}

    public Timestamp getFechaCreacion() {return fechaCreacion;}
    public void setFechaCreacion(Timestamp fechaCreacion) {this.fechaCreacion = fechaCreacion;}
    
    @Override
    public String toString() {
        return "Cita{" +
                "id=" + id +
                ", mascota_id=" + mascotaId +
                ", veterinario_id='" + veterinarioId +
                ", fecha=" + fechaHora +
                ", motivo=" + motivo +
                ", Estado_id='" + estadoId + '\'' +
                ", observaciones=" + observaciones +
                ", fecha creaciòn='" + fechaCreacion +
                '}';
    }
}