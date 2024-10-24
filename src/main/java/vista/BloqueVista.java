package vista;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import modelo.bloques.Bloque;

import java.util.Objects;

public class BloqueVista {
    final int TAMANIO_BLOQUE = 50;

    public StackPane crearVistaBloque(Bloque bloque) {
        StackPane celda = new StackPane();
        celda.setPrefSize(TAMANIO_BLOQUE, TAMANIO_BLOQUE);
        celda.setStyle("-fx-border-color: black");

        if (bloque != null) {
            Color color = obtenerColorParaBloque(bloque);
            celda.setBackground(new Background(new BackgroundFill(color, null, null)));

            if (Objects.equals(bloque.getTipo(), "F")) {
                Line linea1 = new Line(0, 0, TAMANIO_BLOQUE - 5, TAMANIO_BLOQUE - 5);
                Line linea2 = new Line(0, TAMANIO_BLOQUE - 5, TAMANIO_BLOQUE - 5, 0);
                linea1.setStroke(Color.BLACK);
                linea2.setStroke(Color.BLACK);
                celda.getChildren().addAll(linea1, linea2);
            }
        }
        return celda;
    }

    private Color obtenerColorParaBloque(Bloque bloque) {
        switch (bloque.getTipo()) {
            case "R":
                return Color.rgb(0, 139, 139, 1);  // Azul marino
            case ".":
                return Color.rgb(211, 211, 211, 1);  // Gris claro
            case "F":
                return Color.rgb(105, 105, 105, 1);  // Gris oscuro
            case "G":
                return Color.rgb(224, 255, 255, 1); // Celeste
            case "C":
                return Color.rgb(32, 178, 170, 1);
            default:
                return Color.GRAY;
        }
    }

}

