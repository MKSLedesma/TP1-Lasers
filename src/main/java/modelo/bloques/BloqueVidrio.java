package modelo.bloques;

import modelo.Laser;
import modelo.Tablero;

public class BloqueVidrio extends Bloque {
    public BloqueVidrio(int centroX, int centroY) {super(centroX, centroY, "G", true);}

    @Override
    public void interactuarLaser(Laser laser, Tablero tablero) {
    }
}
