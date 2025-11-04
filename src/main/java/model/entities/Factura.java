
package model.entities;

import java.sql.Date;

public class Factura {
    private int id;
    private int duenoId;
    private String numeroFactura;
    private Date fechaEmision;
    private Double subtotal;
    private Double impuesto;
    private Double descuento;
    private Double total;
    private String metodoPago;
    private String estado;
    private String observaciones;

    public Factura() {}
    
    public Factura(int id, int duenoId, String numeroFactura, Date fechaEmision, Double subtotal, Double impuesto, Double descuento, Double total, String metodoPago, String estado, String observaciones) {
        this.id = id;
        this.duenoId = duenoId;
        this.numeroFactura = numeroFactura;
        this.fechaEmision = fechaEmision;
        this.subtotal = subtotal;
        this.impuesto = impuesto;
        this.descuento = descuento;
        this.total = total;
        this.metodoPago = metodoPago;
        this.estado = estado;
        this.observaciones = observaciones;
    }
    
    public Factura(int duenoId, String numeroFactura, Date fechaEmision, Double subtotal, Double impuesto, Double descuento, Double total, String metodoPago, String estado, String observaciones) {
        this.duenoId = duenoId;
        this.numeroFactura = numeroFactura;
        this.fechaEmision = fechaEmision;
        this.subtotal = subtotal;
        this.impuesto = impuesto;
        this.descuento = descuento;
        this.total = total;
        this.metodoPago = metodoPago;
        this.estado = estado;
        this.observaciones = observaciones;
    }
    
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public int getDuenoId() {return duenoId;}
    public void setDuenoId(int duenoId) {this.duenoId = duenoId;}

    public String getNumeroFactura() {return numeroFactura;}
    public void setNumeroFactura(String numeroFactura) {this.numeroFactura = numeroFactura;}

    public Date getFechaEmision() {return fechaEmision;}
    public void setFechaEmision(Date fechaEmision) {this.fechaEmision = fechaEmision;}

    public Double getSubtotal() {return subtotal;}
    public void setSubtotal(Double subtotal) {this.subtotal = subtotal;}

    public Double getImpuesto() {return impuesto;}
    public void setImpuesto(Double impuesto) {this.impuesto = impuesto;}

    public Double getDescuento() {return descuento;}
    public void setDescuento(Double descuento) {this.descuento = descuento;}

    public Double getTotal() {return total;}
    public void setTotal(Double total) {this.total = total;}

    public String getMetodoPago() {return metodoPago;}
    public void setMetodoPago(String metodoPago) {this.metodoPago = metodoPago;}

    public String getEstado() {return estado;}
    public void setEstado(String estado) {this.estado = estado;}

    public String getObservaciones() {return observaciones;}
    public void setObservaciones(String observaciones) {this.observaciones = observaciones;}
    
    @Override
    public String toString() {
        return "Factura{" +
                "id=" + id +
                ", numero de factura=" + numeroFactura +
                ", fecha de emision='" + fechaEmision +
                ", subtotal=" + subtotal+
                ", impuesto=" + impuesto +
                ", descuento=" + descuento +
                ", total=" + total +
                ", metodo de pago=" + metodoPago +
                ", estado='" + estado +
                ", observaciones='" + observaciones +
                '}';
    }
}
