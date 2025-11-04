package model.enums;

public enum TipoBeneficio {
    DESCUENTO("Descuento"),
    SERVICIO_GRATIS("Servicio Gratis"),
    PRODUCTO_GRATIS("Producto Gratis"),
    PUNTOS_EXTRA("Puntos Extra");

    private final String nombreAmigable;

    TipoBeneficio(String nombreAmigable) {
        this.nombreAmigable = nombreAmigable;
    }

    @Override
    public String toString() {
        return nombreAmigable;
    }
}