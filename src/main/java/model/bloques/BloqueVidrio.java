package model.bloques;

import model.Laser;

public class BloqueVidrio extends Bloque{
    public BloqueVidrio(String tipo) {
        super("G", false);
    }

    @Override
    public void interactuarLaser(Laser laser) {
        System.out.println("El láser atraviesa el bloque de vidrio.");
    }
}
