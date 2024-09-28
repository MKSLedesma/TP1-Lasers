package model;

import model.Laser;

public class Emisor implements Interfaz {
    private int x;
    private int y;
    private Direccion direccion;
    private int rango;

    public Emisor(int x, int y, Direccion direccion, int rango) {
        this.x = x;
        this.y = y;
        this.direccion = direccion;
        this.rango = rango;
    }

    @Override
    public void interactuarLaser(Laser laser) {
        for (int i = 0; i < rango; i++) {
            for (int j = 0; j < rango; j++) {
                laser.emitirDesde(x + i, y + j);
            }
        }
    }

    @Override
    public String representacion() {
        return "E";
    }

    public int getX() { return x; }
    public int getY() { return y; }
}
