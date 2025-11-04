package repository;

import model.entities.ItemFactura;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import model.enums.TipoItemFactura;

public class ItemFacturaDAO {

    private Connection conexion;

    public ItemFacturaDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void insertar(ItemFactura item) throws SQLException {
        String sql = "INSERT INTO item_factura (factura_id, tipo_item, producto_id, servicio_id, servicio_descripcion, cantidad, precio_unitario, subtotal) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, item.getFacturaId());
            ps.setString(2, item.getTipoItem().name());
            if (item.getProductoId() != null) {
                ps.setInt(3, item.getProductoId());
            } else {
                ps.setNull(3, Types.INTEGER);
            }

            if (item.getServicioId() != null) {
                ps.setInt(4, item.getServicioId());
            } else {
                ps.setNull(4, Types.INTEGER);
            }

            ps.setString(5, item.getServicioDescripcion());
            ps.setInt(6, item.getCantidad());
            ps.setDouble(7, item.getPrecioUnitario());
            ps.setDouble(8, item.getSubtotal());
            ps.executeUpdate();
        }
    }

    public void actualizar(ItemFactura item) throws SQLException {
        String sql = "UPDATE item_factura SET factura_id=?, tipo_item=?, producto_id=?, servicio_id=?, servicio_descripcion=?, cantidad=?, precio_unitario=?, subtotal=? WHERE id=?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, item.getFacturaId());
            ps.setString(2, item.getTipoItem().name());
            
            if (item.getProductoId() != null) {
                ps.setInt(3, item.getProductoId());
            } else {
                ps.setNull(3, Types.INTEGER);
            }

            if (item.getServicioId() != null) {
                ps.setInt(4, item.getServicioId());
            } else {
                ps.setNull(4, Types.INTEGER);
            }

            ps.setString(5, item.getServicioDescripcion());
            ps.setInt(6, item.getCantidad());
            ps.setDouble(7, item.getPrecioUnitario());
            ps.setDouble(8, item.getSubtotal());
            ps.setInt(9, item.getId());
            ps.executeUpdate();
        }
    }

    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM item_factura WHERE id=?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public ItemFactura obtenerPorId(int id) throws SQLException {
        String sql = "SELECT * FROM item_factura WHERE id=?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearItem(rs);
                }
            }
        }
        return null;
    }

    public List<ItemFactura> listarTodos() throws SQLException {
        List<ItemFactura> lista = new ArrayList<>();
        String sql = "SELECT * FROM item_factura";
        try (Statement st = conexion.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(mapearItem(rs));
            }
        }
        return lista;
    }

    private ItemFactura mapearItem(ResultSet rs) throws SQLException {
        ItemFactura item = new ItemFactura();
        item.setId(rs.getInt("id"));
        item.setFacturaId(rs.getInt("factura_id"));
        item.setTipoItem(TipoItemFactura.valueOf(rs.getString("tipo_item")));
        int productoId = rs.getInt("producto_id");
        item.setProductoId(rs.wasNull() ? null : productoId);
        int servicioId = rs.getInt("servicio_id");
        item.setServicioId(rs.wasNull() ? null : servicioId);
        item.setServicioDescripcion(rs.getString("servicio_descripcion"));
        item.setCantidad(rs.getInt("cantidad"));
        item.setPrecioUnitario(rs.getDouble("precio_unitario"));
        item.setSubtotal(rs.getDouble("subtotal"));
        return item;
    }
}
