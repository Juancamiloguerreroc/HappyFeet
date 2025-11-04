package model.enums;

public enum EstadoCajaBeneficio {
    PENDIENTE("Pendiente"),
    APLICADO("Aplicado"),
    EXPIRADO("Expirado");

    private final String nombreAmigable;

    EstadoCajaBeneficio(String nombreAmigable) {
        this.nombreAmigable = nombreAmigable;
    }

    public String getNombreAmigable() {
        return nombreAmigable;
    }

    @Override
    public String toString() {
        return nombreAmigable;
    }

    // Convierte el texto de BD al enum correcto
    public static EstadoCajaBeneficio desdeNombre(String nombre) {
        for (EstadoCajaBeneficio e : values()) {
            if (e.nombreAmigable.equalsIgnoreCase(nombre)) {
                return e;
            }
        }
        throw new IllegalArgumentException("EstadoCajaBeneficio desconocido: " + nombre);
    }
}
