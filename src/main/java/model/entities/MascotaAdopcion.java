package model.entities;

import java.sql.Date;
import model.enums.EstadoMascotaAdopcion;

public class MascotaAdopcion {
    private int id;
    private int mascotaId;
    private Date fechaIngreso;
    private String motivoIngreso;
    private EstadoMascotaAdopcion estado;
    private String historia;
    private String temperamento;
    private String necesidadesEspeciales;
    private String fotoAdicionalUrl;

    public MascotaAdopcion() {}

    public MascotaAdopcion(int mascotaId, Date fechaIngreso, String motivoIngreso, EstadoMascotaAdopcion estado,
                           String historia, String temperamento, String necesidadesEspeciales, String fotoAdicionalUrl) {
        this.mascotaId = mascotaId;
        this.fechaIngreso = fechaIngreso;
        this.motivoIngreso = motivoIngreso;
        this.estado = estado;
        this.historia = historia;
        this.temperamento = temperamento;
        this.necesidadesEspeciales = necesidadesEspeciales;
        this.fotoAdicionalUrl = fotoAdicionalUrl;
    }

    public MascotaAdopcion(int id, int mascotaId, Date fechaIngreso, String motivoIngreso, EstadoMascotaAdopcion estado,
                           String historia, String temperamento, String necesidadesEspeciales, String fotoAdicionalUrl) {
        this.id = id;
        this.mascotaId = mascotaId;
        this.fechaIngreso = fechaIngreso;
        this.motivoIngreso = motivoIngreso;
        this.estado = estado;
        this.historia = historia;
        this.temperamento = temperamento;
        this.necesidadesEspeciales = necesidadesEspeciales;
        this.fotoAdicionalUrl = fotoAdicionalUrl;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getMascotaId() { return mascotaId; }
    public void setMascotaId(int mascotaId) { this.mascotaId = mascotaId; }

    public Date getFechaIngreso() { return fechaIngreso; }
    public void setFechaIngreso(Date fechaIngreso) { this.fechaIngreso = fechaIngreso; }

    public String getMotivoIngreso() { return motivoIngreso; }
    public void setMotivoIngreso(String motivoIngreso) { this.motivoIngreso = motivoIngreso; }

    public EstadoMascotaAdopcion getEstado() { return estado; }
    public void setEstado(EstadoMascotaAdopcion estado) { this.estado = estado; }

    public String getHistoria() { return historia; }
    public void setHistoria(String historia) { this.historia = historia; }

    public String getTemperamento() { return temperamento; }
    public void setTemperamento(String temperamento) { this.temperamento = temperamento; }

    public String getNecesidadesEspeciales() { return necesidadesEspeciales; }
    public void setNecesidadesEspeciales(String necesidadesEspeciales) { this.necesidadesEspeciales = necesidadesEspeciales; }

    public String getFotoAdicionalUrl() { return fotoAdicionalUrl; }
    public void setFotoAdicionalUrl(String fotoAdicionalUrl) { this.fotoAdicionalUrl = fotoAdicionalUrl; }

    @Override
    public String toString() {
        return "MascotaAdopcion{" +
                "id=" + id +
                ", mascotaId=" + mascotaId +
                ", fechaIngreso=" + fechaIngreso +
                ", motivoIngreso='" + motivoIngreso + '\'' +
                ", estado=" + estado +
                ", historia='" + historia + '\'' +
                ", temperamento='" + temperamento + '\'' +
                ", necesidadesEspeciales='" + necesidadesEspeciales + '\'' +
                ", fotoAdicionalUrl='" + fotoAdicionalUrl + '\'' +
                '}';
    }
}
