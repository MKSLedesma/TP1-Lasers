package model;

public class Nivel {
    private boolean juegoTerminado;
    private boolean objetivoAlcanzado;
    private Tablero tablero;
    private int[] posicionLaser;
    private int[] posicionObjetivo;

    public Nivel(int filas, int columnas) {
        this.juegoTerminado = false;
        this.objetivoAlcanzado = false;
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
        tablero.posicionarObjeto(posicionLaser[0], posicionLaser[1], 'L');

        posicionObjetivo[0] = 1;
        posicionObjetivo[1] = 1;
        tablero.posicionarObjeto(posicionObjetivo[0], posicionObjetivo[1], 'O');

        tablero.posicionarObjeto(0, 1, 'B');
    }

    public void moverLaser() {

        int nuevaFila = posicionLaser[0] + 1;
        int nuevaColumna = posicionLaser[1];

        if (nuevaFila == posicionObjetivo[0] && nuevaColumna == posicionObjetivo[1]) {
            objetivoAlcanzado = true;
            setJuegoTerminado(true);
        }

        tablero.moverObjeto(posicionLaser[0], posicionLaser[1], nuevaFila, nuevaColumna);
        posicionLaser[0] = nuevaFila;
        posicionLaser[1] = nuevaColumna;
    }

    public void actualizarEstado() {

        moverLaser();
        tablero.imprimirTablero();
    }

    public boolean objetivoAlcanzado() {
        return objetivoAlcanzado;
    }

    public boolean juegoTerminado() {
        return juegoTerminado;
    }

    public void setJuegoTerminado(boolean terminado) {
        this.juegoTerminado = terminado;
    }
}

