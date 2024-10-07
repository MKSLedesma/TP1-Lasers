package modelo;

import modelo.bloques.Bloque;

import java.util.ArrayList;
import java.util.List;

public class Tablero {
    private Bloque[][] tablero;
    private final List<Bloque> bloques;
    private final List<Objetivo> objetivos;

    public Tablero(int filas, int columnas) {
        this.tablero = new Bloque[filas][columnas];
        this.bloques = new ArrayList<>();
        this.objetivos = new ArrayList<>();
    }

    public void addBloque(Bloque bloque) {
        List<Coordenada> ocupadas = bloque.getCoordenadasOcupadas();
        boolean colision = false;

        for (Coordenada coor : ocupadas) {
            if (!estaDentroTablero(coor.getX(), coor.getY())) {
                System.out.println("Bloque fuera de los límites en (" + coor.getX() + ", " + coor.getY() + ")");
                return;
            }
            if (estaOcupado(coor.getX(), coor.getY())) {
                System.out.println("Bloque en (" + coor.getX() + ", " + coor.getY() + ") ocupado.");
                colision = true;
                break;
            }
        }

        if (!colision) {
            bloques.add(bloque);
            for (Coordenada coor : ocupadas) {
                tablero[coor.getY()][coor.getX()] = bloque;
            }
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

    public void setBloqueEn(int x, int y, Bloque bloque){
        tablero[x][y] = bloque;
    }

    public int getFilas(){return tablero.length;}

    public int getColumnas(){return tablero[0].length;}
}
