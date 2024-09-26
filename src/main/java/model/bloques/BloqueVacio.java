package model.bloques;

import model.Laser;

public class BloqueVacio extends Bloque {
    public BloqueVacio(String tipo) {
        super(tipo);
    }

    @Override
    public void interactuarLaser(Laser laser) {
    }
}
