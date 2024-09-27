package model.bloques;

import model.Laser;

public class BloqueCristal extends Bloque{
    public BloqueCristal(String tipo) {
        super("C", false);
    }

    @Override
    public void interactuarLaser(Laser laser) {
        System.out.println("El láser atraviesa el bloque de cristal.");
    }
}
