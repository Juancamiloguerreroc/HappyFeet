package model.entities;

import java.sql.Date;
import java.sql.Timestamp;
import model.enums.EstadoProcedimiento;

public class ProcedimientoEspecial {
    private int id;
    private int mascotaId;
    private int veterinarioId;
    private String tipoProcedimiento;
    private String nombreProcedimiento;
    private Timestamp fechaHora;
    private Integer duracionEstimadaMinutos;
    private String informacionPreoperatoria;
    private String detalleProcedimiento;
    private String complicaciones;
    private String seguimientoPostoperatorio;
    private Date proximoControl;
    private EstadoProcedimiento estado;
    private Double costoProcedimiento;


    public ProcedimientoEspecial() {}

    public ProcedimientoEspecial(int mascotaId, int veterinarioId, String tipoProcedimiento, String nombreProcedimiento,
                         Timestamp fechaHora, Integer duracionEstimadaMinutos, String informacionPreoperatoria,
                         String detalleProcedimiento, String complicaciones, String seguimientoPostoperatorio,
                         Date proximoControl, EstadoProcedimiento estado, Double costoProcedimiento) {
        this.mascotaId = mascotaId;
        this.veterinarioId = veterinarioId;
        this.tipoProcedimiento = tipoProcedimiento;
        this.nombreProcedimiento = nombreProcedimiento;
        this.fechaHora = fechaHora;
        this.duracionEstimadaMinutos = duracionEstimadaMinutos;
        this.informacionPreoperatoria = informacionPreoperatoria;
        this.detalleProcedimiento = detalleProcedimiento;
        this.complicaciones = complicaciones;
        this.seguimientoPostoperatorio = seguimientoPostoperatorio;
        this.proximoControl = proximoControl;
        this.estado = estado;
        this.costoProcedimiento = costoProcedimiento;
    }

    public ProcedimientoEspecial(int id, int mascotaId, int veterinarioId, String tipoProcedimiento, String nombreProcedimiento,
                         Timestamp fechaHora, Integer duracionEstimadaMinutos, String informacionPreoperatoria,
                         String detalleProcedimiento, String complicaciones, String seguimientoPostoperatorio,
                         Date proximoControl, EstadoProcedimiento estado, Double costoProcedimiento) {
        this.id = id;
        this.mascotaId = mascotaId;
        this.veterinarioId = veterinarioId;
        this.tipoProcedimiento = tipoProcedimiento;
        this.nombreProcedimiento = nombreProcedimiento;
        this.fechaHora = fechaHora;
        this.duracionEstimadaMinutos = duracionEstimadaMinutos;
        this.informacionPreoperatoria = informacionPreoperatoria;
        this.detalleProcedimiento = detalleProcedimiento;
        this.complicaciones = complicaciones;
        this.seguimientoPostoperatorio = seguimientoPostoperatorio;
        this.proximoControl = proximoControl;
        this.estado = estado;
        this.costoProcedimiento = costoProcedimiento;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getMascotaId() { return mascotaId; }
    public void setMascotaId(int mascotaId) { this.mascotaId = mascotaId; }

    public int getVeterinarioId() { return veterinarioId; }
    public void setVeterinarioId(int veterinarioId) { this.veterinarioId = veterinarioId; }

    public String getTipoProcedimiento() { return tipoProcedimiento; }
    public void setTipoProcedimiento(String tipoProcedimiento) { this.tipoProcedimiento = tipoProcedimiento; }

    public String getNombreProcedimiento() { return nombreProcedimiento; }
    public void setNombreProcedimiento(String nombreProcedimiento) { this.nombreProcedimiento = nombreProcedimiento; }

    public Timestamp getFechaHora() { return fechaHora; }
    public void setFechaHora(Timestamp fechaHora) { this.fechaHora = fechaHora; }

    public Integer getDuracionEstimadaMinutos() { return duracionEstimadaMinutos; }
    public void setDuracionEstimadaMinutos(Integer duracionEstimadaMinutos) { this.duracionEstimadaMinutos = duracionEstimadaMinutos; }

    public String getInformacionPreoperatoria() { return informacionPreoperatoria; }
    public void setInformacionPreoperatoria(String informacionPreoperatoria) { this.informacionPreoperatoria = informacionPreoperatoria; }

    public String getDetalleProcedimiento() { return detalleProcedimiento; }
    public void setDetalleProcedimiento(String detalleProcedimiento) { this.detalleProcedimiento = detalleProcedimiento; }

    public String getComplicaciones() { return complicaciones; }
    public void setComplicaciones(String complicaciones) { this.complicaciones = complicaciones; }


    public String getSeguimientoPostoperatorio() { return seguimientoPostoperatorio; }
    public void setSeguimientoPostoperatorio(String seguimientoPostoperatorio) { this.seguimientoPostoperatorio = seguimientoPostoperatorio; }

    public Date getProximoControl() { return proximoControl; }
    public void setProximoControl(Date proximoControl) { this.proximoControl = proximoControl; }

    public EstadoProcedimiento getEstado() { return estado; }
    public void setEstado(EstadoProcedimiento estado) { this.estado = estado; }

    public Double getCostoProcedimiento() { return costoProcedimiento; }
    public void setCostoProcedimiento(Double costoProcedimiento) { this.costoProcedimiento = costoProcedimiento; }

    @Override
    public String toString() {
        return "Procedimiento{" +
                "id=" + id +
                ", mascotaId=" + mascotaId +
                ", veterinarioId=" + veterinarioId +
                ", tipoProcedimiento='" + tipoProcedimiento + '\'' +
                ", nombreProcedimiento='" + nombreProcedimiento + '\'' +
                ", fechaHora=" + fechaHora +
                ", duracionEstimadaMinutos=" + duracionEstimadaMinutos +
                ", informacionPreoperatoria='" + informacionPreoperatoria + '\'' +
                ", detalleProcedimiento='" + detalleProcedimiento + '\'' +
                ", complicaciones='" + complicaciones + '\'' +
                ", seguimientoPostoperatorio='" + seguimientoPostoperatorio + '\'' +
                ", proximoControl=" + proximoControl +
                ", estado=" + estado +
                ", costoProcedimiento=" + costoProcedimiento +
                '}';
    }
}
