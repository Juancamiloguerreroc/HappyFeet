package view;

import controller.ServicioController;
import model.entities.Servicio;

import java.util.List;
import java.util.Scanner;

public class ServicioView {

    private final ServicioController serviciosController;
    private final Scanner sc;

    public ServicioView() {
        this.serviciosController = new ServicioController();
        this.sc = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n=== GESTIÓN DE SERVICIOS ===");
            System.out.println("1. Registrar nuevo servicio");
            System.out.println("2. Listar todos los servicios");
            System.out.println("3. Buscar servicio por ID");
            System.out.println("4. Actualizar servicio");
            System.out.println("5. Eliminar servicio");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            while (!sc.hasNextInt()) {
                System.out.print("Por favor, ingrese un número válido: ");
                sc.next();
            }
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar salto de línea pendiente

            switch (opcion) {
                case 1 -> registrarServicio();
                case 2 -> listarServicios();
                case 3 -> buscarServicioPorId();
                case 4 -> actualizarServicio();
                case 5 -> eliminarServicio();
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void registrarServicio() {
        System.out.println("\n=== Registrar nuevo servicio ===");

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Descripción: ");
        String descripcion = sc.nextLine();

        System.out.print("Categoría: ");
        String categoria = sc.nextLine();

        System.out.print("Precio base: ");
        while (!sc.hasNextDouble()) {
            System.out.print("Ingrese un valor válido para el precio: ");
            sc.next();
        }
        double precio = sc.nextDouble();

        System.out.print("Duración estimada (minutos): ");
        while (!sc.hasNextInt()) {
            System.out.print("Ingrese un valor válido para la duración: ");
            sc.next();
        }
        int duracion = sc.nextInt();
        sc.nextLine(); // limpiar buffer

        System.out.print("Activo (true/false): ");
        while (!sc.hasNextBoolean()) {
            System.out.print("Ingrese true o false: ");
            sc.next();
        }
        boolean activo = sc.nextBoolean();
        sc.nextLine();

        Servicio servicio = new Servicio(nombre, descripcion, categoria, precio, duracion, activo);
        String resultado = serviciosController.registrarServicio(servicio);
        System.out.println(resultado);
    }

    private void listarServicios() {
        System.out.println("\n=== Lista de todos los servicios ===");
        List<Servicio> servicios = serviciosController.obtenerListaServicios();

        if (servicios.isEmpty()) {
            System.out.println("No hay servicios registrados.");
        } else {
            for (Servicio s : servicios) {
                System.out.println(s);
            }
        }
    }

    private void buscarServicioPorId() {
        System.out.print("\nIngrese el ID del servicio: ");
        while (!sc.hasNextInt()) {
            System.out.print("Ingrese un número válido: ");
            sc.next();
        }
        int id = sc.nextInt();
        sc.nextLine();

        Servicio servicio = serviciosController.encontrarPorId(id);

        if (servicio != null) {
            System.out.println("Servicio encontrado:");
            System.out.println(servicio);
        } else {
            System.out.println("No se encontró un servicio con ese ID.");
        }
    }

    private void actualizarServicio() {
        System.out.print("\nIngrese el ID del servicio a actualizar: ");
        while (!sc.hasNextInt()) {
            System.out.print("Ingrese un número válido: ");
            sc.next();
        }
        int id = sc.nextInt();
        sc.nextLine();

        Servicio servicioExistente = serviciosController.encontrarPorId(id);
        if (servicioExistente == null) {
            System.out.println("No se encontró un servicio con ese ID.");
            return;
        }

        System.out.println("=== Actualizar servicio ===");
        System.out.print("Nombre (" + servicioExistente.getNombre() + "): ");
        String nombre = sc.nextLine();
        if (!nombre.isEmpty()) servicioExistente.setNombre(nombre);

        System.out.print("Descripción (" + servicioExistente.getDescripcion() + "): ");
        String descripcion = sc.nextLine();
        if (!descripcion.isEmpty()) servicioExistente.setDescripcion(descripcion);

        System.out.print("Categoría (" + servicioExistente.getCategoria() + "): ");
        String categoria = sc.nextLine();
        if (!categoria.isEmpty()) servicioExistente.setCategoria(categoria);

        System.out.print("Precio base (" + servicioExistente.getPrecioBase() + "): ");
        String precioStr = sc.nextLine();
        if (!precioStr.isEmpty()) servicioExistente.setPrecioBase(Double.parseDouble(precioStr));

        System.out.print("Duración estimada (" + servicioExistente.getDuracionEstimadaMinutos() + "): ");
        String durStr = sc.nextLine();
        if (!durStr.isEmpty()) servicioExistente.setDuracionEstimadaMinutos(Integer.parseInt(durStr));

        System.out.print("Activo (" + servicioExistente.getActivo() + "): ");
        String actStr = sc.nextLine();
        if (!actStr.isEmpty()) servicioExistente.setActivo(Boolean.parseBoolean(actStr));

        String resultado = serviciosController.modificarServicio(servicioExistente);
        System.out.println(resultado);
    }

    private void eliminarServicio() {
        System.out.print("\nIngrese el ID del servicio a eliminar: ");
        while (!sc.hasNextInt()) {
            System.out.print("Ingrese un número válido: ");
            sc.next();
        }
        int id = sc.nextInt();
        sc.nextLine();

        String resultado = serviciosController.eliminarServicio(id);
        System.out.println(resultado);
    }
}
