package model.bloques;

import model.Laser;

public class BloqueOpacoFijo extends Bloque{
    public BloqueOpacoFijo(String tipo) {
        super("F");
    }

    @Override
    public void interactuarLaser(Laser laser) {

    }
}
