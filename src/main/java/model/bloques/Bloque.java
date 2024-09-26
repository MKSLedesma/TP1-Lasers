package model.bloques;

import model.Laser;
import model.Interfaz;

public abstract class Bloque implements Interfaz {
    private String tipo;
    private boolean esMovible;

    public Bloque(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String representacion() {
        return tipo;
    }

    public abstract void interactuarLaser(Laser laser);
}
