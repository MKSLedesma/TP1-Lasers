package model;

public class Nivel {
    private boolean juegoTerminado;
    private boolean objetivoAlcanzado;
    private Tablero tablero;
    private int[] posicionLaser;
    private int[] posicionObjetivo;

    public Nivel(int filas, int columnas) {
        this.juegoTerminado = false;
        this.tablero = new Tablero(filas, columnas);
        this.posicionLaser = new int[2];
        this.posicionObjetivo = new int[2];
    }

    public void inicializar() {
        posicionarObjetos();
        tablero.imprimirTablero();
    }

    public void posicionarObjetos() {
        posicionLaser[0] = 0;
        posicionLaser[1] = 0;
        tablero.posicionarObjeto(posicionLaser[0], posicionLaser[1], new Emisor());

        posicionObjetivo[0] = 1;
        posicionObjetivo[1] = 1;
        tablero.posicionarObjeto(posicionObjetivo[0], posicionObjetivo[1], new Objetivo());
    }

    public void moverLaser() {
        tablero.moverObjeto(posicionLaser[0], posicionLaser[1], posicionLaser[0] + 1, posicionLaser[1]);
    }

    public void actualizarEstado() {
        Laser laser = new Laser(posicionLaser[0], posicionLaser[1], Direccion.SE, tablero);
        laser.emitir();
        objetivoAlcanzado = tablero.alcanzoObjetivo(posicionLaser[0], posicionLaser[1]);
    }

    public boolean objetivoAlcanzado() {
        return tablero.alcanzoObjetivo(posicionLaser[0], posicionLaser[1]);
    }

    public boolean juegoTerminado() {
        return juegoTerminado;
    }

    public void setJuegoTerminado(boolean terminado) {
        this.juegoTerminado = terminado;
    }
}
