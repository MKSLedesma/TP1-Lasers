package modelo;

import modelo.bloques.Bloque;
import java.util.ArrayList;
import java.util.List;

public class Nivel {
    private final Tablero tablero;
    private final List<Emisor> emisores;
    private final List<Laser> lasers;
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

    public void limpiarLasers(){
        lasers.clear();
    }

    public void actualizar() {
        for (Laser laser : lasers) {
            laser.mover();
            Coordenada coorActual = laser.getCoordenada();

            if (!tablero.estaDentroTablero(coorActual.getX(), coorActual.getY())) {
                System.out.println("Un láser salió del tablero sin colisionar.");
                laser.desactivar();
                continue;
            }

            if (tablero.estaOcupado(coorActual.getX(), coorActual.getY())) {
                System.out.println("Un laser colisiono con un bloque en (" + coorActual.getX() + ", " + coorActual.getY() + ")");
                Bloque bloque = tablero.getBloqueEn(coorActual.getX(), coorActual.getY());
                bloque.interactuarLaser(laser, tablero);
                continue;
            }

            Objetivo objetivoAlcanzado = tablero.getObjetivoEn(coorActual.getX(), coorActual.getY());
            if (objetivoAlcanzado != null) {
                System.out.println("Un laser ha alcanzado el objetivo en (" + coorActual.getX() + ", " + coorActual.getY() + ")");
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
