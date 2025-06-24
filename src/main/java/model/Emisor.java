package model;

import model.Laser;

public class Emisor {
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
}
