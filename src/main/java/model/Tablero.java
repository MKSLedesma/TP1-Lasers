package model;

import model.bloques.Bloque;
import model.bloques.BloqueVacio;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Tablero {
    ArrayList<ArrayList<Bloque>> tablero;

    public Tablero(ArrayList<ArrayList<Bloque>> tablero){
        this.tablero = tablero;
    }

    public void setBloque(Bloque bloque, int fila, int columna){
        tablero.get(fila).set(columna, bloque);
    }

    public int getFilas(){
        return tablero.size();
    }

    public int getColumnas(){
        return tablero.getFirst().size();
    }
}


