package model;

public enum Direccion {
    SE, SW, NE, NW;

    public static Direccion crearPorString(String direccion) {
        return switch (direccion.toUpperCase()) {
            case "NE" -> NE;
            case "SW" -> SW;
            case "SE" -> SE;
            case "NW" -> NW;
            default -> throw new IllegalArgumentException("La direccion '" + direccion + "' no existe");
        };
    }
}
