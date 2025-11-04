package repository;

import ConexionDB.DatabaseConnection;
import model.entities.ProcedimientoEspecial;
import model.entities.InsumoProcedimiento;
import model.enums.EstadoProcedimiento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProcedimientoEspecialDAO {


    public void crearProcedimiento(ProcedimientoEspecial procedimiento) throws SQLException {
        String sql = "INSERT INTO procedimientos_especiales (mascota_id, veterinario_id, tipo_procedimiento, nombre_procedimiento, fecha_hora, duracion_estimada_minutos, " +
                "informacion_preoperatoria, detalle_procedimiento, complicaciones, seguimiento_postoperatorio, proximo_control, estado, costo_procedimiento) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, procedimiento.getMascotaId());
            ps.setInt(2, procedimiento.getVeterinarioId());
            ps.setString(3, procedimiento.getTipoProcedimiento());
            ps.setString(4, procedimiento.getNombreProcedimiento());
            ps.setTimestamp(5, procedimiento.getFechaHora());
            ps.setObject(6, procedimiento.getDuracionEstimadaMinutos(), Types.INTEGER);
            ps.setString(7, procedimiento.getInformacionPreoperatoria());
            ps.setString(8, procedimiento.getDetalleProcedimiento());
            ps.setString(9, procedimiento.getComplicaciones());
            ps.setString(10, procedimiento.getSeguimientoPostoperatorio());
            ps.setDate(11, procedimiento.getProximoControl());
            ps.setString(12, procedimiento.getEstado().name());
            ps.setDouble(13, procedimiento.getCostoProcedimiento());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    procedimiento.setId(rs.getInt(1));
                }
            }

            System.out.println("Procedimiento registrado con éxito");
        } catch (SQLException e) {
            System.err.println("Error al crear procedimiento: " + e.getMessage());
            throw e;
        }
    }

    public List<ProcedimientoEspecial> listarProcedimientos() throws SQLException {
        List<ProcedimientoEspecial> lista = new ArrayList<>();
        String sql = "SELECT * FROM procedimientos_especiales";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(mapearProcedimiento(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar procedimientos: " + e.getMessage());
            throw e;
        }

        return lista;
    }

    public ProcedimientoEspecial obtenerPorId(int id) throws SQLException {
        ProcedimientoEspecial procedimiento = null;
        String sql = "SELECT * FROM procedimientos_especiales WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    procedimiento = mapearProcedimiento(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener procedimiento por ID: " + e.getMessage());
            throw e;
        }

        return procedimiento;
    }

    public void actualizarProcedimiento(ProcedimientoEspecial procedimiento) throws SQLException {
        String sql = "UPDATE procedimientos_especiales SET mascota_id=?, veterinario_id=?, tipo_procedimiento=?, nombre_procedimiento=?, fecha_hora=?, " +
                "duracion_estimada_minutos=?, informacion_preoperatoria=?, detalle_procedimiento=?, complicaciones=?, seguimiento_postoperatorio=?, " +
                "proximo_control=?, estado=?, costo_procedimiento=? WHERE id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, procedimiento.getMascotaId());
            ps.setInt(2, procedimiento.getVeterinarioId());
            ps.setString(3, procedimiento.getTipoProcedimiento());
            ps.setString(4, procedimiento.getNombreProcedimiento());
            ps.setTimestamp(5, procedimiento.getFechaHora());
            ps.setObject(6, procedimiento.getDuracionEstimadaMinutos(), Types.INTEGER);
            ps.setString(7, procedimiento.getInformacionPreoperatoria());
            ps.setString(8, procedimiento.getDetalleProcedimiento());
            ps.setString(9, procedimiento.getComplicaciones());
            ps.setString(10, procedimiento.getSeguimientoPostoperatorio());
            ps.setDate(11, procedimiento.getProximoControl());
            ps.setString(12, procedimiento.getEstado().name().replace("_", " "));
            ps.setDouble(13, procedimiento.getCostoProcedimiento());
            ps.setInt(14, procedimiento.getId());

            ps.executeUpdate();
            System.out.println("Procedimiento actualizado correctamente");
        } catch (SQLException e) {
            System.err.println("Error al actualizar procedimiento: " + e.getMessage());
            throw e;
        }
    }

    public void eliminarProcedimiento(int id) throws SQLException {
        String sql = "DELETE FROM procedimientos_especiales WHERE id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Procedimiento eliminado correctamente");
        } catch (SQLException e) {
            System.err.println("Error al eliminar procedimiento: " + e.getMessage());
            throw e;
        }
    }


    public void agregarInsumo(InsumoProcedimiento insumo) throws SQLException {
        String sql = "INSERT INTO insumos_procedimientos (procedimiento_id, producto_id, cantidad_usada, observaciones) VALUES (?,?,?,?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, insumo.getProcedimientoId());
            ps.setInt(2, insumo.getProductoId());
            ps.setInt(3, insumo.getCantidadUsada());
            ps.setString(4, insumo.getObservaciones());

            ps.executeUpdate();
            System.out.println("Insumo agregado correctamente");
        } catch (SQLException e) {
            System.err.println("Error al agregar insumo: " + e.getMessage());
            throw e;
        }
    }

    public List<InsumoProcedimiento> listarInsumosPorProcedimiento(int procedimientoId) throws SQLException {
        List<InsumoProcedimiento> lista = new ArrayList<>();
        String sql = "SELECT * FROM insumos_procedimientos WHERE procedimiento_id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, procedimientoId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    InsumoProcedimiento insumo = new InsumoProcedimiento();
                    insumo.setId(rs.getInt("id"));
                    insumo.setProcedimientoId(rs.getInt("procedimiento_id"));
                    insumo.setProductoId(rs.getInt("producto_id"));
                    insumo.setCantidadUsada(rs.getInt("cantidad_usada"));
                    insumo.setObservaciones(rs.getString("observaciones"));
                    lista.add(insumo);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al listar insumos: " + e.getMessage());
            throw e;
        }

        return lista;
    }

    public void eliminarInsumo(int id) throws SQLException {
        String sql = "DELETE FROM insumos_procedimientos WHERE id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Insumo eliminado correctamente");
        } catch (SQLException e) {
            System.err.println("Error al eliminar insumo: " + e.getMessage());
            throw e;
        }
    }

    private ProcedimientoEspecial mapearProcedimiento(ResultSet rs) throws SQLException {
        ProcedimientoEspecial p = new ProcedimientoEspecial();
        p.setId(rs.getInt("id"));
        p.setMascotaId(rs.getInt("mascota_id"));
        p.setVeterinarioId(rs.getInt("veterinario_id"));
        p.setTipoProcedimiento(rs.getString("tipo_procedimiento"));
        p.setNombreProcedimiento(rs.getString("nombre_procedimiento"));
        p.setFechaHora(rs.getTimestamp("fecha_hora"));
        p.setDuracionEstimadaMinutos((Integer) rs.getObject("duracion_estimada_minutos"));
        p.setInformacionPreoperatoria(rs.getString("informacion_preoperatoria"));
        p.setDetalleProcedimiento(rs.getString("detalle_procedimiento"));
        p.setComplicaciones(rs.getString("complicaciones"));
        p.setSeguimientoPostoperatorio(rs.getString("seguimiento_postoperatorio"));
        p.setProximoControl(rs.getDate("proximo_control"));
        p.setEstado(EstadoProcedimiento.valueOf(rs.getString("estado")));
        p.setCostoProcedimiento(rs.getDouble("costo_procedimiento"));
        return p;
    }
}
