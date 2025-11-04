package view;

import model.entities.ClubMascotas;
import model.entities.Dueno;
import model.utils.FechaUtils;
import java.util.List;
import java.util.Scanner;
import java.sql.Date;
import java.util.Map;

public class ClubMascotasView {
    private Scanner scanner;
    
    public ClubMascotasView() {
        this.scanner = new Scanner(System.in);
    }
    
    public int mostrarMenu() {
        System.out.println("\n" + "─".repeat(50));
        System.out.println("            CLUB DE MASCOTAS FRECUENTES");
        System.out.println("─".repeat(50));
        System.out.println("1. Registrar Socio al Club");
        System.out.println("2. Listar Socios del Club");
        System.out.println("3. Buscar Socio por ID Dueño");
        System.out.println("4. Acumular Puntos");
        System.out.println("5. Canjear Puntos");
        System.out.println("6. Ver Estadísticas de Puntos");
        System.out.println("7. Actualizar Nivel de Socio");
        System.out.println("0. Volver al Menú Principal");
        System.out.println("─".repeat(50));
        System.out.print("Seleccione una opción: ");
        return leerEntero();
    }
    
    public ClubMascotas capturarDatosRegistro(List<Dueno> duenos) {
        System.out.println("\n" + "─".repeat(40));
        System.out.println("        REGISTRO EN CLUB DE MASCOTAS");
        System.out.println("─".repeat(40));
        if (duenos.isEmpty()) {
            System.out.println(" No hay dueños registrados para inscribir al club.");
            return null;
        }
        System.out.println("\nDueños disponibles para inscripción:");
        for (Dueno dueno : duenos) {
            System.out.println("ID: " + dueno.getId() + " - " + dueno.getNombreCompleto());
        }
        int duenoId = leerEntero("\nID del Dueño a inscribir: ");
        System.out.print("¿El dueño ya está inscrito en el club? (s/n): ");
        String respuesta = scanner.nextLine();
        if (respuesta.equalsIgnoreCase("s")) {
            System.out.println("  El dueño ya está registrado en el club.");
            return null;
        }

        String fechaInscripcionStr;
        Date fechaInscripcion;
        do {
            System.out.print("Ingrese fecha de inscripción (" + FechaUtils.FORMATO_FECHA + "): ");
            fechaInscripcionStr = scanner.nextLine().trim();
            if (FechaUtils.esFechaValida(fechaInscripcionStr, FechaUtils.FORMATO_FECHA)) {
                if (FechaUtils.esFechaFutura(fechaInscripcionStr, FechaUtils.FORMATO_FECHA)) {
                    System.out.println("ERROR: La fecha de inscripción no puede ser futura. Intente de nuevo.");
                    continue;
                }
                fechaInscripcion = Date.valueOf(fechaInscripcionStr);
                break;
            } else {
                System.out.println("ERROR: Formato de fecha inválido. Use el formato yyyy-MM-dd.");
            }
        } while (true);

        String fechaActualizacionStr;
        Date fechaActualizacion;
        do {
            System.out.print("Ingrese fecha de última actualización (" + FechaUtils.FORMATO_FECHA + "): ");
            fechaActualizacionStr = scanner.nextLine().trim();
            if (FechaUtils.esFechaValida(fechaActualizacionStr, FechaUtils.FORMATO_FECHA)) {
                if (FechaUtils.esFechaFutura(fechaActualizacionStr, FechaUtils.FORMATO_FECHA)) {
                    System.out.println("ERROR: La fecha no puede ser futura. Intente de nuevo.");
                    continue;
                }
                fechaActualizacion = Date.valueOf(fechaActualizacionStr);
                break;
            } else {
                System.out.println("ERROR: Formato de fecha inválido. Use el formato yyyy-MM-dd.");
            }
        } while (true);

        String nivel = "Bronce";
        int puntosIniciales = 100;
        System.out.println(" ¡Bienvenido al Club de Mascotas!");
        System.out.println(" Puntos de bienvenida: " + puntosIniciales);
        System.out.println(" Nivel inicial: " + nivel);
        return new ClubMascotas(duenoId, puntosIniciales, 0, puntosIniciales, nivel, fechaInscripcion, fechaActualizacion, true);
    }
    
    public void mostrarSociosClub(List<ClubMascotas> socios) {
        System.out.println("\n" + "─".repeat(100));
        System.out.println("                                   SOCIOS DEL CLUB DE MASCOTAS");
        System.out.println("─".repeat(100));
        if (socios.isEmpty()) {
            System.out.println("No hay socios registrados en el club.");
        } else {
            System.out.printf("%-4s %-12s %-15s %-15s %-15s %-12s %-10s%n", 
                "ID", "Dueño ID", "Puntos Acum.", "Puntos Canj.", "Puntos Disp.", "Nivel", "Activo");
            System.out.println("─".repeat(100));
            for (ClubMascotas socio : socios) {
                System.out.printf("%-4d %-12d %-15d %-15d %-15d %-12s %-10s%n",
                    socio.getId(),
                    socio.getDuenoId(),
                    socio.getPuntosAcumulados(),
                    socio.getPuntosCanjeados(),
                    socio.getPuntosDisponibles(),
                    socio.getNivel(),
                    socio.isActivo() ? "Sí" : "No"
                );
            }
        }
        System.out.println("─".repeat(100));
    }
    
    public void mostrarSocioDetalle(ClubMascotas socio, Dueno dueno) {
        if (socio == null) {
            System.out.println(" Socio no encontrado.");
            return;
        }
        System.out.println("\n" + "═".repeat(60));
        System.out.println("            DETALLE DE SOCIO - CLUB DE MASCOTAS");
        System.out.println("═".repeat(60));
        if (dueno != null) {
            System.out.println(" Dueño: " + dueno.getNombreCompleto());
            System.out.println(" Email: " + dueno.getEmail());
            System.out.println(" Teléfono: " + dueno.getTelefono());
        }
        System.out.println("ID Socio: " + socio.getId());
        System.out.println("ID Dueño: " + socio.getDuenoId());
        System.out.println("Puntos Acumulados: " + socio.getPuntosAcumulados());
        System.out.println("Puntos Canjeados: " + socio.getPuntosCanjeados());
        System.out.println("Puntos Disponibles: " + socio.getPuntosDisponibles());
        System.out.println("Nivel: " + socio.getNivel());
        System.out.println("Fecha Inscripción: " + socio.getFechaInscripcion());
        System.out.println("Última Actualización: " + socio.getFechaUltimaActualizacion());
        System.out.println("Activo: " + (socio.isActivo() ? "Sí" : "No"));
        System.out.println("═".repeat(60));
    }
    
    public int pedirIdDueno() {
        return leerEntero("Ingrese ID del dueño: ");
    }
    
    public int pedirIdSocio() {
        return leerEntero("Ingrese ID del socio: ");
    }
    
    public int pedirPuntosAcumular() {
        return leerEntero("Puntos a acumular: ");
    }
    
    public int pedirPuntosCanjear() {
        return leerEntero("Puntos a canjear: ");
    }
    
    public String pedirMotivoAcumulacion() {
        System.out.print("Motivo de acumulación (compra/servicio/otros): ");
        return scanner.nextLine();
    }
    
    public String pedirBeneficioCanje() {
        System.out.print("Beneficio a canjear: ");
        return scanner.nextLine();
    }
    
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
    
    public void mostrarError(String mensaje) {
        System.out.println(" ERROR: " + mensaje);
    }
    
    public void mostrarExito(String mensaje) {
        System.out.println(" " + mensaje);
    }
    
    public void mostrarPuntosInsuficientes(int puntosDisponibles, int puntosRequeridos) {
        System.out.println(" Puntos insuficientes:");
        System.out.println("   Puntos disponibles: " + puntosDisponibles);
        System.out.println("   Puntos requeridos: " + puntosRequeridos);
        System.out.println("   Faltan: " + (puntosRequeridos - puntosDisponibles) + " puntos");
    }
    
    public void mostrarTransaccionExitosa(String tipo, int puntos, int nuevoSaldo) {
        System.out.println(" Transacción exitosa:");
        System.out.println("   Tipo: " + tipo);
        System.out.println("   Puntos: " + puntos);
        System.out.println("   Nuevo saldo: " + nuevoSaldo + " puntos");
    }
    
    public void mostrarEstadisticasClub(int totalSocios, int sociosActivos, int puntosTotales, Map<String, Integer> sociosPorNivel) {
        System.out.println("\n" + "".repeat(60));
        System.out.println("                       ESTADÍSTICAS DEL CLUB DE MASCOTAS");
        System.out.println("".repeat(60));
        System.out.println(" Total de socios: " + totalSocios);
        System.out.println(" Socios activos: " + sociosActivos);
        System.out.println(" Puntos totales en circulación: " + puntosTotales);
        if (!sociosPorNivel.isEmpty()) {
            System.out.println("\n🏆 Distribución por niveles:");
            sociosPorNivel.forEach((nivel, cantidad) -> {
                System.out.println("   " + nivel + ": " + cantidad + " socios");
            });
        }
        System.out.println("".repeat(60));
    }
    
    public String pedirNuevoNivel() {
        System.out.println("\nSeleccione el nuevo nivel:");
        System.out.println("1. Bronce (0-999 puntos)");
        System.out.println("2. Plata (1000-2999 puntos)");
        System.out.println("3. Oro (3000-5999 puntos)");
        System.out.println("4. Platino (6000+ puntos)");
        int opcion = leerEntero("Opción (1-4): ");
        return switch (opcion) {
            case 1 -> "Bronce";
            case 2 -> "Plata";
            case 3 -> "Oro";
            case 4 -> "Platino";
            default -> "Bronce";
        };
    }
    
    public boolean confirmarOperacion(String operacion) {
        System.out.print("¿Confirmar " + operacion + "? (s/n): ");
        String respuesta = scanner.nextLine();
        return respuesta.equalsIgnoreCase("s");
    }
    
    private int leerEntero() {
        int valor = scanner.nextInt();
        scanner.nextLine();
        return valor;
    }
    
    private int leerEntero(String mensaje) {
        System.out.print(mensaje);
        return leerEntero();
    }
}
