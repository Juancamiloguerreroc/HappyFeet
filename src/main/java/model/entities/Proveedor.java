
package model.entities;

import java.sql.Date;

public class Proveedor {
    private int id;
    private String nombreEmpresa;
    private String contacto;
    private String telefono;
    private String email;
    private String direccion;
    private String sitioWeb;
    private boolean activo;
    private Date fechaRegistro;
    
   public Proveedor(int id, String nombreEmpresa, String contacto, String telefono, String email, String direccion, String sitioWeb, boolean activo, Date fechaRegistro) {
        this.id = id;
        this.nombreEmpresa = nombreEmpresa;
        this.contacto = contacto;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
        this.sitioWeb = sitioWeb;
        this.activo = activo;
        this.fechaRegistro = fechaRegistro;
    } 
   
   public Proveedor(String nombreEmpresa, String contacto, String telefono, String email, String direccion, String sitioWeb, boolean activo, Date fechaRegistro) {
        this.nombreEmpresa = nombreEmpresa;
        this.contacto = contacto;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
        this.sitioWeb = sitioWeb;
        this.activo = activo;
        this.fechaRegistro = fechaRegistro;
    }

    public Proveedor() {}
   
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getNombreEmpresa() {return nombreEmpresa;}
    public void setNombreEmpresa(String nombreEmpresa) {this.nombreEmpresa = nombreEmpresa;}

    public String getContacto() {return contacto;}
    public void setContacto(String contacto) {this.contacto = contacto;}

    public String getTelefono() {return telefono;}
    public void setTelefono(String telefono) {this.telefono = telefono;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getDireccion() {return direccion;}
    public void setDireccion(String direccion) {this.direccion = direccion;}

    public String getSitioWeb() {return sitioWeb;}
    public void setSitioWeb(String sitioWeb) {this.sitioWeb = sitioWeb;}

    public boolean getActivo() {return activo;}
    public void setActivo(boolean activo) {this.activo = activo;}

    public Date getFechaRegistro() {return fechaRegistro;}
    public void setFechaRegistro(Date fechaRegistro) {this.fechaRegistro = fechaRegistro;}
    
    @Override
    public String toString() {
        return "Proveedor{" +
                "id=" + id +
                ", nombreEmpresa='" + nombreEmpresa + '\'' +
                ", contacto='" + contacto + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                ", direccion='" + direccion + '\'' +
                ", sitioWeb='" + sitioWeb + '\'' +
                ", activo=" + activo +
                ", fechaRegistro=" + fechaRegistro +
                '}';
    }
}
