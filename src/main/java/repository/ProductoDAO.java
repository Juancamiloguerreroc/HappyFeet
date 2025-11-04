package repository;

import ConexionDB.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.entities.Producto;

public class ProductoDAO {
    public void crear(Producto producto) throws SQLException{
        String sql = "INSERT INTO producto_tipos (nombre,  descripcion) VALUES (?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){
            
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            
            ps.executeUpdate();
            
            System.out.println("Producto agregado con exito");
        } catch (SQLException e) {
            System.out.println("Error al agregar producto: "+ e.getMessage());
        }
    }
    public List<Producto> ListarMascotas(){
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM mascotas";
        try (Connection conn = DatabaseConnection.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql)){
            
            while(rs.next()){
                Producto p = new Producto();
                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                p.setDescripcion(rs.getString("descripcion"));
                productos.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar los productos: "+e.getMessage());
        }
        return productos;
    }
}
