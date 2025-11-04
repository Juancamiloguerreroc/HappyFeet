
package model.entities;

import model.enums.TipoItemFactura;

public class ItemFactura {
    private int id;
    private int facturaId;
    //Se cambio el private de TipoItemFactura a public para compatibilidad con el dao de la clase, revisar luego;
    public TipoItemFactura tipoItem;
    private Integer productoId;
    private Integer servicioId;
    private String servicioDescripcion;
    private int cantidad;
    private Double precioUnitario;
    private Double subtotal;

    public ItemFactura(int id, int facturaId, TipoItemFactura tipoItem, Integer productoId, Integer servicioId, String servicioDescripcion, int cantidad, Double precioUnitario, Double subtotal) {
        this.id = id;
        this.facturaId = facturaId;
        this.tipoItem = tipoItem;
        this.productoId = productoId;
        this.servicioId = servicioId;
        this.servicioDescripcion = servicioDescripcion;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
    }

    public ItemFactura(int facturaId, TipoItemFactura tipoItem, Integer productoId, Integer servicioId, String servicioDescripcion, int cantidad, Double precioUnitario, Double subtotal) {
        this.facturaId = facturaId;
        this.tipoItem = tipoItem;
        this.productoId = productoId;
        this.servicioId = servicioId;
        this.servicioDescripcion = servicioDescripcion;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
    }
    
    
    public ItemFactura() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getFacturaId() { return facturaId; }
    public void setFacturaId(int facturaId) { this.facturaId = facturaId; }

    public TipoItemFactura getTipoItem() { return tipoItem; }
    public void setTipoItem(TipoItemFactura tipoItem) { this.tipoItem = tipoItem; }

    public Integer getProductoId() { return productoId; }
    public void setProductoId(Integer productoId) { this.productoId = productoId; }

    public Integer getServicioId() { return servicioId; }
    public void setServicioId(Integer servicioId) { this.servicioId = servicioId; }

    public String getServicioDescripcion() { return servicioDescripcion; }
    public void setServicioDescripcion(String servicioDescripcion) { this.servicioDescripcion = servicioDescripcion; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public Double getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(Double precioUnitario) { this.precioUnitario = precioUnitario; }

    public Double getSubtotal() { return subtotal; }
    public void setSubtotal(Double subtotal) { this.subtotal = subtotal; }


    @Override
    public String toString() {
        return "ItemFactura{" +
                "id=" + id +
                ", facturaId=" + facturaId +
                ", tipoItem=" + tipoItem +
                ", productoId=" + productoId +
                ", servicioId=" + servicioId +
                ", servicioDescripcion='" + servicioDescripcion + '\'' +
                ", cantidad=" + cantidad +
                ", precioUnitario=" + precioUnitario +
                ", subtotal=" + subtotal +
                '}';
    }
}
