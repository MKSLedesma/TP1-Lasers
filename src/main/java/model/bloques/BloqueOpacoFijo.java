package model.bloques;

import model.Laser;

public class BloqueOpacoFijo extends Bloque {

    @Override
    public void interactuarConLaser(Laser laser) {

    }

    @Override
    public boolean esMovible() {
        return false;
    }
}
