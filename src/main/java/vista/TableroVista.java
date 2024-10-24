package vista;

import controlador.NivelControlador;
import javafx.scene.layout.*;
import modelo.Nivel;
import modelo.Tablero;
import modelo.bloques.Bloque;

public class TableroVista {

    private final GridPane tableroPane;
    private NivelControlador nivelControlador;
    private final BloqueVista bloqueVista;

    public TableroVista(){
        tableroPane = new GridPane();
        tableroPane.setGridLinesVisible(true);
        tableroPane.setPrefSize(600, 500);
        bloqueVista = new BloqueVista();
    }

    public void setControlador(NivelControlador controlador) {
        this.nivelControlador = controlador;
    }

    public void actualizarTablero(Nivel nivel){
        tableroPane.getChildren().clear();
        Tablero tablero = nivel.getTablero();
        for (int y = 1; y < tablero.getFilas(); y += 2){
            for (int x = 1; x < tablero.getColumnas(); x += 2){
                Bloque bloque = tablero.getBloqueEn(x, y);
                StackPane celda = bloqueVista.crearVistaBloque(bloque);

                int finalX = x;
                int finalY = y;

                celda.setOnMouseClicked(e -> nivelControlador.handleClickCelda(finalX, finalY, nivel));

                tableroPane.add(celda, x, y);
            }
        }
    }

    public GridPane getTableroPane(){
        return tableroPane;
    }
}
