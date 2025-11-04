
package repository;

import ConexionDB.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.entities.Proveedor;

public class ProveedorDAO {
    public void crear(Proveedor proveedor) throws SQLException{
        String sql = "INSERT INTO proveedores (nombre_empresa, contacto, telefono , email, direccion, sitio_web, activo) VALUES (?,?,?,?,?,?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){
            
            ps.setString(1, proveedor.getNombreEmpresa());
            ps.setString(2, proveedor.getContacto());
            ps.setString(3, proveedor.getTelefono());
            ps.setString(4, proveedor.getEmail());
            ps.setString(5, proveedor.getDireccion());
            ps.setString(6, proveedor.getSitioWeb());
            ps.setBoolean(7, proveedor.getActivo());
            
            ps.executeUpdate();
            
            System.out.println("Proveedor agregada con exito");
        } catch (SQLException e) {
            System.out.println("Error al agregar proveedor: "+ e.getMessage());
        }
    }
    public List<Proveedor> ListarMascotas(){
        List<Proveedor> proveedores = new ArrayList<>();
        String sql = "SELECT * FROM proveedores";
        try (Connection conn = DatabaseConnection.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql)){
            
            while(rs.next()){
                Proveedor p = new Proveedor();
                p.setId(rs.getInt("id"));
                p.setNombreEmpresa(rs.getString("nombre_empresa"));
                p.setContacto(rs.getString("contacto"));
                p.setTelefono(rs.getString("telefono"));
                p.setEmail(rs.getString("email"));
                p.setDireccion(rs.getString("direccion"));
                p.setSitioWeb(rs.getString("sitio_web"));
                p.setActivo(rs.getBoolean("activo"));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar los proveedores: "+e.getMessage());
        }
        return proveedores;
    }
}
