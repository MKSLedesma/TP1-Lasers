package modelo.bloques;

import modelo.Coordenada;
import modelo.Lado;
import modelo.Laser;
import modelo.Nivel;

import java.util.ArrayList;
import java.util.List;

public abstract class Bloque {
    private int centroX;
    private int centroY;
    private final String tipo;
    private Lado lado;
    private final boolean esMovible;

    public Bloque(int centroX, int centroY, String tipo, boolean esMovible) {
        this.centroX = centroX;
        this.centroY = centroY;
        this.tipo = tipo;
        this.lado = Lado.CENTRO;
        this.esMovible = esMovible;
    }

    public Bloque clonar() {
        return new Bloque(this.centroX, this.centroY, this.tipo, this.esMovible) {
            @Override
            public void interactuarLaser(Laser laser, Nivel nivel) {
            }
        };
    }

    public String getTipo() {
        return tipo;
    }

    public int getCentroX() {
        return centroX;
    }

    public int getCentroY() {
        return centroY;
    }

    public void setCentroX(int x) {
        centroX = x;
    }

    public void setCentroY(int y) {
        centroY = y;
    }

    public void setLado(Lado lado) {
        this.lado = lado;
    }

    public Lado getLado() {
        return lado;
    }

    public List<Coordenada> getCoordenadasOcupadas() {
        List<Coordenada> posiciones = new ArrayList<>();
        posiciones.add(new Coordenada(centroX, centroY)); // Solo el centro
        posiciones.add(new Coordenada(centroX + 1, centroY));
        posiciones.add(new Coordenada(centroX, centroY + 1));
        posiciones.add(new Coordenada(centroX - 1, centroY));
        posiciones.add(new Coordenada(centroX, centroY - 1));
        return posiciones;
    }

    public boolean esMovible() {
        return esMovible;
    }

    public abstract void interactuarLaser(Laser laser, Nivel nivel);
}