package vista;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import modelo.Emisor;
import modelo.Laser;
import modelo.Nivel;
import modelo.Objetivo;

public class ElementosVista {
    private final int TAMANIO_BLOQUE = 50;
    private Pane elementosPane;

    public ElementosVista() {
        elementosPane = new Pane();
        elementosPane.setPickOnBounds(false);
    }

    public Pane getElementosPane() {
        return elementosPane;
    }

    public void actualizarElementos(Nivel nivel){
        elementosPane.getChildren().clear();

        for (Objetivo objetivo : nivel.getObjetivos()) {
            Circle circulo = new Circle(objetivo.getPosicion().getX() * TAMANIO_BLOQUE / 2,
                    objetivo.getPosicion().getY() * TAMANIO_BLOQUE / 2, 5);
            circulo.setFill(Color.WHITE);
            circulo.setStroke(Color.RED);
            elementosPane.getChildren().add(circulo);

            for (Laser laser : nivel.getLasers()) {
                if (objetivo.getPosicion().equals(laser.getCoordenada())) {
                    objetivo.setActivo(true);
                }
            }
        }

        for (Emisor emisor : nivel.getEmisores()) {
            Circle circulo = new Circle(emisor.getCoordenada().getX() * TAMANIO_BLOQUE / 2,
                    emisor.getCoordenada().getY() * TAMANIO_BLOQUE / 2, 5);
            circulo.setFill(Color.RED);
            elementosPane.getChildren().add(circulo);
        }
    }
}
