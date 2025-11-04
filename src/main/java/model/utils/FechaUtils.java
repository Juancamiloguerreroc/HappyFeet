package model.utils;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class FechaUtils {

    public static final String FORMATO_FECHA = "yyyy-MM-dd";
    public static final String FORMATO_HORA = "HH:mm";
    public static final String FORMATO_FECHA_HORA = "yyyy-MM-dd HH:mm:ss";

    /**
     * Verifica si una fecha o fecha-hora es válida según el formato indicado.
     */
    public static boolean esFechaValida(String fechaStr, String formato) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formato);

            if (tieneHora(formato)) {
                LocalDateTime.parse(fechaStr, formatter);
            } else {
                LocalDate.parse(fechaStr, formatter);
            }
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Determina si un formato contiene componentes de hora.
     */
    public static boolean tieneHora(String formato) {
        return formato.contains("H") || formato.contains("m") || formato.contains("s");
    }

    /**
     * Verifica si una fecha es futura.
     */
    public static boolean esFechaFutura(String fechaStr, String formato) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formato);

            if (tieneHora(formato)) {
                LocalDateTime fechaIngresada = LocalDateTime.parse(fechaStr, formatter);
                return fechaIngresada.isAfter(LocalDateTime.now());
            } else {
                LocalDate fechaIngresada = LocalDate.parse(fechaStr, formatter);
                return fechaIngresada.isAfter(LocalDate.now());
            }

        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Verifica si una fecha es pasada.
     */
    public static boolean esFechaPasada(String fechaStr, String formato) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formato);

            if (tieneHora(formato)) {
                LocalDateTime fechaIngresada = LocalDateTime.parse(fechaStr, formatter);
                return fechaIngresada.isBefore(LocalDateTime.now());
            } else {
                LocalDate fechaIngresada = LocalDate.parse(fechaStr, formatter);
                return fechaIngresada.isBefore(LocalDate.now());
            }

        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Convierte una cadena (yyyy-MM-dd) en java.sql.Date.
     */
    public static Date parseFecha(String fechaStr) {
        try {
            LocalDate fecha = LocalDate.parse(fechaStr, DateTimeFormatter.ofPattern(FORMATO_FECHA));
            return Date.valueOf(fecha);
        } catch (DateTimeParseException e) {
            System.out.println("Formato de fecha inválido. Use " + FORMATO_FECHA);
            return null;
        }
    }

    /**
     * Convierte una cadena (HH:mm o HH:mm:ss) en java.sql.Time.
     */
    public static Time parseHora(String horaStr) {
        try {
            if (horaStr.length() == 5) { // si solo tiene HH:mm
                horaStr += ":00";
            }
            LocalTime hora = LocalTime.parse(horaStr, DateTimeFormatter.ofPattern("HH:mm:ss"));
            return Time.valueOf(hora);
        } catch (DateTimeParseException e) {
            System.out.println("Formato de hora inválido. Use HH:mm o HH:mm:ss");
            return null;
        }
    }

    /**
     * Devuelve la fecha y hora actual del sistema en java.sql.Timestamp.
     */
    public static Timestamp obtenerFechaHoraActual() {
        return Timestamp.valueOf(LocalDateTime.now());
    }

    /**
     * Convierte LocalDate a java.sql.Date.
     */
    public static Date convertirLocalDate(LocalDate fecha) {
        return Date.valueOf(fecha);
    }

    /**
     * Convierte LocalDateTime a java.sql.Timestamp.
     */
    public static Timestamp convertirLocalDateTime(LocalDateTime fechaHora) {
        return Timestamp.valueOf(fechaHora);
    }

    /**
     * Convierte LocalTime a java.sql.Time.
     */
    public static Time convertirLocalTime(LocalTime hora) {
        return Time.valueOf(hora);
    }

    /**
     * Formatea una fecha LocalDate a cadena según formato estándar.
     */
    public static String formatearFecha(LocalDate fecha) {
        return fecha.format(DateTimeFormatter.ofPattern(FORMATO_FECHA));
    }

    /**
     * Formatea una fecha y hora LocalDateTime a cadena.
     */
    public static String formatearFechaHora(LocalDateTime fechaHora) {
        return fechaHora.format(DateTimeFormatter.ofPattern(FORMATO_FECHA_HORA));
    }
}
