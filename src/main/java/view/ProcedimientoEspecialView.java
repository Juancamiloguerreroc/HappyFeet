package view;

import controller.ProcedimientoEspecialController;
import model.entities.ProcedimientoEspecial;
import model.entities.InsumoProcedimiento;
import model.enums.EstadoProcedimiento;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;

public class ProcedimientoEspecialView {

    private final ProcedimientoEspecialController controller;
    private final Scanner sc;

    public ProcedimientoEspecialView() {
        this.controller = new ProcedimientoEspecialController();
        this.sc = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n=== GESTIÓN DE PROCEDIMIENTOS ===");
            System.out.println("1. Registrar procedimiento");
            System.out.println("2. Listar procedimientos");
            System.out.println("3. Buscar procedimiento por ID");
            System.out.println("4. Actualizar procedimiento");
            System.out.println("5. Eliminar procedimiento");
            System.out.println("6. Agregar insumo a procedimiento");
            System.out.println("7. Listar insumos por procedimiento");
            System.out.println("8. Eliminar insumo");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            while (!sc.hasNextInt()) {
                System.out.print("Por favor, ingrese un número válido: ");
                sc.next();
            }
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar salto de línea

            switch (opcion) {
                case 1 -> registrarProcedimiento();
                case 2 -> listarProcedimientos();
                case 3 -> buscarProcedimientoPorId();
                case 4 -> actualizarProcedimiento();
                case 5 -> eliminarProcedimiento();
                case 6 -> agregarInsumo();
                case 7 -> listarInsumosPorProcedimiento();
                case 8 -> eliminarInsumo();
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void registrarProcedimiento() {
        System.out.println("\n=== Registrar nuevo procedimiento ===");

        System.out.print("ID de la mascota: ");
        int mascotaId = sc.nextInt();
        sc.nextLine();

        System.out.print("ID del veterinario: ");
        int veterinarioId = sc.nextInt();
        sc.nextLine();

        System.out.print("Tipo de procedimiento: ");
        String tipo = sc.nextLine();

        System.out.print("Nombre del procedimiento: ");
        String nombre = sc.nextLine();

        // Fecha y hora actuales del registro
        Timestamp fechaHora = new Timestamp(System.currentTimeMillis());

        System.out.print("Duración estimada (minutos): ");
        int duracion = sc.nextInt();
        sc.nextLine();

        System.out.print("Información preoperatoria: ");
        String pre = sc.nextLine();

        System.out.print("Detalle del procedimiento: ");
        String detalle = sc.nextLine();

        System.out.print("Complicaciones (si las hubo): ");
        String complicaciones = sc.nextLine();

        System.out.print("Seguimiento postoperatorio: ");
        String seguimiento = sc.nextLine();

        System.out.print("Fecha del próximo control (YYYY-MM-DD): ");
        Date proximoControl = Date.valueOf(sc.nextLine());

        System.out.print("Costo total del procedimiento: ");
        double costo = sc.nextDouble();
        sc.nextLine();

        EstadoProcedimiento estado = EstadoProcedimiento.Programado; // por defecto

        ProcedimientoEspecial procedimiento = new ProcedimientoEspecial();
        procedimiento.setMascotaId(mascotaId);
        procedimiento.setVeterinarioId(veterinarioId);
        procedimiento.setTipoProcedimiento(tipo);
        procedimiento.setNombreProcedimiento(nombre);
        procedimiento.setFechaHora(fechaHora);
        procedimiento.setDuracionEstimadaMinutos(duracion);
        procedimiento.setInformacionPreoperatoria(pre);
        procedimiento.setDetalleProcedimiento(detalle);
        procedimiento.setComplicaciones(complicaciones);
        procedimiento.setSeguimientoPostoperatorio(seguimiento);
        procedimiento.setProximoControl(proximoControl);
        procedimiento.setCostoProcedimiento(costo);
        procedimiento.setEstado(estado);

        String resultado = controller.registrarProcedimiento(procedimiento);
        System.out.println(resultado);
    }

    private void listarProcedimientos() {
        System.out.println("\n=== Lista de procedimientos ===");
        List<ProcedimientoEspecial> lista = controller.listarProcedimientos();

        if (lista.isEmpty()) {
            System.out.println("No hay procedimientos registrados.");
        } else {
            lista.forEach(System.out::println);
        }
    }

    private void buscarProcedimientoPorId() {
        System.out.print("\nIngrese el ID del procedimiento: ");
        int id = sc.nextInt();
        sc.nextLine();

        ProcedimientoEspecial p = controller.obtenerProcedimientoPorId(id);
        if (p != null) {
            System.out.println("Procedimiento encontrado:");
            System.out.println(p);
        } else {
            System.out.println("No se encontró el procedimiento con ese ID.");
        }
    }

    private void actualizarProcedimiento() {
        System.out.print("\nIngrese el ID del procedimiento a actualizar: ");
        int id = sc.nextInt();
        sc.nextLine();

        ProcedimientoEspecial p = controller.obtenerProcedimientoPorId(id);
        if (p == null) {
            System.out.println("No se encontró el procedimiento.");
            return;
        }

        System.out.println("=== Actualizar procedimiento ===");

        System.out.print("Tipo (" + p.getTipoProcedimiento() + "): ");
        String tipo = sc.nextLine();
        if (!tipo.isEmpty()) p.setTipoProcedimiento(tipo);

        System.out.print("Nombre (" + p.getNombreProcedimiento() + "): ");
        String nombre = sc.nextLine();
        if (!nombre.isEmpty()) p.setNombreProcedimiento(nombre);

        System.out.print("Duración (" + p.getDuracionEstimadaMinutos() + "): ");
        String dur = sc.nextLine();
        if (!dur.isEmpty()) p.setDuracionEstimadaMinutos(Integer.parseInt(dur));

        System.out.print("Estado (" + p.getEstado() + "): ");
        String est = sc.nextLine();

        if (!est.isEmpty()) {
            boolean existe = false;
            for (EstadoProcedimiento ep : EstadoProcedimiento.values()) {
                if (ep.name().equalsIgnoreCase(est)) {
                    p.setEstado(ep);
                    existe = true;
                    break;
                }
            }
            if (!existe) {
                System.out.println("El estado ingresado no es válido. Valores posibles:");
                for (EstadoProcedimiento ep : EstadoProcedimiento.values()) {
                    System.out.println(" - " + ep.name());
                }
            }
        }

        // Fecha de modificación actual
        Timestamp fechaActualizacion = new Timestamp(System.currentTimeMillis());
        p.setFechaHora(fechaActualizacion);

        String resultado = controller.actualizarProcedimiento(p);
        System.out.println(resultado);
    }

    private void eliminarProcedimiento() {
        System.out.print("\nIngrese el ID del procedimiento a eliminar: ");
        int id = sc.nextInt();
        sc.nextLine();

        String resultado = controller.eliminarProcedimiento(id);
        System.out.println(resultado);
    }

    private void agregarInsumo() {
        System.out.println("\n=== Agregar insumo a procedimiento ===");

        System.out.print("ID del procedimiento: ");
        int procedimientoId = sc.nextInt();
        sc.nextLine();

        System.out.print("ID del producto: ");
        int productoId = sc.nextInt();
        sc.nextLine();

        System.out.print("Cantidad usada: ");
        int cantidad = sc.nextInt();
        sc.nextLine();

        System.out.print("Observaciones: ");
        String obs = sc.nextLine();

        InsumoProcedimiento insumo = new InsumoProcedimiento();
        insumo.setProcedimientoId(procedimientoId);
        insumo.setProductoId(productoId);
        insumo.setCantidadUsada(cantidad);
        insumo.setObservaciones(obs);

        String resultado = controller.agregarInsumoAProcedimiento(insumo);
        System.out.println(resultado);
    }

    private void listarInsumosPorProcedimiento() {
        System.out.print("\nIngrese el ID del procedimiento: ");
        int procedimientoId = sc.nextInt();
        sc.nextLine();

        List<InsumoProcedimiento> lista = controller.listarInsumosPorProcedimiento(procedimientoId);

        if (lista.isEmpty()) {
            System.out.println("No hay insumos asociados a este procedimiento.");
        } else {
            lista.forEach(System.out::println);
        }
    }

    private void eliminarInsumo() {
        System.out.print("\nIngrese el ID del insumo a eliminar: ");
        int id = sc.nextInt();
        sc.nextLine();

        String resultado = controller.eliminarInsumo(id);
        System.out.println(resultado);
    }
}
