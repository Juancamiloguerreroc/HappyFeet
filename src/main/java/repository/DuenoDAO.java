package repository;

import ConexionDB.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.entities.Dueno;
import java.util.ArrayList;
import java.util.List;

public class DuenoDAO {
    public void crear(Dueno dueno) throws SQLException{
        String sql = "INSERT INTO duenos (nombre_completo, documento_identidad, direccion, telefono, email, contacto_emergencia, fecha_registro, activo) VALUES (?,?,?,?,?,?,?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){
            
            ps.setString(1, dueno.getNombreCompleto());
            ps.setString(2, dueno.getDocumentoIdentidad());
            ps.setString(3, dueno.getDireccion());
            ps.setString(4, dueno.getTelefono());
            ps.setString(5, dueno.getEmail());
            ps.setString(6, dueno.getContactoEmergencia());
            ps.setTimestamp(7, dueno.getFechaRegistro());
            ps.setBoolean(8,dueno.getActivo());
            ps.executeUpdate();
            
            System.out.println("Dueño agregado con exito");
        } catch (SQLException e) {
            System.out.println("Error al agregar dueño: "+ e.getMessage());
            throw e;
        }
    }
    public List<Dueno> ListarDuenos() throws SQLException{
        List<Dueno> duenos = new ArrayList<>();
        String sql = "SELECT * FROM duenos";
        try (Connection conn = DatabaseConnection.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql)){
            
            while(rs.next()){
                duenos.add(mapearDueno(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar los dueños: "+e.getMessage());
            throw e;
        }
        return duenos;
    }
    
    private Dueno mapearDueno(ResultSet rs) throws SQLException{
        Dueno d = new Dueno();
        d.setId(rs.getInt("id"));
        d.setNombreCompleto(rs.getString("nombre_completo"));
        d.setDocumentoIdentidad(rs.getString("documento_identidad"));
        d.setDireccion(rs.getString("direccion"));
        d.setTelefono(rs.getString("telefono"));
        d.setEmail(rs.getString("email"));
        d.setContactoEmergencia(rs.getString("contacto_emergencia"));
        d.setFechaRegistro(rs.getTimestamp("fecha_registro"));
        d.setActivo(rs.getBoolean("activo"));
        return d;
    }
    
    public Dueno obtenerPorDocumento(String documento) throws SQLException{
        Dueno dueno = null;
        String sql = "SELECT * FROM duenos WHERE documento_identidad = ?";
        
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            
            ps.setString(1, documento);
            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    dueno = mapearDueno(rs);
                }
            }
        } catch (SQLException e){
            System.out.println("Error al obtener dueño por documento DAO: "+e.getMessage());
            throw e;
        }
        return dueno;
    }
    
    public void actualizar(Dueno dueno) throws SQLException {
        String sql = "UPDATE duenos SET nombre_completo = ?, direccion = ?, telefono = ?, email = ?, contacto_emergencia = ?, activo = ? WHERE documento_identidad = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, dueno.getNombreCompleto());
            ps.setString(2, dueno.getDireccion());
            ps.setString(3, dueno.getTelefono());
            ps.setString(4, dueno.getEmail());
            ps.setString(5, dueno.getContactoEmergencia());
            ps.setBoolean(6, dueno.getActivo());
            ps.setString(7, dueno.getDocumentoIdentidad());

            int filasActualizadas = ps.executeUpdate();
            if(filasActualizadas > 0) {
                System.out.println("Dueño actualizado con éxito");
            } else {
                System.out.println("No se encontró un dueño con el documento especificado");
            }

        } catch (SQLException e) {
            System.out.println("Error al actualizar dueño: " + e.getMessage());
            throw e;
        }
    }

    public void eliminar(String documento) throws SQLException {
        String sql = "DELETE FROM duenos WHERE documento_identidad = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, documento);
            int filasEliminadas = ps.executeUpdate();
            if(filasEliminadas > 0) {
                System.out.println("Dueño eliminado con éxito");
            } else {
                System.out.println("No se encontró un dueño con el documento especificado");
            }

        } catch (SQLException e) {
            System.out.println("Error al eliminar dueño: " + e.getMessage());
            throw e;
        }
    }
    
    public Integer obtenerIdPorDocumento(String documentoIdentidad) {
        String sql = "SELECT id FROM duenos WHERE documento_identidad = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, documentoIdentidad);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}