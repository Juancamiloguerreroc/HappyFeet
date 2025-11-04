package view;

import controller.InventarioController;
import model.entities.Inventario;
import model.entities.MovimientoInventario;
import model.utils.FechaUtils;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;

public class InventarioView {

    private final InventarioController inventarioController;
    private final Scanner sc;

    public InventarioView() {
        this.inventarioController = new InventarioController();
        this.sc = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n=== GESTIÓN DE INVENTARIO ===");
            System.out.println("1. Registrar nuevo producto");
            System.out.println("2. Listar inventario");
            System.out.println("3. Buscar producto por ID");
            System.out.println("4. Actualizar producto");
            System.out.println("5. Eliminar producto");
            System.out.println("6. Registrar movimiento (Entrada/Salida)");
            System.out.println("7. Ver movimientos de un producto");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            while (!sc.hasNextInt()) {
                System.out.print("Por favor, ingrese un número válido: ");
                sc.next();
            }
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> registrarProducto();
                case 2 -> listarInventario();
                case 3 -> buscarPorId();
                case 4 -> actualizarProducto();
                case 5 -> eliminarProducto();
                case 6 -> registrarMovimiento();
                case 7 -> verMovimientos();
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void registrarProducto() {
        System.out.println("\n=== Registrar nuevo producto ===");

        System.out.print("Nombre del producto: ");
        String nombre = sc.nextLine();

        System.out.print("Descripción: ");
        String descripcion = sc.nextLine();

        System.out.print("Fabricante: ");
        String fabricante = sc.nextLine();

        System.out.print("ID de tipo de producto: ");
        int tipo = sc.nextInt();

        System.out.print("ID del proveedor (o 0 si no aplica): ");
        int proveedor = sc.nextInt();

        sc.nextLine();
        System.out.print("Lote: ");
        String lote = sc.nextLine();

        System.out.print("Cantidad en stock: ");
        int stock = sc.nextInt();

        System.out.print("Stock mínimo: ");
        int minimo = sc.nextInt();

        sc.nextLine();
        System.out.print("Unidad de medida (ej: unidad, caja, ml): ");
        String unidad = sc.nextLine();

        String fechaVencimientoStr;
        Date fechaVencimiento;
        do {
            System.out.print("Fecha de vencimiento (" + FechaUtils.FORMATO_FECHA + "): ");
            fechaVencimientoStr = sc.nextLine().trim();
            if (FechaUtils.esFechaValida(fechaVencimientoStr, FechaUtils.FORMATO_FECHA)) {
                if (FechaUtils.esFechaFutura(fechaVencimientoStr, FechaUtils.FORMATO_FECHA)) {
                    fechaVencimiento = Date.valueOf(fechaVencimientoStr);
                    break;
                } else {
                    System.out.println("ERROR: La fecha de vencimiento debe ser futura.");
                }
            } else {
                System.out.println("ERROR: Formato de fecha inválido. Use el formato yyyy-MM-dd.");
            }
        } while (true);

        System.out.print("Precio de compra: ");
        double compra = sc.nextDouble();

        System.out.print("Precio de venta: ");
        double venta = sc.nextDouble();

        System.out.print("¿Requiere receta? (true/false): ");
        boolean receta = sc.nextBoolean();

        System.out.print("¿Activo? (true/false): ");
        boolean activo = sc.nextBoolean();

        Timestamp fechaRegistro = new Timestamp(System.currentTimeMillis());

        Inventario inv = new Inventario();
        inv.setNombreProducto(nombre);
        inv.setDescripcion(descripcion);
        inv.setFabricante(fabricante);
        inv.setProductoTipoId(tipo);
        inv.setProveedorId(proveedor == 0 ? null : proveedor);
        inv.setLote(lote);
        inv.setCantidadStock(stock);
        inv.setStockMinimo(minimo);
        inv.setUnidadMedida(unidad);
        inv.setFechaVencimiento(fechaVencimiento);
        inv.setPrecioCompra(compra);
        inv.setPrecioVenta(venta);
        inv.setRequiereReceta(receta);
        inv.setActivo(activo);
        inv.setFechaRegistro(fechaRegistro);

        String resultado = inventarioController.registrarInventario(inv);
        System.out.println(resultado);
    }

    private void listarInventario() {
        System.out.println("\n=== LISTA DE PRODUCTOS ===");
        List<Inventario> lista = inventarioController.listarInventario();
        if (lista.isEmpty()) {
            System.out.println("No hay productos registrados.");
        } else {
            for (Inventario inv : lista) {
                System.out.println(inv);
            }
        }
    }

    private void buscarPorId() {
        System.out.print("\nIngrese el ID del producto: ");
        int id = sc.nextInt();
        sc.nextLine();

        Inventario inv = inventarioController.buscarPorId(id);
        if (inv == null) {
            System.out.println("No se encontró el producto.");
        } else {
            System.out.println(inv);
        }
    }

    private void actualizarProducto() {
        System.out.print("\nIngrese el ID del producto a actualizar: ");
        int id = sc.nextInt();
        sc.nextLine();

        Inventario inv = inventarioController.buscarPorId(id);
        if (inv == null) {
            System.out.println("No se encontró el producto.");
            return;
        }

        System.out.print("Nuevo nombre (" + inv.getNombreProducto() + "): ");
        String nombre = sc.nextLine();
        if (!nombre.isEmpty()) inv.setNombreProducto(nombre);

        System.out.print("Nueva descripción (" + inv.getDescripcion() + "): ");
        String desc = sc.nextLine();
        if (!desc.isEmpty()) inv.setDescripcion(desc);

        System.out.print("Nuevo fabricante (" + inv.getFabricante() + "): ");
        String fab = sc.nextLine();
        if (!fab.isEmpty()) inv.setFabricante(fab);

        System.out.print("Nuevo stock (" + inv.getCantidadStock() + "): ");
        String stockStr = sc.nextLine();
        if (!stockStr.isEmpty()) inv.setCantidadStock(Integer.parseInt(stockStr));

        System.out.print("¿Activo? (" + inv.isActivo() + "): ");
        String activoStr = sc.nextLine();
        if (!activoStr.isEmpty()) inv.setActivo(Boolean.parseBoolean(activoStr));

        String resultado = inventarioController.actualizarInventario(inv);
        System.out.println(resultado);
    }

    private void eliminarProducto() {
        System.out.print("\nIngrese el ID del producto a eliminar: ");
        int id = sc.nextInt();
        sc.nextLine();

        String resultado = inventarioController.eliminarInventario(id);
        System.out.println(resultado);
    }

    private void registrarMovimiento() {
        System.out.println("\n=== Registrar movimiento de inventario ===");

        System.out.print("ID del producto: ");
        int id = sc.nextInt();

        System.out.print("Tipo de movimiento (ENTRADA/SALIDA): ");
        String tipo = sc.next();

        System.out.print("Cantidad: ");
        int cantidad = sc.nextInt();

        sc.nextLine();
        System.out.print("Motivo: ");
        String motivo = sc.nextLine();

        System.out.print("Usuario responsable: ");
        String usuario = sc.nextLine();

        String resultado = inventarioController.ajustarStock(id, cantidad, tipo, motivo, usuario);
        System.out.println(resultado);
    }

    private void verMovimientos() {
        System.out.print("\nIngrese el ID del producto: ");
        int id = sc.nextInt();

        List<MovimientoInventario> movimientos = inventarioController.obtenerMovimientosPorProducto(id);
        if (movimientos.isEmpty()) {
            System.out.println("No hay movimientos registrados para este producto.");
        } else {
            System.out.println("\n=== HISTORIAL DE MOVIMIENTOS ===");
            for (MovimientoInventario mov : movimientos) {
                System.out.println(mov);
            }
        }
    }
}
