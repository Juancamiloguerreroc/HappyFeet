package repository;

import ConexionDB.DatabaseConnection;
import model.entities.Veterinario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VeterinarioDAO {

    public void crear(Veterinario veterinario) throws SQLException {
        String sql = "INSERT INTO veterinarios (nombre_completo, documento_identidad, licencia_profesional, especialidad, telefono, email, fecha_contratacion, activo) VALUES (?,?,?,?,?,?,?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, veterinario.getNombreCompleto());
            ps.setString(2, veterinario.getDocumentoIdentidad());
            ps.setString(3, veterinario.getLicenciaProfesional());
            ps.setString(4, veterinario.getEspecialidad());
            ps.setString(5, veterinario.getTelefono());
            ps.setString(6, veterinario.getEmail());
            ps.setDate(7, veterinario.getFechaContratacion());
            ps.setBoolean(8, veterinario.isActivo());

            ps.executeUpdate();
            System.out.println("Veterinario agregado con éxito.");
        } catch (SQLException e) {
            System.out.println("Error al agregar veterinario: " + e.getMessage());
        }
    }

    public List<Veterinario> listarVeterinarios() {
        List<Veterinario> lista = new ArrayList<>();
        String sql = "SELECT * FROM veterinarios";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            
            while (rs.next()) {
                Veterinario v = new Veterinario();
                v.setId(rs.getInt("id"));
                v.setNombreCompleto(rs.getString("nombre_completo"));
                v.setDocumentoIdentidad(rs.getString("documento_identidad"));
                v.setLicenciaProfesional(rs.getString("licencia_profesional"));
                v.setEspecialidad(rs.getString("especialidad"));
                v.setTelefono(rs.getString("telefono"));
                v.setEmail(rs.getString("email"));
                v.setFechaContratacion(rs.getDate("fecha_contratacion"));
                v.setActivo(rs.getBoolean("activo"));
                lista.add(v);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar veterinarios: " + e.getMessage());
        }
        return lista;
    }

    // 🔹 Buscar por ID
    public Veterinario buscarPorId(int id) {
        Veterinario v = null;
        String sql = "SELECT * FROM veterinarios WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    v = new Veterinario();
                    v.setId(rs.getInt("id"));
                    v.setNombreCompleto(rs.getString("nombre_completo"));
                    v.setDocumentoIdentidad(rs.getString("documento_identidad"));
                    v.setLicenciaProfesional(rs.getString("licencia_profesional"));
                    v.setEspecialidad(rs.getString("especialidad"));
                    v.setTelefono(rs.getString("telefono"));
                    v.setEmail(rs.getString("email"));
                    v.setFechaContratacion(rs.getDate("fecha_contratacion"));
                    v.setActivo(rs.getBoolean("activo"));
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar veterinario por ID: " + e.getMessage());
        }
        return v;
    }

    // 🔹 Actualizar veterinario
    public void actualizar(Veterinario veterinario) {
        String sql = "UPDATE veterinarios SET nombre_completo=?, documento_identidad=?, licencia_profesional=?, especialidad=?, telefono=?, email=?, fecha_contratacion=?, activo=? WHERE id=?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, veterinario.getNombreCompleto());
            ps.setString(2, veterinario.getDocumentoIdentidad());
            ps.setString(3, veterinario.getLicenciaProfesional());
            ps.setString(4, veterinario.getEspecialidad());
            ps.setString(5, veterinario.getTelefono());
            ps.setString(6, veterinario.getEmail());
            ps.setDate(7, veterinario.getFechaContratacion());
            ps.setBoolean(8, veterinario.isActivo());
            ps.setInt(9, veterinario.getId());
            
            ps.executeUpdate();
            System.out.println("Veterinario actualizado con éxito.");

        } catch (SQLException e) {
            System.out.println("Error al actualizar veterinario: " + e.getMessage());
        }
    }

    // 🔹 Eliminar veterinario
    public void eliminar(int id) {
        String sql = "DELETE FROM veterinarios WHERE id=?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Veterinario eliminado con éxito.");

        } catch (SQLException e) {
            System.out.println("Error al eliminar veterinario: " + e.getMessage());
        }
    }
}

