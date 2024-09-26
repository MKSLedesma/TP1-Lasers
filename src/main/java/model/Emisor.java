package model;

import model.Laser;

public class Emisor implements Interfaz {

    @Override
    public void interactuarLaser(Laser laser) {
        laser.emitir();
    }

    @Override
    public String representacion() {
        return "E";
    }
}
