package model.entities;

import model.enums.TipoBeneficio;

public class BeneficioClub {

    private int id;
    private String nombre;
    private String descripcion;
    private String nivelRequerido;
    private int puntosNecesarios;
    private TipoBeneficio tipoBeneficio;
    private Double valorBeneficio;
    private boolean activo;

    public BeneficioClub() {}

    public BeneficioClub(int id, String nombre, String descripcion, String nivelRequerido, int puntosNecesarios, TipoBeneficio tipoBeneficio, Double valorBeneficio, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.nivelRequerido = nivelRequerido;
        this.puntosNecesarios = puntosNecesarios;
        this.tipoBeneficio = tipoBeneficio;
        this.valorBeneficio = valorBeneficio;
        this.activo = activo;
    }

    public BeneficioClub(String nombre, String descripcion, String nivelRequerido, int puntosNecesarios, TipoBeneficio tipoBeneficio, Double valorBeneficio, boolean activo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.nivelRequerido = nivelRequerido;
        this.puntosNecesarios = puntosNecesarios;
        this.tipoBeneficio = tipoBeneficio;
        this.valorBeneficio = valorBeneficio;
        this.activo = activo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNivelRequerido() {
        return nivelRequerido;
    }

    public void setNivelRequerido(String nivelRequerido) {
        this.nivelRequerido = nivelRequerido;
    }

    public int getPuntosNecesarios() {
        return puntosNecesarios;
    }

    public void setPuntosNecesarios(int puntosNecesarios) {
        this.puntosNecesarios = puntosNecesarios;
    }

    public TipoBeneficio getTipoBeneficio() {
        return tipoBeneficio;
    }

    public void setTipoBeneficio(TipoBeneficio tipoBeneficio) {
        this.tipoBeneficio = tipoBeneficio;
    }

    public Double getValorBeneficio() {
        return valorBeneficio;
    }

    public void setValorBeneficio(Double valorBeneficio) {
        this.valorBeneficio = valorBeneficio;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "BeneficioClub{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", Nivel requerido='" + nivelRequerido + '\'' +
                ", Puntos necesarios='" + descripcion + '\'' +
                ", Tipo de beneficio='" + tipoBeneficio + '\'' +
                ", Valor de beneficio='" + valorBeneficio + '\'' +
                ", activo=" + activo +
                '}';
    }
}
