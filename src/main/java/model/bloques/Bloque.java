package model.bloques;

import model.Laser;
import model.Interfaz;

public abstract class Bloque implements Interfaz {
    private final String tipo;
    private final boolean esMovible;

    public Bloque(String tipo, boolean esMovible) {
        this.tipo = tipo;
        this.esMovible = esMovible;
    }

    @Override
    public String representacion() {
        return tipo;
    }

    public abstract void interactuarLaser(Laser laser);
}

