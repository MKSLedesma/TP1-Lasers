package model.bloques;

import model.Laser;

public class BloqueOpacoFijo extends Bloque{
    public BloqueOpacoFijo(String tipo) {
        super("F", false);
    }

    @Override
    public void interactuarLaser(Laser laser) {
        System.out.println("El láser se detiene al chocar con un bloque opaco fijo.");
        laser.detener();
    }
}
