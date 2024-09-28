package model;

import model.Laser;

public class Emisor implements Interfaz {
    private int x;
    private int y;
    private Direccion direccion;

    public Emisor(int x, int y, Direccion direccion) {
    this.x = x;
    this.y = y;
    this.direccion = direccion;
    }

    @Override
    public void interactuarLaser(Laser laser) {
        laser.emitir();
    }

    @Override
    public String representacion() {
        return "E";
    }
}
