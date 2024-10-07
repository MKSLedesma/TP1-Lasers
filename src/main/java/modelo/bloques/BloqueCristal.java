package modelo.bloques;

import modelo.Direccion;
import modelo.Laser;
import modelo.Tablero;

public class BloqueCristal extends Bloque {
    public BloqueCristal(int centroX, int centroY) {
        super(centroX, centroY, "R");
    }

    @Override
    public void interactuarLaser(Laser laser, Tablero tablero) {
        laser.setDireccion(invertirDireccion(laser.getDireccion()));
        laser.mover();
    }

    private Direccion invertirDireccion(Direccion dir) {
        if (dir == Direccion.NE) {
            return Direccion.NW;
        } else if (dir == Direccion.NW) {
            return Direccion.NE;
        } else if (dir == Direccion.SE) {
            return Direccion.SW;
        } else if (dir == Direccion.SW) {
            return Direccion.SE;
        } else {
            return dir;
        }
    }
}
