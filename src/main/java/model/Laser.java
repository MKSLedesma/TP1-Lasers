package model;

import model.bloques.*;

public class Laser {
    private int x;
    private int y;
    private Direccion direccion;
    private Tablero tablero;
    private boolean estaActivo;

    public Laser(int x, int y, Direccion direccionInicial, Tablero tableroInicial) {
        this.direccion = direccionInicial;
        this.x = x;
        this.y = y;
        this.tablero = tableroInicial;
        estaActivo = true;
    }

    public void emitir(){
        while(estaActivo){
            mover();
            comprobarInteraccion();
        }
    }

    private void comprobarInteraccion() {
        Bloque bloque = tablero.obtenerValor(x, y);
        if (bloque != null) {
            if (bloque instanceof BloqueCristal){
                reflejar();
            }
            else if (bloque instanceof BloqueOpacoFijo || bloque instanceof BloqueOpacoMovil){
                detener();
            }
            else if (bloque instanceof BloqueVidrio){
                dividir();
            }
            else if (bloque instanceof BloqueEspejo){
                reflejar();
            }
        }

        /* IMPLEMENTAR METODO
        if (tablero.alcanzoObjetivo(x,y)){
            System.out.println("Llegaste al objetivo");
            estaActivo = false;
        }
        */
    }

    private void mover() {
        switch (direccion) {
            case SE -> {x += 1;y += 1;}
            case SW -> {x -= 1;y += 1;}
            case NE -> {x += 1;y -= 1;}
            case NW -> {x -= 1;y -= 1;}
        }
    }

    public void detener(){
        estaActivo = false;
    }

    public void reflejar(){

    }

    public void dividir(){

    }
}
