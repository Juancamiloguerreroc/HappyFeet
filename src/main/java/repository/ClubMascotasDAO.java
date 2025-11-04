package repository;

import ConexionDB.DatabaseConnection;
import model.entities.ClubMascotas;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClubMascotasDAO {

    public void crear(ClubMascotas club) throws SQLException {
        String sql = "INSERT INTO club_mascotas (dueno_id, puntos_acumulados, puntos_canjeados, puntos_disponibles, nivel, fecha_inscripcion, fecha_ultima_actualizacion, activo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setInt(1, club.getDuenoId());
            ps.setInt(2, club.getPuntosAcumulados());
            ps.setInt(3, club.getPuntosCanjeados());
            ps.setInt(4, club.getPuntosDisponibles());
            ps.setString(5, club.getNivel());
            ps.setDate(6, new java.sql.Date(club.getFechaInscripcion().getTime()));
            ps.setDate(7, new java.sql.Date(club.getFechaUltimaActualizacion().getTime()));
            ps.setBoolean(8, club.isActivo());
            
            ps.executeUpdate();
            
            // Obtener el ID generado
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    club.setId(generatedKeys.getInt(1));
                }
            }
            
            System.out.println(" Socio registrado en el club con éxito");
            
        } catch (SQLException e) {
            System.err.println(" Error al registrar socio en el club: " + e.getMessage());
            throw e;
        }
    }

    public List<ClubMascotas> listarTodos() throws SQLException {
        List<ClubMascotas> socios = new ArrayList<>();
        String sql = "SELECT * FROM club_mascotas ORDER BY fecha_inscripcion DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            
            while (rs.next()) {
                socios.add(mapearClubMascotas(rs));
            }
        } catch (SQLException e) {
            System.err.println("  Error al listar socios del club: " + e.getMessage());
            throw e;
        }
        return socios;
    }

    public ClubMascotas obtenerPorId(int id) throws SQLException {
        String sql = "SELECT * FROM club_mascotas WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearClubMascotas(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println(" Error al obtener socio por ID: " + e.getMessage());
            throw e;
        }
        return null;
    }

    public ClubMascotas obtenerPorDuenoId(int duenoId) throws SQLException {
        String sql = "SELECT * FROM club_mascotas WHERE dueno_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, duenoId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearClubMascotas(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println(" Error al obtener socio por dueño ID: " + e.getMessage());
            throw e;
        }
        return null;
    }

    public void actualizar(ClubMascotas club) throws SQLException {
        String sql = "UPDATE club_mascotas SET puntos_acumulados = ?, puntos_canjeados = ?, puntos_disponibles = ?, nivel = ?, fecha_ultima_actualizacion = ?, activo = ? WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, club.getPuntosAcumulados());
            ps.setInt(2, club.getPuntosCanjeados());
            ps.setInt(3, club.getPuntosDisponibles());
            ps.setString(4, club.getNivel());
            ps.setDate(5, new java.sql.Date(new java.util.Date().getTime())); // Fecha actual
            ps.setBoolean(6, club.isActivo());
            ps.setInt(7, club.getId());
            
            ps.executeUpdate();
            System.out.println("✅ Socio del club actualizado con éxito");
            
        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar socio del club: " + e.getMessage());
            throw e;
        }
    }

    public void acumularPuntos(int idSocio, int puntos) throws SQLException {
        String sql = "UPDATE club_mascotas SET puntos_acumulados = puntos_acumulados + ?, puntos_disponibles = puntos_disponibles + ?, fecha_ultima_actualizacion = ? WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, puntos);
            ps.setInt(2, puntos);
            ps.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
            ps.setInt(4, idSocio);
            
            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println(" " + puntos + " puntos acumulados al socio ID: " + idSocio);
            }
            
        } catch (SQLException e) {
            System.err.println(" Error al acumular puntos: " + e.getMessage());
            throw e;
        }
    }

    public boolean canjearPuntos(int idSocio, int puntos) throws SQLException {
        // Primero verificar que tenga puntos suficientes
        ClubMascotas socio = obtenerPorId(idSocio);
        if (socio == null || socio.getPuntosDisponibles() < puntos) {
            return false;
        }
        
        String sql = "UPDATE club_mascotas SET puntos_canjeados = puntos_canjeados + ?, puntos_disponibles = puntos_disponibles - ?, fecha_ultima_actualizacion = ? WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, puntos);
            ps.setInt(2, puntos);
            ps.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
            ps.setInt(4, idSocio);
            
            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println(" " + puntos + " puntos canjeados del socio ID: " + idSocio);
                return true;
            }
            return false;
            
        } catch (SQLException e) {
            System.err.println(" Error al canjear puntos: " + e.getMessage());
            throw e;
        }
    }

    public void actualizarNivel(int idSocio, String nuevoNivel) throws SQLException {
        String sql = "UPDATE club_mascotas SET nivel = ?, fecha_ultima_actualizacion = ? WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, nuevoNivel);
            ps.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
            ps.setInt(3, idSocio);
            
            ps.executeUpdate();
            System.out.println(" Nivel actualizado a '" + nuevoNivel + "' para socio ID: " + idSocio);
            
        } catch (SQLException e) {
            System.err.println(" Error al actualizar nivel: " + e.getMessage());
            throw e;
        }
    }

    public void desactivarSocio(int idSocio) throws SQLException {
        String sql = "UPDATE club_mascotas SET activo = false, fecha_ultima_actualizacion = ? WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
            ps.setInt(2, idSocio);
            
            ps.executeUpdate();
            System.out.println(" Socio ID: " + idSocio + " desactivado del club");
            
        } catch (SQLException e) {
            System.err.println(" Error al desactivar socio: " + e.getMessage());
            throw e;
        }
    }

    public List<ClubMascotas> obtenerSociosActivos() throws SQLException {
        List<ClubMascotas> socios = new ArrayList<>();
        String sql = "SELECT * FROM club_mascotas WHERE activo = true ORDER BY puntos_disponibles DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            
            while (rs.next()) {
                socios.add(mapearClubMascotas(rs));
            }
        } catch (SQLException e) {
            System.err.println(" Error al obtener socios activos: " + e.getMessage());
            throw e;
        }
        return socios;
    }

    public int contarTotalSocios() throws SQLException {
        String sql = "SELECT COUNT(*) as total FROM club_mascotas";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            System.err.println(" Error al contar socios: " + e.getMessage());
            throw e;
        }
        return 0;
    }

    public int obtenerPuntosTotales() throws SQLException {
        String sql = "SELECT SUM(puntos_disponibles) as total_puntos FROM club_mascotas WHERE activo = true";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            
            if (rs.next()) {
                return rs.getInt("total_puntos");
            }
        } catch (SQLException e) {
            System.err.println(" Error al obtener puntos totales: " + e.getMessage());
            throw e;
        }
        return 0;
    }

    private ClubMascotas mapearClubMascotas(ResultSet rs) throws SQLException {
        ClubMascotas club = new ClubMascotas();
        club.setId(rs.getInt("id"));
        club.setDuenoId(rs.getInt("dueno_id"));
        club.setPuntosAcumulados(rs.getInt("puntos_acumulados"));
        club.setPuntosCanjeados(rs.getInt("puntos_canjeados"));
        club.setPuntosDisponibles(rs.getInt("puntos_disponibles"));
        club.setNivel(rs.getString("nivel"));
        club.setFechaInscripcion(rs.getDate("fecha_inscripcion"));
        club.setFechaUltimaActualizacion(rs.getDate("fecha_ultima_actualizacion"));
        club.setActivo(rs.getBoolean("activo"));
        return club;
    }
}