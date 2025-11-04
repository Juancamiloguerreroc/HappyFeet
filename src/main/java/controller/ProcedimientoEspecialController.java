package controller;

import model.entities.ProcedimientoEspecial;
import model.entities.InsumoProcedimiento;
import repository.ProcedimientoEspecialDAO;

import java.sql.SQLException;
import java.util.List;

public class ProcedimientoEspecialController {

    private final ProcedimientoEspecialDAO procedimientoDAO;

    public ProcedimientoEspecialController() {
        this.procedimientoDAO = new ProcedimientoEspecialDAO();
    }

    public String registrarProcedimiento(ProcedimientoEspecial procedimiento) {
        try {
            procedimientoDAO.crearProcedimiento(procedimiento);
            return "Procedimiento registrado correctamente.";
        } catch (SQLException e) {
            return "Error al registrar el procedimiento: " + e.getMessage();
        }
    }

    public List<ProcedimientoEspecial> listarProcedimientos() {
        try {
            return procedimientoDAO.listarProcedimientos();
        } catch (SQLException e) {
            System.err.println("Error al listar procedimientos: " + e.getMessage());
            return List.of();
        }
    }

    public ProcedimientoEspecial obtenerProcedimientoPorId(int id) {
        try {
            return procedimientoDAO.obtenerPorId(id);
        } catch (SQLException e) {
            System.err.println("Error al obtener procedimiento: " + e.getMessage());
            return null;
        }
    }

    public String actualizarProcedimiento(ProcedimientoEspecial procedimiento) {
        try {
            procedimientoDAO.actualizarProcedimiento(procedimiento);
            return "Procedimiento actualizado correctamente.";
        } catch (SQLException e) {
            return "Error al actualizar el procedimiento: " + e.getMessage();
        }
    }

    public String eliminarProcedimiento(int id) {
        try {
            procedimientoDAO.eliminarProcedimiento(id);
            return "Procedimiento eliminado correctamente.";
        } catch (SQLException e) {
            return "Error al eliminar el procedimiento: " + e.getMessage();
        }
    }

    public String agregarInsumoAProcedimiento(InsumoProcedimiento insumo) {
        try {
            procedimientoDAO.agregarInsumo(insumo);
            return "Insumo agregado correctamente al procedimiento.";
        } catch (SQLException e) {
            return "Error al agregar insumo: " + e.getMessage();
        }
    }

    public List<InsumoProcedimiento> listarInsumosPorProcedimiento(int procedimientoId) {
        try {
            return procedimientoDAO.listarInsumosPorProcedimiento(procedimientoId);
        } catch (SQLException e) {
            System.err.println("Error al listar insumos: " + e.getMessage());
            return List.of();
        }
    }

    public String eliminarInsumo(int id) {
        try {
            procedimientoDAO.eliminarInsumo(id);
            return "Insumo eliminado correctamente.";
        } catch (SQLException e) {
            return "Error al eliminar el insumo: " + e.getMessage();
        }
    }
}
