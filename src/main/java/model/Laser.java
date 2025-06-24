package model;

public class Laser {
    private int x;
    private int y;
    private Direccion direccion;
    private Tablero tablero;
    private boolean estaActivo;

    public Laser(int x, int y, Direccion direccion, Tablero tablero) {
        this.x = x;
        this.y = y;
        this.direccion = direccion;
        this.tablero = tablero;
        this.estaActivo = true;
    }
}
