package repository;

import ConexionDB.DatabaseConnection;
import model.entities.Mascota;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.entities.Especie;
import model.entities.MascotaAdopcion;
import model.entities.Raza;
import model.enums.EstadoMascotaAdopcion;
import model.enums.SexoMascota;

public class MascotaDAO {
    public int crear(Mascota mascota) throws SQLException {
    String sql = "INSERT INTO mascotas (dueno_id, raza_id, nombre, fecha_nacimiento, sexo, peso_actual, microchip, tatuaje, url_foto, alergias, condiciones_preexistentes, activo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

        ps.setInt(1, mascota.getDuenoId());
        ps.setInt(2, mascota.getRazaId());
        ps.setString(3, mascota.getNombre());
        ps.setDate(4, mascota.getFechaNacimiento());
        ps.setString(5, mascota.getSexo().toString());
        ps.setDouble(6, mascota.getPesoActual());
        ps.setString(7, mascota.getMicrochip());
        ps.setString(8, mascota.getTatuaje());
        ps.setString(9, mascota.getUrlFoto());
        ps.setString(10, mascota.getAlergias());
        ps.setString(11, mascota.getCondicionesPreexistentes());
        ps.setBoolean(12, mascota.getActivo());

        ps.executeUpdate();

        try (ResultSet rs = ps.getGeneratedKeys()) {
            if (rs.next()) {
                int idGenerado = rs.getInt(1);
                mascota.setId(idGenerado);
                return idGenerado;
            }
        }
    }
    return -1;
}

    
    public List<Mascota> ListarMascotas(){
        List<Mascota> mascotas = new ArrayList<>();
        String sql = "SELECT * FROM mascotas";
        try (Connection conn = DatabaseConnection.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql)){
            
            while(rs.next()){
                Mascota m = new Mascota();
                m.setId(rs.getInt("id"));
                m.setDuenoId(rs.getInt("dueno_id"));
                m.setRazaId(rs.getInt("raza_id"));
                m.setNombre(rs.getString("nombre"));
                m.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                m.setSexo(SexoMascota.valueOf(rs.getString("sexo")));
                m.setPesoActual(rs.getDouble("peso_actual"));
                m.setMicrochip(rs.getString("microchip"));
                m.setTatuaje(rs.getString("tatuaje"));
                m.setUrlFoto(rs.getString("url_foto"));
                m.setAlergias(rs.getString("alergias"));
                m.setCondicionesPreexistentes(rs.getString("condiciones_preexistentes"));
                m.setFechaRegistro(rs.getTimestamp("fecha_registro"));
                m.setActivo(rs.getBoolean("activo"));
                mascotas.add(m);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar las mascotas: "+e.getMessage());
        }
        return mascotas;
    }
    
    public Mascota buscarMascotaPorId(int id) {
        String sql = "SELECT * FROM mascotas WHERE id = ?";
        Mascota mascota = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    mascota = new Mascota();
                    mascota.setId(rs.getInt("id"));
                    mascota.setDuenoId(rs.getInt("dueno_id"));
                    mascota.setNombre(rs.getString("nombre"));
                    mascota.setRazaId(rs.getInt("raza_id"));
                    mascota.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                    mascota.setSexo(SexoMascota.valueOf(rs.getString("sexo")));
                    mascota.setPesoActual(rs.getDouble("peso_actual"));
                    mascota.setMicrochip(rs.getString("microchip"));
                    mascota.setTatuaje(rs.getString("tatuaje"));
                    mascota.setUrlFoto(rs.getString("url_foto"));
                    mascota.setAlergias(rs.getString("alergias"));
                    mascota.setCondicionesPreexistentes(rs.getString("condiciones_preexistentes"));
                    mascota.setFechaRegistro(rs.getTimestamp("fecha_registro"));
                    mascota.setActivo(rs.getBoolean("activo"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar mascota por ID: " + e.getMessage());
        }
        return mascota;
    }
    
    public boolean modificarMascota(Mascota mascota) throws SQLException {
        String sql = "UPDATE mascotas SET " +
                "dueno_id = ?, nombre = ?, raza_id = ?, fecha_nacimiento = ?, " +
                "sexo = ?, peso_actual = ?, microchip = ?, tatuaje = ?, url_foto = ?, " +
                "alergias = ?, condiciones_preexistentes = ?, activo = ? " +
                "WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, mascota.getDuenoId());
            ps.setString(2, mascota.getNombre());
            ps.setInt(3, mascota.getRazaId());
            ps.setDate(4, mascota.getFechaNacimiento());
            ps.setString(5, mascota.getSexo());
            ps.setDouble(6, mascota.getPesoActual());
            ps.setString(7, mascota.getMicrochip());
            ps.setString(8, mascota.getTatuaje());
            ps.setString(9, mascota.getUrlFoto());
            ps.setString(10, mascota.getAlergias());
            ps.setString(11, mascota.getCondicionesPreexistentes());
            ps.setBoolean(12, mascota.getActivo());
            ps.setInt(13, mascota.getId());

            int filasActualizadas = ps.executeUpdate();
            return filasActualizadas > 0;
        }
    }
    
    public boolean eliminarMascota(int id) throws SQLException {
        String sql = "DELETE FROM mascotas WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            return filas > 0;
        }
    }
    public List<Especie> listarEspecies() {
        List<Especie> especies = new ArrayList<>();
        String sql = "SELECT id, nombre, descripcion FROM especies";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Especie e = new Especie();
                e.setId(rs.getInt("id"));
                e.setNombre(rs.getString("nombre"));
                e.setDescripcion(rs.getString("descripcion"));
                especies.add(e);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return especies;
    }

    public List<Raza> listarRazasPorEspecie(int especieId) {
        List<Raza> razas = new ArrayList<>();
        String sql = "SELECT id, especie_id, nombre, caracteristicas FROM razas WHERE especie_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, especieId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Raza r = new Raza();
                    r.setId(rs.getInt("id"));
                    r.setEspecieId(rs.getInt("especie_id"));
                    r.setNombre(rs.getString("nombre"));
                    r.setCaracteristicas(rs.getString("caracteristicas"));
                    razas.add(r);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return razas;
    }
    
    public String crearRaza(int especieId, String nombre, String caracteristicas) {
        String sql = """
            INSERT INTO razas (especie_id, nombre, caracteristicas)
            VALUES (?, ?, ?)
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, especieId);
            ps.setString(2, nombre);
            ps.setString(3, caracteristicas);

            int filas = ps.executeUpdate();

            if (filas > 0) {
                return "Raza registrada correctamente en la base de datos.";
            } else {
                return "No se pudo registrar la raza. Verifique los datos.";
            }

        } catch (SQLException e) {
            if (e.getSQLState() != null && e.getSQLState().startsWith("23")) {
                return "❌ Error: Ya existe una raza con ese nombre para la especie seleccionada.";
            }
            e.printStackTrace();
            return "Error al registrar la raza: " + e.getMessage();
        }
    }
    
    public MascotaAdopcion buscarMascotaAdopcionPorId(int id) {
        String sql = """
            SELECT id, mascota_id, fecha_ingreso, motivo_ingreso, estado,
                   historia, temperamento, necesidades_especiales, foto_adicional_url
            FROM mascotas_adopcion
            WHERE id = ?
        """;

        MascotaAdopcion mascotaAdopcion = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    mascotaAdopcion = new MascotaAdopcion();
                    mascotaAdopcion.setId(rs.getInt("id"));
                    mascotaAdopcion.setMascotaId(rs.getInt("mascota_id"));
                    mascotaAdopcion.setFechaIngreso(rs.getDate("fecha_ingreso"));
                    mascotaAdopcion.setMotivoIngreso(rs.getString("motivo_ingreso"));
                    mascotaAdopcion.setEstado(EstadoMascotaAdopcion.valueOf(rs.getString("estado").replace(" ", "_")));
                    mascotaAdopcion.setHistoria(rs.getString("historia"));
                    mascotaAdopcion.setTemperamento(rs.getString("temperamento"));
                    mascotaAdopcion.setNecesidadesEspeciales(rs.getString("necesidades_especiales"));
                    mascotaAdopcion.setFotoAdicionalUrl(rs.getString("foto_adicional_url"));
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar la mascota en adopción por ID: " + e.getMessage());
        }
        return mascotaAdopcion;
    }
    
    public boolean actualizarEstadoMascotaAdopcion(int idMascotaAdopcion, EstadoMascotaAdopcion nuevoEstado) throws SQLException {
        String sql = "UPDATE mascotas_adopcion SET estado = ? WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, nuevoEstado.name().replace("_", " "));
            ps.setInt(2, idMascotaAdopcion);
            
            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            throw new SQLException("Error al actualizar estado de la mascota en adopción: " + e.getMessage(), e);
        }
    }
    
    public boolean actualizarDuenoMascota(int mascotaId, int nuevoDuenoId) throws SQLException {
        String sql = "UPDATE mascotas SET dueno_id = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, nuevoDuenoId);
            ps.setInt(2, mascotaId);

            int filas = ps.executeUpdate();
            return filas > 0;
        }
    }

}
