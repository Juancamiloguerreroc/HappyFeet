
package model.entities;

public class Servicio {

    
    private int id;
    private String nombre;
    private String descripcion;
    private String categoria;
    private Double precioBase;
    private Integer duracionEstimadaMinutos;
    private boolean activo;
    
    public Servicio(int id, String nombre, String descripcion, String categoria, Double precioBase, Integer duracionEstimadaMinutos, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.precioBase = precioBase;
        this.duracionEstimadaMinutos = duracionEstimadaMinutos;
        this.activo = activo;
    }
    

    public Servicio(String nombre, String descripcion, String categoria, Double precioBase, Integer duracionEstimadaMinutos, boolean activo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.precioBase = precioBase;
        this.duracionEstimadaMinutos = duracionEstimadaMinutos;
        this.activo = activo;
    }

    public Servicio() {}  
    
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getDescripcion() {return descripcion;}
    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}

    public String getCategoria() {return categoria;}
    public void setCategoria(String categoria) {this.categoria = categoria;}

    public Double getPrecioBase() {return precioBase;}
    public void setPrecioBase(Double precioBase) {this.precioBase = precioBase;}


    public Integer getDuracionEstimadaMinutos() {return duracionEstimadaMinutos;}
    public void setDuracionEstimadaMinutos(Integer duracionEstimadaMinutos) {this.duracionEstimadaMinutos = duracionEstimadaMinutos;}

    public boolean getActivo() {return activo;}
    public void setActivo(boolean activo) {this.activo = activo;}
    
    @Override
    public String toString() {
        return "Servicio{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", categoria='" + categoria + '\'' +
                ", precio base='" + precioBase + '\'' +
                ", duracion estimada (minutos)='" + duracionEstimadaMinutos + '\'' +
                ", activo='" + activo + '\'' +
                '}';
    }
}
