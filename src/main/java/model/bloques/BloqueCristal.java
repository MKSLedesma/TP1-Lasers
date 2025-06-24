package model.bloques;

import model.Laser;
import model.Posicion;

public class BloqueCristal extends Bloque{

    @Override
    public void interactuarConLaser(Laser laser) {

    }

    @Override
    public boolean esMovible() {
        return true;
    }
}
