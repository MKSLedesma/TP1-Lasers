package modelo.bloques;

import modelo.Laser;
import modelo.Tablero;

public class BloqueVacio extends Bloque {
    public BloqueVacio(int centroX, int centroY) {
        super(centroX, centroY, ".", false);
    }

    @Override
    public void interactuarLaser(Laser laser, Tablero tablero) {
    }
}
