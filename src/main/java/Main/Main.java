package Main;

import view.*;
import java.util.Scanner;

public class Main {

    private final Scanner scanner = new Scanner(System.in);

    private final CitaView citaView = new CitaView();
    private final MascotaView mascotaView = new MascotaView();
    private final VeterinarioView veterinarioView = new VeterinarioView();
    private final DuenoView duenoView = new DuenoView();
    private final ServicioView serviciosView = new ServicioView();
    private final ProcedimientoEspecialView procedimientoView = new ProcedimientoEspecialView();
    private final InventarioView inventarioView = new InventarioView();
    private final AdopcionView adopcionView = new AdopcionView();
    private final VacunacionView jornadaView = new VacunacionView();
    private final ClubMascotasView clubView = new ClubMascotasView();
    private final FacturaView facturaView = new FacturaView();

    public void iniciar() {
        int opcion;

        do {
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Gestionar Citas");
            System.out.println("2. Gestionar Mascotas");
            System.out.println("3. Gestionar Veterinarios");
            System.out.println("4. Gestionar Dueños");
            System.out.println("5. Gestionar Servicios");
            System.out.println("6. Gestionar Procedimientos Especiales");
            System.out.println("7. Gestionar Inventario");
            System.out.println("8. Gestionar Adopciones");
            System.out.println("9. Gestionar Jornadas de Vacunación");
            System.out.println("10. Gestionar Club de Mascotas");
            System.out.println("11. Gestionar Facturas");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = leerEntero();

            switch (opcion) {
                case 1 -> citaView.mostrarMenu();
                case 2 -> mascotaView.mostrarMenu();
                case 3 -> veterinarioView.mostrarMenu();
                case 4 -> duenoView.mostrarMenu();
                case 5 -> serviciosView.mostrarMenu();
                case 6 -> procedimientoView.mostrarMenu();
                case 7 -> inventarioView.mostrarMenu();
                case 8 -> adopcionView.mostrarMenu();
                case 9 -> jornadaView.mostrarMenu();
/*                 case 10 -> clubView.mostrarMenu();
                case 11 -> facturaView.mostrarMenu();*/
                case 0 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private int leerEntero() {
        while (!scanner.hasNextInt()) {
            System.out.print("Por favor, ingrese un número válido: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    public static void main(String[] args) {
        new Main().iniciar();
    }
}
