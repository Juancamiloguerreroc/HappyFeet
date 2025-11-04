package view;

import controller.DuenoController;
import model.entities.Dueno;
import model.utils.FechaUtils;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;

public class DuenoView {

    private final DuenoController duenoController;
    private final Scanner sc;

    public DuenoView() {
        this.duenoController = new DuenoController();
        this.sc = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n=== GESTIÓN DE DUEÑOS ===");
            System.out.println("1. Registrar nuevo dueño");
            System.out.println("2. Listar todos los dueños");
            System.out.println("3. Buscar dueño por documento");
            System.out.println("4. Actualizar dueño");
            System.out.println("5. Eliminar dueño");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            while (!sc.hasNextInt()) {
                System.out.print("Por favor, ingrese un número válido: ");
                sc.next();
            }
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> registrarDueno();
                case 2 -> listarDuenos();
                case 3 -> buscarDuenoPorDocumento();
                case 4 -> actualizarDueno();
                case 5 -> eliminarDueno();
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void registrarDueno() {
        System.out.println("\n=== Registrar nuevo dueño ===");

        System.out.print("Nombre completo: ");
        String nombre = sc.nextLine();

        System.out.print("Documento de identidad: ");
        String documento = sc.nextLine();

        System.out.print("Dirección: ");
        String direccion = sc.nextLine();

        System.out.print("Teléfono: ");
        String telefono = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Contacto de emergencia: ");
        String contactoEmergencia = sc.nextLine();

        String fechaRegistroStr;
        Timestamp fechaRegistro;
        do {
            System.out.print("Ingrese fecha de registro (" + FechaUtils.FORMATO_FECHA_HORA + "): ");
            fechaRegistroStr = sc.nextLine().trim();

            if (FechaUtils.esFechaValida(fechaRegistroStr, FechaUtils.FORMATO_FECHA_HORA)) {
                if (FechaUtils.esFechaFutura(fechaRegistroStr, FechaUtils.FORMATO_FECHA_HORA)) {
                    System.out.println("ERROR: La fecha de registro no puede ser futura. Intente de nuevo.");
                    continue;
                }
                fechaRegistro = Timestamp.valueOf(fechaRegistroStr);
                break;
            } else {
                System.out.println("ERROR: Formato de fecha inválido. Use el formato yyyy-MM-dd.");
            }
        } while (true);

        System.out.print("¿Está activo? (true/false): ");
        while (!sc.hasNextBoolean()) {
            System.out.print("Por favor, ingrese true o false: ");
            sc.next();
        }
        boolean activo = sc.nextBoolean();
        sc.nextLine();

        Dueno dueno = new Dueno(nombre, documento, direccion, telefono, email, contactoEmergencia, fechaRegistro, activo);
        String resultado = duenoController.registrarDueno(dueno);
        System.out.println(resultado);
    }

    private void listarDuenos() {
        System.out.println("\n=== Lista de todos los dueños ===");
        List<Dueno> duenos = duenoController.obtenerListarDuenos();

        if (duenos.isEmpty()) {
            System.out.println("No hay dueños registrados.");
        } else {
            for (Dueno d : duenos) {
                System.out.println(d);
            }
        }
    }

    private void buscarDuenoPorDocumento() {
        System.out.print("\nIngrese el documento de identidad del dueño: ");
        String documento = sc.nextLine();

        Dueno dueno = duenoController.econtrarPorId(documento);

        if (dueno != null) {
            System.out.println("Dueño encontrado:");
            System.out.println(dueno);
        } else {
            System.out.println("No se encontró un dueño con ese documento.");
        }
    }

    private void actualizarDueno() {
        System.out.print("\nIngrese el documento de identidad del dueño a actualizar: ");
        String documento = sc.nextLine();

        Dueno duenoExistente = duenoController.econtrarPorId(documento);

        if (duenoExistente == null) {
            System.out.println("No se encontró un dueño con ese documento.");
            return;
        }

        System.out.println("=== Actualizar dueño ===");
        System.out.print("Nombre completo (" + duenoExistente.getNombreCompleto() + "): ");
        String nombre = sc.nextLine();
        if (!nombre.isEmpty()) duenoExistente.setNombreCompleto(nombre);

        System.out.print("Dirección (" + duenoExistente.getDireccion() + "): ");
        String direccion = sc.nextLine();
        if (!direccion.isEmpty()) duenoExistente.setDireccion(direccion);

        System.out.print("Teléfono (" + duenoExistente.getTelefono() + "): ");
        String telefono = sc.nextLine();
        if (!telefono.isEmpty()) duenoExistente.setTelefono(telefono);

        System.out.print("Email (" + duenoExistente.getEmail() + "): ");
        String email = sc.nextLine();
        if (!email.isEmpty()) duenoExistente.setEmail(email);

        System.out.print("Contacto de emergencia (" + duenoExistente.getContactoEmergencia() + "): ");
        String contactoEmergencia = sc.nextLine();
        if (!contactoEmergencia.isEmpty()) duenoExistente.setContactoEmergencia(contactoEmergencia);

        System.out.print("¿Está activo? (" + duenoExistente.getActivo() + "): ");
        String activoStr = sc.nextLine();
        if (!activoStr.isEmpty()) duenoExistente.setActivo(Boolean.parseBoolean(activoStr));

        String resultado = duenoController.actualizarDueno(duenoExistente);
        System.out.println(resultado);
    }

    private void eliminarDueno() {
        System.out.print("\nIngrese el documento de identidad del dueño a eliminar: ");
        String documento = sc.nextLine();

        String resultado = duenoController.eliminarDueno(documento);
        System.out.println(resultado);
    }
}
