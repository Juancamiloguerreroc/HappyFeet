
package model.entities;

import java.sql.Date;

public class HistorialMedico {
    private int id;
    private int mascotaId;
    private Date fechaEvento;
    private int eventoTipoId;
    private String descripcion;
    private String tratamientoRecomendado;
    private String diagnostico;
    private Integer veterinarioId;
    private Integer consultaId;
    private Integer procedimientoId;
    

    public HistorialMedico() {}

    public HistorialMedico(int mascotaId, Date fechaEvento, int eventoTipoId, String descripcion, String diagnostico, String tratamientoRecomendado, Integer veterinarioId, Integer consultaId, Integer procedimientoId) {
        this.mascotaId = mascotaId;
        this.fechaEvento = fechaEvento;
        this.eventoTipoId = eventoTipoId;
        this.descripcion = descripcion;
        this.diagnostico = diagnostico;
        this.tratamientoRecomendado = tratamientoRecomendado;
        this.veterinarioId = veterinarioId;
        this.consultaId = consultaId;
        this.procedimientoId = procedimientoId;
    }

    public HistorialMedico(int id, int mascotaId, Date fechaEvento, int eventoTipoId, String descripcion, String diagnostico, String tratamientoRecomendado, Integer veterinarioId, Integer consultaId, Integer procedimientoId) {
        this.id = id;
        this.mascotaId = mascotaId;
        this.fechaEvento = fechaEvento;
        this.eventoTipoId = eventoTipoId;
        this.descripcion = descripcion;
        this.diagnostico = diagnostico;
        this.tratamientoRecomendado = tratamientoRecomendado;
        this.veterinarioId = veterinarioId;
        this.consultaId = consultaId;
        this.procedimientoId = procedimientoId;
    }
    

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public int getMascotaId() {return mascotaId;}
    public void setMascotaId(int mascotaId) {this.mascotaId = mascotaId;}

    public Date getFechaEvento() {return fechaEvento;}
    public void setFechaEvento(Date fechaEvento) {this.fechaEvento = fechaEvento;}

    public int getEventoTipoId() {return eventoTipoId;}
    public void setEventoTipoId(int eventoTipoId) {this.eventoTipoId = eventoTipoId;}

    public String getDescripcion() {return descripcion;}
    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}

    public String getDiagnostico() {return diagnostico;}
    public void setDiagnostico(String diagnostico) {this.diagnostico = diagnostico;}

    public String getTratamientoRecomendado() {return tratamientoRecomendado;}
    public void setTratamientoRecomendado(String tratamientoRecomendado) {this.tratamientoRecomendado = tratamientoRecomendado;}

    public Integer getVeterinarioId() {return veterinarioId;}
    public void setVeterinarioId(Integer veterinarioId) {this.veterinarioId = veterinarioId;}

    public Integer getConsultaId() {return consultaId;}
    public void setConsultaId(Integer consultaId) {this.consultaId = consultaId;}

    public Integer getProcedimientoId() {return procedimientoId;}
    public void setProcedimientoId(Integer procedimientoId) {this.procedimientoId = procedimientoId;}

    
    @Override
    public String toString() {
        return "Historial medico{" +
                "id=" + id +
                ", mascota id=" + mascotaId +
                ", fecha del evento='" + fechaEvento +
                ", Id tipo de evento=" + eventoTipoId+
                ", descrpcion=" + descripcion +
                ", diagnostico=" + diagnostico +
                ", Tratamiento recomendado=" + tratamientoRecomendado +
                ", Id veterinario=" + veterinarioId +
                ", Id consulta=" + consultaId +
                ", Id procedimiento='" + procedimientoId +
                '}';
    }
}
