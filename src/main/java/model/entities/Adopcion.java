package model.entities;

import java.sql.Date;
import model.enums.EstadoAdopcion;

public class Adopcion {

    private int id;
    private int idMascotaAdopcion; // FK hacia MascotaAdopcion
    private int duenoId;
    private Date fechaAdopcion;
    private String contratoTexto;
    private String condicionesEspeciales;
    private boolean seguimientoRequerido;
    private Date fechaPrimerSeguimiento;
    
    private EstadoAdopcion estado; // Enum


    public Adopcion() {}

    public Adopcion(int id, int idMascotaAdopcion, int duenoId, Date fechaAdopcion, String contratoTexto, String condicionesEspeciales, boolean seguimientoRequerido, Date fechaPrimerSeguimiento, EstadoAdopcion estado) {
        this.id = id;
        this.idMascotaAdopcion = idMascotaAdopcion;
        this.duenoId = duenoId;
        this.fechaAdopcion = fechaAdopcion;
        this.contratoTexto = contratoTexto;
        this.condicionesEspeciales = condicionesEspeciales;
        this.seguimientoRequerido = seguimientoRequerido;
        this.fechaPrimerSeguimiento = fechaPrimerSeguimiento;
        this.estado = estado;
    }

    public Adopcion(int idMascotaAdopcion, int duenoId, Date fechaAdopcion, String contratoTexto, String condicionesEspeciales, boolean seguimientoRequerido, Date fechaPrimerSeguimiento, EstadoAdopcion estado) {
        this.idMascotaAdopcion = idMascotaAdopcion;
        this.duenoId = duenoId;
        this.fechaAdopcion = fechaAdopcion;
        this.contratoTexto = contratoTexto;
        this.condicionesEspeciales = condicionesEspeciales;
        this.seguimientoRequerido = seguimientoRequerido;
        this.fechaPrimerSeguimiento = fechaPrimerSeguimiento;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdMascotaAdopcion() {
        return idMascotaAdopcion;
    }

    public void setIdMascotaAdopcion(int idMascotaAdopcion) {
        this.idMascotaAdopcion = idMascotaAdopcion;
    }

    public int getDuenoId() {
        return duenoId;
    }

    public void setDuenoId(int duenoId) {
        this.duenoId = duenoId;
    }

    public Date getFechaAdopcion() {
        return fechaAdopcion;
    }

    public void setFechaAdopcion(Date fechaAdopcion) {
        this.fechaAdopcion = fechaAdopcion;
    }

    public String getContratoTexto() {
        return contratoTexto;
    }

    public void setContratoTexto(String contratoTexto) {
        this.contratoTexto = contratoTexto;
    }

    public String getCondicionesEspeciales() {
        return condicionesEspeciales;
    }

    public void setCondicionesEspeciales(String condicionesEspeciales) {
        this.condicionesEspeciales = condicionesEspeciales;
    }

    public boolean isSeguimientoRequerido() {
        return seguimientoRequerido;
    }

    public void setSeguimientoRequerido(boolean seguimientoRequerido) {
        this.seguimientoRequerido = seguimientoRequerido;
    }

    public Date getFechaPrimerSeguimiento() {
        return fechaPrimerSeguimiento;
    }

    public void setFechaPrimerSeguimiento(Date fechaPrimerSeguimiento) {
        this.fechaPrimerSeguimiento = fechaPrimerSeguimiento;
    }

    public EstadoAdopcion getEstado() {
        return estado;
    }

    public void setEstado(EstadoAdopcion estado) {
        this.estado = estado;
    }
    
    @Override
    public String toString() {
        return "Adopcion{" +
                "id=" + id +
                ", idMascotaAdopcion=" + idMascotaAdopcion +
                ", dueño id='" + duenoId + '\'' +
                ", fecha adopcion='" + fechaAdopcion + '\'' +
                ", contrato texto='" + contratoTexto + '\'' +
                ", condiciones especiales='" + condicionesEspeciales + '\'' +
                ", seguimiento requerido=" + seguimientoRequerido +
                ", fecha primer seguimiento=" + fechaPrimerSeguimiento +
                '}';
    }
}
