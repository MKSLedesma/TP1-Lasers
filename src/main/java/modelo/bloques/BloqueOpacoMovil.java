package modelo.bloques;

import modelo.Laser;
import modelo.Tablero;

public class BloqueOpacoMovil extends Bloque {
    public BloqueOpacoMovil(int centroX, int centroY) {
        super(centroX, centroY, "B", true);
    }

    @Override
    public BloqueOpacoMovil clonar() {
        return new BloqueOpacoMovil(this.getCentroX(), this.getCentroY());
    }
    @Override
    public void interactuarLaser(Laser laser, Tablero tablero) {
        laser.desactivar();
    }
}
