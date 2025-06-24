package model;

public class Posicion {
    private final int x;
    private final int y;

    public Posicion(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Posicion posicionSiguiente(Direccion direccion) {
        return switch (direccion) {
            case NE -> new Posicion(x + 1, y - 1);
            case NW -> new Posicion(x - 1, y - 1);
            case SE -> new Posicion(x + 1, y + 1);
            case SW -> new Posicion(x - 1, y + 1);
        };
    }
}
