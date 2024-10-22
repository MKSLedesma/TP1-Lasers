package vista;

import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import modelo.*;
import modelo.bloques.Bloque;
import modelo.bloques.BloqueVacio;
import java.util.List;

public class LasersVista extends Application {
    public static void main(String[] args) {
        Application.launch();
    }

    private Juego juego;
    private GridPane tableroPane;
    private Pane lasersPane;
    private Pane elementosPane;
    private ComboBox<String> selectorNiveles;
    private Label estadoLabel;
    private Bloque bloqueSeleccionado = null;

    private static final int TAMANIO_BLOQUE = 50;

    @Override
    public void start(Stage stage) {
        juego = new Juego();
        juego.cargarNiveles();

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));

        selectorNiveles = new ComboBox<>();
        for(Nivel nivel : juego.getNiveles()){
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

        tableroPane = new GridPane();
        tableroPane.setGridLinesVisible(true);
        tableroPane.setPrefSize(600, 600);

        lasersPane = new Pane();
        lasersPane.setPickOnBounds(false);

        elementosPane = new Pane();
        elementosPane.setPickOnBounds(false);


        StackPane centerPane = new StackPane();
        centerPane.getChildren().addAll(tableroPane, lasersPane, elementosPane);
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

    private void cargarNivel(){
        int indice = selectorNiveles.getSelectionModel().getSelectedIndex();
        if (indice < 0 || indice >= juego.getNiveles().size()){
            estadoLabel.setText("Nivel seleccionado invalido.");
            return;
        }
        Nivel nivelSeleccionado = juego.getNiveles().get(indice);
        nivelSeleccionado.emitirLasersIniciales();
        actualizarTablero(nivelSeleccionado);
        actualizarLasers(nivelSeleccionado);
        actualizarElementos(nivelSeleccionado);
        estadoLabel.setText("Nivel " + (indice + 1) + " cargado.");
    }

    private void actualizarElementos(Nivel nivel){
        elementosPane.getChildren().clear();
        List<Objetivo> objetivos = nivel.getObjetivos();
        List<Emisor> emisores = nivel.getEmisores();

        for (Objetivo objetivo : objetivos) {
            Circle circle = new Circle(objetivo.getPosicion().getX() * TAMANIO_BLOQUE / 2,
                    objetivo.getPosicion().getY() * TAMANIO_BLOQUE / 2, 5);
            circle.setFill(Color.WHITE);
            circle.setStroke(Color.RED);
            elementosPane.getChildren().add(circle);

            for(Laser laser : nivel.getLasers()){
                if(objetivo.getPosicion() == laser.getCoordenada()){
                    objetivo.setActivo(true);
                }
            }
        }

        for (Emisor emisor : emisores) {
            Circle circle = new Circle(emisor.getCoordenada().getX() * TAMANIO_BLOQUE / 2
                    , emisor.getCoordenada().getY() * TAMANIO_BLOQUE / 2, 5);
            circle.setFill(Color.RED);
            elementosPane.getChildren().add(circle);
        }
    }

    private void actualizarTablero(Nivel nivel){
        tableroPane.getChildren().clear();
        Tablero tablero = nivel.getTablero();
        int filas = tablero.getFilas();
        int columnas = tablero.getColumnas();

        for (int y = 1; y < filas; y += 2){
            for (int x = 1; x < columnas; x += 2){
                Bloque bloque = tablero.getBloqueEn(x, y);
                StackPane celda = new StackPane();
                celda.setPrefSize(TAMANIO_BLOQUE, TAMANIO_BLOQUE);
                celda.setStyle("-fx-border-color: black");

                if (bloque != null){
                    Color color = obtenerColorParaBloque(bloque);
                    celda.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));

                    if (bloque.getTipo().equals("F")) {
                        Line linea1 = new Line(0, 0, TAMANIO_BLOQUE - 5, TAMANIO_BLOQUE - 5);
                        Line linea2 = new Line(0, TAMANIO_BLOQUE - 5, TAMANIO_BLOQUE - 5, 0);
                        linea1.setStroke(Color.BLACK);
                        linea2.setStroke(Color.BLACK);
                        linea1.setStrokeWidth(2);
                        linea2.setStrokeWidth(2);

                        celda.getChildren().addAll(linea1, linea2);
                    }

                } else {
                    celda.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
                }

                int finalX = x;
                int finalY = y;
                celda.setOnMouseClicked(e -> handleClickCelda(finalX, finalY, nivel));

                tableroPane.add(celda, x, y);
            }
        }
    }

    private void actualizarLasers(Nivel nivel){
        lasersPane.getChildren().clear();

        for (Laser laser : nivel.getLasers()) {
            Coordenada coorEmisor = laser.getEmisor().getCoordenada();
            int inicioX = coorEmisor.getX() * TAMANIO_BLOQUE / 2;
            int inicioY = coorEmisor.getY() * TAMANIO_BLOQUE / 2;


            while(nivel.getTablero().getFilas() > laser.getCoordenada().getY() &&
                    nivel.getTablero().getColumnas() > laser.getCoordenada().getX() &&
                    0 <= laser.getCoordenada().getX() && 0 <= laser.getCoordenada().getY() &&
                    laser.estaActivo()){
                Coordenada coor = laser.getCoordenada();

                for (Objetivo objetivo : nivel.getObjetivos()) {
                    if (objetivo.getPosicion().equals(coor)) {
                        objetivo.setActivo(true);
                        break;
                    }
                }

                Bloque bloque = nivel.getTablero().getBloqueEn(coor.getX(), coor.getY());

                if (bloque != null && !(bloque instanceof BloqueVacio)) {
                    bloque.interactuarLaser(laser, nivel.getTablero());
                }
                else {laser.mover();}

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

    private Color obtenerColorParaBloque(Bloque bloque) {
        switch (bloque.getTipo()) {
            case "R":
                return Color.rgb(0, 139, 139, 1);
            case ".":
                return Color.rgb(211, 211, 211, 1);
            case "F":
                return Color.rgb(105, 105, 105, 1);
            default:
                return Color.GRAY;
        }
    }

    private void handleClickCelda(int x, int y, Nivel nivel){
        Bloque bloqueClickeado = nivel.getTablero().getBloqueEn(x, y);

        if (bloqueSeleccionado == null){
            if (bloqueClickeado != null && bloqueClickeado.esMovible()){
                bloqueSeleccionado = bloqueClickeado;
            }
        }
        else {
            if (bloqueClickeado instanceof BloqueVacio) {
                if (esValido(x, y, nivel)) {
                    moverBloque(bloqueSeleccionado, x, y, nivel);
                    bloqueSeleccionado = null;
                    actualizarTablero(nivel);
                }
            }
            else {
                if (bloqueClickeado != bloqueSeleccionado){
                    bloqueSeleccionado = bloqueClickeado;
                }
                else {
                    bloqueSeleccionado = null;
                }
            }
        }
    }

    private boolean esValido(int x, int y, Nivel nivel){
        if (!nivel.getTablero().estaDentroTablero(x, y)) {
            return false;
        }
        else return nivel.getTablero().getBloqueEn(x, y) != null;
    }

    private void moverBloque(Bloque bloque, int x, int y, Nivel nivel){
        nivel.limpiarLasers();
        nivel.emitirLasersIniciales();
        for (Coordenada coor : bloque.getCoordenadasOcupadas()){
            if (esValido(coor.getX(), coor.getY(), nivel)) {
                nivel.getTablero().setBloqueEn(coor.getX(), coor.getY(), new BloqueVacio(x, y));
            }
        }

        bloque.setCentroX(x);
        bloque.setCentroY(y);

        nivel.getTablero().addBloque(bloque);

        actualizarJuego();
    }

    private void actualizarJuego(){
        int indice = selectorNiveles.getSelectionModel().getSelectedIndex();
        Nivel nivelActual = juego.getNiveles().get(indice);

        actualizarTablero(nivelActual);
        actualizarLasers(nivelActual);
        actualizarElementos(nivelActual);

        if (nivelActual.todosObjetivosAlcanzados()) {
            estadoLabel.setText("¡Todos los objetivos han sido alcanzados! ¡Has ganado!");
        }


        nivelActual.actualizar();
    }
}