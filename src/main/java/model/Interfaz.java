package model;

import model.Laser;

public interface Interfaz {
    void interactuarLaser(Laser laser);  // Método para la interacción del láser con el elemento
    String representacion();               // Método para la representación visual en el tablero
}
