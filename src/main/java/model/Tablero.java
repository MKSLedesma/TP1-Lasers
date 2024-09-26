package model;

import model.bloques.Bloque;
import model.bloques.BloqueVacio;

public class Tablero {
    private Interfaz[][] tablero;
    private final int filas;
    private final int columnas;

    public Tablero(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.tablero = new Interfaz[filas][columnas];
        crearTablero();
    }

    public void crearTablero() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                tablero[i][j] = new BloqueVacio(" ");
            }
        }
    }


    public void posicionarObjeto(int fila, int columna, Interfaz elemento) {
        if (fila >= 0 && fila < filas && columna >= 0 && columna < columnas) {
            tablero[fila][columna] = elemento;
        }
    }

    public Interfaz obtenerValor(int fila, int columna) {
        return tablero[fila][columna];
    }

    public boolean alcanzoObjetivo(int x, int y) {
        return tablero[x][y] instanceof Objetivo;
    }

    public void moverObjeto(int filaActual, int columnaActual, int nuevaFila, int nuevaColumna) {
        tablero[nuevaFila][nuevaColumna] = tablero[filaActual][columnaActual];
        tablero[filaActual][columnaActual] = new BloqueVacio("");
    }

    public void imprimirTablero() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print(tablero[i][j].representacion() + " ");
            }
            System.out.println();
        }
    }
}
