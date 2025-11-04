package view;

import java.util.Scanner;

public class MenuPrincipalView {
    private Scanner scanner;
    
    public MenuPrincipalView() {
        this.scanner = new Scanner(System.in);
    }
    
    public int mostrarMenuPrincipal() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("       🐾 VETERINARIA HAPPY FEET 🐾");
        System.out.println("=".repeat(50));
        System.out.println("1. 🏥  Gestión de Pacientes");
        System.out.println("2. 📅  Servicios Médicos y Citas");
        System.out.println("3. 📦  Inventario y Farmacia");
        System.out.println("4. 💰  Facturación");
        System.out.println("5. 🌟  Actividades Especiales");
        System.out.println("6. 📊  Reportes");
        System.out.println("0. 🚪  Salir");
        System.out.println("=".repeat(50));
        System.out.print("Seleccione una opción: ");
        
        return leerEntero();
    }
    
    public int mostrarMenuPacientes() {
        System.out.println("\n" + "─".repeat(40));
        System.out.println("       🏥 GESTIÓN DE PACIENTES");
        System.out.println("─".repeat(40));
        System.out.println("1. 👤  Registrar Dueño");
        System.out.println("2. 🐕  Registrar Mascota");
        System.out.println("3. 📋  Listar Dueños");
        System.out.println("4. 📋  Listar Mascotas");
        System.out.println("5. 🔍  Buscar Dueño por Documento");
        System.out.println("6. 👨‍⚕️  Registrar Veterinario");
        System.out.println("7. 📋  Listar Veterinarios");
        System.out.println("0. ↩️  Volver al Menú Principal");
        System.out.println("─".repeat(40));
        System.out.print("Seleccione una opción: ");
        
        return leerEntero();
    }
    
    public int mostrarMenuServicios() {
        System.out.println("\n" + "─".repeat(45));
        System.out.println("       📅 SERVICIOS MÉDICOS Y CITAS");
        System.out.println("─".repeat(45));
        System.out.println("1. 📝  Agendar Cita");
        System.out.println("2. 📋  Listar Citas");
        System.out.println("3. 🔍  Consultar Cita por ID");
        System.out.println("4. ✏️   Modificar Cita");
        System.out.println("5. ❌  Cancelar Cita");
        System.out.println("6. 🩺  Registrar Consulta Médica");
        System.out.println("7. 📋  Listar Consultas");
        System.out.println("8. 🏥  Registrar Procedimiento");
        System.out.println("9. 📋  Listar Procedimientos");
        System.out.println("0. ↩️  Volver al Menú Principal");
        System.out.println("─".repeat(45));
        System.out.print("Seleccione una opción: ");
        
        return leerEntero();
    }
    
    public int mostrarMenuInventario() {
        System.out.println("\n" + "─".repeat(40));
        System.out.println("       📦 INVENTARIO Y FARMACIA");
        System.out.println("─".repeat(40));
        System.out.println("1. 💊  Registrar Producto");
        System.out.println("2. 📋  Listar Productos");
        System.out.println("3. 🏢  Registrar Proveedor");
        System.out.println("4. 📋  Listar Proveedores");
        System.out.println("5. 📝  Registrar Servicio");
        System.out.println("6. 📋  Listar Servicios");
        System.out.println("0. ↩️  Volver al Menú Principal");
        System.out.println("─".repeat(40));
        System.out.print("Seleccione una opción: ");
        
        return leerEntero();
    }
    
    public int mostrarMenuFacturacion() {
        System.out.println("\n" + "─".repeat(35));
        System.out.println("       💰 FACTURACIÓN");
        System.out.println("─".repeat(35));
        System.out.println("1. 🧾  Generar Factura");
        System.out.println("2. 📋  Listar Facturas");
        System.out.println("3. 📦  Agregar Item a Factura");
        System.out.println("0. ↩️  Volver al Menú Principal");
        System.out.println("─".repeat(35));
        System.out.print("Seleccione una opción: ");
        
        return leerEntero();
    }
    
    public int mostrarMenuActividades() {
        System.out.println("\n" + "─".repeat(45));
        System.out.println("       🌟 ACTIVIDADES ESPECIALES");
        System.out.println("─".repeat(45));
        System.out.println("1. 🏠  Gestión de Adopciones");
        System.out.println("2. 💉  Jornadas de Vacunación");
        System.out.println("3. 🎯  Club de Mascotas");
        System.out.println("4. 🎁  Beneficios del Club");
        System.out.println("5. 🔄  Canje de Beneficios");
        System.out.println("0. ↩️  Volver al Menú Principal");
        System.out.println("─".repeat(45));
        System.out.print("Seleccione una opción: ");
        
        return leerEntero();
    }
    
    public int mostrarMenuReportes() {
        System.out.println("\n" + "─".repeat(35));
        System.out.println("       📊 REPORTES");
        System.out.println("─".repeat(35));
        System.out.println("1. 📅  Reporte de Citas");
        System.out.println("2. 💰  Reporte de Ventas");
        System.out.println("3. 📦  Reporte de Inventario");
        System.out.println("4. 🏥  Reporte Médico");
        System.out.println("0. ↩️  Volver al Menú Principal");
        System.out.println("─".repeat(35));
        System.out.print("Seleccione una opción: ");
        
        return leerEntero();
    }
    
    // Métodos utilitarios
    public String leerCadena(String mensaje) {
        System.out.print(mensaje);
        scanner.nextLine(); // Limpiar buffer
        return scanner.nextLine();
    }
    
    public int leerEntero() {
        try {
            return scanner.nextInt();
        } finally {
            scanner.nextLine(); // Limpiar buffer
        }
    }
    
    public int leerEntero(String mensaje) {
        System.out.print(mensaje);
        return leerEntero();
    }
    
    public double leerDouble(String mensaje) {
        System.out.print(mensaje);
        double valor = scanner.nextDouble();
        scanner.nextLine(); // Limpiar buffer
        return valor;
    }
    
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
    
    public void mostrarError(String mensaje) {
        System.out.println("❌ ERROR: " + mensaje);
    }
    
    public void mostrarExito(String mensaje) {
        System.out.println("✅ " + mensaje);
    }
    
    public void pausa() {
        System.out.print("\nPresione Enter para continuar...");
        scanner.nextLine();
    }
}