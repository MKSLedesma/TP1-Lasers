package model;

public class Objetivo implements Interfaz {

    @Override
    public void interactuarLaser(Laser laser) {
        // El láser ha alcanzado el objetivo
        laser.detener(); // Detiene el láser al alcanzar el objetivo
        System.out.println("¡Llegaste al objetivo!");
    }

    @Override
    public String representacion() {
        return "O"; // Representación del objetivo en el tablero
    }
}
