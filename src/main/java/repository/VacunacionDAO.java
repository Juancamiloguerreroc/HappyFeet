package repository;

import ConexionDB.DatabaseConnection;
import model.entities.Inventario;
import model.entities.JornadaVacunacion;
import model.entities.RegistroJornadaVacunacion;
import model.enums.EstadoJornadaVacunacion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VacunacionDAO {

    public List<Inventario> obtenerVacunas() {
        List<Inventario> lista = new ArrayList<>();
        String sql = "SELECT * FROM inventario WHERE producto_tipo_id = ? AND activo = true"; // Suponiendo tipo = vacuna

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, 2); // Ejemplo: 2 = tipo vacuna (ajustar según DB)
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(mapInventario(rs));
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener vacunas: " + e.getMessage());
        }
        return lista;
    }

    public boolean crearJornada(JornadaVacunacion jornada) {
        String sql = """
            INSERT INTO jornadas_vacunacion (nombre, fecha, hora_inicio, hora_fin, ubicacion, descripcion, capacidad_maxima, estado)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, jornada.getNombre());
            ps.setDate(2, jornada.getFecha());
            ps.setTime(3, jornada.getHoraInicio());
            ps.setTime(4, jornada.getHoraFin());
            ps.setString(5, jornada.getUbicacion());
            ps.setString(6, jornada.getDescripcion());
            ps.setInt(7, jornada.getCapacidadMaxima());
            ps.setString(8, jornada.getEstado().name());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al crear jornada: " + e.getMessage());
            return false;
        }
    }

    public List<JornadaVacunacion> obtenerJornadas() {
        List<JornadaVacunacion> jornadas = new ArrayList<>();
        String sql = "SELECT * FROM jornadas_vacunacion ORDER BY fecha DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                jornadas.add(mapJornada(rs));
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener jornadas: " + e.getMessage());
        }
        return jornadas;
    }

    public boolean actualizarEstadoJornada(int id, EstadoJornadaVacunacion.Estado nuevoEstado) {
        String sql = "UPDATE jornadas_vacunacion SET estado = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nuevoEstado.name());
            ps.setInt(2, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar estado de jornada: " + e.getMessage());
            return false;
        }
    }

    public boolean registrarVacunacion(RegistroJornadaVacunacion reg) {
        String sql = """
            INSERT INTO registro_jornada_vacunacion 
            (jornada_id, mascota_id, dueno_id, vacuna_id, veterinario_id, fecha_hora, lote_vacuna, proxima_dosis, observaciones)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, reg.getJornadaId());
            ps.setInt(2, reg.getMascotaId());
            ps.setInt(3, reg.getDuenoId());
            ps.setInt(4, reg.getVacunaId());
            ps.setInt(5, reg.getVeterinarioId());
            ps.setTimestamp(6, reg.getFechaHora());
            ps.setString(7, reg.getLoteVacuna());
            ps.setDate(8, reg.getProximaDosis());
            ps.setString(9, reg.getObservaciones());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al registrar vacunación: " + e.getMessage());
            return false;
        }
    }

    public List<RegistroJornadaVacunacion> obtenerRegistrosPorJornada(int jornadaId) {
        List<RegistroJornadaVacunacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM registro_jornada_vacunacion WHERE jornada_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, jornadaId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(mapRegistro(rs));
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener registros: " + e.getMessage());
        }
        return lista;
    }

    private Inventario mapInventario(ResultSet rs) throws SQLException {
        Inventario inv = new Inventario();
        inv.setId(rs.getInt("id"));
        inv.setNombreProducto(rs.getString("nombre_producto"));
        inv.setDescripcion(rs.getString("descripcion"));
        inv.setCantidadStock(rs.getInt("cantidad_stock"));
        inv.setUnidadMedida(rs.getString("unidad_medida"));
        inv.setFechaVencimiento(rs.getDate("fecha_vencimiento"));
        inv.setActivo(rs.getBoolean("activo"));
        return inv;
    }

    private JornadaVacunacion mapJornada(ResultSet rs) throws SQLException {
        JornadaVacunacion j = new JornadaVacunacion();
        j.setId(rs.getInt("id"));
        j.setNombre(rs.getString("nombre"));
        j.setFecha(rs.getDate("fecha"));
        j.setHoraInicio(rs.getTime("hora_inicio"));
        j.setHoraFin(rs.getTime("hora_fin"));
        j.setUbicacion(rs.getString("ubicacion"));
        j.setDescripcion(rs.getString("descripcion"));
        j.setCapacidadMaxima(rs.getInt("capacidad_maxima"));
        j.setEstado(EstadoJornadaVacunacion.Estado.valueOf(rs.getString("estado")));
        return j;
    }

    private RegistroJornadaVacunacion mapRegistro(ResultSet rs) throws SQLException {
        RegistroJornadaVacunacion r = new RegistroJornadaVacunacion();
        r.setId(rs.getInt("id"));
        r.setJornadaId(rs.getInt("jornada_id"));
        r.setMascotaId(rs.getInt("mascota_id"));
        r.setDuenoId(rs.getInt("dueno_id"));
        r.setVacunaId(rs.getInt("vacuna_id"));
        r.setVeterinarioId(rs.getInt("veterinario_id"));
        r.setFechaHora(rs.getTimestamp("fecha_hora"));
        r.setLoteVacuna(rs.getString("lote_vacuna"));
        r.setProximaDosis(rs.getDate("proxima_dosis"));
        r.setObservaciones(rs.getString("observaciones"));
        return r;
    }
    
    public JornadaVacunacion obtenerJornadaPorId(int id) {
        String sql = "SELECT * FROM jornadas_vacunacion WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapJornada(rs);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener jornada por ID: " + e.getMessage());
        }

        return null;
    }

}
