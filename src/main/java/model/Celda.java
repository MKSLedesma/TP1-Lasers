package model;

import model.bloques.Bloque;

public class Celda {
    private final int x;
    private final int y;
    private boolean tienePiso;
    private Bloque bloque;

    public Celda(int x, int y) {
        this.x = x;
        this.y = y;
        this.tienePiso = false;
        this.bloque = null;
    }

    public boolean estaVacia(){
        return bloque == null && tienePiso == false;
    }

    public void setBloque(Bloque bloque){
        if(estaVacia()){
            this.bloque = bloque;
        }
    }

    public void removeBloque(Bloque bloque){
        if(this.bloque != null){
            this.bloque = null;
        }
    }

    public void setPiso(boolean tienePiso){
        this.tienePiso = tienePiso;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public Bloque getBloque(){
        return bloque;
    }
}
