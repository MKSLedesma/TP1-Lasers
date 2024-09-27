package model.bloques;

import model.Laser;

public class BloqueOpacoMovil extends Bloque{
    public BloqueOpacoMovil(String tipo) {
        super("B", true);
    }

    @Override
    public void interactuarLaser(Laser laser) {
        System.out.println("El láser se detiene al chocar con un bloque opaco móvil.");
        laser.detener();
    }
}
