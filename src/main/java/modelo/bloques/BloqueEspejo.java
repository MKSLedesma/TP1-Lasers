package modelo.bloques;

import modelo.Direccion;
import modelo.Lado;
import modelo.Laser;
import modelo.Tablero;

public class BloqueEspejo extends Bloque {
    public BloqueEspejo(int centroX, int centroY) {
        super(centroX, centroY, "R", true);
    }

    @Override
    public BloqueEspejo clonar(){
        return new BloqueEspejo(this.getCentroX(), this.getCentroY());
    }

    @Override
    public void interactuarLaser(Laser laser, Tablero tablero) {
        laser.setDireccion(invertirDireccion(laser.getDireccion(), this.getLado()));
        laser.mover();
    }

    private Direccion invertirDireccion(Direccion dir, Lado lado) {
        switch (lado) {
            case NORTE:
                if (dir == Direccion.SE) return Direccion.NE;
                else if (dir == Direccion.SW) return Direccion.NW;
            case SUR:
                if (dir == Direccion.NE) return Direccion.SE;
                else if (dir == Direccion.NW) return Direccion.SW;
            case ESTE:
                if (dir == Direccion.NW) return Direccion.NE;
                else if (dir == Direccion.SW) return Direccion.SE;
            case OESTE:
                if (dir == Direccion.SE) return Direccion.SW;
                else if (dir == Direccion.NE) return Direccion.NW;

        }
        return dir;
    }
}
