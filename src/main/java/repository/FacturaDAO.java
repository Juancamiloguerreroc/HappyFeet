package repository;

import ConexionDB.DatabaseConnection;
import model.entities.Factura;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FacturaDAO {
    public void crear(Factura factura) throws SQLException{
        String sql = "INSERT INTO facturas (dueno_id, numero_factura, fecha_emision,subtotal, impuesto, descuento, total, metodo_pago, estado, observaciones) VALUES (?,?,?,?,?,?,?,?,?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){
            
            ps.setInt(1, factura.getDuenoId());
            ps.setString(2, factura.getNumeroFactura());
            ps.setDate(3, factura.getFechaEmision());
            ps.setDouble(4, factura.getSubtotal());
            ps.setDouble(5, factura.getImpuesto());
            ps.setDouble(6, factura.getDescuento());
            ps.setDouble(7, factura.getTotal());
            ps.setString(8, factura.getMetodoPago());
            ps.setString(9, factura.getEstado());
            ps.setString(10, factura.getObservaciones());
            
            ps.executeUpdate();
            
            System.out.println("Factura agregada con exito");
        } catch (SQLException e) {
            System.out.println("Error al agregar factura: "+ e.getMessage());
        }
    }
    public List<Factura> ListarDuenos(){
        List<Factura> facturas = new ArrayList<>();
        String sql = "SELECT * FROM facturas";
        try (Connection conn = DatabaseConnection.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql)){
            
            while(rs.next()){
                Factura f = new Factura();
                f.setId(rs.getInt("id"));
                f.setDuenoId(rs.getInt("dueno_id"));
                f.setNumeroFactura(rs.getString("numero_factura"));
                f.setFechaEmision(rs.getDate("fecha_emision"));
                f.setSubtotal(rs.getDouble("subtotal"));
                f.setImpuesto(rs.getDouble("impuesto"));
                f.setDescuento(rs.getDouble("descuento"));
                f.setTotal(rs.getDouble("total"));
                f.setMetodoPago(rs.getString("metodo_pago"));
                f.setEstado(rs.getString("estado"));
                f.setObservaciones(rs.getString("observaciones"));
                facturas.add(f);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar los dueños: "+e.getMessage());
        }
        return facturas;
    }
}
