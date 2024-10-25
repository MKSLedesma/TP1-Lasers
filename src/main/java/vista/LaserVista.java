package vista;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import modelo.Coordenada;
import modelo.Laser;
import modelo.Nivel;

public class LaserVista {
    private final Pane lasersPane;

    public LaserVista() {
        lasersPane = new Pane();
        lasersPane.setPickOnBounds(false);
    }

    public Pane getLasersPane() {
        return lasersPane;
    }

    public void actualizarLasers(Nivel nivel) {
        lasersPane.getChildren().clear();

        for (Laser laser : nivel.getLasers()) {
            Coordenada coorEmisor = laser.getEmisor().getCoordenada();
            int TAMANIO_BLOQUE = 50;
            int inicioX = coorEmisor.getX() * TAMANIO_BLOQUE / 2;
            int inicioY = coorEmisor.getY() * TAMANIO_BLOQUE / 2;

            while (laser.estaActivo()) {
                laser.mover(nivel);
                if (laser.estaActivo()) {
                    int finX = inicioX + laser.getDireccion().getDeltaX() * TAMANIO_BLOQUE / 2;
                    int finY = inicioY + laser.getDireccion().getDeltaY() * TAMANIO_BLOQUE / 2;

                    Line lineaLaser = new Line(inicioX, inicioY, finX, finY);
                    lineaLaser.setStroke(Color.RED);
                    lineaLaser.setStrokeWidth(3);
                    lasersPane.getChildren().add(lineaLaser);

                    inicioX = finX;
                    inicioY = finY;
                }
            }
        }
    }
}
