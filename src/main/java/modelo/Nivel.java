package modelo;

import modelo.bloques.Bloque;
import modelo.bloques.BloqueVacio;

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
        recolocarBloques();
        for (Laser laser : lasers) {
            Coordenada coorActual = laser.getCoordenada();

            if (!tablero.estaDentroTablero(coorActual.getX(), coorActual.getY())) {
                laser.desactivar();
                continue;
            }

            if (tablero.estaOcupado(coorActual.getX(), coorActual.getY())) {
                Bloque bloque = tablero.getBloqueEn(coorActual.getX(), coorActual.getY());
                bloque.interactuarLaser(laser, this);
                continue;
            }

            Objetivo objetivoAlcanzado = tablero.getObjetivoEn(coorActual.getX(), coorActual.getY());
            if (objetivoAlcanzado != null) {
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

    public void moverBloque(Bloque bloque, int x, int y){
        limpiarLasers();
        emitirLasersIniciales();
        for (Coordenada coor : bloque.getCoordenadasOcupadas()){
            if (tablero.estaDentroTablero(coor.getX(), coor.getY())){
                tablero.setBloqueEn(coor.getX(), coor.getY(), new BloqueVacio(x, y));
            }
        }

        bloque.setCentroX(x);
        bloque.setCentroY(y);

        tablero.addBloque(bloque);
        actualizar();
    }

    public void recolocarBloques() {
        for (int y = 1; y < tablero.getFilas(); y += 2) {
            for (int x = 1; x < tablero.getColumnas(); x += 2) {
                Bloque bloqueActual = tablero.getBloqueEn(x, y);
                if (bloqueActual != null && !(bloqueActual instanceof BloqueVacio)) {
                    tablero.addBloque(bloqueActual);
                }
            }
        }
    }


    public List<Laser> getLasers(){return lasers;}

    public Tablero getTablero() {
        return tablero;
    }

    public List<Emisor> getEmisores() { return emisores;}

    public List<Objetivo> getObjetivos() { return objetivos;}

    public boolean estaDentroDeLimites(int x, int y) {return tablero.estaDentroTablero(x, y);}

    public Bloque obtenerBloqueEn(int x, int y) {return tablero.getBloqueEn(x, y);}

}
