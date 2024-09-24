package model;

import model.bloques.Bloque;
import model.bloques.BloqueVacio;

public class Tablero {
    private Bloque[][] tablero;
    private final int filas;
    private final int columnas;

    private final char VACIO = ' ';
    private final char BLOQUE = 'B';
    private final char LASER = 'L';
    private final char OBJETIVO = 'O';

    public Tablero(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.tablero = new Bloque[filas][columnas];
        crearTablero();
    }

    public void crearTablero() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                tablero[i][j] = new BloqueVacio(" ");
            }
        }
    }

    public void posicionarObjeto(int fila, int columna, Bloque bloque) {
        if (fila >= 0 && fila < filas && columna >= 0 && columna < columnas) {
            tablero[fila][columna] = bloque;
        }
    }

    public Bloque obtenerValor(int fila, int columna) {
        return tablero[fila][columna];
    }

    public void moverObjeto(int filaActual, int columnaActual, int nuevaFila, int nuevaColumna) {
        if (nuevaFila >= 0 && nuevaFila < filas && nuevaColumna >= 0 && nuevaColumna < columnas) {
            Bloque bloque = tablero[filaActual][columnaActual];
            tablero[filaActual][columnaActual] = new BloqueVacio(" ");
            tablero[nuevaFila][nuevaColumna] = bloque;
        }
    }

    public void imprimirTablero() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println();
        }
    }
}
