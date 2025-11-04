package controller;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import model.entities.Veterinario;
import repository.VeterinarioDAO;

public class VeterinarioController {
    private final VeterinarioDAO veterinarioDAO;

    public VeterinarioController() {
        this.veterinarioDAO = new VeterinarioDAO();
    }

    // 🔹 Registrar veterinario
    public String registrarVeterinario(Veterinario veterinario) {
        if (veterinario == null ||
            veterinario.getNombreCompleto() == null || veterinario.getNombreCompleto().isEmpty() ||
            veterinario.getDocumentoIdentidad() == null || veterinario.getDocumentoIdentidad().isEmpty() ||
            veterinario.getEmail() == null || veterinario.getEmail().isEmpty()) {
            return "Error: Faltan datos obligatorios (Nombre completo, Documento de identidad o Email)";
        }

        try {
            veterinarioDAO.crear(veterinario);
            return "Veterinario registrado exitosamente.";
        } catch (SQLException e) {
            if (e.getSQLState() != null && e.getSQLState().startsWith("23")) {
                return "Error: El veterinario ya ha sido registrado anteriormente. Verifique los datos.";
            }
            System.out.println("Error en Controller al registrar veterinario: " + e.getMessage());
            return "Error de base de datos al registrar veterinario. Contacte a soporte.";
        }
    }

    // 🔹 Buscar veterinario por ID
    public Veterinario encontrarPorId(Integer id) {
        if (id == null || id <= 0) {
            System.out.println("Por favor ingrese un ID válido.");
            return null;
        }

        try {
            return veterinarioDAO.buscarPorId(id);
        } catch (Exception e) {
            System.out.println("Error en Controller al buscar veterinario: " + e.getMessage());
            return null;
        }
    }

    // 🔹 Obtener lista de veterinarios
    public List<Veterinario> obtenerListaVeterinarios() {
        try {
            return veterinarioDAO.listarVeterinarios();
        } catch (Exception e) {
            System.out.println("Error en Controller al listar veterinarios: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    // 🔹 Modificar veterinario
    public String modificarVeterinario(Veterinario veterinario) {
        if (veterinario == null || veterinario.getId() <= 0) {
            return "Error: Veterinario inválido o sin ID.";
        }

        try {
            veterinarioDAO.actualizar(veterinario);
            return "Veterinario modificado correctamente.";
        } catch (Exception e) {
            System.err.println("Error en Controller al modificar veterinario: " + e.getMessage());
            return "Error de base de datos al modificar veterinario.";
        }
    }

    // 🔹 Eliminar veterinario
    public String eliminarVeterinario(int id) {
        if (id <= 0) {
            return "Error: ID inválido.";
        }

        try {
            veterinarioDAO.eliminar(id);
            return "Veterinario eliminado correctamente.";
        } catch (Exception e) {
            System.err.println("Error en Controller al eliminar veterinario: " + e.getMessage());
            return "Error de base de datos al eliminar veterinario.";
        }
    }
}
