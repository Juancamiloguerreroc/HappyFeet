package repository;

import ConexionDB.DatabaseConnection;
import model.entities.ConsultaMedica;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class ConsultaMedicaDAO {

    public void crear(ConsultaMedica consulta) throws SQLException {
        String sql = "INSERT INTO consultas_medicas (mascota_id, veterinario_id, cita_id, fecha_hora, motivo, sintomas, diagnostico, recomendaciones, observaciones, peso_registrado, temperatura) VALUES (?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, consulta.getMascotaid());
            ps.setInt(2, consulta.getVeterinarioId());

            if (consulta.getCitaId() != null) {
                ps.setInt(3, consulta.getCitaId());
            } else {
                ps.setNull(3, Types.INTEGER);
            }

            ps.setTimestamp(4, consulta.getFechaHora());
            ps.setString(5, consulta.getMotivo());
            ps.setString(6, consulta.getSintomas());
            ps.setString(7, consulta.getDiagnostico());
            ps.setString(8, consulta.getRecomendaciones());
            ps.setString(9, consulta.getObservaciones());
            ps.setDouble(10, consulta.getPesoregistrado());
            ps.setDouble(11, consulta.getTemperatura());

            ps.executeUpdate();
            System.out.println("Consulta médica agregada con éxito");

        } catch (SQLException e) {
            System.out.println("Error al agregar la consulta médica: " + e.getMessage());
        }
    }

    public List<ConsultaMedica> listarConsultas() {
        List<ConsultaMedica> consultas = new ArrayList<>();
        String sql = "SELECT * FROM consultas_medicas";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                ConsultaMedica c = new ConsultaMedica();
                c.setId(rs.getInt("id"));
                c.setMascotaid(rs.getInt("mascota_id"));
                c.setVeterinarioId(rs.getInt("veterinario_id"));
                c.setCitaId((Integer) rs.getObject("cita_id"));
                c.setFechaHora(rs.getTimestamp("fecha_hora"));
                c.setMotivo(rs.getString("motivo"));
                c.setSintomas(rs.getString("sintomas"));
                c.setDiagnostico(rs.getString("diagnostico"));
                c.setRecomendaciones(rs.getString("recomendaciones"));
                c.setObservaciones(rs.getString("observaciones"));
                c.setPesoRegistrado(rs.getDouble("peso_registrado"));
                c.setTemperatura(rs.getDouble("temperatura"));
                consultas.add(c);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar las consultas médicas: " + e.getMessage());
        }
        return consultas;
    }

    public void actualizar(ConsultaMedica consulta) {
        String sql = "UPDATE consultas_medicas SET mascota_id=?, veterinario_id=?, cita_id=?, fecha_hora=?, motivo=?, sintomas=?, diagnostico=?, recomendaciones=?, observaciones=?, peso_registrado=?, temperatura=? WHERE id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, consulta.getMascotaid());
            ps.setInt(2, consulta.getVeterinarioId());

            if (consulta.getCitaId() != null) {
                ps.setInt(3, consulta.getCitaId());
            } else {
                ps.setNull(3, Types.INTEGER);
            }

            ps.setTimestamp(4, consulta.getFechaHora());
            ps.setString(5, consulta.getMotivo());
            ps.setString(6, consulta.getSintomas());
            ps.setString(7, consulta.getDiagnostico());
            ps.setString(8, consulta.getRecomendaciones());
            ps.setString(9, consulta.getObservaciones());
            ps.setDouble(10, consulta.getPesoregistrado());
            ps.setDouble(11, consulta.getTemperatura());
            ps.setInt(12, consulta.getId());

            ps.executeUpdate();
            System.out.println("Consulta médica actualizada con éxito");

        } catch (SQLException e) {
            System.out.println("Error al actualizar la consulta médica: " + e.getMessage());
        }
    }

    public void eliminar(int id) {
        String sql = "DELETE FROM consultas_medicas WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Consulta médica eliminada con éxito");

        } catch (SQLException e) {
            System.out.println("Error al eliminar la consulta médica: " + e.getMessage());
        }
    }
}
