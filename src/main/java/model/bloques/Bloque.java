package model.bloques;

import model.Laser;

public abstract class Bloque {
    private final String tipo;
    private final boolean esMovible;

    public Bloque(String tipo, boolean esMovible) {
        this.tipo = tipo;
        this.esMovible = esMovible;
    }
}

