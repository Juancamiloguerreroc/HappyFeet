package controller;

import repository.CitaDAO;
import model.entities.Cita;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.List;

public class CitaController {

    private final CitaDAO citaDAO;

    public CitaController() {
        this.citaDAO = new CitaDAO();
    }

    public String registrarCita(Cita cita) {
        if (cita == null || cita.getFechaHora() == null || cita.getMascotaId() <= 0) {
            return "Error: Faltan datos obligatorios (Fecha/Hora o ID de Mascota).";
        }

        try {
            citaDAO.crear(cita); 
            return "Cita agendada exitosamente.";
            
        } catch (SQLException e) {
            System.err.println("Error en Controller al registrar cita: " + e.getMessage());
            return "Error de base de datos al agendar cita. Verifique IDs de Mascota/Veterinario."; 
        }
    }

    public List<Cita> obtenerListaCitas() {
        try {
            return citaDAO.listarTodas(); 
        } catch (SQLException e) {
            System.err.println("Error en Controller al listar citas: " + e.getMessage());
            return Collections.emptyList(); 
        }
    }

    public Cita consultarCita(int id) {
        if (id <= 0) {
            System.err.println("Error: El ID de la cita debe ser positivo.");
            return null;
        }
        try {
            return citaDAO.obtenerPorId(id);
        } catch (SQLException e) {
            System.err.println("Error en Controller al consultar cita: " + e.getMessage());
            return null;
        }
    }

    public String modificarCita(Cita cita) {
        if (cita == null || cita.getId() <= 0) {
            return "Error: La cita a modificar no tiene un ID válido.";
        }

        try {
            citaDAO.actualizar(cita); 
            return "Cita con ID " + cita.getId() + " modificada exitosamente.";
            
        } catch (SQLException e) {
            System.err.println("Error en Controller al modificar cita: " + e.getMessage());
            return "Error de base de datos al modificar cita. Intente de nuevo."; 
        }
    }

    public String cancelarCita(int id, int estadoCanceladoId) {
        if (id <= 0) {
            return "Error: ID de cita no válido para cancelar.";
        }
        
        try {
            citaDAO.cancelar(id, estadoCanceladoId); 
            return "Cita con ID " + id + " cancelada exitosamente.";
            
        } catch (SQLException e) {
            System.err.println("Error en Controller al cancelar cita: " + e.getMessage());
            return "Error de base de datos al cancelar cita."; 
        }
    }

    public boolean esFechaValida(String fechaStr, String formato) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formato);
            LocalDate.parse(fechaStr, formatter);
            return true;
            } catch (DateTimeParseException e) {
            return false;
        }
    }
} 