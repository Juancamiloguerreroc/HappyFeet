package view;

import model.entities.Factura;
import model.entities.Dueno;
import model.utils.FechaUtils;
import java.util.List;
import java.sql.Date;
import java.util.Scanner;
import java.util.Optional;
import java.util.function.DoubleUnaryOperator;

public class FacturaView {
    private final Scanner scanner;

    public FacturaView() {
        this.scanner = new Scanner(System.in);
    }

    public Factura capturarDatosFactura(List<Dueno> duenos) {
        System.out.println("\n" + "─".repeat(40));
        System.out.println("        GENERAR FACTURA");
        System.out.println("─".repeat(40));

        if (duenos.isEmpty()) {
            System.out.println("No hay dueños registrados. No se puede generar una factura.");
            return null;
        }

        System.out.println("\nDueños disponibles:");
        duenos.stream()
                .forEach(dueno -> System.out.println("ID: " + dueno.getId() + " - " + dueno.getNombreCompleto()));

        int duenoId = leerEntero("\nID del Dueño: ");

        Optional<Dueno> duenoSeleccionado = duenos.stream()
                .filter(d -> d.getId() == duenoId)
                .findFirst();

        if (duenoSeleccionado.isEmpty()) {
            System.out.println("ERROR: No existe un dueño con ese ID.");
            return null;
        }

        System.out.print("Número de Factura: ");
        String numeroFactura = scanner.nextLine();

        String fechaStr;
        Date fechaEmision;
        do {
            System.out.print("Ingrese fecha de emisión (" + FechaUtils.FORMATO_FECHA + "): ");
            fechaStr = scanner.nextLine().trim();

            if (FechaUtils.esFechaValida(fechaStr, FechaUtils.FORMATO_FECHA)) {
                if (FechaUtils.esFechaFutura(fechaStr, FechaUtils.FORMATO_FECHA)) {
                    System.out.println("ERROR: La fecha de emisión no puede ser futura. Intente de nuevo.");
                    continue;
                }
                fechaEmision = Date.valueOf(fechaStr);
                break;
            } else {
                System.out.println("ERROR: Formato de fecha inválido. Use el formato yyyy-MM-dd.");
            }
        } while (true);

        System.out.print("Subtotal: ");
        double subtotal = leerDouble();
        System.out.print("Impuesto: ");
        double impuesto = leerDouble();
        System.out.print("Descuento: ");
        double descuento = leerDouble();

        double total = ((DoubleUnaryOperator) (x -> subtotal + impuesto - descuento)).applyAsDouble(0);

        System.out.println("Método de Pago:");
        System.out.println("1. Efectivo");
        System.out.println("2. Tarjeta");
        System.out.println("3. Transferencia");
        System.out.println("4. Mixto");
        int opcionPago = leerEntero("Seleccione (1-4): ");
        String metodoPago = switch (opcionPago) {
            case 1 -> "Efectivo";
            case 2 -> "Tarjeta";
            case 3 -> "Transferencia";
            case 4 -> "Mixto";
            default -> "Efectivo";
        };

        System.out.print("Observaciones: ");
        String observaciones = scanner.nextLine();
        String estado = "Pendiente";

        return new Factura(
                duenoSeleccionado.get().getId(),
                numeroFactura,
                fechaEmision,
                subtotal,
                impuesto,
                descuento,
                total,
                metodoPago,
                estado,
                observaciones
        );
    }

    public void mostrarFacturas(List<Factura> facturas) {
        System.out.println("\n" + "─".repeat(120));
        System.out.println("LISTA DE FACTURAS");
        System.out.println("─".repeat(120));

        if (facturas.isEmpty()) {
            System.out.println("No hay facturas registradas.");
        } else {
            System.out.printf("%-4s %-15s %-12s %-12s %-10s %-10s %-10s %-12s %-10s%n",
                    "ID", "Número", "Fecha", "Dueño ID", "Subtotal", "Impuesto",
                    "Descuento", "Total", "Estado");
            System.out.println("─".repeat(120));

            facturas.stream().forEach(factura -> System.out.printf(
                    "%-4d %-15s %-12s %-12d %-10.2f %-10.2f %-10.2f %-12.2f %-10s%n",
                    factura.getId(),
                    factura.getNumeroFactura(),
                    factura.getFechaEmision(),
                    factura.getDuenoId(),
                    factura.getSubtotal(),
                    factura.getImpuesto(),
                    factura.getDescuento(),
                    factura.getTotal(),
                    factura.getEstado()
            ));
            double totalGeneral = facturas.stream()
                    .mapToDouble(Factura::getTotal)
                    .sum();

            System.out.println("─".repeat(120));
            System.out.printf("Total general de todas las facturas: $%.2f%n", totalGeneral);
        }
        System.out.println("─".repeat(120));
    }

    public void mostrarFacturaDetalle(Factura factura) {
        Optional.ofNullable(factura).ifPresentOrElse(f -> {
            System.out.println("\n" + "═".repeat(60));
            System.out.println("DETALLE DE FACTURA");
            System.out.println("═".repeat(60));
            System.out.println("ID: " + f.getId());
            System.out.println("Número: " + f.getNumeroFactura());
            System.out.println("Dueño ID: " + f.getDuenoId());
            System.out.println("Fecha Emisión: " + f.getFechaEmision());
            System.out.println("Subtotal: $" + f.getSubtotal());
            System.out.println("Impuesto: $" + f.getImpuesto());
            System.out.println("Descuento: $" + f.getDescuento());
            System.out.println("Total: $" + f.getTotal());
            System.out.println("Método Pago: " + f.getMetodoPago());
            System.out.println("Estado: " + f.getEstado());
            System.out.println("Observaciones: " + f.getObservaciones());
            System.out.println("═".repeat(60));
        }, () -> System.out.println("Factura no encontrada."));
    }

    // Métodos auxiliares

    private int leerEntero() {
        int valor = scanner.nextInt();
        scanner.nextLine();
        return valor;
    }

    private int leerEntero(String mensaje) {
        System.out.print(mensaje);
        while (!scanner.hasNextInt()) {
            System.out.print("Valor inválido. " + mensaje);
            scanner.next();
        }
        return leerEntero();
    }

    private double leerDouble() {
        double valor = scanner.nextDouble();
        scanner.nextLine();
        return valor;
    }

    private double leerDouble(String mensaje) {
        System.out.print(mensaje);
        while (!scanner.hasNextDouble()) {
            System.out.print("Valor inválido. " + mensaje);
            scanner.next();
        }
        return leerDouble();
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}
