package model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Nivel {
    private boolean juegoTerminado;
    private boolean objetivoAlcanzado;
    private ArrayList<Emisor> emisores;
    private ArrayList<Objetivo> objetivos;
    private Tablero tablero;

    public Nivel(ArrayList<Emisor> emisores, ArrayList<Objetivo> objetivos, Tablero tablero){
        this.emisores = emisores;
        this.objetivos = objetivos;
        this.tablero = tablero;
    }
}
