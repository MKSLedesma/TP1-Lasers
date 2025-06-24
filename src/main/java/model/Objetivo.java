package model;

public class Objetivo {
    private final Posicion posicion;
    private boolean estaActivo;

    public Objetivo(Posicion posicion) {
        this.posicion = posicion;
        this.estaActivo = false;
    }

    public boolean estaActivo() {
        return this.estaActivo;
    }

    public Posicion getPosicion(){
        return this.posicion;
    }

    public void setActivo(boolean estado){
        this.estaActivo = estado;
    }
}
