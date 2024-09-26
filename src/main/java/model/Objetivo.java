package model;

public class Objetivo implements Interfaz {

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
