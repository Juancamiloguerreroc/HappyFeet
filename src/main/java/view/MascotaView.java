package view;

import controller.MascotaController;
import model.entities.Mascota;
import model.enums.SexoMascota;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class MascotaView {

    private final MascotaController mascotaController;
    private final Scanner sc;
    private static final String FECHA_FORMATO = "YYYY-MM-DD";

    public MascotaView() {
        this.mascotaController = new MascotaController();
        this.sc = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n=== GESTIÓN DE MASCOTAS ===");
            System.out.println("1. Registrar nueva mascota");
            System.out.println("2. Listar todas las mascotas");
            System.out.println("3. Buscar mascota por ID");
            System.out.println("4. Modificar mascota");
            System.out.println("5. Eliminar mascota");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            opcion = leerEntero();

            switch (opcion) {
                case 1 -> registrarMascota();
                case 2 -> listarMascotas();
                case 3 -> buscarMascotaPorId();
                case 4 -> modificarMascota();
                case 5 -> eliminarMascota();
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }
    
    private int leerEntero() {
        while (true) {
            if (sc.hasNextInt()) {
                int valor = sc.nextInt();
                sc.nextLine();
                return valor;
            } else {
                System.out.print("Por favor, ingrese un número válido: ");
                sc.next();
            }
        }
    }

    private SexoMascota leerSexoMascota() {
        while (true) {
            System.out.print("Sexo (1. MACHO / 2. HEMBRA): ");
            String input = sc.nextLine().trim();

            switch (input.toUpperCase()) {
                case "1", "M", "MACHO" -> {
                    return SexoMascota.Macho;
                }
                case "2", "F", "HEMBRA" -> {
                    return SexoMascota.Hembra;
                }
                default -> System.out.println("Entrada inválida. Intente nuevamente.");
            }
        }
    }

    private void registrarMascota() {
        System.out.println("\n=== Registrar nueva mascota ===");

        System.out.print("ID del dueño: ");
        int duenoId = leerEntero();

        System.out.print("ID de la raza: ");
        int razaId = leerEntero();

        System.out.print("Nombre de la mascota: ");
        String nombre = sc.nextLine();

        Date fechaNacimiento;
        
        while (true) {
            System.out.print("Fecha de nacimiento (" + FECHA_FORMATO + "): ");
            String fechaStr = sc.nextLine().trim();
            
            try {
                fechaNacimiento = Date.valueOf(fechaStr);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Formato de fecha inválido. Por favor, use " + FECHA_FORMATO + ".");
            }
        }

        SexoMascota sexoEnum = leerSexoMascota();

        System.out.print("Peso actual (kg): ");
        double peso;
        while (!sc.hasNextDouble()) {
            System.out.print("Por favor, ingrese un número válido para el peso (kg): ");
            sc.next();
        }
        peso = sc.nextDouble();
        sc.nextLine();

        System.out.print("Microchip (si aplica, o vacío): ");
        String microchip = sc.nextLine();

        System.out.print("Tatuaje (si aplica, o vacío): ");
        String tatuaje = sc.nextLine();

        System.out.print("URL de foto (opcional): ");
        String urlFoto = sc.nextLine();

        System.out.print("Alergias (si aplica, o vacío): ");
        String alergias = sc.nextLine();

        System.out.print("Condiciones preexistentes (si aplica, o vacío): ");
        String condiciones = sc.nextLine();

        System.out.print("¿Se encuentra activo? (true/false): ");
        boolean activo;
        while (!sc.hasNextBoolean()) {
            System.out.print("Por favor, ingrese true o false: ");
            sc.next();
        }
        activo = sc.nextBoolean();
        sc.nextLine();

        Mascota mascota = new Mascota(
                duenoId, nombre, razaId, fechaNacimiento, sexoEnum, peso,
                microchip, tatuaje, urlFoto, alergias, condiciones, activo
        );

        String resultado = mascotaController.registrarMascota(mascota);
        System.out.println(resultado);
    }

    private void listarMascotas() {
        System.out.println("\n=== Lista de todas las mascotas ===");
        List<Mascota> mascotas = mascotaController.listarMascotas();

        if (mascotas.isEmpty()) {
            System.out.println("No hay mascotas registradas.");
        } else {
            for (Mascota m : mascotas) {
                System.out.println(m);
            }
        }
    }

    private void buscarMascotaPorId() {
        System.out.print("\nIngrese el ID de la mascota a buscar: ");
        int id = leerEntero();

        Mascota mascota = mascotaController.buscarMascotaPorId(id);

        if (mascota != null) {
            System.out.println("Mascota encontrada:");
            System.out.println(mascota);
        } else {
            System.out.println("No se encontró una mascota con el ID especificado.");
        }
    }

    private void modificarMascota() {
        System.out.print("\nIngrese el ID de la mascota a modificar: ");
        int id = leerEntero();

        Mascota mascotaExistente = mascotaController.buscarMascotaPorId(id);
        if (mascotaExistente == null) {
            System.out.println("No se encontró la mascota con ID " + id);
            return;
        }

        System.out.println("Deje en blanco un campo si no desea cambiarlo.");

        System.out.print("Nuevo nombre (actual: " + mascotaExistente.getNombre() + "): ");
        String nombre = sc.nextLine();
        if (!nombre.trim().isEmpty()) mascotaExistente.setNombre(nombre);

        System.out.print("Nuevo peso (actual: " + mascotaExistente.getPesoActual() + "): ");
        String pesoStr = sc.nextLine();
        if (!pesoStr.trim().isEmpty()) {
            try {
                double peso = Double.parseDouble(pesoStr);
                mascotaExistente.setPesoActual(peso);
            } catch (NumberFormatException e) {
                System.out.println("Peso inválido, se mantiene el actual.");
            }
        }

        System.out.println("Sexo actual: " + mascotaExistente.getSexo());
        System.out.print("¿Desea cambiarlo? (S/N): ");
        String cambiar = sc.nextLine();
        if (cambiar.equalsIgnoreCase("S")) {
            SexoMascota nuevoSexo = leerSexoMascota();
            mascotaExistente.setSexo(nuevoSexo);
        }

        System.out.print("Nuevas alergias (actual: " + mascotaExistente.getAlergias() + "): ");
        String alergias = sc.nextLine();
        if (!alergias.trim().isEmpty()) mascotaExistente.setAlergias(alergias);

        System.out.print("Nuevas condiciones preexistentes (actual: " + mascotaExistente.getCondicionesPreexistentes() + "): ");
        String condiciones = sc.nextLine();
        if (!condiciones.trim().isEmpty()) mascotaExistente.setCondicionesPreexistentes(condiciones);

        String resultado = mascotaController.modificarMascota(mascotaExistente);
        System.out.println(resultado);
    }

    private void eliminarMascota() {
        System.out.print("\nIngrese el ID de la mascota a eliminar: ");
        int id = leerEntero();

        String resultado = mascotaController.eliminarMascota(id);
        System.out.println(resultado);
    }
}