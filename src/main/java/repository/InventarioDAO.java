package repository;

import ConexionDB.DatabaseConnection;
import model.entities.Inventario;
import model.entities.MovimientoInventario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventarioDAO {

    public InventarioDAO() {}

    public List<Inventario> obtenerTodos() {
        List<Inventario> lista = new ArrayList<>();
        String sql = "SELECT * FROM inventario";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(mapInventario(rs));
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al obtener inventario: " + e.getMessage());
        }
        return lista;
    }

    public Inventario obtenerPorId(int id) {
        String sql = "SELECT * FROM inventario WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapInventario(rs);

        } catch (SQLException e) {
            System.out.println("❌ Error al obtener inventario por ID: " + e.getMessage());
        }
        return null;
    }

    public boolean agregarInventario(Inventario inv) {
        String sql = """
            INSERT INTO inventario 
            (nombre_producto, producto_tipo_id, descripcion, fabricante, proveedor_id, lote,
            cantidad_stock, stock_minimo, unidad_medida, fecha_vencimiento, precio_compra,
            precio_venta, requiere_receta, activo, fecha_registro)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, inv.getNombreProducto());
            ps.setInt(2, inv.getProductoTipoId());
            ps.setString(3, inv.getDescripcion());
            ps.setString(4, inv.getFabricante());
            ps.setObject(5, inv.getProveedorId(), Types.INTEGER);
            ps.setString(6, inv.getLote());
            ps.setInt(7, inv.getCantidadStock());
            ps.setInt(8, inv.getStockMinimo());
            ps.setString(9, inv.getUnidadMedida());
            ps.setDate(10, inv.getFechaVencimiento());
            ps.setDouble(11, inv.getPrecioCompra());
            ps.setDouble(12, inv.getPrecioVenta());
            ps.setBoolean(13, inv.isRequiereReceta());
            ps.setBoolean(14, inv.isActivo());
            ps.setTimestamp(15, inv.getFechaRegistro());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("❌ Error al agregar inventario: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizarInventario(Inventario inv) {
        String sql = """
            UPDATE inventario SET 
                nombre_producto=?, producto_tipo_id=?, descripcion=?, fabricante=?, 
                proveedor_id=?, lote=?, cantidad_stock=?, stock_minimo=?, unidad_medida=?, 
                fecha_vencimiento=?, precio_compra=?, precio_venta=?, 
                requiere_receta=?, activo=? 
            WHERE id=?
        """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, inv.getNombreProducto());
            ps.setInt(2, inv.getProductoTipoId());
            ps.setString(3, inv.getDescripcion());
            ps.setString(4, inv.getFabricante());
            ps.setObject(5, inv.getProveedorId(), Types.INTEGER);
            ps.setString(6, inv.getLote());
            ps.setInt(7, inv.getCantidadStock());
            ps.setInt(8, inv.getStockMinimo());
            ps.setString(9, inv.getUnidadMedida());
            ps.setDate(10, inv.getFechaVencimiento());
            ps.setDouble(11, inv.getPrecioCompra());
            ps.setDouble(12, inv.getPrecioVenta());
            ps.setBoolean(13, inv.isRequiereReceta());
            ps.setBoolean(14, inv.isActivo());
            ps.setInt(15, inv.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar inventario: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarInventario(int id) {
        String sql = "DELETE FROM inventario WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar inventario: " + e.getMessage());
            return false;
        }
    }

    public boolean registrarMovimiento(MovimientoInventario mov) {
        String sql = """
            INSERT INTO movimiento_inventario 
            (producto_id, tipo_movimiento, cantidad, stock_anterior, stock_nuevo, motivo, 
            referencia_consulta_id, referencia_procedimiento_id, usuario, fecha_movimiento)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, mov.getProductoId());
            ps.setString(2, mov.getTipoMovimiento());
            ps.setInt(3, mov.getCantidad());
            ps.setInt(4, mov.getStockAnterior());
            ps.setInt(5, mov.getStockNuevo());
            ps.setString(6, mov.getMotivo());
            ps.setObject(7, mov.getReferenciaConsultaId(), Types.INTEGER);
            ps.setObject(8, mov.getReferenciaProcedimientoId(), Types.INTEGER);
            ps.setString(9, mov.getUsuario());
            ps.setDate(10, mov.getFechaMovimiento());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("❌ Error al registrar movimiento: " + e.getMessage());
            return false;
        }
    }

    public List<MovimientoInventario> obtenerMovimientosPorProducto(int productoId) {
        List<MovimientoInventario> lista = new ArrayList<>();
        String sql = "SELECT * FROM movimiento_inventario WHERE producto_id = ? ORDER BY fecha_movimiento DESC";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, productoId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                MovimientoInventario mov = new MovimientoInventario();
                mov.setId(rs.getInt("id"));
                mov.setProductoId(rs.getInt("producto_id"));
                mov.setTipoMovimiento(rs.getString("tipo_movimiento"));
                mov.setCantidad(rs.getInt("cantidad"));
                mov.setStockAnterior(rs.getInt("stock_anterior"));
                mov.setStockNuevo(rs.getInt("stock_nuevo"));
                mov.setMotivo(rs.getString("motivo"));
                mov.setReferenciaConsultaId(rs.getInt("referencia_consulta_id"));
                mov.setReferenciaProcedimientoId(rs.getInt("referencia_procedimiento_id"));
                mov.setUsuario(rs.getString("usuario"));
                mov.setFechaMovimiento(rs.getDate("fecha_movimiento"));
                lista.add(mov);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al obtener movimientos: " + e.getMessage());
        }
        return lista;
    }

    private Inventario mapInventario(ResultSet rs) throws SQLException {
        Inventario inv = new Inventario();
        inv.setId(rs.getInt("id"));
        inv.setNombreProducto(rs.getString("nombre_producto"));
        inv.setProductoTipoId(rs.getInt("producto_tipo_id"));
        inv.setDescripcion(rs.getString("descripcion"));
        inv.setFabricante(rs.getString("fabricante"));
        inv.setProveedorId(rs.getInt("proveedor_id"));
        inv.setLote(rs.getString("lote"));
        inv.setCantidadStock(rs.getInt("cantidad_stock"));
        inv.setStockMinimo(rs.getInt("stock_minimo"));
        inv.setUnidadMedida(rs.getString("unidad_medida"));
        inv.setFechaVencimiento(rs.getDate("fecha_vencimiento"));
        inv.setPrecioCompra(rs.getDouble("precio_compra"));
        inv.setPrecioVenta(rs.getDouble("precio_venta"));
        inv.setRequiereReceta(rs.getBoolean("requiere_receta"));
        inv.setActivo(rs.getBoolean("activo"));
        inv.setFechaRegistro(rs.getTimestamp("fecha_registro"));
        return inv;
    }
    
    public Inventario obtenerVacunaPorId(int id) {
        String sql = "SELECT * FROM inventario WHERE producto_tipo_id = 1 AND id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapInventario(rs);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener vacuna por ID: " + e.getMessage());
        }
        return null;
    }

}