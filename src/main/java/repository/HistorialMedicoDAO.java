package repository;

import ConexionDB.DatabaseConnection;
import model.entities.HistorialMedico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class HistorialMedicoDAO {

    public void crear(HistorialMedico historial) throws SQLException {
        String sql = """
            INSERT INTO historial_medico 
            (mascota_id, fecha_evento, evento_tipo_id, descripcion, diagnostico, 
             tratamiento_recomendado, veterinario_id, consulta_id, procedimiento_id)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, historial.getMascotaId());
            ps.setDate(2, historial.getFechaEvento());
            ps.setInt(3, historial.getEventoTipoId());
            ps.setString(4, historial.getDescripcion());
            ps.setString(5, historial.getDiagnostico());
            ps.setString(6, historial.getTratamientoRecomendado());

            if (historial.getVeterinarioId() != null) {
                ps.setInt(7, historial.getVeterinarioId());
            } else {
                ps.setNull(7, Types.INTEGER);
            }

            if (historial.getConsultaId() != null) {
                ps.setInt(8, historial.getConsultaId());
            } else {
                ps.setNull(8, Types.INTEGER);
            }

            if (historial.getProcedimientoId() != null) {
                ps.setInt(9, historial.getProcedimientoId());
            } else {
                ps.setNull(9, Types.INTEGER);
            }

            ps.executeUpdate();
            System.out.println("Historial médico agregado con éxito.");

        } catch (SQLException e) {
            System.out.println("Error al agregar historial médico: " + e.getMessage());
        }
    }

    public List<HistorialMedico> listarHistoriales() {
        List<HistorialMedico> historiales = new ArrayList<>();
        String sql = "SELECT * FROM historial_medico";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                HistorialMedico h = new HistorialMedico();
                h.setId(rs.getInt("id"));
                h.setMascotaId(rs.getInt("mascota_id"));
                h.setFechaEvento(rs.getDate("fecha_evento"));
                h.setEventoTipoId(rs.getInt("evento_tipo_id"));
                h.setDescripcion(rs.getString("descripcion"));
                h.setDiagnostico(rs.getString("diagnostico"));
                h.setTratamientoRecomendado(rs.getString("tratamiento_recomendado"));
                h.setVeterinarioId(rs.getObject("veterinario_id") != null ? rs.getInt("veterinario_id") : null);
                h.setConsultaId(rs.getObject("consulta_id") != null ? rs.getInt("consulta_id") : null);
                h.setProcedimientoId(rs.getObject("procedimiento_id") != null ? rs.getInt("procedimiento_id") : null);
                historiales.add(h);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar los historiales médicos: " + e.getMessage());
        }

        return historiales;
    }
}
