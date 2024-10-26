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
        // Verificar si la posición está dentro de los límites del tablero a través del método de Nivel
        if (nivel.estaDentroDeLimites(getCoordenada().getX(), getCoordenada().getY()) && estaActivo()) {

            // Verificar si el láser ha alcanzado algún objetivo
            for (Objetivo objetivo : nivel.getObjetivos()) {
                if (getCoordenada().equals(objetivo.getPosicion())) {
                    objetivo.setActivo(true);
                }
            }

            Coordenada coor = getCoordenada();

            // Obtener el bloque a través de un método de Nivel
            Bloque bloque = nivel.obtenerBloqueEn(coor.getX(), coor.getY());

            // Interactuar con el bloque si no es un BloqueVacio
            if (bloque != null && !(bloque instanceof BloqueVacio)) {
                bloque.interactuarLaser(this, nivel);
                if (!this.estaActivo()) {
                    return;
                }
            }

            // Mover el láser si todavía está activo
            if (estaActivo) {
                this.x += direccion.getDeltaX();
                this.y += direccion.getDeltaY();
            }
        } else {
            desactivar(); // Desactivar el láser si sale del tablero
        }
    }

    public void atravesar(int x, int y) {
        this.x += x;
        this.y += y;
    }

    public void desactivar() {
        this.estaActivo = false;
    }

    public void setDireccion(Direccion nuevaDireccion) {
        this.direccion = nuevaDireccion;
    }

    public Emisor getEmisor() {
        return emisor;
    }

}
