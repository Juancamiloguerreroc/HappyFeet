package controller;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import model.entities.Servicio;
import repository.ServicioDAO;

public class ServicioController {

    private final ServicioDAO servicioDAO;

    public ServicioController() {
        this.servicioDAO = new ServicioDAO();
    }

    // Registrar un nuevo servicio
    public String registrarServicio(Servicio servicio) {
        if (servicio == null || servicio.getNombre() == null || servicio.getPrecioBase() == null) {
            return "Error: Faltan datos obligatorios (Nombre, Precio Base)";
        }

        try {
            servicioDAO.crear(servicio);
            return "Servicio registrado exitosamente.";
        } catch (SQLException e) {
            // Manejo de errores según código SQL
            if (e.getSQLState() != null && e.getSQLState().startsWith("23")) {
                return "Error: El servicio ya existe. Verifique los datos.";
            }

            System.err.println("Error en Controller al registrar servicio: " + e.getMessage());
            return "Error de base de datos al registrar el servicio. Contacte a soporte.";
        }
    }

    // Obtener servicio por ID
    public Servicio encontrarPorId(int id) {
        if (id <= 0) {
            System.out.println("Por favor ingrese un ID válido.");
            return null;
        }

        try {
            return servicioDAO.obtenerPorId(id);
        } catch (Exception e) {
            System.err.println("Error en Controller al buscar servicio: " + e.getMessage());
            return null;
        }
    }

    // Listar todos los servicios
    public List<Servicio> obtenerListaServicios() {
        try {
            return servicioDAO.listarServicios();
        } catch (Exception e) {
            System.err.println("Error en Controller al listar servicios: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    // Modificar un servicio
    public String modificarServicio(Servicio servicio) {
        if (servicio == null || servicio.getId() <= 0) {
            return "Error: Servicio inválido o sin ID.";
        }

        try {
            servicioDAO.actualizar(servicio);
            return "Servicio modificado correctamente.";
        } catch (SQLException e) {
            System.err.println("Error en Controller al modificar servicio: " + e.getMessage());
            return "Error de base de datos al modificar el servicio.";
        }
    }

    // Eliminar un servicio
    public String eliminarServicio(int id) {
        if (id <= 0) {
            return "Error: ID inválido.";
        }

        try {
            servicioDAO.eliminar(id);
            return "Servicio eliminado correctamente.";
        } catch (SQLException e) {
            System.err.println("Error en Controller al eliminar servicio: " + e.getMessage());
            return "Error de base de datos al eliminar el servicio.";
        }
    }
}
