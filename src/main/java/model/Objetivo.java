package model;

public class Objetivo implements Interfaz {
    private int x;
    private int y;

    public Objetivo(int x, int y) {
    this.x = x;
    this.y = y;
    }

    @Override
    public void interactuarLaser(Laser laser) {
        laser.detener(); 
        System.out.println("¡Llegaste al objetivo!");
    }

    @Override
    public String representacion() {
        return "O"; 
    }
}
