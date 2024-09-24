package model.bloques;

import model.Laser;

public abstract class Bloque {
    private String tipo;
    private boolean esMovible;

    public Bloque(String tipo) {
        this.tipo = tipo;
    }

    public abstract void interactuarLaser(Laser laser);
}
