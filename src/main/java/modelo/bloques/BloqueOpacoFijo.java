package modelo.bloques;

import modelo.Laser;
import modelo.Tablero;

public class BloqueOpacoFijo extends Bloque {
    public BloqueOpacoFijo(int centroX, int centroY) {
        super(centroX, centroY, "F", false);
    }

    @Override
    public BloqueOpacoFijo clonar(){
        return new BloqueOpacoFijo(this.getCentroX(), this.getCentroY());
    }
    @Override
    public void interactuarLaser(Laser laser, Tablero tablero) {
        laser.desactivar();
    }
}
