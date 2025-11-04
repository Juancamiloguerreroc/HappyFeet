
package model.entities;

import java.sql.Date;
import java.sql.Timestamp;
import model.enums.SexoMascota;

public class Mascota {
    private int id;
    private Integer duenoId;
    private String nombre;
    private Integer razaId;
    private Date fechaNacimiento;
    private SexoMascota sexo;
    private Double pesoActual;
    private String microchip;
    private String tatuaje;
    private String urlFoto;
    private String alergias;
    private String condicionesPreexistentes;
    private Timestamp fechaRegistro;
    private boolean activo;

    public Mascota() {}
    
    public Mascota(int id, Integer duenoId, String nombre, Integer razaId, Date fechaNacimiento, SexoMascota sexo, Double pesoActual, String microchip, String tatuaje, String urlFoto, String alergias, String condicionesPreexistentes, Timestamp fechaRegistro, boolean activo) {
        this.id = id;
        this.duenoId = duenoId;
        this.nombre = nombre;
        this.razaId = razaId;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.pesoActual = pesoActual;
        this.microchip = microchip;
        this.tatuaje = tatuaje;
        this.urlFoto = urlFoto;
        this.alergias = alergias;
        this.condicionesPreexistentes = condicionesPreexistentes;
        this.fechaRegistro = fechaRegistro;
        this.activo = activo;
    }
    
    public Mascota(Integer duenoId, String nombre, Integer razaId, Date fechaNacimiento, SexoMascota sexo, Double pesoActual, String microchip, String tatuaje, String urlFoto, String alergias, String condicionesPreexistentes, boolean activo) {
        this.duenoId = duenoId;
        this.nombre = nombre;
        this.razaId = razaId;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.pesoActual = pesoActual;
        this.microchip = microchip;
        this.tatuaje = tatuaje;
        this.urlFoto = urlFoto;
        this.alergias = alergias;
        this.condicionesPreexistentes = condicionesPreexistentes;
        this.activo = activo;
    }
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Integer getDuenoId() { return duenoId; }
    public void setDuenoId(Integer duenoId) { this.duenoId = duenoId; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Integer getRazaId() { return razaId; }
    public void setRazaId(Integer razaId) { this.razaId = razaId; }

    public Date getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(Date fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public String getSexo() { return sexo.name(); }
    public void setSexo(SexoMascota sexo) { this.sexo = sexo; }

    public Double getPesoActual() { return pesoActual; }
    public void setPesoActual(Double pesoActual) { this.pesoActual = pesoActual; }

    public String getMicrochip() { return microchip; }
    public void setMicrochip(String microchip) { this.microchip = microchip; }

    public String getTatuaje() { return tatuaje; }
    public void setTatuaje(String tatuaje) { this.tatuaje = tatuaje; }

    public String getUrlFoto() { return urlFoto; }
    public void setUrlFoto(String urlFoto) { this.urlFoto = urlFoto; }

    public String getAlergias() { return alergias; }
    public void setAlergias(String alergias) { this.alergias = alergias; }

    public String getCondicionesPreexistentes() { return condicionesPreexistentes; }
    public void setCondicionesPreexistentes(String condiciones_preexistentes) { this.condicionesPreexistentes = condiciones_preexistentes; }

    public Timestamp getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(Timestamp fechaRegistro) { this.fechaRegistro = fechaRegistro; }

    public boolean getActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
    
    @Override
    public String toString() {
        return "Mascota{" +
                "id=" + id +
                ", dueno_id=" + duenoId +
                ", nombre='" + nombre + '\'' +
                ", raza_id=" + razaId +
                ", fecha_nacimiento=" + fechaNacimiento +
                ", sexo='" + sexo + '\'' +
                ", peso_actual=" + pesoActual +
                ", microchip='" + microchip + '\'' +
                ", tatuaje='" + tatuaje + '\'' +
                ", url_foto='" + urlFoto + '\'' +
                ", alergias='" + alergias + '\'' +
                ", condiciones_preexistentes='" + condicionesPreexistentes + '\'' +
                ", fecha_registro=" + fechaRegistro +
                ", activo=" + activo +
                '}';
    }
}
