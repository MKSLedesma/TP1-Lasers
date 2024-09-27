package model.bloques;

import model.Laser;

public class BloqueEspejo extends Bloque{
    public BloqueEspejo(String tipo) {
        super("R", false);
    }

    @Override
    public void interactuarLaser(Laser laser) {
        System.out.println("El láser es reflejado por el bloque espejo.");
        laser.reflejar();
    }
}
