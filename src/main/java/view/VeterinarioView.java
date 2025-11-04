package view;

import controller.VeterinarioController;
import model.entities.Veterinario;
import model.utils.FechaUtils;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class VeterinarioView {

    private final VeterinarioController veterinarioController;
    private final Scanner sc;

    public VeterinarioView() {
        this.veterinarioController = new VeterinarioController();
        this.sc = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n=== GESTIÓN DE VETERINARIOS ===");
            System.out.println("1. Registrar nuevo veterinario");
            System.out.println("2. Listar todos los veterinarios");
            System.out.println("3. Buscar veterinario por ID");
            System.out.println("4. Modificar veterinario");
            System.out.println("5. Eliminar veterinario");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            while (!sc.hasNextInt()) {
                System.out.print("Por favor, ingrese un número válido: ");
                sc.next();
            }
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> registrarVeterinario();
                case 2 -> listarVeterinarios();
                case 3 -> buscarVeterinarioPorId();
                case 4 -> modificarVeterinario();
                case 5 -> eliminarVeterinario();
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void registrarVeterinario() {
        System.out.println("\n=== Registrar nuevo veterinario ===");

        System.out.print("Nombre completo: ");
        String nombre = sc.nextLine();

        System.out.print("Documento de identidad: ");
        String documento = sc.nextLine();

        System.out.print("Licencia profesional: ");
        String licencia = sc.nextLine();

        System.out.print("Especialidad: ");
        String especialidad = sc.nextLine();

        System.out.print("Teléfono: ");
        String telefono = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Fecha de contratación (YYYY-MM-DD): ");
        String fechaStr = sc.nextLine();
        if (!FechaUtils.esFechaValida(fechaStr, FechaUtils.FORMATO_FECHA)) {
            System.out.println("Formato de fecha inválido. Use YYYY-MM-DD.");
            return;
        }

        Date fechaContratacion = Date.valueOf(fechaStr);

        System.out.print("¿Está activo? (true/false): ");
        while (!sc.hasNextBoolean()) {
            System.out.print("Por favor, ingrese true o false: ");
            sc.next();
        }
        boolean activo = sc.nextBoolean();
        sc.nextLine();

        Veterinario v = new Veterinario(
                nombre, documento, licencia, especialidad, telefono, email, fechaContratacion, activo
        );

        String resultado = veterinarioController.registrarVeterinario(v);
        System.out.println(resultado);
    }

    private void listarVeterinarios() {
        System.out.println("\n=== Lista de todos los veterinarios ===");
        List<Veterinario> lista = veterinarioController.obtenerListaVeterinarios();

        if (lista.isEmpty()) {
            System.out.println("No hay veterinarios registrados.");
        } else {
            for (Veterinario v : lista) {
                System.out.println(v);
            }
        }
    }

    private void buscarVeterinarioPorId() {
        System.out.print("\nIngrese el ID del veterinario a buscar: ");
        while (!sc.hasNextInt()) {
            System.out.print("Por favor, ingrese un número válido: ");
            sc.next();
        }
        int id = sc.nextInt();
        sc.nextLine();

        Veterinario v = veterinarioController.encontrarPorId(id);

        if (v != null) {
            System.out.println("Veterinario encontrado:");
            System.out.println(v);
        } else {
            System.out.println("No se encontró un veterinario con el ID especificado.");
        }
    }

    private void modificarVeterinario() {
        System.out.print("\nIngrese el ID del veterinario a modificar: ");
        while (!sc.hasNextInt()) {
            System.out.print("Por favor, ingrese un número válido: ");
            sc.next();
        }
        int id = sc.nextInt();
        sc.nextLine();

        Veterinario vExistente = veterinarioController.encontrarPorId(id);
        if (vExistente == null) {
            System.out.println("No se encontró el veterinario con ID " + id);
            return;
        }

        System.out.println("Deje en blanco un campo si no desea cambiarlo.");

        System.out.print("Nuevo nombre completo (actual: " + vExistente.getNombreCompleto() + "): ");
        String nombre = sc.nextLine();
        if (!nombre.trim().isEmpty()) vExistente.setNombreCompleto(nombre);

        System.out.print("Nuevo documento de identidad (actual: " + vExistente.getDocumentoIdentidad() + "): ");
        String documento = sc.nextLine();
        if (!documento.trim().isEmpty()) vExistente.setDocumentoIdentidad(documento);

        System.out.print("Nueva licencia profesional (actual: " + vExistente.getLicenciaProfesional() + "): ");
        String licencia = sc.nextLine();
        if (!licencia.trim().isEmpty()) vExistente.setLicenciaProfesional(licencia);

        System.out.print("Nueva especialidad (actual: " + vExistente.getEspecialidad() + "): ");
        String especialidad = sc.nextLine();
        if (!especialidad.trim().isEmpty()) vExistente.setEspecialidad(especialidad);

        System.out.print("Nuevo teléfono (actual: " + vExistente.getTelefono() + "): ");
        String telefono = sc.nextLine();
        if (!telefono.trim().isEmpty()) vExistente.setTelefono(telefono);

        System.out.print("Nuevo email (actual: " + vExistente.getEmail() + "): ");
        String email = sc.nextLine();
        if (!email.trim().isEmpty()) vExistente.setEmail(email);

        System.out.print("Nueva fecha de contratación (YYYY-MM-DD) (actual: " + vExistente.getFechaContratacion() + "): ");
        String fechaStr = sc.nextLine();
        if (!fechaStr.trim().isEmpty()) {
            if (FechaUtils.esFechaValida(fechaStr, FechaUtils.FORMATO_FECHA)) {
                vExistente.setFechaContratacion(Date.valueOf(fechaStr));
            } else {
                System.out.println("Formato de fecha inválido, se mantiene la actual.");
            }
        }

        System.out.print("¿Activo? (actual: " + vExistente.isActivo() + "): ");
        String activoStr = sc.nextLine();
        if (!activoStr.trim().isEmpty()) {
            vExistente.setActivo(Boolean.parseBoolean(activoStr));
        }

        String resultado = veterinarioController.modificarVeterinario(vExistente);
        System.out.println(resultado);
    }

    private void eliminarVeterinario() {
        System.out.print("\nIngrese el ID del veterinario a eliminar: ");
        while (!sc.hasNextInt()) {
            System.out.print("Por favor, ingrese un número válido: ");
            sc.next();
        }
        int id = sc.nextInt();
        sc.nextLine();

        String resultado = veterinarioController.eliminarVeterinario(id);
        System.out.println(resultado);
    }
}
