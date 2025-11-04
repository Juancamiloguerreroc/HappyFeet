package controller;

import model.entities.ClubMascotas;
import model.entities.Dueno;
import repository.ClubMascotasDAO;
import repository.DuenoDAO;
import view.ClubMascotasView;
import java.sql.SQLException;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class ClubMascotasController {
    private ClubMascotasDAO clubDAO;
    private DuenoDAO duenoDAO;
    private ClubMascotasView clubView;
    
    public ClubMascotasController() {
        this.clubDAO = new ClubMascotasDAO();
        this.duenoDAO = new DuenoDAO();
        this.clubView = new ClubMascotasView();
    }
    
    public void gestionarClubMascotas() {
        int opcion;
        do {
            opcion = clubView.mostrarMenu();
            switch (opcion) {
                case 1:
                    registrarSocio();
                    break;
                case 2:
                    listarSocios();
                    break;
                case 3:
                    buscarSocioPorDuenoId();
                    break;
                case 4:
                    acumularPuntos();
                    break;
                case 5:
                    canjearPuntos();
                    break;
                case 6:
                    mostrarEstadisticas();
                    break;
                case 7:
                    actualizarNivel();
                    break;
                case 0:
                    clubView.mostrarMensaje("Volviendo al menú principal...");
                    break;
                default:
                    clubView.mostrarError("Opción no válida.");
            }
        } while (opcion != 0);
    }
    
    public void registrarSocio() {
        try {
            List<Dueno> duenos = duenoDAO.ListarDuenos();
            ClubMascotas socio = clubView.capturarDatosRegistro(duenos);
            
            if (socio != null) {
                // Verificar que el dueño no esté ya registrado
                ClubMascotas existente = clubDAO.obtenerPorDuenoId(socio.getDuenoId());
                if (existente != null) {
                    clubView.mostrarError("El dueño ya está registrado en el club.");
                    return;
                }
                
                clubDAO.crear(socio);
                clubView.mostrarExito("Socio registrado exitosamente en el club.");
            }
        } catch (SQLException e) {
            clubView.mostrarError("Error al registrar socio: " + e.getMessage());
        }
    }
    
    public void listarSocios() {
        try {
            List<ClubMascotas> socios = clubDAO.listarTodos();
            clubView.mostrarSociosClub(socios);
        } catch (SQLException e) {
            clubView.mostrarError("Error al listar socios: " + e.getMessage());
        }
    }
    
    public void buscarSocioPorDuenoId() {
        try {
            int duenoId = clubView.pedirIdDueno();
            ClubMascotas socio = clubDAO.obtenerPorDuenoId(duenoId);
            
            if (socio != null) {
                Dueno dueno = duenoDAO.obtenerPorId(duenoId);
                clubView.mostrarSocioDetalle(socio, dueno);
            } else {
                clubView.mostrarError("No se encontró socio con ID dueño: " + duenoId);
            }
        } catch (SQLException e) {
            clubView.mostrarError("Error al buscar socio: " + e.getMessage());
        }
    }
    
    public void acumularPuntos() {
        try {
            int idSocio = clubView.pedirIdSocio();
            ClubMascotas socio = clubDAO.obtenerPorId(idSocio);
            
            if (socio == null) {
                clubView.mostrarError("No se encontró socio con ID: " + idSocio);
                return;
            }
            
            int puntos = clubView.pedirPuntosAcumular();
            String motivo = clubView.pedirMotivoAcumulacion();
            
            if (clubView.confirmarOperacion("acumulación de " + puntos + " puntos")) {
                clubDAO.acumularPuntos(idSocio, puntos);
                
                // Actualizar nivel si es necesario
                actualizarNivelAutomatico(idSocio);
                
                clubView.mostrarTransaccionExitosa("Acumulación", puntos, 
                    socio.getPuntosDisponibles() + puntos);
            }
        } catch (SQLException e) {
            clubView.mostrarError("Error al acumular puntos: " + e.getMessage());
        }
    }
    
    public void canjearPuntos() {
        try {
            int idSocio = clubView.pedirIdSocio();
            ClubMascotas socio = clubDAO.obtenerPorId(idSocio);
            
            if (socio == null) {
                clubView.mostrarError("No se encontró socio con ID: " + idSocio);
                return;
            }
            
            int puntos = clubView.pedirPuntosCanjear();
            String beneficio = clubView.pedirBeneficioCanje();
            
            if (socio.getPuntosDisponibles() < puntos) {
                clubView.mostrarPuntosInsuficientes(socio.getPuntosDisponibles(), puntos);
                return;
            }
            
            if (clubView.confirmarOperacion("canje de " + puntos + " puntos por: " + beneficio)) {
                boolean exito = clubDAO.canjearPuntos(idSocio, puntos);
                
                if (exito) {
                    clubView.mostrarTransaccionExitosa("Canje", puntos, 
                        socio.getPuntosDisponibles() - puntos);
                } else {
                    clubView.mostrarError("Error al canjear puntos.");
                }
            }
        } catch (SQLException e) {
            clubView.mostrarError("Error al canjear puntos: " + e.getMessage());
        }
    }
    
    public void mostrarEstadisticas() {
        try {
            int totalSocios = clubDAO.contarTotalSocios();
            List<ClubMascotas> sociosActivos = clubDAO.obtenerSociosActivos();
            int puntosTotales = clubDAO.obtenerPuntosTotales();
            
            // Calcular distribución por niveles
            Map<String, Integer> sociosPorNivel = new HashMap<>();
            for (ClubMascotas socio : sociosActivos) {
                String nivel = socio.getNivel();
                sociosPorNivel.put(nivel, sociosPorNivel.getOrDefault(nivel, 0) + 1);
            }
            
            clubView.mostrarEstadisticasClub(totalSocios, sociosActivos.size(), 
                puntosTotales, sociosPorNivel);
        } catch (SQLException e) {
            clubView.mostrarError("Error al obtener estadísticas: " + e.getMessage());
        }
    }
    
    public void actualizarNivel() {
        try {
            int idSocio = clubView.pedirIdSocio();
            ClubMascotas socio = clubDAO.obtenerPorId(idSocio);
            
            if (socio == null) {
                clubView.mostrarError("No se encontró socio con ID: " + idSocio);
                return;
            }
            
            String nuevoNivel = clubView.pedirNuevoNivel();
            
            if (clubView.confirmarOperacion("cambio de nivel a " + nuevoNivel)) {
                clubDAO.actualizarNivel(idSocio, nuevoNivel);
                clubView.mostrarExito("Nivel actualizado exitosamente.");
            }
        } catch (SQLException e) {
            clubView.mostrarError("Error al actualizar nivel: " + e.getMessage());
        }
    }
    
    private void actualizarNivelAutomatico(int idSocio) throws SQLException {
        ClubMascotas socio = clubDAO.obtenerPorId(idSocio);
        if (socio == null) return;
        
        int puntos = socio.getPuntosDisponibles();
        String nuevoNivel = calcularNivel(puntos);
        
        if (!nuevoNivel.equals(socio.getNivel())) {
            clubDAO.actualizarNivel(idSocio, nuevoNivel);
            clubView.mostrarMensaje("🎉 ¡Nivel actualizado automáticamente a: " + nuevoNivel + "!");
        }
    }
    
    private String calcularNivel(int puntos) {
        if (puntos >= 6000) return "Platino";
        if (puntos >= 3000) return "Oro";
        if (puntos >= 1000) return "Plata";
        return "Bronce";
    }
    
    // Para usar en el Main
    public ClubMascotasView getView() {
        return clubView;
    }
}