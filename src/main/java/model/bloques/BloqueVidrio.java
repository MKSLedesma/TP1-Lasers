package model.bloques;

import model.Laser;

public class BloqueVidrio extends Bloque{

    @Override
    public void interactuarConLaser(Laser laser) {

    }

    @Override
    public boolean esMovible() {
        return true;
    }
}
