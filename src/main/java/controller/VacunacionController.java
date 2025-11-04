package controller;

import model.entities.Inventario;
import model.entities.JornadaVacunacion;
import model.entities.RegistroJornadaVacunacion;
import model.enums.EstadoJornadaVacunacion;
import repository.VacunacionDAO;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class VacunacionController {

    private final VacunacionDAO vacunacionDAO;
    private final InventarioController inventarioController;

    public VacunacionController() {
        this.vacunacionDAO = new VacunacionDAO();
        this.inventarioController = new InventarioController();
    }

    public List<Inventario> listarVacunasDisponibles() {
        return vacunacionDAO.obtenerVacunas();
    }

    public Inventario buscarVacunaPorId(int id) {
        return inventarioController.buscarVacunaPorId(id);
    }

    public String crearJornada(JornadaVacunacion jornada) {
        if (jornada == null || jornada.getNombre() == null || jornada.getFecha() == null) {
            return "Error: Datos de jornada incompletos.";
        }

        boolean creada = vacunacionDAO.crearJornada(jornada);
        return creada ? "Jornada de vacunación creada exitosamente."
                      : "Error al crear la jornada.";
    }

    public List<JornadaVacunacion> obtenerTodasJornadas() {
        return vacunacionDAO.obtenerJornadas();
    }

    public String cambiarEstadoJornada(int jornadaId, EstadoJornadaVacunacion.Estado nuevoEstado) {
        if (jornadaId <= 0) return "Error: ID de jornada inválido.";

        boolean actualizado = vacunacionDAO.actualizarEstadoJornada(jornadaId, nuevoEstado);
        return actualizado ? "Estado actualizado correctamente."
                           : "No se pudo actualizar el estado.";
    }

    public JornadaVacunacion buscarJornadaPorId(int id) {
        return vacunacionDAO.obtenerJornadaPorId(id);
    }

    public String registrarVacunacion(RegistroJornadaVacunacion registro) throws SQLException {
        if (registro == null) {
            return "Error: Datos inválidos para registrar vacunación.";
        }

        boolean registrado = vacunacionDAO.registrarVacunacion(registro);
        return registrado ? "Vacunación registrada exitosamente."
                          : "Error al registrar la vacunación.";
    }

    public List<RegistroJornadaVacunacion> obtenerRegistrosDeJornada(int jornadaId) {
        return vacunacionDAO.obtenerRegistrosPorJornada(jornadaId);
    }
    
    public void mostrarVacunas() {
        List<Inventario> vacunas = listarVacunasDisponibles();
        if (vacunas.isEmpty()) {
            System.out.println("No hay vacunas registradas en el inventario.");
            return;
        }
        System.out.println("\nVacunas disponibles:");
        vacunas.forEach(v ->
                System.out.printf("ID: %d | %s | Stock: %d | Vence: %s%n",
                        v.getId(), v.getNombreProducto(), v.getCantidadStock(), v.getFechaVencimiento()));
    }

    public void mostrarJornadas() {
        List<JornadaVacunacion> jornadas = obtenerTodasJornadas();
        if (jornadas.isEmpty()) {
            System.out.println("No hay jornadas registradas.");
            return;
        }
        System.out.println("\nJornadas de Vacunación:");
        jornadas.forEach(j ->
                System.out.printf("ID: %d | %s | %s | %s | %s | Estado: %s%n",
                        j.getId(), j.getNombre(), j.getFecha(), j.getHoraInicio(),
                        j.getUbicacion(), j.getEstado()));
    }
}
