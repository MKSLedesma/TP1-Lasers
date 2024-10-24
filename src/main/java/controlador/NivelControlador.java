package controlador;

import javafx.scene.control.Label;
import modelo.Juego;
import modelo.Nivel;
import modelo.bloques.Bloque;
import modelo.bloques.BloqueVacio;
import vista.ElementosVista;
import vista.LaserVista;
import vista.TableroVista;

public class NivelControlador {
    private final Juego juego;
    private final TableroVista tableroVista;
    private final LaserVista laserVista;
    private final ElementosVista elementosVista;
    private Bloque bloqueSeleccionado = null;

    public NivelControlador(Juego juego, TableroVista tableroVista, LaserVista laserVista,
                            ElementosVista elementosVista) {
        this.juego = juego;
        this.tableroVista = tableroVista;
        this.laserVista = laserVista;
        this.elementosVista = elementosVista;
    }

    public void cargarNivel(int indice, Label estadoLabel) {
        if (indice < 0 || indice >= juego.getNiveles().size()) {
            estadoLabel.setText("Nivel seleccionado invalido.");
            return;
        }

        Nivel nivelSeleccionado = juego.getNiveles().get(indice);
        nivelSeleccionado.emitirLasersIniciales();

        tableroVista.actualizarTablero(nivelSeleccionado);
        laserVista.actualizarLasers(nivelSeleccionado);
        elementosVista.actualizarElementos(nivelSeleccionado);

        estadoLabel.setText("Nivel " + (indice + 1) + " cargado.");
    }

    public void handleClickCelda(int x, int y, Nivel nivel) {
        Bloque bloqueClickeado = nivel.getTablero().getBloqueEn(x, y);

        if (bloqueSeleccionado == null) {
            // Seleccionar un bloque si es movible
            if (bloqueClickeado != null && bloqueClickeado.esMovible()) {
                bloqueSeleccionado = bloqueClickeado;
            }
        } else {
            // Intentar mover el bloque seleccionado
            if (bloqueClickeado instanceof BloqueVacio) {
                if (esValido(x, y, nivel)) {
                    moverBloque(bloqueSeleccionado, x, y, nivel);
                    bloqueSeleccionado = null;
                }
            } else {
                // Des-seleccionar si se vuelve a hacer clic en el mismo bloque
                bloqueSeleccionado = (bloqueClickeado != bloqueSeleccionado) ? bloqueClickeado : null;
            }
        }
    }

    private boolean esValido(int x, int y, Nivel nivel) {
        // Validar si la posición está dentro del tablero y es válida para mover
        return nivel.getTablero().estaDentroTablero(x, y) && nivel.getTablero().getBloqueEn(x, y) != null;
    }

    private void moverBloque(Bloque bloque, int x, int y, Nivel nivel) {
        nivel.moverBloque(bloque, x, y);
        actualizarJuego(nivel);  // Llamar a actualizarJuego después de mover el bloque
    }

    private void actualizarJuego(Nivel nivel) {
        // Actualizar la vista del tablero, láseres y elementos
        tableroVista.actualizarTablero(nivel);
        laserVista.actualizarLasers(nivel);
        elementosVista.actualizarElementos(nivel);

        if (nivel.todosObjetivosAlcanzados()) {
            System.out.println("¡Todos los objetivos han sido alcanzados! ¡Has ganado!");
        }
    }
}
