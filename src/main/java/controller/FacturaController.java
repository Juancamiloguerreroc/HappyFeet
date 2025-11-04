package controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import model.entities.Dueno;
import model.entities.Factura;
import repository.DuenoDAO; // Necesitamos esto para obtener la lista de dueños
import repository.FacturaDAO;
import view.FacturaView;

public class FacturaController {

    private FacturaView view;
    private FacturaDAO dao;
    private DuenoDAO duenoDAO; // Dependencia para listar dueños
    private Scanner scanner;

    // Constructor para inyectar las dependencias
    public FacturaController(FacturaView view, FacturaDAO dao, DuenoDAO duenoDAO) {
        this.view = view;
        this.dao = dao;
        this.duenoDAO = duenoDAO;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Muestra el menú de gestión de facturas y maneja la lógica.
     */
    public void gestionarMenuFacturas() {
        int opcion;
        do {
            System.out.println("\n--- 🧾 MENÚ GESTIÓN DE FACTURAS ---");
            System.out.println("1. Crear Nueva Factura");
            System.out.println("2. Listar Todas las Facturas");
            System.out.println("0. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");

            opcion = leerEntero();

            switch (opcion) {
                case 1 -> crearFactura();
                case 2 -> listarFacturas();
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> view.mostrarMensaje("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 0);
    }

    /**
     * Lógica para crear una factura.
     */
    private void crearFactura() {
    try {
        // 1. Obtener la lista de dueños para mostrarla en la vista
        //    Asegúrate de que la llamada coincida con tu DAO:
        List<Dueno> duenos = duenoDAO.ListarDuenos(); // <-- CAMBIO AQUÍ (L mayúscula)
        
        // 2. Pedir a la vista que capture los datos
        Factura factura = view.capturarDatosFactura(duenos);

        // 3. Si la factura no es nula, guardarla
        if (factura != null) {
            dao.crear(factura);
            view.mostrarMensaje("✅ Factura creada exitosamente.");
        } else {
            view.mostrarMensaje("Creación de factura cancelada.");
        }
    } catch (SQLException e) {
        view.mostrarMensaje("❌ Error al crear la factura: " + e.getMessage());
    }
}

    /**
     * Lógica para listar facturas y mostrar un resumen usando una lambda.
     */
    private void listarFacturas() {
        try {
            List<Factura> facturas = dao.listarFacturas();
            view.mostrarFacturas(facturas);

            // --- USO DE LAMBDA ---
            // Aquí usamos el API Stream y una función lambda para filtrar las
            // facturas "Pendiente" y sumar sus totales.
            double totalPendiente = facturas.stream()
                    .filter(f -> f.getEstado().equalsIgnoreCase("Pendiente")) // (f -> ...) es la lambda
                    .mapToDouble(Factura::getTotal) // Factura::getTotal es una referencia a método
                    .sum();

            view.mostrarMensaje(String.format(">> Total de facturas pendientes: $%.2f", totalPendiente));
            
        } catch (SQLException e) {
            view.mostrarMensaje("❌ Error al listar facturas: " + e.getMessage());
        }
    }

    private int leerEntero() {
        while (!scanner.hasNextInt()) {
            System.out.print("Por favor, ingrese un número válido: ");
            scanner.next();
        }
        int valor = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        return valor;
    }
}