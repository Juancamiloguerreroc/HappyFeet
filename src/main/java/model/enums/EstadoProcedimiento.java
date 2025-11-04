package model.enums;

public enum EstadoProcedimiento {
    Programado("Programado"),
    En_Proceso("En proceso"),
    Finalizado("Finalizado"),
    Cancelado("Cancelado");

    private final String nombreAmigable;

    EstadoProcedimiento(String nombreAmigable) {
        this.nombreAmigable = nombreAmigable;
    }

    public String getNombreAmigable() {
        return nombreAmigable;
    }

    public static EstadoProcedimiento desdeNombre(String nombre) {
        for (EstadoProcedimiento e : values()) {
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
