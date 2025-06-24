package model;

public class Laser {
    private int x;
    private int y;
    private Direccion direccion;
    private boolean estaActivo;

    public Laser(int x, int y, Direccion direccion) {
        this.x = x;
        this.y = y;
        this.direccion = direccion;
        this.estaActivo = true;
    }
}
