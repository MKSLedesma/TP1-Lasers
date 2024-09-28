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
    private int[] posicionLaser;
    private int[] posicionObjetivo;

    public Nivel(ArrayList<Emisor> emisores, ArrayList<Objetivo> objetivos, Tablero tablero){
        this.emisores = emisores;
        this.objetivos = objetivos;
        this.tablero = tablero;
    }

    public void moverLaser() {
        tablero.moverObjeto(posicionLaser[0], posicionLaser[1], posicionLaser[0] + 1, posicionLaser[1]);
    }

    public void actualizarEstado() {
        Laser laser = new Laser(posicionLaser[0], posicionLaser[1], Direccion.SE, tablero);
        laser.emitir();
        objetivoAlcanzado = tablero.alcanzoObjetivo(posicionLaser[0], posicionLaser[1]);
    }

    public boolean objetivoAlcanzado() {
        return tablero.alcanzoObjetivo(posicionLaser[0], posicionLaser[1]);
    }

    public boolean juegoTerminado() {
        return juegoTerminado;
    }

    public void setJuegoTerminado(boolean terminado) {
        this.juegoTerminado = terminado;
    }
}
