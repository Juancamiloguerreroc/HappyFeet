package view;
import model.utils.FechaUtils;
import controller.AdopcionController;
import controller.DuenoController;
import controller.MascotaController;
import model.entities.*;
import model.enums.EstadoMascotaAdopcion;
import repository.MascotaDAO;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import model.enums.SexoMascota;

public class AdopcionView {

    private final AdopcionController adopcionController;
    private final MascotaDAO mascotaDAO;
    private final Scanner sc;
    private final MascotaController mascotaController;
    private final DuenoController duenoController;

    public AdopcionView() {
        this.adopcionController = new AdopcionController();
        this.mascotaDAO = new MascotaDAO();
        this.mascotaController = new MascotaController();
        this.duenoController = new DuenoController();
        this.sc = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n=== GESTIÓN DE ADOPCIONES ===");
            System.out.println("1. Registrar mascota en adopción");
            System.out.println("2. Listar mascotas disponibles");
            System.out.println("3. Buscar mascota por ID");
            System.out.println("4. Registrar adopción");
            System.out.println("5. Actualizar estado de adopción");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            while (!sc.hasNextInt()) {
                System.out.print("Por favor, ingrese un número válido: ");
                sc.next();
            }
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> registrarMascotaEnAdopcion();
                case 2 -> listarMascotasDisponibles();
                case 3 -> buscarMascotaPorId();
                case 4 -> registrarAdopcion();
                case 5 -> actualizarEstadoAdopcion();
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void registrarMascotaEnAdopcion() {
        System.out.println("\n=== Registrar Mascota en Adopción ===");

        Mascota mascota = new Mascota();

        mascota.setDuenoId(1);

        System.out.print("Ingrese nombre de la mascota: ");
        mascota.setNombre(sc.nextLine());

        System.out.print("Ingrese ID de la raza: ");
        int razaId = sc.nextInt();
        sc.nextLine();
        mascota.setRazaId(razaId);


    String fechaNacimientoStr;
    Date fechaNacimiento;

    do {
        System.out.print("Ingrese fecha de nacimiento (" + FechaUtils.FORMATO_FECHA + "): ");
        fechaNacimientoStr = sc.nextLine().trim();

        if (FechaUtils.esFechaValida(fechaNacimientoStr, FechaUtils.FORMATO_FECHA)) {

            // Verificamos que no sea una fecha futura
            if (FechaUtils.esFechaFutura(fechaNacimientoStr, FechaUtils.FORMATO_FECHA)) {
                System.out.println("ERROR: La fecha de nacimiento no puede ser futura. Intente de nuevo.");
                continue;
            }

            // Convertimos la cadena a java.sql.Date y la asignamos
            fechaNacimiento = Date.valueOf(fechaNacimientoStr);
            mascota.setFechaNacimiento(fechaNacimiento);
            break;

        } else {
            System.out.println("ERROR: Formato de fecha inválido. Use el formato yyyy-MM-dd.");
        }
    } while (true);

        System.out.println("Seleccione sexo de la mascota:");
        System.out.println("1. Macho");
        System.out.println("2. Hembra");
        System.out.print("Opción: ");
        int sexoOpt = sc.nextInt();
        sc.nextLine();
        mascota.setSexo(sexoOpt == 1 ? SexoMascota.Macho : SexoMascota.Hembra);

        System.out.print("Ingrese peso actual (ej: 4.5) o dejar vacío: ");
        String pesoInput = sc.nextLine();
        if (!pesoInput.isBlank()) {
            mascota.setPesoActual(Double.parseDouble(pesoInput));
        }

        System.out.print("Ingrese número de microchip o dejar vacío: ");
        String microchip = sc.nextLine();
        if (!microchip.isBlank()) {
            mascota.setMicrochip(microchip);
        }

        System.out.print("Ingrese número de tatuaje o dejar vacío: ");
        String tatuaje = sc.nextLine();
        if (!tatuaje.isBlank()) {
            mascota.setTatuaje(tatuaje);
        }

        System.out.print("Ingrese URL de la foto o dejar vacío: ");
        String urlFoto = sc.nextLine();
        if (!urlFoto.isBlank()) {
            mascota.setUrlFoto(urlFoto);
        }

        System.out.print("Ingrese alergias o dejar vacío: ");
        String alergias = sc.nextLine();
        if (!alergias.isBlank()) {
            mascota.setAlergias(alergias);
        }

        System.out.print("Ingrese condiciones preexistentes o dejar vacío: ");
        String condiciones = sc.nextLine();
        if (!condiciones.isBlank()) {
            mascota.setCondicionesPreexistentes(condiciones);
        }

        mascota.setActivo(true);

        int idGenerado = -1;
        try {
            idGenerado = mascotaDAO.crear(mascota);
            } catch (SQLException e) {
        System.out.println("Error al registrar la mascota: " + e.getMessage());
        return; 
            }

        if (idGenerado <= 0) {
            System.out.println("Error: no se pudo registrar la mascota correctamente.");
            return;
        }

        System.out.println("Mascota registrada con ID: " + idGenerado);

        MascotaAdopcion mascotaAdopcion = new MascotaAdopcion();
        mascotaAdopcion.setMascotaId(idGenerado);

        
        Date fechaIngreso = new Date(System.currentTimeMillis());
        mascotaAdopcion.setFechaIngreso(fechaIngreso);

        System.out.print("Motivo de ingreso: ");
        mascotaAdopcion.setMotivoIngreso(sc.nextLine());

        System.out.print("Historia: ");
        mascotaAdopcion.setHistoria(sc.nextLine());

        System.out.println("Seleccione el temperamento:");
        System.out.println("1. Tranquilo");
        System.out.println("2. Juguetón");
        System.out.println("3. Nervioso");
        System.out.println("4. Protector");
        System.out.print("Opción: ");
        int tempOpt = sc.nextInt();
        sc.nextLine();
        mascotaAdopcion.setTemperamento(
                switch (tempOpt) {
                    case 1 -> "Tranquilo";
                    case 2 -> "Juguetón";
                    case 3 -> "Nervioso";
                    case 4 -> "Protector";
                    default -> "Desconocido";
                }
        );

        System.out.print("¿Tiene necesidades especiales? (1. Sí / 2. No): ");
        int nec = sc.nextInt();
        sc.nextLine();
        mascotaAdopcion.setNecesidadesEspeciales(nec == 1 ? "Sí, requiere atención especial" : "No");

        System.out.print("URL de foto adicional (opcional): ");
        mascotaAdopcion.setFotoAdicionalUrl(sc.nextLine());

        mascotaAdopcion.setEstado(EstadoMascotaAdopcion.Disponible);
        mascotaAdopcion.setFechaIngreso(new Date(System.currentTimeMillis()));

        String resultadoAdopcion = adopcionController.registrarMascotaEnAdopcion(mascotaAdopcion);
        System.out.println(resultadoAdopcion);
    }


    private void listarMascotasDisponibles() {
        System.out.println("\n=== Mascotas disponibles para adopción ===");
        List<MascotaAdopcion> lista = adopcionController.listarMascotasDisponibles();

        if (lista.isEmpty()) {
            System.out.println("No hay mascotas disponibles actualmente.");
        } else {
            lista.forEach(System.out::println);
        }
    }

    private void buscarMascotaPorId() {
        System.out.print("\nIngrese el ID de la mascota en adopción: ");
        while (!sc.hasNextInt()) {
            System.out.print("Por favor, ingrese un número válido: ");
            sc.next();
        }
        int id = sc.nextInt();
        sc.nextLine();

        MascotaAdopcion mascota = adopcionController.buscarMascotaAdopcionPorId(id);

        if (mascota != null) {
            System.out.println("Mascota encontrada:");
            System.out.println(mascota);
        } else {
            System.out.println("No se encontró una mascota con ese ID.");
        }
    }

    private void registrarAdopcion() {
        System.out.println("\n=== Registrar Nueva Adopción ===");

        System.out.print("ID de la mascota en adopción: ");
        while (!sc.hasNextInt()) {
            System.out.print("Por favor, ingrese un número válido: ");
            sc.next();
        }
        int mascotaAdopcionId = sc.nextInt();
        sc.nextLine();

        var mascotaAdopcion = adopcionController.buscarMascotaAdopcionPorId(mascotaAdopcionId);
        if (mascotaAdopcion == null) {
            System.out.println("No se encontró una mascota en adopción con ese ID.");
            return;
        }

        System.out.println("\nMascota en adopción encontrada:");
        System.out.println("ID interno de mascota: " + mascotaAdopcion.getMascotaId());
        System.out.println("Estado actual: " + mascotaAdopcion.getEstado());
        System.out.println("Temperamento: " + mascotaAdopcion.getTemperamento());
        System.out.println("Necesidades especiales: " + mascotaAdopcion.getNecesidadesEspeciales());

        System.out.print("\nDocumento de identidad del nuevo dueño: ");
        String documento = sc.nextLine();

        var dueno = duenoController.econtrarPorId(documento);
        if (dueno == null) {
            System.out.println("No se encontró un dueño con ese documento.");
            System.out.print("¿Desea registrar un nuevo dueño? (1. Sí / 2. No): ");
            int opcionNuevo = sc.nextInt();
            sc.nextLine();
            if (opcionNuevo == 1) {
            System.out.println("\n=== Registrar Nuevo Dueño ===");
            System.out.print("Nombre completo: ");
            String nombre = sc.nextLine();

            System.out.print("Teléfono: ");
            String telefono = sc.nextLine();

            System.out.print("Correo electrónico: ");
            String correo = sc.nextLine();

            System.out.print("Dirección: ");
            String direccion = sc.nextLine();

            System.out.print("¿Activo? (true/false): ");
            while (!sc.hasNextBoolean()) {
                System.out.print("Por favor, ingrese true o false: ");
                sc.next();
            }
            boolean activo = sc.nextBoolean();
            sc.nextLine();

            // Se crea el objeto dueño con todos los datos
            var nuevoDueno = new Dueno();
            nuevoDueno.setDocumentoIdentidad(documento);
            nuevoDueno.setNombreCompleto(nombre);
            nuevoDueno.setTelefono(telefono);
            nuevoDueno.setEmail(correo);
            nuevoDueno.setDireccion(direccion);
            nuevoDueno.setActivo(activo);

            // Se registra usando el controlador
            String resultadoRegistro = duenoController.registrarDueno(nuevoDueno);
            System.out.println(resultadoRegistro);

            // Si fue exitoso, lo volvemos a buscar para continuar
            dueno = duenoController.econtrarPorId(documento);

            if (dueno == null) {
                System.out.println("Error al registrar el nuevo dueño. No se puede continuar con la adopción.");
                return;
            }
        } else {
            System.out.println("Operación cancelada.");
            return;
        }
    }

        System.out.println("Dueño asignado: " + dueno.getNombreCompleto());

        System.out.print("\n¿Deseas actualizar información de la mascota antes de asignarla? (1. Sí / 2. No): ");
        int opcionActualizar = sc.nextInt();
        sc.nextLine();

        if (opcionActualizar == 1) {
            System.out.print("Nuevo peso (kg): ");
            while (!sc.hasNextDouble()) {
                System.out.print("Por favor, ingrese un número válido: ");
                sc.next();
            }
            double nuevoPeso = sc.nextDouble();
            sc.nextLine();

            System.out.print("Nuevas condiciones médicas (vacío si no hay cambios): ");
            String nuevasCondiciones = sc.nextLine();

            adopcionController.actualizarInformacionMascota(
                    mascotaAdopcion.getMascotaId(),
                    nuevoPeso,
                    nuevasCondiciones
            );
            System.out.println("Información de la mascota actualizada correctamente.");
        }

        adopcionController.actualizarDuenoMascota(
                mascotaAdopcion.getMascotaId(),
                dueno.getId()
        );

        System.out.println("Dueño actualizado correctamente. Adopción registrada exitosamente.");
    }



    private void actualizarEstadoAdopcion() {
        System.out.print("\nIngrese el ID de la mascota en adopción: ");
        while (!sc.hasNextInt()) {
            System.out.print("Por favor, ingrese un número válido: ");
            sc.next();
        }
        int id = sc.nextInt();
        sc.nextLine();

        System.out.println("Seleccione el nuevo estado:");
        System.out.println("1. Disponible");
        System.out.println("2. En_Proceso");
        System.out.println("3. Adoptada");
        System.out.print("Opción: ");
        int opt = sc.nextInt();
        sc.nextLine();

        String estado = switch (opt) {
            case 2 -> "En_Proceso";
            case 3 -> "Adoptada";
            default -> "Disponible";
        };

        String resultado = adopcionController.actualizarEstadoAdopcion(id, estado);
        System.out.println(resultado);
    }
}
