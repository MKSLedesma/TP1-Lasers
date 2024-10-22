package modelo;

import modelo.bloques.Bloque;

import java.util.ArrayList;
import java.util.List;

public class Tablero {
    private final Bloque[][] tablero;
    private final List<Bloque> bloques;
    private final List<Objetivo> objetivos;

    public Tablero(int filas, int columnas) {
        this.tablero = new Bloque[filas][columnas];
        this.bloques = new ArrayList<>();
        this.objetivos = new ArrayList<>();
    }

    public void addBloque(Bloque bloque) {
        if (bloque.getCentroX() < 0 || bloque.getCentroX() >= getColumnas()
                || bloque.getCentroY() < 0 || bloque.getCentroY() >= getFilas()){
            return;
        }

        tablero[bloque.getCentroY()][bloque.getCentroX()] = bloque;
        agregarLado(bloque, bloque.getCentroX() - 1, bloque.getCentroY(), Lado.OESTE);
        agregarLado(bloque, bloque.getCentroX() + 1, bloque.getCentroY(), Lado.ESTE);
        agregarLado(bloque, bloque.getCentroX(), bloque.getCentroY() - 1, Lado.NORTE);
        agregarLado(bloque, bloque.getCentroX(), bloque.getCentroY() + 1, Lado.SUR);
    }

    private void agregarLado(Bloque bloque, int x, int y, Lado lado) {
        if (x >= 0 && x < getColumnas() && y >= 0 && y < getFilas()) {
            Bloque copia = bloque.clonar();  // Crea una nueva copia
            copia.setLado(lado);  // Asigna la dirección como cara
            tablero[y][x] = copia;  // Coloca la copia en la matriz
        }
    }

    public boolean estaDentroTablero(int x, int y) { return x >= 0 && x < getColumnas() && y >= 0 && y < getFilas();}

    // Verifica si una posición está ocupada por un bloque.

    public boolean estaOcupado(int x, int y) {
        if (estaDentroTablero(x, y)) {
            return tablero[y][x] != null;
        }
        return false;
    }

    public Objetivo getObjetivoEn(int x, int y) {
        for (Objetivo obj : objetivos) {
            if (obj.getPosicion().getX() == x && obj.getPosicion().getY() == y && !obj.estaActivo()) {
                return obj;
            }
        }
        return null;
    }

    public Bloque getBloqueEn(int x, int y) {
        return tablero[y][x];
    }

    public void marcarObjetivoComoActivo(Objetivo objetivo) {
        objetivo.setActivo(true);
    }

    public void setBloqueEn(int x, int y, Bloque bloque){tablero[y][x] = bloque;
    }

    public int getFilas(){return tablero.length;}

    public int getColumnas(){return tablero[0].length;}

    public List<Bloque> getBloques(){return bloques;}
}
