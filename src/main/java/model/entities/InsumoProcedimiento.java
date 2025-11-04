package model.entities;

public class InsumoProcedimiento {
    private int id;
    private int procedimientoId;
    private int productoId;
    private int cantidadUsada;
    private String observaciones;

    public InsumoProcedimiento() {
    }

    public InsumoProcedimiento(int procedimientoId, int productoId, int cantidadUsada, String observaciones) {
        this.procedimientoId = procedimientoId;
        this.productoId = productoId;
        this.cantidadUsada = cantidadUsada;
        this.observaciones = observaciones;
    }

    public InsumoProcedimiento(int id, int procedimientoId, int productoId, int cantidadUsada) {
        this.id = id;
        this.procedimientoId = procedimientoId;
        this.productoId = productoId;
        this.cantidadUsada = cantidadUsada;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProcedimientoId() {
        return procedimientoId;
    }

    public void setProcedimientoId(int procedimientoId) {
        this.procedimientoId = procedimientoId;
    }

    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }

    public int getCantidadUsada() {
        return cantidadUsada;
    }

    public void setCantidadUsada(int cantidadUsada) {
        this.cantidadUsada = cantidadUsada;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Override
    public String toString() {
        return "InsumoProcedimiento{" +
                "id=" + id +
                ", procedimientoId=" + procedimientoId +
                ", productoId=" + productoId +
                ", cantidadUsada=" + cantidadUsada +
                '}';
    }
}
