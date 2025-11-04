
package model.entities;

import java.sql.Date;

public class MovimientoInventario {
    private int id;
    private int productoId;
    private String tipoMovimiento;
    private int cantidad;
    private int stockAnterior;
    private int stockNuevo;
    private String motivo;
    private int referenciaConsultaId;
    private int referenciaProcedimientoId;
    private String usuario;
    private Date fechaMovimiento;

    public MovimientoInventario() {}
    
    public MovimientoInventario(int id, int productoId, String tipoMovimiento, int cantidad, int stockAnterior, int stockNuevo, String motivo, int referenciaConsultaId, int referenciaProcedimientoId, String usuario, Date fechaMovimiento) {
        this.id = id;
        this.productoId = productoId;
        this.tipoMovimiento = tipoMovimiento;
        this.cantidad = cantidad;
        this.stockAnterior = stockAnterior;
        this.stockNuevo = stockNuevo;
        this.motivo = motivo;
        this.referenciaConsultaId = referenciaConsultaId;
        this.referenciaProcedimientoId = referenciaProcedimientoId;
        this.usuario = usuario;
        this.fechaMovimiento = fechaMovimiento;
    }
    
    public MovimientoInventario(int productoId, String tipoMovimiento, int cantidad, int stockAnterior, int stockNuevo, String motivo, int referenciaConsultaId, int referenciaProcedimientoId, String usuario, Date fechaMovimiento) {
        this.productoId = productoId;
        this.tipoMovimiento = tipoMovimiento;
        this.cantidad = cantidad;
        this.stockAnterior = stockAnterior;
        this.stockNuevo = stockNuevo;
        this.motivo = motivo;
        this.referenciaConsultaId = referenciaConsultaId;
        this.referenciaProcedimientoId = referenciaProcedimientoId;
        this.usuario = usuario;
        this.fechaMovimiento = fechaMovimiento;
    }
    
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public int getProductoId() {return productoId;}
    public void setProductoId(int productoId) {this.productoId = productoId;}

    public String getTipoMovimiento() {return tipoMovimiento;}
    public void setTipoMovimiento(String tipoMovimiento) {this.tipoMovimiento = tipoMovimiento;}

    public int getCantidad() {return cantidad;}
    public void setCantidad(int cantidad) {this.cantidad = cantidad;}

    public int getStockAnterior() {return stockAnterior;}
    public void setStockAnterior(int stockAnterior) {this.stockAnterior = stockAnterior;}

    public int getStockNuevo() {return stockNuevo;}
    public void setStockNuevo(int stockNuevo) {this.stockNuevo = stockNuevo;}

    public String getMotivo() {return motivo;}
    public void setMotivo(String motivo) {this.motivo = motivo;}

    public int getReferenciaConsultaId() {return referenciaConsultaId;}
    public void setReferenciaConsultaId(int referenciaConsultaId) {this.referenciaConsultaId = referenciaConsultaId;}

    public int getReferenciaProcedimientoId() {return referenciaProcedimientoId;}
    public void setReferenciaProcedimientoId(int referenciaProcedimientoId) {this.referenciaProcedimientoId = referenciaProcedimientoId;}

    public String getUsuario() {return usuario;}
    public void setUsuario(String usuario) {this.usuario = usuario;}

    public Date getFechaMovimiento() {return fechaMovimiento;}
    public void setFechaMovimiento(Date fechaMovimiento) {this.fechaMovimiento = fechaMovimiento;}
    
    @Override
    public String toString() {
        return "Movimiento inventario{" +
                "id=" + id +
                ", producto_id=" + productoId +
                ", tipo de movimiento='" + tipoMovimiento + '\'' +
                ", cantidad=" + cantidad +
                ", stock anterior=" + stockAnterior +
                ", stock nuevo='" + stockNuevo + '\'' +
                ", motivo=" + motivo +
                ", Id consulta referencia='" + referenciaConsultaId + '\'' +
                ", Id procedimiento referencia='" + referenciaProcedimientoId + '\'' +
                ", usuario='" + usuario + '\'' +
                ", fecha del movimiento='" + fechaMovimiento +
                '}';
    }
}
