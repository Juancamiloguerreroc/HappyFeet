package controller;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import model.entities.Adopcion;
import model.entities.Dueno;
import model.entities.Mascota;
import model.entities.MascotaAdopcion;
import repository.AdopcionDAO;
import repository.MascotaDAO;

public class AdopcionController {

    private final AdopcionDAO adopcionDAO;

    public AdopcionController() {
        this.adopcionDAO = new AdopcionDAO();
    }

    public String registrarMascotaEnAdopcion(MascotaAdopcion mascotaAdopcion) {
        if (mascotaAdopcion == null) {
            return "Error: Datos insuficientes para registrar la mascota en adopción.";
        }

        try {
            return adopcionDAO.registrarMascotaEnAdopcion(mascotaAdopcion);
        } catch (Exception e) {
            System.out.println("Error en Controller al registrar mascota en adopción: " + e.getMessage());
            return "Error de base de datos al registrar la mascota. Contacte a soporte.";
        }
    }

    public String registrarAdopcion(Adopcion adopcion, Mascota nuevaMascota, Dueno dueno) {
        if (adopcion == null || nuevaMascota == null || dueno == null) {
            return "Error: Faltan datos para registrar la adopción.";
        }

        try {
            return adopcionDAO.registrarAdopcion(adopcion, nuevaMascota, dueno);
        } catch (Exception e) {
            System.out.println("Error en Controller al registrar adopción: " + e.getMessage());
            return "Error de base de datos al registrar la adopción. Contacte a soporte.";
        }
    }

    public String actualizarEstadoAdopcion(int mascotaAdopcionId, String nuevoEstado) {
        if (mascotaAdopcionId <= 0 || nuevoEstado == null || nuevoEstado.trim().isEmpty()) {
            return "Error: Datos inválidos para actualizar el estado de adopción.";
        }

        try {
            boolean actualizado = adopcionDAO.actualizarEstadoAdopcion(mascotaAdopcionId, nuevoEstado);
            return actualizado ? "Estado de adopción actualizado correctamente."
                    : "No se pudo actualizar el estado de adopción.";
        } catch (Exception e) {
            System.out.println("Error en Controller al actualizar estado de adopción: " + e.getMessage());
            return "Error de base de datos al actualizar el estado. Contacte a soporte.";
        }
    }

    public List<MascotaAdopcion> listarMascotasDisponibles() {
        try {
            return adopcionDAO.listarMascotasDisponibles();
        } catch (Exception e) {
            System.out.println("Error en Controller al listar mascotas disponibles: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public MascotaAdopcion buscarMascotaAdopcionPorId(int id) {
        if (id <= 0) {
            System.out.println("Por favor, ingrese un ID de mascota válido.");
            return null;
        }

        try {
            return adopcionDAO.buscarMascotaAdopcionPorId(id);
        } catch (Exception e) {
            System.out.println("Error en Controller al buscar mascota en adopción: " + e.getMessage());
            return null;
        }
    }
    
    public int verificacionId() {
    int idMascota = 0;
    int idMascotaAdopcion = 0;

    try {
        idMascota = adopcionDAO.obtenerUltimoIdMascota();
        idMascotaAdopcion = adopcionDAO.obtenerUltimoIdMascotaAdopcion();
        } catch (SQLException ex) {
            System.err.println("Error al obtener últimos IDs: " + ex.getMessage());
        }

        if (idMascotaAdopcion > idMascota) {
            return idMascotaAdopcion;
        } else if (idMascota > idMascotaAdopcion) {
            return idMascota;
        } else {
            return idMascota + 1;
        }
    }

    
    public String actualizarInformacionMascota(int mascotaId, double nuevoPeso, String nuevasCondiciones) {
        try {
            MascotaDAO mascotaDAO = new MascotaDAO();
            Mascota mascota = mascotaDAO.buscarMascotaPorId(mascotaId);

            if (mascota == null) {
                return "No se encontró la mascota con ID: " + mascotaId;
            }

            // Actualizar datos
            mascota.setPesoActual(nuevoPeso);
            if (nuevasCondiciones != null && !nuevasCondiciones.trim().isEmpty()) {
                mascota.setCondicionesPreexistentes(nuevasCondiciones);
            }

            boolean actualizado = mascotaDAO.modificarMascota(mascota);
            return actualizado
                    ? "Información de la mascota actualizada correctamente."
                    : "No se pudo actualizar la información de la mascota.";

        } catch (Exception e) {
            e.printStackTrace();
            return "Error al actualizar información de la mascota: " + e.getMessage();
        }
    }

    public String actualizarDuenoMascota(int mascotaId, int nuevoDuenoId) {
        try {
            MascotaDAO mascotaDAO = new MascotaDAO();
            boolean actualizado = mascotaDAO.actualizarDuenoMascota(mascotaId, nuevoDuenoId);

            return actualizado
                    ? "Dueño asignado correctamente."
                    : "No se pudo actualizar el dueño de la mascota.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al actualizar el dueño de la mascota: " + e.getMessage();
        }
    }

}