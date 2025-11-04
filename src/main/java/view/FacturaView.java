package view;

import model.entities.Factura;
import model.entities.Dueno;
import java.util.List;
import java.sql.Date;
import java.util.Scanner;

public class FacturaView {
    private Scanner scanner;
    
    public FacturaView() {
        this.scanner = new Scanner(System.in);
    }
    
    public Factura capturarDatosFactura(List<Dueno> duenos) {
        System.out.println("\n" + "─".repeat(40));
        System.out.println("       🧾 GENERAR FACTURA");
        System.out.println("─".repeat(40));
        
        if (duenos.isEmpty()) {
            System.out.println("❌ No hay dueños registrados.");
            return null;
        }
        
        // Mostrar dueños disponibles
        System.out.println("\nDueños disponibles:");
        for (Dueno dueno : duenos) {
            System.out.println("ID: " + dueno.getId() + " - " + dueno.getNombreCompleto());
        }
        
        int duenoId = leerEntero("\nID del Dueño: ");
        
        System.out.print("Número de Factura: ");
        String numeroFactura = scanner.nextLine();
        
        Date fechaEmision = new Date(System.currentTimeMillis());
        
        System.out.print("Subtotal: ");
        double subtotal = leerDouble();
        
        System.out.print("Impuesto: ");
        double impuesto = leerDouble();
        
        System.out.print("Descuento: ");
        double descuento = leerDouble();
        
        double total = subtotal + impuesto - descuento;
        
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
        
        String estado = "Pendiente"; // Estado por defecto
        
        return new Factura(duenoId, numeroFactura, fechaEmision, subtotal, 
                          impuesto, descuento, total, metodoPago, estado, observaciones);
    }
    
    public void mostrarFacturas(List<Factura> facturas) {
        System.out.println("\n" + "─".repeat(120));
        System.out.println("                                          📋 LISTA DE FACTURAS");
        System.out.println("─".repeat(120));
        
        if (facturas.isEmpty()) {
            System.out.println("No hay facturas registradas.");
        } else {
            System.out.printf("%-4s %-15s %-12s %-12s %-10s %-10s %-10s %-12s %-10s%n", 
                "ID", "Número", "Fecha", "Dueño ID", "Subtotal", "Impuesto", "Descuento", "Total", "Estado");
            System.out.println("─".repeat(120));
            
            for (Factura factura : facturas) {
                System.out.printf("%-4d %-15s %-12s %-12d %-10.2f %-10.2f %-10.2f %-12.2f %-10s%n",
                    factura.getId(),
                    factura.getNumeroFactura(),
                    factura.getFechaEmision(),
                    factura.getDuenoId(),
                    factura.getSubtotal(),
                    factura.getImpuesto(),
                    factura.getDescuento(),
                    factura.getTotal(),
                    factura.getEstado()
                );
            }
        }
        System.out.println("─".repeat(120));
    }
    
    public void mostrarFacturaDetalle(Factura factura) {
        if (factura == null) {
            System.out.println("❌ Factura no encontrada.");
            return;
        }
        
        System.out.println("\n" + "═".repeat(60));
        System.out.println("                   🧾 DETALLE DE FACTURA");
        System.out.println("═".repeat(60));
        System.out.println("ID: " + factura.getId());
        System.out.println("Número: " + factura.getNumeroFactura());
        System.out.println("Dueño ID: " + factura.getDuenoId());
        System.out.println("Fecha Emisión: " + factura.getFechaEmision());
        System.out.println("Subtotal: $" + factura.getSubtotal());
        System.out.println("Impuesto: $" + factura.getImpuesto());
        System.out.println("Descuento: $" + factura.getDescuento());
        System.out.println("Total: $" + factura.getTotal());
        System.out.println("Método Pago: " + factura.getMetodoPago());
        System.out.println("Estado: " + factura.getEstado());
        System.out.println("Observaciones: " + factura.getObservaciones());
        System.out.println("═".repeat(60));
    }
    
    private int leerEntero() {
        int valor = scanner.nextInt();
        scanner.nextLine(); // limpiar buffer
        return valor;
    }
    
    private int leerEntero(String mensaje) {
        System.out.print(mensaje);
        return leerEntero();
    }
    
    private double leerDouble() {
        double valor = scanner.nextDouble();
        scanner.nextLine(); // limpiar buffer
        return valor;
    }
    
    private double leerDouble(String mensaje) {
        System.out.print(mensaje);
        return leerDouble();
    }
    
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}