package vista;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import modelo.Emisor;
import modelo.Laser;
import modelo.Nivel;
import modelo.Objetivo;

public class ElementosVista {
    private final Pane elementosPane;

    public ElementosVista() {
        elementosPane = new Pane();
        elementosPane.setPickOnBounds(false);
    }

    public Pane getElementosPane() {
        return elementosPane;
    }

    public void actualizarElementos(Nivel nivel){
        elementosPane.getChildren().clear();

        int TAMANIO_BLOQUE = 50;
        for (Objetivo objetivo : nivel.getObjetivos()) {
            Circle circulo = new Circle((double) (objetivo.getPosicion().getX() * TAMANIO_BLOQUE) / 2,
                    (double) (objetivo.getPosicion().getY() * TAMANIO_BLOQUE) / 2, 5);
            if (objetivo.estaActivo()) {
                circulo.setFill(Color.RED);  // Color rojo si está activo
            } else {
                circulo.setFill(Color.WHITE);  // Color blanco si no está activo
            }

            circulo.setStroke(Color.RED);
            elementosPane.getChildren().add(circulo);

            for (Laser laser : nivel.getLasers()) {
                if (objetivo.getPosicion().equals(laser.getCoordenada())) {
                    objetivo.setActivo(true);
                    circulo.setFill(Color.RED);
                }
            }
        }

        for (Emisor emisor : nivel.getEmisores()) {
            Circle circulo = new Circle((double) (emisor.getCoordenada().getX() * TAMANIO_BLOQUE) / 2,
                    (double) (emisor.getCoordenada().getY() * TAMANIO_BLOQUE) / 2, 5);
            circulo.setFill(Color.RED);
            elementosPane.getChildren().add(circulo);
        }
    }
}
