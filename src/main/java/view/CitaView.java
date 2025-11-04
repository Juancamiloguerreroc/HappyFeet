package view;

import controller.CitaController;
import model.entities.Cita;
import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;

public class CitaView {

    private final CitaController citaController;
    private final Scanner sc;
    private static final String FECHA_HORA_FORMATO = "yyyy-MM-dd HH:mm:ss"; 

    public CitaView() {
        this.citaController = new CitaController();
        this.sc = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n=== GESTIÓN DE CITAS ===");
            System.out.println("1. Registrar nueva cita");
            System.out.println("2. Listar todas las citas");
            System.out.println("3. Buscar cita por ID");
            System.out.println("4. Modificar cita");
            System.out.println("5. Cancelar cita");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            
            opcion = leerEntero();

            switch (opcion) {
                case 1 -> registrarCita();
                case 2 -> listarCitas();
                case 3 -> buscarCitaPorId();
                case 4 -> modificarCita();
                case 5 -> cancelarCita();
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }


    private void registrarCita() {
        System.out.println("\n=== Registrar nueva cita ===");

        System.out.print("ID de la mascota: ");
        int mascotaId = leerEntero();

        System.out.print("ID del veterinario: ");
        int veterinarioId = leerEntero();
        
        String fechaStr;
        Timestamp fechaHora;

        do {
            System.out.print("Fecha y hora (" + FECHA_HORA_FORMATO + "): ");
            fechaStr = sc.nextLine().trim();

            if (citaController.esFechaValida(fechaStr, FECHA_HORA_FORMATO)) {
                fechaHora = Timestamp.valueOf(fechaStr);
                break;
            } else {
                System.out.println("ERROR: Formato de fecha y hora inválido. Intente de nuevo.");
            }
        } while (true);


        System.out.print("Motivo de la cita: ");
        String motivo = sc.nextLine();

        System.out.print("ID del estado inicial: ");
        int estadoId = leerEntero();

        System.out.print("Observaciones: ");
        String observaciones = sc.nextLine();

        Timestamp fechaCreacion = new Timestamp(System.currentTimeMillis());

        Cita cita = new Cita(mascotaId, veterinarioId, fechaHora, motivo, estadoId, observaciones, fechaCreacion);
        String resultado = citaController.registrarCita(cita);
        System.out.println(resultado);
    }

    private void listarCitas() {
        System.out.println("\n=== Lista de todas las citas ===");
        List<Cita> citas = citaController.obtenerListaCitas();

        if (citas.isEmpty()) {
            System.out.println("No hay citas registradas.");
        } else {
            for (Cita c : citas) {
                System.out.println(c);
            }
        }
    }

    private void buscarCitaPorId() {
        System.out.print("\nIngrese el ID de la cita a buscar: ");
        int id = leerEntero();

        Cita cita = citaController.consultarCita(id);

        if (cita != null) {
            System.out.println("Cita encontrada:");
            System.out.println(cita);
        } else {
            System.out.println("No se encontró una cita con el ID especificado.");
        }
    }

    private void modificarCita() {
        System.out.print("\nIngrese el ID de la cita a modificar: ");
        int id = leerEntero();

        Cita citaExistente = citaController.consultarCita(id);
        if (citaExistente == null) {
            System.out.println("No se encontró la cita con ID " + id);
            return;
        }

        System.out.println("Deje en blanco un campo si no desea cambiarlo.");

        System.out.print("Nuevo motivo (actual: " + citaExistente.getMotivo() + "): ");
        String motivo = sc.nextLine();
        if (!motivo.trim().isEmpty()) citaExistente.setMotivo(motivo);

        System.out.print("Nuevas observaciones (actual: " + citaExistente.getObservaciones() + "): ");
        String obs = sc.nextLine();
        if (!obs.trim().isEmpty()) citaExistente.setObservaciones(obs);

        String resultado = citaController.modificarCita(citaExistente);
        System.out.println(resultado);
    }

    private void cancelarCita() {
        System.out.print("\nIngrese el ID de la cita a cancelar: ");
        int id = leerEntero();

        System.out.print("Ingrese el ID del estado de cancelación: ");
        int estadoCancelado = leerEntero();

        String resultado = citaController.cancelarCita(id, estadoCancelado);
        System.out.println(resultado);
    }

    private int leerEntero() {
        while (true) {
            try {
                String input = sc.nextLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Por favor, ingrese un número válido: ");
            }
        }
    }
}