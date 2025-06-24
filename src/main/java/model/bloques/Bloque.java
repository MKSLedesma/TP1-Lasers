package model.bloques;

import model.Laser;
import model.Posicion;

import java.util.ArrayList;
import java.util.List;

public abstract class Bloque {
    private List<Posicion> posiciones;

    public Bloque() {
        this.posiciones = new ArrayList<Posicion>();
    }

    public void setPosiciones(List<Posicion> posiciones) {
        this.posiciones = posiciones;
    }

    public List<Posicion> getPosiciones() {
        return posiciones;
    }

    public abstract void interactuarConLaser(Laser laser);

    public abstract boolean esMovible();
}

