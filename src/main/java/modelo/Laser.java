package modelo;

import modelo.bloques.Bloque;
import modelo.bloques.BloqueVacio;

public class Laser {
    private int x;
    private int y;
    private Direccion direccion;
    private boolean estaActivo;
    private final Emisor emisor;

    public Laser(int inicioX, int inicioY, Direccion direccion, Emisor emisor) {
        this.x = inicioX;
        this.y = inicioY;
        this.direccion = direccion;
        this.estaActivo = true;
        this.emisor = emisor;
    }

    public Coordenada getCoordenada() {
        return new Coordenada(x, y);
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public boolean estaActivo() {
        return estaActivo;
    }

    public void mover(Nivel nivel) {
        if (nivel.getTablero().getFilas() > getCoordenada().getY() &&
                nivel.getTablero().getColumnas() > getCoordenada().getX() &&
                0 <= getCoordenada().getX() && 0 <= getCoordenada().getY() &&
                estaActivo()) {

            for (Objetivo objetivo : nivel.getObjetivos()){
                if (getCoordenada().equals(objetivo.getPosicion())){
                    objetivo.setActivo(true);
                }
            }

            Coordenada coor = getCoordenada();

            Bloque bloque = nivel.getTablero().getBloqueEn(coor.getX(), coor.getY());

            if (bloque != null && !(bloque instanceof BloqueVacio)) {
                bloque.interactuarLaser(this, nivel.getTablero());
                if (!this.estaActivo()){ return;}
            }
            if (estaActivo) {
                this.x += direccion.getDeltaX();
                this.y += direccion.getDeltaY();
            }
        }
        else {desactivar();}
    }

    public void atravesar(int x, int y){
        this.x += x;
        this.y += y;
    }


    public void desactivar() {
        this.estaActivo = false;
    }

    public void setDireccion(Direccion nuevaDireccion) {
        this.direccion = nuevaDireccion;
    }

    public Emisor getEmisor(){return emisor;}
}

