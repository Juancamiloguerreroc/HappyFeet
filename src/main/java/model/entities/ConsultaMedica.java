
package model.entities;

import java.sql.Timestamp;

public class ConsultaMedica {
    private int id;
    private int mascotaid;
    private int veterinarioId;
    private Integer citaId;
    private Timestamp fechaHora;
    private String motivo;
    private String sintomas;
    private String diagnostico;
    private String recomendaciones;
    private String observaciones;
    private Double pesoRegistrado;
    private Double temperatura;

    public ConsultaMedica(int id, int mascotaid, int veterinarioId, Integer citaId, Timestamp fechaHora, String motivo, String sintomas, String diagnostico, String recomendaciones, String observaciones, Double pesoRegistrado, Double temperatura) {
        this.id = id;
        this.mascotaid = mascotaid;
        this.veterinarioId = veterinarioId;
        this.citaId = citaId;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.sintomas = sintomas;
        this.diagnostico = diagnostico;
        this.recomendaciones = recomendaciones;
        this.observaciones = observaciones;
        this.pesoRegistrado = pesoRegistrado;
        this.temperatura = temperatura;
    }

    public ConsultaMedica(int mascotaid, int veterinarioId, Integer citaId, Timestamp fechaHora, String motivo, String sintomas, String diagnostico, String recomendaciones, String observaciones, Double pesoRegistrado, Double temperatura) {
        this.mascotaid = mascotaid;
        this.veterinarioId = veterinarioId;
        this.citaId = citaId;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.sintomas = sintomas;
        this.diagnostico = diagnostico;
        this.recomendaciones = recomendaciones;
        this.observaciones = observaciones;
        this.pesoRegistrado = pesoRegistrado;
        this.temperatura = temperatura;
    }

    public ConsultaMedica() {}
    
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public int getMascotaid() {return mascotaid;}
    public void setMascotaid(int mascotaid) {this.mascotaid = mascotaid;}

    public int getVeterinarioId() {return veterinarioId;}
    public void setVeterinarioId(int veterinarioId) {this.veterinarioId = veterinarioId;}

    public Integer getCitaId() {return citaId;}
    public void setCitaId(Integer citaId) {this.citaId = citaId;}

    public Timestamp getFechaHora() {return fechaHora;}
    public void setFechaHora(Timestamp fechaHora) {this.fechaHora = fechaHora;}

    public String getMotivo() {return motivo;}
    public void setMotivo(String motivo) {this.motivo = motivo;}

    public String getSintomas() {return sintomas;}
    public void setSintomas(String sintomas) {this.sintomas = sintomas;}

    public String getDiagnostico() {return diagnostico;}
    public void setDiagnostico(String diagnostico) {this.diagnostico = diagnostico;}

    public String getRecomendaciones() {return recomendaciones;}
    public void setRecomendaciones(String recomendaciones) {this.recomendaciones = recomendaciones;}

    public String getObservaciones() {return observaciones;}
    public void setObservaciones(String observaciones) {this.observaciones = observaciones;}

    public Double getPesoregistrado() {return pesoRegistrado;}
    public void setPesoRegistrado(Double pesoRegistrado) {this.pesoRegistrado = pesoRegistrado;}

    public Double getTemperatura() {return temperatura;}
    public void setTemperatura(Double temperatura) {this.temperatura = temperatura;}
    
    @Override
    public String toString() {
        return "ConsultaMedica{" +
                "id=" + id +
                ", mascota_id=" + mascotaid +
                ", veterinario_id='" + veterinarioId +
                ", cita_id=" + citaId+
                ", fecha=" + fechaHora +
                ", motivo=" + motivo +
                ", sintomas=" + sintomas +
                ", diagnostico=" + diagnostico +
                ", recomendaciones='" + recomendaciones + '\'' +
                ", observaciones=" + observaciones +
                ", peso registrado='" + pesoRegistrado +
                ", temperatura=" + temperatura +
                '}';
    }
}
