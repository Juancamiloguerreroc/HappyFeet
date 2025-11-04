package controller;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import model.entities.Dueno;
import repository.DuenoDAO;

public class DuenoController {
    private final DuenoDAO duenoDAO;
    
    public DuenoController(){
        this.duenoDAO = new DuenoDAO();
    }
    
    public String registrarDueno(Dueno dueno){
        if(dueno == null || dueno.getNombreCompleto() == null || dueno.getDocumentoIdentidad() == null || dueno.getEmail() == null){
            return "Error: Faltan datos obligatorios (Nombre completo, Documento de identidad, Email";
        }
        
        try {
            duenoDAO.crear(dueno);
            return "Dueño registrado exitosamente.";
        } catch (SQLException e) {
            if(e.getSQLState().startsWith("23")){
                return "Error: El documento de identidad o email ya estan registrados. Verifique los datos";
            }
            
            System.out.println("Error en Controller al registrar dueño: "+e.getMessage());
            
            return "Error de base de datos al registrar el dueño. Contacte a soporte";
        }
    }
    
    public Dueno econtrarPorId(String documento){
        if(documento == null || documento.trim().isEmpty()){
            System.out.println("Porfavor ingrese un numero de documento valido.");
            return null;
        }
                
        try{
            return duenoDAO.obtenerPorDocumento(documento);
        }catch(Exception e){
            System.out.println("Error en Controller al buscar dueño: "+e.getMessage());
            return null;
        }
    }
    
    public List<Dueno> obtenerListarDuenos(){
        try {
            return duenoDAO.ListarDuenos();
        } catch (Exception e) {
            System.out.println("Error en Controller al listar dueños: "+e.getMessage());
            return Collections.emptyList();
        }
    }  
    public String actualizarDueno(Dueno dueno){
        if(dueno == null || dueno.getDocumentoIdentidad() == null){
            return "Error: No se puede actualizar un dueño sin documento de identidad.";
        }
        
        try {
            duenoDAO.actualizar(dueno);
            return "Dueño actualizado exitosamente.";
        } catch (Exception e) {
            System.out.println("Error en Controller al actualizar dueño: " + e.getMessage());
            return "Error de base de datos al actualizar el dueño. Contacte a soporte.";
        }
    }
    
    public String eliminarDueno(String documento){
        if(documento == null || documento.trim().isEmpty()){
            return "Error: No se puede eliminar un dueño sin un documento válido.";
        }
        
        try {
            duenoDAO.eliminar(documento);
            return "Dueño eliminado exitosamente.";
        } catch (Exception e) {
            System.out.println("Error en Controller al eliminar dueño: " + e.getMessage());
            return "Error de base de datos al eliminar el dueño. Contacte a soporte.";
        }
    }
}