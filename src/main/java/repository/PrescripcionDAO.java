package repository;

import ConexionDB.DatabaseConnection;
import model.entities.Prescripcion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class PrescripcionDAO {

    public void crear(Prescripcion prescripcion) throws SQLException {
        String sql = "INSERT INTO prescripciones (consulta_id, procedimiento_id, producto_id, cantidad, dosis, frecuencia, duracion_dias, instrucciones) VALUES (?,?,?,?,?,?,?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            if (prescripcion.getConsultaId() != null) {
                ps.setInt(1, prescripcion.getConsultaId());
            } else {
                ps.setNull(1, Types.INTEGER);
            }

            if (prescripcion.getProcedimientoId() != null) {
                ps.setInt(2, prescripcion.getProcedimientoId());
            } else {
                ps.setNull(2, Types.INTEGER);
            }

            ps.setInt(3, prescripcion.getProductoId());
            ps.setInt(4, prescripcion.getCantidad());
            ps.setString(5, prescripcion.getDosis());
            ps.setString(6, prescripcion.getFrecuencia());
            ps.setInt(7, prescripcion.getDuracionDias());
            ps.setString(8, prescripcion.getInstrucciones());

            ps.executeUpdate();
            System.out.println("Prescripción agregada con éxito");

        } catch (SQLException e) {
            System.out.println("Error al agregar la prescripción: " + e.getMessage());
        }
    }

    public List<Prescripcion> listarPrescripciones() {
        List<Prescripcion> prescripciones = new ArrayList<>();
        String sql = "SELECT * FROM prescripciones";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Prescripcion p = new Prescripcion();
                p.setId(rs.getInt("id"));
                p.setConsultaId((Integer) rs.getObject("consulta_id"));
                p.setProcedimientoId((Integer) rs.getObject("procedimiento_id"));
                p.setProductoId(rs.getInt("producto_id"));
                p.setCantidad(rs.getInt("cantidad"));
                p.setDosis(rs.getString("dosis"));
                p.setFrecuencia(rs.getString("frecuencia"));
                p.setDuracionDias(rs.getInt("duracion_dias"));
                p.setInstrucciones(rs.getString("instrucciones"));
                p.setFechaPrescripcion(rs.getTimestamp("fecha_prescripcion"));
                prescripciones.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar las prescripciones: " + e.getMessage());
        }
        return prescripciones;
    }

    public void eliminar(int id) {
        String sql = "DELETE FROM prescripciones WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Prescripción eliminada con éxito");

        } catch (SQLException e) {
            System.out.println("Error al eliminar la prescripción: " + e.getMessage());
        }
    }

    public void actualizar(Prescripcion prescripcion) {
        String sql = "UPDATE prescripciones SET consulta_id=?, procedimiento_id=?, producto_id=?, cantidad=?, dosis=?, frecuencia=?, duracion_dias=?, instrucciones=?, fecha_prescripcion=? WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            if (prescripcion.getConsultaId() != null) {
                ps.setInt(1, prescripcion.getConsultaId());
            } else {
                ps.setNull(1, Types.INTEGER);
            }

            if (prescripcion.getProcedimientoId() != null) {
                ps.setInt(2, prescripcion.getProcedimientoId());
            } else {
                ps.setNull(2, Types.INTEGER);
            }

            ps.setInt(3, prescripcion.getProductoId());
            ps.setInt(4, prescripcion.getCantidad());
            ps.setString(5, prescripcion.getDosis());
            ps.setString(6, prescripcion.getFrecuencia());
            ps.setInt(7, prescripcion.getDuracionDias());
            ps.setString(8, prescripcion.getInstrucciones());
            ps.setTimestamp(9, prescripcion.getFechaPrescripcion());
            ps.setInt(10, prescripcion.getId());

            ps.executeUpdate();
            System.out.println("Prescripción actualizada con éxito");

        } catch (SQLException e) {
            System.out.println("Error al actualizar la prescripción: " + e.getMessage());
        }
    }
}
