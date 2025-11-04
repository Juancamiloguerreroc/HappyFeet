package repository;

import ConexionDB.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.entities.Cita;
import java.util.ArrayList;
import java.util.List;

public class CitaDAO {

    public void crear(Cita cita) throws SQLException {
        String sql = "INSERT INTO citas (mascota_id, veterinario_id, fecha_hora, motivo, estado_id, observaciones, fecha_creacion) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, cita.getMascotaId());
            ps.setInt(2, cita.getVeterinarioId());
            ps.setTimestamp(3, cita.getFechaHora());
            ps.setString(4, cita.getMotivo());
            ps.setInt(5, cita.getEstadoId());
            ps.setString(6, cita.getObservaciones());
            ps.setTimestamp(7, cita.getFechaCreacion());
            
            ps.executeUpdate();
            System.out.println("Cita creada correctamente.");

        } catch (SQLException e) {
            System.err.println("Error al crear cita en DAO: " + e.getMessage());
            throw e;
        }
    }

    public List<Cita> listarTodas() throws SQLException {
        List<Cita> lista = new ArrayList<>();
        String sql = "SELECT * FROM citas"; 
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Cita c = new Cita();
                c.setId(rs.getInt("id"));
                c.setMascotaId(rs.getInt("mascota_id"));
                c.setVeterinarioId(rs.getInt("veterinario_id"));
                c.setFechaHora(rs.getTimestamp("fecha_hora"));
                c.setMotivo(rs.getString("motivo"));
                c.setEstadoId(rs.getInt("estado_id"));
                c.setObservaciones(rs.getString("observaciones"));
                c.setFechaCreacion(rs.getTimestamp("fecha_creacion"));
                lista.add(c);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar citas en DAO: " + e.getMessage());
            throw e;
        }
        return lista;
    }

    public Cita obtenerPorId(int id) throws SQLException {
        String sql = "SELECT * FROM citas WHERE id = ?";
        Cita cita = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cita = new Cita();
                    cita.setId(rs.getInt("id"));
                    cita.setMascotaId(rs.getInt("mascota_id"));
                    cita.setVeterinarioId(rs.getInt("veterinario_id"));
                    cita.setFechaHora(rs.getTimestamp("fecha_hora"));
                    cita.setMotivo(rs.getString("motivo"));
                    cita.setEstadoId(rs.getInt("estado_id"));
                    cita.setObservaciones(rs.getString("observaciones"));
                    cita.setFechaCreacion(rs.getTimestamp("fecha_creacion"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener cita por ID en DAO: " + e.getMessage());
            throw e;
        }
        return cita;
    }

    public void actualizar(Cita cita) throws SQLException {
        String sql = "UPDATE citas SET mascota_id = ?, veterinario_id = ?, fecha_hora = ?, motivo = ?, estado_id = ?, observaciones = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, cita.getMascotaId());
            ps.setInt(2, cita.getVeterinarioId());
            ps.setTimestamp(3, cita.getFechaHora());
            ps.setString(4, cita.getMotivo());
            ps.setInt(5, cita.getEstadoId());
            ps.setString(6, cita.getObservaciones());
            ps.setInt(7, cita.getId()); 

            ps.executeUpdate();
            System.out.println("Cita con ID " + cita.getId() + " actualizada correctamente.");

        } catch (SQLException e) {
            System.err.println("Error al actualizar cita en DAO: " + e.getMessage());
            throw e;
        }
    }

    public void cancelar(int idCita, int idEstadoCancelado) throws SQLException {
        String sql = "UPDATE citas SET estado_id = ? WHERE id = ?"; 

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idEstadoCancelado); 
            ps.setInt(2, idCita);

            ps.executeUpdate();
            System.out.println("Cita con ID " + idCita + " marcada como Cancelada (Estado: " + idEstadoCancelado + ").");

        } catch (SQLException e) {
            System.err.println("Error al cancelar cita en DAO: " + e.getMessage());
            throw e;
        }
    }
}