package repository;

import ConexionDB.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.entities.Adopcion;
import model.entities.Mascota;
import model.entities.MascotaAdopcion;
import model.entities.Dueno;

public class AdopcionDAO {

    private final DuenoDAO duenoDAO;
    private final MascotaDAO mascotaDAO;

    public AdopcionDAO() {
        this.duenoDAO = new DuenoDAO();
        this.mascotaDAO = new MascotaDAO();
    }

    public String registrarMascotaEnAdopcion(MascotaAdopcion mascotaAdopcion) {
        String sql = """
            INSERT INTO mascotas_adopcion (
                mascota_id, fecha_ingreso, motivo_ingreso, estado,
                historia, temperamento, necesidades_especiales, foto_adicional_url
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setObject(1, mascotaAdopcion.getMascotaId(), Types.INTEGER);
            ps.setDate(2, mascotaAdopcion.getFechaIngreso());
            ps.setString(3, mascotaAdopcion.getMotivoIngreso());
            ps.setString(4, mascotaAdopcion.getEstado().toString());
            ps.setString(5, mascotaAdopcion.getHistoria());
            ps.setString(6, mascotaAdopcion.getTemperamento());
            ps.setString(7, mascotaAdopcion.getNecesidadesEspeciales());
            ps.setString(8, mascotaAdopcion.getFotoAdicionalUrl());

            int filas = ps.executeUpdate();
            return filas > 0 ? "Mascota registrada en refugio correctamente." :
                    "No se pudo registrar la mascota en adopción.";

        } catch (SQLException e) {
            e.printStackTrace();
            return "Error al registrar la mascota en adopción: " + e.getMessage();
        }
    }

    public int obtenerUltimoIdMascota() throws SQLException {
        String sql = "SELECT MAX(id) AS ultimo_id FROM mascotas";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) return rs.getInt("ultimo_id");
        }
        return 0;
    }
    
    public int obtenerUltimoIdMascotaAdopcion() throws SQLException {
        String sql = "SELECT MAX(mascota_id) AS ultimo_id FROM mascotas_adopcion";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) return rs.getInt("ultimo_id");
        }
        return 0;
    }

    public String registrarAdopcion(Adopcion adopcion, Mascota nuevaMascota, Dueno dueno) {
        String mensaje = "";
        Connection conn = null;

        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);

            Integer duenoId = duenoDAO.obtenerIdPorDocumento(dueno.getDocumentoIdentidad());
            if (duenoId == null) {
                System.out.println("🔹 Dueño no encontrado, registrando nuevo...");
                duenoDAO.crear(dueno);
                duenoId = duenoDAO.obtenerIdPorDocumento(dueno.getDocumentoIdentidad());
            }
            nuevaMascota.setDuenoId(duenoId);

            int nuevoIdMascota = obtenerUltimoIdMascota() + 1;
            nuevaMascota.setId(nuevoIdMascota);

            mascotaDAO.crear(nuevaMascota);

            String sqlAdopcion = """
                INSERT INTO adopciones (
                    mascota_adopcion_id, dueno_id, fecha_adopcion, contrato_texto,
                    condiciones_especiales, seguimiento_requerido, fecha_primer_seguimiento
                ) VALUES (?, ?, ?, ?, ?, ?, ?)
            """;

            try (PreparedStatement ps = conn.prepareStatement(sqlAdopcion)) {
                ps.setInt(1, adopcion.getIdMascotaAdopcion());
                ps.setInt(2, duenoId);
                ps.setDate(3, adopcion.getFechaAdopcion());
                ps.setString(4, adopcion.getContratoTexto());
                ps.setString(5, adopcion.getCondicionesEspeciales());
                ps.setBoolean(6, adopcion.isSeguimientoRequerido());
                ps.setDate(7, adopcion.getFechaPrimerSeguimiento());
                ps.executeUpdate();
            }

            // Actualizar estado de la mascota en adopción
            actualizarEstadoAdopcion(adopcion.getIdMascotaAdopcion(), "Adoptada");

            conn.commit();
            mensaje = "Adopción registrada correctamente con mascota ID: " + nuevoIdMascota;

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            mensaje = "Error al registrar la adopción: " + e.getMessage();
        } finally {
            try {
                if (conn != null) conn.setAutoCommit(true);
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return mensaje;
    }

    public boolean actualizarEstadoAdopcion(int mascotaAdopcionId, String nuevoEstado) {
        String sql = "UPDATE mascotas_adopcion SET estado = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nuevoEstado);
            ps.setInt(2, mascotaAdopcionId);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<MascotaAdopcion> listarMascotasDisponibles() {
        List<MascotaAdopcion> lista = new ArrayList<>();
        String sql = "SELECT * FROM mascotas_adopcion WHERE estado = 'Disponible'";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                MascotaAdopcion m = new MascotaAdopcion();
                m.setId(rs.getInt("id"));
                m.setMascotaId(rs.getInt("mascota_id"));
                m.setFechaIngreso(rs.getDate("fecha_ingreso"));
                m.setMotivoIngreso(rs.getString("motivo_ingreso"));
                m.setEstado(model.enums.EstadoMascotaAdopcion.valueOf(rs.getString("estado")));
                m.setHistoria(rs.getString("historia"));
                m.setTemperamento(rs.getString("temperamento"));
                m.setNecesidadesEspeciales(rs.getString("necesidades_especiales"));
                m.setFotoAdicionalUrl(rs.getString("foto_adicional_url"));
                lista.add(m);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
    
    public MascotaAdopcion buscarMascotaAdopcionPorId(int id) {
        String sql = "SELECT * FROM mascotas_adopcion WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    MascotaAdopcion m = new MascotaAdopcion();
                    m.setId(rs.getInt("id"));
                    m.setMascotaId(rs.getInt("mascota_id"));
                    m.setFechaIngreso(rs.getDate("fecha_ingreso"));
                    m.setMotivoIngreso(rs.getString("motivo_ingreso"));
                    m.setEstado(model.enums.EstadoMascotaAdopcion.valueOf(rs.getString("estado")));
                    m.setHistoria(rs.getString("historia"));
                    m.setTemperamento(rs.getString("temperamento"));
                    m.setNecesidadesEspeciales(rs.getString("necesidades_especiales"));
                    m.setFotoAdicionalUrl(rs.getString("foto_adicional_url"));
                    return m;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
