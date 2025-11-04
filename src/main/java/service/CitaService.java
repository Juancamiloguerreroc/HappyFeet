package service;

import java.sql.SQLException;
import model.entities.Cita;
import repository.CitaDAO;
import java.util.List;

public class CitaService {
    private final CitaDAO citaDAO = new CitaDAO();

    public void registrarCita(Cita cita) throws SQLException {
        if (cita.getFechaHora() == null) {
            System.out.println("La fecha no puede ser nula.");
            return;
        }
        citaDAO.crear(cita);
    }

    public List<Cita> obtenerCitas() throws SQLException {
        return citaDAO.listarTodas();
    }
}