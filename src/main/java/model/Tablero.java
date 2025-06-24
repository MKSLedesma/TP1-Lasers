package model;

import java.util.HashMap;
import java.util.Map;

public class Tablero {
    private final Map<Posicion, Celda> celdas;

    public Tablero(){
        celdas = new HashMap<>();
    }

    public void agregarCelda(Celda celda){
        Posicion posicion = new Posicion(celda.getX(), celda.getY());
        celdas.put(posicion, celda);
    }

    public Celda getCelda(Posicion posicion) {
        return celdas.get(posicion);
    }
}


