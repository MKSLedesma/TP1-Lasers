package modelo.bloques;

import modelo.Laser;
import modelo.Nivel;
import modelo.Direccion;
import modelo.Coordenada;
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

        Laser laserClonado = new Laser(
                laser.getCoordenada().getX() + laser.getDireccion().getDeltaX() * 2,
                laser.getCoordenada().getY() + laser.getDireccion().getDeltaY() * 2,
                laser.getDireccion(),
                laser.getEmisor()
        );

        nivel.getLasers().add(laserClonado);
        laser.setDireccion(direccionReflejada);
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
