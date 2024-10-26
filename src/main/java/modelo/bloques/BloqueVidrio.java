package modelo.bloques;

import modelo.Laser;
import modelo.Nivel;
import modelo.Direccion;
import modelo.Lado;

public class BloqueVidrio extends Bloque {
    public BloqueVidrio(int centroX, int centroY) {
        super(centroX, centroY, "G", true);
    }

    @Override
    public BloqueVidrio clonar() {
        return new BloqueVidrio(this.getCentroX(), this.getCentroY());
    }

    @Override
    public void interactuarLaser(Laser laser, Nivel nivel) {
        Direccion direccionReflejada = invertirDireccion(laser.getDireccion(), this.getLado());
        laser.setDireccion(direccionReflejada);

        laser.atravesar(direccionReflejada.getDeltaX(), direccionReflejada.getDeltaY());

        nivel.getLasers().add(laser);

        Laser laserClonado = new Laser(
                laser.getCoordenada().getX(),
                laser.getCoordenada().getY(),
                laser.getDireccion(),
                laser.getEmisor()
        );

        atravesarCristal(laserClonado, this.getLado());

        nivel.getLasers().add(laserClonado);
    }

    private void atravesarCristal(Laser laser, Lado lado) {
        switch (lado) {
            case NORTE:
                laser.atravesar(0, 2);
                break;
            case SUR:
                laser.atravesar(0, -2);
                break;
            case ESTE:
                laser.atravesar(-2, 0);
                break;
            case OESTE:
                laser.atravesar(2, 0);
                break;
        }
    }

    private Direccion invertirDireccion(Direccion dir, Lado lado) {
        switch (lado) {
            case NORTE:
                if (dir == Direccion.NW) return Direccion.NE;
                else if (dir == Direccion.NE) return Direccion.NW;
                else if (dir == Direccion.SE) return Direccion.SW;
                else if (dir == Direccion.SW) return Direccion.SE;
                break;

            case SUR:
                if (dir == Direccion.NW) return Direccion.SW;
                else if (dir == Direccion.NE) return Direccion.SE;
                else if (dir == Direccion.SE) return Direccion.NE;
                else if (dir == Direccion.SW) return Direccion.NW;
                break;

            case ESTE:
                if (dir == Direccion.NW) return Direccion.SW;
                else if (dir == Direccion.NE) return Direccion.SE;
                else if (dir == Direccion.SE) return Direccion.NW;
                else if (dir == Direccion.SW) return Direccion.NE;
                break;

            case OESTE:
                if (dir == Direccion.NW) return Direccion.NE;
                else if (dir == Direccion.NE) return Direccion.NW;
                else if (dir == Direccion.SE) return Direccion.SW;
                else if (dir == Direccion.SW) return Direccion.SE;
                break;
        }
        return dir;
    }
}