package modelo;

import modelo.bloques.Bloque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Nivel {
    private Tablero tablero;
    private final List<Emisor> emisores;
    private List<Laser> lasers;
    private final List<Objetivo> objetivos;

    public Nivel(List<Emisor> emisores, List<Objetivo> objetivos, Tablero tablero) {
        this.emisores = emisores;
        this.objetivos = objetivos;
        this.tablero = tablero;
        this.lasers = new ArrayList<>();
    }

    public void emitirLasersIniciales() {
        for (Emisor emisor : emisores) {
            Laser laser = emisor.emitirLaser();
            if (laser != null) {
                lasers.add(laser);
            }
        }
    }

    public void actualizar() {
        Iterator<Laser> laserIterator = lasers.iterator();
        while (laserIterator.hasNext()) {
            Laser laser = laserIterator.next();
            if (!laser.estaActivo()) {
                continue;
            }

            laser.mover();
            Coordenada coorActual = laser.getCoordenada();

            // Verificar si el laser ha salido del tablero
            if (!tablero.estaDentroTablero(coorActual.getX(), coorActual.getY())) {
                System.out.println("Un láser salió del tablero sin colisionar.");
                laser.desactivar();
                continue;
            }

            // Verificar colision con bloque
            if (tablero.estaOcupado(coorActual.getX(), coorActual.getY())) {
                System.out.println("Un laser colisiono con un bloque en (" + coorActual.getX() + ", " + coorActual.getY());
                Bloque bloque = tablero.getBloqueEn(coorActual.getX(), coorActual.getY());
                bloque.interactuarLaser(laser, tablero);
                continue;
            }

            // Verificar si el laser ha alcanzado un objetivo
            Objetivo objetivoAlcanzado = tablero.getObjetivoEn(coorActual.getX(), coorActual.getY());
            if (objetivoAlcanzado != null) {
                System.out.println("Un laser ha alcanzado el objetivo en (" + coorActual.getX() + ", " + coorActual.getY());
                tablero.marcarObjetivoComoActivo(objetivoAlcanzado);
                laser.desactivar();
            }
        }
    }

    public boolean todosObjetivosAlcanzados() {
        for (Objetivo obj : objetivos) {
            if (!obj.estaActivo()) {
                return false;
            }
        }
        return true;
    }

    public List<Laser> getLasers(){return lasers;}

    public Tablero getTablero() {
        return tablero;
    }

    public List<Emisor> getEmisores() { return emisores;}

    public List<Objetivo> getObjetivos() { return objetivos;}
}
