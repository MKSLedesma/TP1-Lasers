package model;

public class Juego {
    private Nivel nivelIniciado;
    private final int filas = 2;
    private final int columnas = 2;

    public Juego() {
        this.nivelIniciado = new Nivel(filas, columnas);
    }

    public void iniciarJuego() {
        inicializarNivel();

        while (!nivelIniciado.juegoTerminado()) {
            actualizarJuego();
        }

        mostrarResultadoFinal();
    }

    private void inicializarNivel() {
        nivelIniciado.inicializar();
        System.out.println("Nivel iniciado!");
    }

    private void actualizarJuego() {
        nivelIniciado.actualizarEstado();
        nivelIniciado.moverLaser();

        if (nivelIniciado.objetivoAlcanzado()) {
            nivelIniciado.setJuegoTerminado(true);
        }
    }

    private void mostrarResultadoFinal() {
        if (nivelIniciado.objetivoAlcanzado()) {
            System.out.println("Felicidades, ¡ganaste!");
        } else {
            System.out.println("Juego terminado. Intente de nuevo.");
        }
    }
}
