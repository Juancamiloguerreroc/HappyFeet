package repository;

import ConexionDB.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.entities.CanjeBeneficio;
import model.enums.EstadoCajaBeneficio;

public class CanjeBeneficioDAO {

    private Connection conexion;

    public CanjeBeneficioDAO() throws SQLException {
        this.conexion = DatabaseConnection.getConnection();
    }

    // Insertar nuevo canje
    public boolean insertar(CanjeBeneficio canje) throws SQLException {
        String sql = "INSERT INTO canjes_beneficios (club_mascotas_id, beneficio_id, puntos_canjeados, estado, fecha_expiracion, factura_id) " +
                     "VALUES (?,?,?,?,?,?,?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, canje.getClubMascotasId());
            ps.setInt(2, canje.getBeneficioId());
            ps.setInt(3, canje.getPuntosCanjeados());
            ps.setString(4, canje.getEstado().toString());
            ps.setDate(5, canje.getFechaExpiracion());
            if (canje.getFacturaId() != null) {
                ps.setInt(6, canje.getFacturaId());
            } else {
                ps.setNull(6, Types.INTEGER);
            }
            return ps.executeUpdate() > 0;
        }
    }

    // Obtener todos los canjes
    public List<CanjeBeneficio> obtenerTodos() throws SQLException {
        List<CanjeBeneficio> lista = new ArrayList<>();
        String sql = "SELECT * FROM canjes_beneficios ORDER BY fecha_canje DESC";

        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(mapearCanje(rs));
            }
        }
        return lista;
    }

    // Obtener por ID
    public CanjeBeneficio obtenerPorId(int id) throws SQLException {
        String sql = "SELECT * FROM canjes_beneficios WHERE id = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapearCanje(rs);
            }
        }
        return null;
    }

    // Actualizar estado del canje
    public boolean actualizarEstado(int id, String nuevoEstado) throws SQLException {
        String sql = "UPDATE canjes_beneficios SET estado = ? WHERE id = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, nuevoEstado);
            ps.setInt(2, id);
            return ps.executeUpdate() > 0;
        }
    }

    // Eliminar canje
    public boolean eliminar(int id) throws SQLException {
        String sql = "DELETE FROM canjes_beneficios WHERE id = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    // Mapear resultado SQL → Objeto Java
    private CanjeBeneficio mapearCanje(ResultSet rs) throws SQLException {
        CanjeBeneficio canje = new CanjeBeneficio();
        canje.setId(rs.getInt("id"));
        canje.setClubMascotasId(rs.getInt("club_mascotas_id"));
        canje.setBeneficioId(rs.getInt("beneficio_id"));
        canje.setFechaCanje(rs.getTimestamp("fecha_canje"));
        canje.setPuntosCanjeados(rs.getInt("puntos_canjeados"));
        String estadoStr = rs.getString("estado");
        if (estadoStr != null) {
            canje.setEstado(EstadoCajaBeneficio.desdeNombre(estadoStr));
        }

        canje.setFechaExpiracion(rs.getDate("fecha_expiracion"));
        canje.setFacturaId(rs.getObject("factura_id") != null ? rs.getInt("factura_id") : null);
        return canje;
    }
}
