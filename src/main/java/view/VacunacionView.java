package view;

import controller.VacunacionController;
import model.entities.JornadaVacunacion;
import model.entities.RegistroJornadaVacunacion;
import model.enums.EstadoJornadaVacunacion;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Scanner;

public class VacunacionView {

    private final VacunacionController vacunacionController;
    private final Scanner sc;

    public VacunacionView() {
        this.vacunacionController = new VacunacionController();
        this.sc = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n=== GESTIÓN DE VACUNACIÓN ===");
            System.out.println("1. Listar vacunas disponibles");
            System.out.println("2. Crear nueva jornada de vacunación");
            System.out.println("3. Listar todas las jornadas");
            System.out.println("4. Buscar jornada específica");
            System.out.println("5. Registrar vacunación en jornada");
            System.out.println("6. Cambiar estado de una jornada");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            while (!sc.hasNextInt()) {
                System.out.print("Por favor, ingrese un número válido: ");
                sc.next();
            }
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> listarVacunas();
                case 2 -> crearJornada();
                case 3 -> listarJornadas();
                case 4 -> buscarJornadaPorId();
                case 5 -> registrarVacunacion();
                case 6 -> cambiarEstadoJornada();
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void listarVacunas() {
        System.out.println("\n=== Vacunas disponibles ===");
        vacunacionController.mostrarVacunas();
    }

    private void crearJornada() {
        System.out.println("\n=== Crear nueva jornada de vacunación ===");

        JornadaVacunacion jornada = new JornadaVacunacion();

        System.out.print("Nombre de la jornada: ");
        jornada.setNombre(sc.nextLine());

        System.out.print("Fecha (yyyy-mm-dd): ");
        jornada.setFecha(Date.valueOf(sc.nextLine()));

        System.out.print("Hora de inicio (HH:MM): ");
        jornada.setHoraInicio(Time.valueOf(sc.nextLine() + ":00"));

        System.out.print("Hora de fin (HH:MM): ");
        jornada.setHoraFin(Time.valueOf(sc.nextLine() + ":00"));

        System.out.print("Ubicación: ");
        jornada.setUbicacion(sc.nextLine());

        System.out.print("Descripción: ");
        jornada.setDescripcion(sc.nextLine());

        System.out.print("Capacidad máxima: ");
        jornada.setCapacidadMaxima(sc.nextInt());
        sc.nextLine();

        jornada.setEstado(EstadoJornadaVacunacion.Estado.PLANIFICADA);

        String resultado = vacunacionController.crearJornada(jornada);
        System.out.println(resultado);
    }

    private void listarJornadas() {
        vacunacionController.mostrarJornadas();
    }

    private void buscarJornadaPorId() {
        System.out.print("\nIngrese el ID de la jornada: ");
        while (!sc.hasNextInt()) {
            System.out.print("Por favor, ingrese un número válido: ");
            sc.next();
        }
        int id = sc.nextInt();
        sc.nextLine();

        JornadaVacunacion jornada = vacunacionController.buscarJornadaPorId(id);
        if (jornada == null) {
            System.out.println("❌ No se encontró ninguna jornada con ese ID.");
            return;
        }

        System.out.println("\n=== Jornada encontrada ===");
        System.out.printf("ID: %d%nNombre: %s%nFecha: %s%nInicio: %s%nFin: %s%nUbicación: %s%nEstado: %s%n",
                jornada.getId(), jornada.getNombre(), jornada.getFecha(),
                jornada.getHoraInicio(), jornada.getHoraFin(),
                jornada.getUbicacion(), jornada.getEstado());
    }

    private void registrarVacunacion() {
        System.out.println("\n=== Registrar Vacunación ===");
        try {
            System.out.print("ID de la jornada: ");
            int jornadaId = sc.nextInt();
            sc.nextLine();

            JornadaVacunacion jornada = vacunacionController.buscarJornadaPorId(jornadaId);
            if (jornada == null) {
                System.out.println("❌ No se encontró la jornada.");
                return;
            }

            System.out.print("ID de la mascota: ");
            int mascotaId = sc.nextInt();
            sc.nextLine();

            System.out.print("ID del dueño: ");
            int duenoId = sc.nextInt();
            sc.nextLine();

            vacunacionController.mostrarVacunas();
            System.out.print("ID de la vacuna aplicada: ");
            int vacunaId = sc.nextInt();
            sc.nextLine();

            System.out.print("ID del veterinario: ");
            int vetId = sc.nextInt();
            sc.nextLine();

            System.out.print("Lote de la vacuna: ");
            String lote = sc.nextLine();

            System.out.print("Próxima dosis (yyyy-mm-dd) o vacío si no aplica: ");
            String proxDosisStr = sc.nextLine();
            Date proximaDosis = proxDosisStr.isBlank() ? null : Date.valueOf(proxDosisStr);

            System.out.print("Observaciones: ");
            String observaciones = sc.nextLine();

            RegistroJornadaVacunacion reg = new RegistroJornadaVacunacion();
            reg.setJornadaId(jornadaId);
            reg.setMascotaId(mascotaId);
            reg.setDuenoId(duenoId);
            reg.setVacunaId(vacunaId);
            reg.setVeterinarioId(vetId);
            reg.setFechaHora(new java.sql.Timestamp(System.currentTimeMillis()));
            reg.setLoteVacuna(lote);
            reg.setProximaDosis(proximaDosis);
            reg.setObservaciones(observaciones);

            String resultado = vacunacionController.registrarVacunacion(reg);
            System.out.println(resultado);

        } catch (SQLException e) {
            System.out.println("❌ Error al registrar vacunación: " + e.getMessage());
        }
    }

    private void cambiarEstadoJornada() {
        System.out.print("\nIngrese el ID de la jornada: ");
        while (!sc.hasNextInt()) {
            System.out.print("Por favor, ingrese un número válido: ");
            sc.next();
        }
        int id = sc.nextInt();
        sc.nextLine();

        System.out.println("Seleccione el nuevo estado:");
        System.out.println("1. En curso");
        System.out.println("2. Finalizada");
        System.out.println("3. Cancelada");
        System.out.print("Opción: ");

        int opt = sc.nextInt();
        sc.nextLine();

        EstadoJornadaVacunacion.Estado nuevoEstado = switch (opt) {
            case 2 -> EstadoJornadaVacunacion.Estado.EN_CURSO;
            case 3 -> EstadoJornadaVacunacion.Estado.FINALIZADA;
            default -> EstadoJornadaVacunacion.Estado.CANCELADA;
        };

        String resultado = vacunacionController.cambiarEstadoJornada(id, nuevoEstado);
        System.out.println(resultado);
    }
}
