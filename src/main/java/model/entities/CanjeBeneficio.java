package model.entities;

import java.sql.Date;
import java.sql.Timestamp;
import model.enums.EstadoCajaBeneficio;

public class CanjeBeneficio {

    private int id;
    private int clubMascotasId;
    private int beneficioId;
    private Timestamp fechaCanje;
    private int puntosCanjeados;
    private EstadoCajaBeneficio estado;
    private Date fechaExpiracion;
    private Integer facturaId;

    public CanjeBeneficio() {}

    public CanjeBeneficio(int id, int clubMascotasId, int beneficioId, Timestamp fechaCanje, int puntosCanjeados, EstadoCajaBeneficio estado, Date fechaExpiracion, Integer facturaId) {
        this.id = id;
        this.clubMascotasId = clubMascotasId;
        this.beneficioId = beneficioId;
        this.fechaCanje = fechaCanje;
        this.puntosCanjeados = puntosCanjeados;
        this.estado = estado;
        this.fechaExpiracion = fechaExpiracion;
        this.facturaId = facturaId;
    }

    public CanjeBeneficio(int clubMascotasId, int beneficioId, int puntosCanjeados, EstadoCajaBeneficio estado, Date fechaExpiracion, Integer facturaId) {
        this.clubMascotasId = clubMascotasId;
        this.beneficioId = beneficioId;
        this.puntosCanjeados = puntosCanjeados;
        this.estado = estado;
        this.fechaExpiracion = fechaExpiracion;
        this.facturaId = facturaId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClubMascotasId() {
        return clubMascotasId;
    }

    public void setClubMascotasId(int clubMascotasId) {
        this.clubMascotasId = clubMascotasId;
    }

    public int getBeneficioId() {
        return beneficioId;
    }

    public void setBeneficioId(int beneficioId) {
        this.beneficioId = beneficioId;
    }

    public Timestamp getFechaCanje() {
        return fechaCanje;
    }

    public void setFechaCanje(Timestamp fechaCanje) {
        this.fechaCanje = fechaCanje;
    }

    public int getPuntosCanjeados() {
        return puntosCanjeados;
    }

    public void setPuntosCanjeados(int puntosCanjeados) {
        this.puntosCanjeados = puntosCanjeados;
    }

    public EstadoCajaBeneficio getEstado() {
        return estado;
    }

    public void setEstado(EstadoCajaBeneficio estado) {
        this.estado = estado;
    }

    public Date getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(Date fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public Integer getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(Integer facturaId) {
        this.facturaId = facturaId;
    }
    
    
    @Override
    public String toString() {
        return "CanjeBeneficio{" +
                "id=" + id +
                ", clubMascotasId=" + clubMascotasId +
                ", beneficioId=" + beneficioId +
                ", fechaCanje=" + fechaCanje +
                ", puntosCanjeados=" + puntosCanjeados +
                ", estado=" + estado + // ← corregido
                ", fechaExpiracion=" + fechaExpiracion +
                ", facturaId=" + facturaId +
                '}';
    }
    
    
}
