package model.entities;

import java.sql.Timestamp;

public class TransaccionPuntos {

    private int id;
    private int clubMascotasId;
    private int facturaId;
    private int puntos;
    private String tipo;
    private Timestamp fecha;
    private String descripcion;
    private int saldoAnterior;
    private int saldoNuevo;

    public TransaccionPuntos() {}

    public TransaccionPuntos(int id, int clubMascotasId, int facturaId, int puntos, String tipo, Timestamp fecha, String descripcion, int saldoAnterior, int saldoNuevo) {
        this.id = id;
        this.clubMascotasId = clubMascotasId;
        this.facturaId = facturaId;
        this.puntos = puntos;
        this.tipo = tipo;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.saldoAnterior = saldoAnterior;
        this.saldoNuevo = saldoNuevo;
    }

    public TransaccionPuntos(int clubMascotasId, int facturaId, int puntos, String tipo, String descripcion, int saldoAnterior, int saldoNuevo) {
        this.clubMascotasId = clubMascotasId;
        this.facturaId = facturaId;
        this.puntos = puntos;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.saldoAnterior = saldoAnterior;
        this.saldoNuevo = saldoNuevo;
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

    public int getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(int facturaId) {
        this.facturaId = facturaId;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getSaldoAnterior() {
        return saldoAnterior;
    }

    public void setSaldoAnterior(int saldoAnterior) {
        this.saldoAnterior = saldoAnterior;
    }

    public int getSaldoNuevo() {
        return saldoNuevo;
    }

    public void setSaldoNuevo(int saldoNuevo) {
        this.saldoNuevo = saldoNuevo;
    }

    @Override
    public String toString() {
        return "TransaccionPuntos{" +
                "id=" + id +
                ", Club Mascotas Id=" + clubMascotasId +
                ", Factura Id=" + facturaId +
                ", Puntos=" + puntos +
                ", Tipo='" + tipo + '\'' +
                ", Fecha=" + fecha +
                ", Descripcion='" + descripcion + '\'' +
                ", Saldo anterior=" + saldoAnterior +
                ", Saldo nuevo=" + saldoNuevo +
                '}';
    }
}
