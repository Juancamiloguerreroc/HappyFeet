package model.entities;

import java.sql.Date;
import java.sql.Timestamp;

public class Inventario {

    private int id;
    private String nombreProducto;
    private int productoTipoId;
    private String descripcion;
    private String fabricante;
    private Integer proveedorId; 
    private String lote;
    private int cantidadStock;
    private int stockMinimo;
    private String unidadMedida;
    private Date fechaVencimiento;
    private double precioCompra;
    private double precioVenta;
    private boolean requiereReceta;
    private boolean activo;
    private Timestamp fechaRegistro;

    public Inventario() {}

    public Inventario(int id, String nombreProducto, int productoTipoId, String descripcion, String fabricante,
                      Integer proveedorId, String lote, int cantidadStock, int stockMinimo, String unidadMedida,
                      Date fechaVencimiento, double precioCompra, double precioVenta, boolean requiereReceta,
                      boolean activo, Timestamp fechaRegistro) {
        this.id = id;
        this.nombreProducto = nombreProducto;
        this.productoTipoId = productoTipoId;
        this.descripcion = descripcion;
        this.fabricante = fabricante;
        this.proveedorId = proveedorId;
        this.lote = lote;
        this.cantidadStock = cantidadStock;
        this.stockMinimo = stockMinimo;
        this.unidadMedida = unidadMedida;
        this.fechaVencimiento = fechaVencimiento;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.requiereReceta = requiereReceta;
        this.activo = activo;
        this.fechaRegistro = fechaRegistro;
    }

    public Inventario(String nombreProducto, int productoTipoId, String descripcion, String fabricante,
                      Integer proveedorId, String lote, int cantidadStock, int stockMinimo, String unidadMedida,
                      Date fechaVencimiento, double precioCompra, double precioVenta, boolean requiereReceta,
                      boolean activo, Timestamp fechaRegistro) {
        this.nombreProducto = nombreProducto;
        this.productoTipoId = productoTipoId;
        this.descripcion = descripcion;
        this.fabricante = fabricante;
        this.proveedorId = proveedorId;
        this.lote = lote;
        this.cantidadStock = cantidadStock;
        this.stockMinimo = stockMinimo;
        this.unidadMedida = unidadMedida;
        this.fechaVencimiento = fechaVencimiento;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.requiereReceta = requiereReceta;
        this.activo = activo;
        this.fechaRegistro = fechaRegistro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getProductoTipoId() {
        return productoTipoId;
    }

    public void setProductoTipoId(int productoTipoId) {
        this.productoTipoId = productoTipoId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public Integer getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(Integer proveedorId) {
        this.proveedorId = proveedorId;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public int getCantidadStock() {
        return cantidadStock;
    }

    public void setCantidadStock(int cantidadStock) {
        this.cantidadStock = cantidadStock;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(int stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public boolean isRequiereReceta() {
        return requiereReceta;
    }

    public void setRequiereReceta(boolean requiereReceta) {
        this.requiereReceta = requiereReceta;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Timestamp getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Timestamp fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Override
    public String toString() {
        return "Inventario{" +
                "id=" + id +
                ", nombreProducto='" + nombreProducto + '\'' +
                ", productoTipoId=" + productoTipoId +
                ", descripcion='" + descripcion + '\'' +
                ", fabricante='" + fabricante + '\'' +
                ", proveedorId=" + proveedorId +
                ", lote='" + lote + '\'' +
                ", cantidadStock=" + cantidadStock +
                ", stockMinimo=" + stockMinimo +
                ", unidadMedida='" + unidadMedida + '\'' +
                ", fechaVencimiento=" + fechaVencimiento +
                ", precioCompra=" + precioCompra +
                ", precioVenta=" + precioVenta +
                ", requiereReceta=" + requiereReceta +
                ", activo=" + activo +
                ", fechaRegistro=" + fechaRegistro +
                '}';
    }
}
