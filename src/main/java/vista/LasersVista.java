package vista;

import controlador.NivelControlador;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modelo.*;

public class LasersVista extends Application {
    public static void main(String[] args) {
        Application.launch();
    }

    private Juego juego;
    private NivelControlador nivelControlador;
    private ComboBox<String> selectorNiveles;
    private Label estadoLabel;

    @Override
    public void start(Stage stage) {
        juego = new Juego();
        juego.cargarNiveles();

        TableroVista tableroVista = new TableroVista();
        LaserVista laserVista = new LaserVista();
        ElementosVista elementosVista = new ElementosVista();

        nivelControlador = new NivelControlador(juego, tableroVista, laserVista, elementosVista);

        tableroVista.setControlador(nivelControlador);

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));

        selectorNiveles = new ComboBox<>();
        for (Nivel nivel : juego.getNiveles()) {
            selectorNiveles.getItems().add("Nivel " + (juego.getNiveles().indexOf(nivel) + 1));
        }
        selectorNiveles.getSelectionModel().selectFirst();
        selectorNiveles.setOnAction(e -> cargarNivel());

        Label seleccionarLabel = new Label("Seleccionar un nivel:");
        BorderPane topPane = new BorderPane();
        topPane.setLeft(seleccionarLabel);
        topPane.setCenter(selectorNiveles);
        topPane.setPadding(new Insets(0, 0, 10, 0));
        root.setTop(topPane);

        StackPane centerPane = new StackPane();
        centerPane.getChildren().addAll(tableroVista.getTableroPane(), laserVista.getLasersPane(),
                elementosVista.getElementosPane());
        root.setCenter(centerPane);

        estadoLabel = new Label("Selecciona un nivel para jugar.");
        root.setBottom(estadoLabel);
        BorderPane.setMargin(estadoLabel, new Insets(10, 0, 0, 0));

        Scene escena = new Scene(root, 600, 650);
        stage.setTitle("Lasers");
        stage.setScene(escena);
        stage.show();

        cargarNivel();
    }

    private void cargarNivel() {
        int indice = selectorNiveles.getSelectionModel().getSelectedIndex();
        if (indice >= 0 && indice < juego.getNiveles().size()) {
            // El controlador se encarga de cargar y actualizar las vistas
            nivelControlador.cargarNivel(indice, estadoLabel);
            estadoLabel.setText("Nivel " + (indice + 1) + " cargado.");
        } else {
            estadoLabel.setText("Nivel seleccionado inválido.");
        }
    }
}
