package modelo.bloques;

import modelo.Direccion;
import modelo.Laser;
import modelo.Tablero;

public class BloqueVidrio extends Bloque {
    public BloqueVidrio(int centroX, int centroY) {
        super(centroX, centroY, "G");
    }

    @Override
    public void interactuarLaser(Laser laser, Tablero tablero) {
        laser.mover();
    }
}
