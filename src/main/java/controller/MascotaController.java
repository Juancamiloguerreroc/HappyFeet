package controller;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import model.entities.Mascota;
import model.entities.Especie;
import model.entities.Raza;
import model.entities.MascotaAdopcion;
import model.enums.EstadoMascotaAdopcion;
import repository.MascotaDAO;

public class MascotaController {

    private final MascotaDAO mascotaDAO;

    public MascotaController() {
        this.mascotaDAO = new MascotaDAO();
    }

    public String registrarMascota(Mascota mascota) {
        if (mascota == null) {
            return "Error: La información de la mascota no puede ser nula.";
        }
        try {
            int id = mascotaDAO.crear(mascota);
            if (id > 0) {
                return "Mascota registrada correctamente con ID: " + id;
            } else {
                return "No se pudo registrar la mascota.";
            }
        } catch (SQLException e) {
            return "Error al registrar la mascota: " + e.getMessage();
        }
    }

    public List<Mascota> listarMascotas() {
        try {
            List<Mascota> mascotas = mascotaDAO.ListarMascotas();
            return mascotas != null ? mascotas : Collections.emptyList();
        } catch (Exception e) {
            System.err.println("Error al listar las mascotas: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public Mascota buscarMascotaPorId(int id) {
        if (id <= 0) {
            System.out.println("ID inválido para buscar mascota.");
            return null;
        }
        return mascotaDAO.buscarMascotaPorId(id);
    }

    public String modificarMascota(Mascota mascota) {
        if (mascota == null || mascota.getId() <= 0) {
            return "Datos inválidos para actualizar la mascota.";
        }

        try {
            boolean actualizado = mascotaDAO.modificarMascota(mascota);
            return actualizado ? "Mascota actualizada correctamente." : "No se encontró la mascota para actualizar.";
        } catch (SQLException e) {
            return "Error al modificar la mascota: " + e.getMessage();
        }
    }

    public String eliminarMascota(int id) {
        if (id <= 0) {
            return "ID inválido para eliminar la mascota.";
        }

        try {
            boolean eliminado = mascotaDAO.eliminarMascota(id);
            return eliminado ? "Mascota eliminada correctamente." : "No se encontró la mascota con el ID especificado.";
        } catch (SQLException e) {
            return "Error al eliminar la mascota: " + e.getMessage();
        }
    }

    public List<Especie> listarEspecies() {
        try {
            return mascotaDAO.listarEspecies();
        } catch (Exception e) {
            System.err.println("Error al listar especies: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public List<Raza> listarRazasPorEspecie(int especieId) {
        if (especieId <= 0) {
            System.out.println("ID de especie inválido.");
            return Collections.emptyList();
        }
        return mascotaDAO.listarRazasPorEspecie(especieId);
    }

    public String crearRaza(int especieId, String nombre, String caracteristicas) {
        if (especieId <= 0 || nombre == null || nombre.isBlank()) {
            return "Datos inválidos para crear una raza.";
        }
        return mascotaDAO.crearRaza(especieId, nombre, caracteristicas);
    }

    public MascotaAdopcion buscarMascotaAdopcionPorId(int id) {
        if (id <= 0) {
            System.out.println("ID inválido para buscar mascota en adopción.");
            return null;
        }
        return mascotaDAO.buscarMascotaAdopcionPorId(id);
    }

    public String actualizarEstadoMascotaAdopcion(int idMascotaAdopcion, EstadoMascotaAdopcion nuevoEstado) {
        if (idMascotaAdopcion <= 0 || nuevoEstado == null) {
            return "Datos inválidos para actualizar el estado de la mascota en adopción.";
        }

        try {
            boolean actualizado = mascotaDAO.actualizarEstadoMascotaAdopcion(idMascotaAdopcion, nuevoEstado);
            return actualizado ? "Estado actualizado correctamente." : "No se encontró la mascota en adopción con el ID especificado.";
        } catch (SQLException e) {
            return "Error al actualizar estado de la mascota en adopción: " + e.getMessage();
        }
    }

    public String adoptarMascota(int idMascotaAdopcion, int idNuevoDueno) {
        if (idMascotaAdopcion <= 0 || idNuevoDueno <= 0) {
            return "Datos inválidos para realizar la adopción.";
        }

        MascotaAdopcion mascotaAdopcion = buscarMascotaAdopcionPorId(idMascotaAdopcion);
        if (mascotaAdopcion == null) {
            return "No se encontró la mascota en adopción con el ID proporcionado.";
        }

        if (mascotaAdopcion.getEstado() != EstadoMascotaAdopcion.Disponible) {
            return "La mascota no está disponible para adopción.";
        }

        Mascota mascota = buscarMascotaPorId(mascotaAdopcion.getMascotaId());
        if (mascota == null) {
            return "No se encontró la información de la mascota asociada.";
        }

        try {
            mascota.setDuenoId(idNuevoDueno);
            mascotaDAO.modificarMascota(mascota);

            mascotaDAO.actualizarEstadoMascotaAdopcion(idMascotaAdopcion, EstadoMascotaAdopcion.Adoptada);

            return "La adopción se realizó correctamente.";
        } catch (SQLException e) {
            return "Error al realizar la adopción: " + e.getMessage();
        }
    }
}
