package view;

import java.util.Scanner;

public class ReporteView {
    private Scanner scanner;
    
    public ReporteView() {
        this.scanner = new Scanner(System.in);
    }
    
    public int mostrarMenuReportes() {
        System.out.println("\n" + "─".repeat(50));
        System.out.println("       📊 GENERAR REPORTES");
        System.out.println("─".repeat(50));
        System.out.println("1. 📅  Reporte de Citas por Fecha");
        System.out.println("2. 💰  Reporte de Ventas por Período");
        System.out.println("3. 📦  Reporte de Inventario Bajo");
        System.out.println("4. 🏥  Reporte de Procedimientos");
        System.out.println("5. 🐕  Reporte de Mascotas por Especie");
        System.out.println("6. 👨‍⚕️  Reporte de Veterinarios");
        System.out.println("0. ↩️  Volver");
        System.out.println("─".repeat(50));
        System.out.print("Seleccione el tipo de reporte: ");
        
        return leerEntero();
    }
    
    public String pedirFechaInicio() {
        System.out.print("Fecha inicio (YYYY-MM-DD): ");
        return scanner.nextLine();
    }
    
    public String pedirFechaFin() {
        System.out.print("Fecha fin (YYYY-MM-DD): ");
        return scanner.nextLine();
    }
    
    public int pedirStockMinimo() {
        return leerEntero("Stock mínimo para alerta: ");
    }
    
    public void mostrarReporte(String titulo, String contenido) {
        System.out.println("\n" + "═".repeat(60));
        System.out.println("                   📊 " + titulo);
        System.out.println("═".repeat(60));
        System.out.println(contenido);
        System.out.println("═".repeat(60));
    }
    
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
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
}