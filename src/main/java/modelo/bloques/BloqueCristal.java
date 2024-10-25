package modelo.bloques;

import modelo.Lado;
import modelo.Laser;
import modelo.Tablero;

public class BloqueCristal extends Bloque {
    public BloqueCristal(int centroX, int centroY) {
        super(centroX, centroY, "C", true);
    }

    @Override
    public BloqueCristal clonar(){
        return new BloqueCristal(this.getCentroX(), this.getCentroY());
    }

    @Override
    public void interactuarLaser(Laser laser, Tablero tablero) {
        atravesarCristal(laser, this.getLado());
    }

    private void atravesarCristal(Laser laser, Lado lado) {
        switch (lado) {
            case NORTE: laser.atravesar(0, 2); break;
            case SUR: laser.atravesar(0, -2); break;
            case ESTE: laser.atravesar(-2, 0); break;
            case OESTE: laser.atravesar(2, 0); break;
        }
    }
}
