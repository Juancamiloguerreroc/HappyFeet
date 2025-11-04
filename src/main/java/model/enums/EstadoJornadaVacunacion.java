/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.enums;

/**
 *
 * @author crumbleeh
 */
public class EstadoJornadaVacunacion {
    public enum Estado {
        PLANIFICADA("Planificada"),
        EN_CURSO("En Curso"),
        FINALIZADA("Finalizada"),
        CANCELADA("Cancelada");

        private final String nombreAmigable;

        Estado(String nombreAmigable) {
            this.nombreAmigable = nombreAmigable;
        }

        public String getNombreAmigable() {
            return nombreAmigable;
        }

        public static Estado desdeNombre(String nombre) {
            for (Estado e : values()) {
                if (e.nombreAmigable.equalsIgnoreCase(nombre)) {
                    return e;
                }
            }
            return null;
        }

        @Override
        public String toString() {
            return nombreAmigable;
        }
    }
}
