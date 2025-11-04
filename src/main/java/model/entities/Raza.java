
package model.entities;

public class Raza {
    private int id;
    private int especieId;
    private String nombre;
    private String caracteristicas;

    public Raza(int id, int especieId, String nombre, String caracterisiticas) {
        this.id = id;
        this.especieId = especieId;
        this.nombre = nombre;
        this.caracteristicas = caracteristicas;
    }
    
    public Raza(int especieId, String nombre, String caracterisiticas) {
        this.especieId = especieId;
        this.nombre = nombre;
        this.caracteristicas = caracteristicas;
    }

    public Raza() {}
    
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public int getEspecieId() {return especieId;}
    public void setEspecieId(int especieId) {this.especieId = especieId;}

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getCaracteristicas() {return caracteristicas;}
    public void setCaracteristicas(String caracteristicas) {this.caracteristicas = caracteristicas;}
    
    @Override
    public String toString() {
        return "Raza{" +
                "id=" + id +
                ", Id especie='" + especieId + '\'' +
                ", nombre='" + nombre + '\'' +
                ", Caracteristicas='" + caracteristicas + '\'' +
                '}';
    }

}

