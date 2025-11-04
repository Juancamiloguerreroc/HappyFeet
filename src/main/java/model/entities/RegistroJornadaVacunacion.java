package model.entities;

import java.sql.Date;
import java.sql.Timestamp;

public class RegistroJornadaVacunacion {

    
    private int id;
    private int jornadaId;
    private int mascotaId;
    private int duenoId;
    private int vacunaId;
    private int veterinarioId;
    private Timestamp fechaHora;
    private String loteVacuna;
    private Date proximaDosis;
    private String observaciones;

    public RegistroJornadaVacunacion() {
    }

    public RegistroJornadaVacunacion(int jornadaId, int mascotaId, int duenoId, int vacunaId, int veterinarioId, Timestamp fechaHora, String loteVacuna, Date proximaDosis, String observaciones) {
            this.jornadaId = jornadaId;
            this.mascotaId = mascotaId;
            this.duenoId = duenoId;
            this.vacunaId = vacunaId;
            this.veterinarioId = veterinarioId;
            this.fechaHora = fechaHora;
            this.loteVacuna = loteVacuna;
            this.proximaDosis = proximaDosis;
            this.observaciones = observaciones;
    }

    public RegistroJornadaVacunacion(int id, int jornadaId, int mascotaId, int duenoId, int vacunaId, int veterinarioId, Timestamp fechaHora, String loteVacuna, Date proximaDosis, String observaciones) {
            this.id = id;
            this.jornadaId = jornadaId;
            this.mascotaId = mascotaId;
            this.duenoId = duenoId;
            this.vacunaId = vacunaId;
            this.veterinarioId = veterinarioId;
            this.fechaHora = fechaHora;
            this.loteVacuna = loteVacuna;
            this.proximaDosis = proximaDosis;
            this.observaciones = observaciones;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getJornadaId() {
        return jornadaId;
    }

    public void setJornadaId(int jornadaId) {
        this.jornadaId = jornadaId;
    }

    public int getMascotaId() {
        return mascotaId;
    }

    public void setMascotaId(int mascotaId) {
        this.mascotaId = mascotaId;
    }

    public int getDuenoId() {
        return duenoId;
    }

    public void setDuenoId(int duenoId) {
        this.duenoId = duenoId;
    }

    public int getVacunaId() {
        return vacunaId;
    }

    public void setVacunaId(int vacunaId) {
        this.vacunaId = vacunaId;
    }

    public int getVeterinarioId() {
        return veterinarioId;
    }

    public void setVeterinarioId(int veterinarioId) {
        this.veterinarioId = veterinarioId;
    }

    public Timestamp getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Timestamp fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getLoteVacuna() {
        return loteVacuna;
    }

    public void setLoteVacuna(String loteVacuna) {
        this.loteVacuna = loteVacuna;
    }

    public Date getProximaDosis() {
        return proximaDosis;
    }

    public void setProximaDosis(Date proximaDosis) {
        this.proximaDosis = proximaDosis;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }


    @Override
    public String toString() {
        return "RegistroJornadaVacunacion{" +
                "id=" + id +
                ", Jornada Id=" + jornadaId +
                ", Mascota Id=" + mascotaId +
                ", Dueño d=" + duenoId +
                ", Vacuna Id=" + vacunaId +
                ", Veterinario Id=" + veterinarioId +
                ", Fecha y hora=" + fechaHora +
                ", Lote vacuna=" + loteVacuna +
                ", Proxima dosis=" + proximaDosis +
                ", observaciones='" + observaciones + '\'' +
                '}';
    }
}
