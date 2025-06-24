package model;

import java.util.ArrayList;
import java.util.List;

public class Laser {
    private final Posicion salida;
    private final Direccion direccionSalida;
    private Posicion posicionActual;
    private Direccion direccionActual;
    private boolean estaActivo;
    private List<Posicion> trayectoria;

    public Laser(Posicion salida, Direccion direccionSalida) {
        this.salida = salida;
        this.direccionSalida = direccionSalida;
        this.posicionActual = salida;
        this.direccionActual = direccionSalida;
        this.estaActivo = true;
        this.trayectoria = new ArrayList<>();
        trayectoria.add(salida);
    }

    public void avanzar() {
        if (!estaActivo) return;
        Posicion nuevaPos = posicionActual.posicionSiguiente(direccionActual);
        trayectoria.add(nuevaPos);
        setPosicion(nuevaPos);
    }

    public void detener() {
        this.estaActivo = false;
    }

    public void cambiarDireccion(Direccion direccion) {
        this.direccionActual = direccion;
    }

    public Direccion getDireccion() {
        return direccionActual;
    }

    public void setPosicion(Posicion posicion) {
        this.posicionActual = posicion;
    }

    public boolean estaActivo() {
        return estaActivo;
    }

    public List<Posicion> getTrayectoria() {
        return new ArrayList<>(trayectoria);
    }
}
