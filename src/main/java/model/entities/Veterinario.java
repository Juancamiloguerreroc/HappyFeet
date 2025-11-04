
package model.entities;

import java.sql.Date;

public class Veterinario {
    private int id;
    private String nombreCompleto;
    private String documentoIdentidad;
    private String licenciaProfesional;
    private String especialidad;
    private String telefono;
    private String email;
    private Date fechaContratacion;
    private boolean activo;

    public Veterinario() {
    }

    public Veterinario(int id, String nombreCompleto, String documentoIdentidad, String licenciaProfesional, String especialidad, String telefono, String email, Date fechaContratacion, boolean activo) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.documentoIdentidad = documentoIdentidad;
        this.licenciaProfesional = licenciaProfesional;
        this.especialidad = especialidad;
        this.telefono = telefono;
        this.email = email;
        this.fechaContratacion = fechaContratacion;
        this.activo = activo;
    }

    public Veterinario(String nombreCompleto, String documentoIdentidad, String licenciaProfesional, String especialidad, String telefono, String email, Date fechaContratacion, boolean activo) {
        this.nombreCompleto = nombreCompleto;
        this.documentoIdentidad = documentoIdentidad;
        this.licenciaProfesional = licenciaProfesional;
        this.especialidad = especialidad;
        this.telefono = telefono;
        this.email = email;
        this.fechaContratacion = fechaContratacion;
        this.activo = activo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public void setDocumentoIdentidad(String documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
    }

    public String getLicenciaProfesional() {
        return licenciaProfesional;
    }

    public void setLicenciaProfesional(String licenciaProfesional) {
        this.licenciaProfesional = licenciaProfesional;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(Date fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    @Override
    public String toString() {
        return "Veterinario{" +
                "id=" + id +
                ", nombre='" + nombreCompleto + '\'' +
                ", documento de identidad='" + documentoIdentidad + '\'' +
                ", licencia profesional='" + licenciaProfesional + '\'' +
                ", especialidad='" + especialidad + '\'' +
                ", telefono=" + telefono +
                ", email=" + email +
                ", fecha de contratacion=" + fechaContratacion +
                ", activo=" + activo +
                '}';
    }
}
