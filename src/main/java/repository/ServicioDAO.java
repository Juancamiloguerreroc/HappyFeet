package repository;

import ConexionDB.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.entities.Servicio;
import java.util.ArrayList;
import java.util.List;

public class ServicioDAO {

    // Crear un nuevo servicio
    public void crear(Servicio servicio) throws SQLException {
        String sql = "INSERT INTO servicios (nombre, descripcion, categoria, precio_base, duracion_estimada_minutos, activo) VALUES (?,?,?,?,?,?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, servicio.getNombre());
            ps.setString(2, servicio.getDescripcion());
            ps.setString(3, servicio.getCategoria());
            ps.setDouble(4, servicio.getPrecioBase());
            ps.setInt(5, servicio.getDuracionEstimadaMinutos());
            ps.setBoolean(6, servicio.getActivo());

            ps.executeUpdate();

            // Obtener el id generado automáticamente
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    servicio.setId(rs.getInt(1));
                }
            }

            System.out.println("Servicio agregado con éxito");

        } catch (SQLException e) {
            System.out.println("Error al agregar servicio: " + e.getMessage());
            throw e;
        }
    }

    // Listar todos los servicios
    public List<Servicio> listarServicios() throws SQLException {
        List<Servicio> servicios = new ArrayList<>();
        String sql = "SELECT * FROM servicios";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                servicios.add(mapearServicio(rs));
            }

        } catch (SQLException e) {
            System.out.println("Error al listar servicios: " + e.getMessage());
            throw e;
        }

        return servicios;
    }

    // Obtener un servicio por ID
    public Servicio obtenerPorId(int id) throws SQLException {
        Servicio servicio = null;
        String sql = "SELECT * FROM servicios WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    servicio = mapearServicio(rs);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener servicio por ID: " + e.getMessage());
            throw e;
        }

        return servicio;
    }

    // Actualizar un servicio existente
    public void actualizar(Servicio servicio) throws SQLException {
        String sql = "UPDATE servicios SET nombre = ?, descripcion = ?, categoria = ?, precio_base = ?, duracion_estimada_minutos = ?, activo = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, servicio.getNombre());
            ps.setString(2, servicio.getDescripcion());
            ps.setString(3, servicio.getCategoria());
            ps.setDouble(4, servicio.getPrecioBase());
            ps.setInt(5, servicio.getDuracionEstimadaMinutos());
            ps.setBoolean(6, servicio.getActivo());
            ps.setInt(7, servicio.getId());

            int filasActualizadas = ps.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("Servicio actualizado con éxito");
            } else {
                System.out.println("No se encontró un servicio con el ID especificado");
            }

        } catch (SQLException e) {
            System.out.println("Error al actualizar servicio: " + e.getMessage());
            throw e;
        }
    }

    // Eliminar un servicio
    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM servicios WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            int filasEliminadas = ps.executeUpdate();
            if (filasEliminadas > 0) {
                System.out.println("Servicio eliminado con éxito");
            } else {
                System.out.println("No se encontró un servicio con el ID especificado");
            }

        } catch (SQLException e) {
            System.out.println("Error al eliminar servicio: " + e.getMessage());
            throw e;
        }
    }

    // Método privado para mapear ResultSet a objeto Servicio
    private Servicio mapearServicio(ResultSet rs) throws SQLException {
        Servicio s = new Servicio();
        s.setId(rs.getInt("id"));
        s.setNombre(rs.getString("nombre"));
        s.setDescripcion(rs.getString("descripcion"));
        s.setCategoria(rs.getString("categoria"));
        s.setPrecioBase(rs.getDouble("precio_base"));
        s.setDuracionEstimadaMinutos(rs.getInt("duracion_estimada_minutos"));
        s.setActivo(rs.getBoolean("activo"));
        return s;
    }
}
