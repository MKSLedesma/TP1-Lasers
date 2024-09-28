package model;

import model.bloques.Bloque;
import model.bloques.BloqueVacio;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Tablero {
    private ArrayList<ArrayList<Interfaz>> tablero;

    public Tablero(ArrayList<ArrayList<Interfaz>> tablero){
        this.tablero = tablero;
    }

    public void posicionarObjeto(int fila, int columna, Interfaz elemento) {
        if (fila >= 0 && fila < obtenerFilas() && columna >= 0 && columna < obtenerColumnas()) {
            tablero.get(fila).set(columna, elemento);
        }
    }

    public Interfaz obtenerValor(int fila, int columna) {
        return tablero.get(fila).get(columna);
    }

    public boolean alcanzoObjetivo(int x, int y) {
        return tablero.get(x).get(y) instanceof Objetivo;
    }

    public void moverObjeto(int filaActual, int columnaActual, int nuevaFila, int nuevaColumna) {
        tablero.get(nuevaFila).set(nuevaColumna, tablero.get(filaActual).get(columnaActual));
        tablero.get(filaActual).set(columnaActual, new BloqueVacio(""));
    }

    public void imprimirTablero() {
        for (int i = 0; i < obtenerFilas(); i++) {
            for (int j = 0; j < obtenerColumnas(); j++) {
                System.out.print(tablero.get(i).get(j).representacion() + " ");
            }
            System.out.println();
        }
    }

    public int obtenerFilas(){
        return tablero.size();
    }

    public int obtenerColumnas(){
        return tablero.getFirst().size();
    }
}


