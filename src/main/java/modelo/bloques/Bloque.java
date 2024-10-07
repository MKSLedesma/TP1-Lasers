package modelo.bloques;

import modelo.Coordenada;
import modelo.Laser;
import modelo.Tablero;

import java.util.ArrayList;
import java.util.List;

public abstract class Bloque {
    private int centroX;
    private int centroY;
    private String tipo;

    public Bloque(int centroX, int centroY, String tipo) {
        this.centroX = centroX;
        this.centroY = centroY;
        this.tipo = tipo;
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

    public void setCentroX(int x){centroX = x;}

    public void setCentroY(int y){centroY = y;}

    // Devuelve una lista de posiciones que ocupa el bloque en el tablero.
    public List<Coordenada> getCoordenadasOcupadas() {
        List<Coordenada> posiciones = new ArrayList<>();
        posiciones.add(new Coordenada(centroX, centroY)); // Solo el centro
        return posiciones;
    }


    public void interactuarLaser(Laser laser, Tablero tablero) {}
}
