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

public class LasersVista extends Application {
    public static void main(String[] args) {
        Application.launch();
    }

    private Juego juego;
    private GridPane tableroPane;
    private Pane lasersPane;
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

        StackPane centerPane = new StackPane();
        centerPane.getChildren().addAll(tableroPane, lasersPane);
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
        estadoLabel.setText("Nivel " + (indice + 1) + " cargado.");
    }

    private void actualizarTablero(Nivel nivel){
        tableroPane.getChildren().clear();
        Tablero tablero = nivel.getTablero();
        int filas = tablero.getFilas();
        int columnas = tablero.getColumnas();

        for (int y = 0; y < filas; y++){
            for (int x = 0; x < columnas; x++){
                Bloque bloque = tablero.getBloqueEn(x, y);
                StackPane celda = new StackPane();
                celda.setPrefSize(TAMANIO_BLOQUE, TAMANIO_BLOQUE);
                celda.setStyle("-fx-border-color: black");

                if (bloque != null){
                    Color color = obtenerColorParaBloque(bloque);
                    celda.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
                } else {
                    celda.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
                }

                for (Emisor emisor : nivel.getEmisores()) {
                    if (emisor.getCoordenada().getX() == x && emisor.getCoordenada().getY() == y) {
                        Circle emisorCirculo = new Circle((double) TAMANIO_BLOQUE / 5 - 5);
                        emisorCirculo.setFill(Color.RED);
                        celda.getChildren().add(emisorCirculo);
                    }
                }

                for (Objetivo objetivo : nivel.getObjetivos()) {
                    if (objetivo.getPosicion().getX() == x && objetivo.getPosicion().getY() == y) {
                        Circle objetivoCirculo = new Circle((double) TAMANIO_BLOQUE / 5 - 5);
                        objetivoCirculo.setFill(Color.WHITE);
                        objetivoCirculo.setStroke(Color.BLACK);
                        celda.getChildren().add(objetivoCirculo);
                    }
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
        for(Laser laser : nivel.getLasers()) {
            Coordenada coor = laser.getCoordenada();
            Coordenada coorEmisor = laser.getEmisor().getCoordenada();

            if (nivel.getTablero().estaDentroTablero(coor.getX(), coor.getY())){
                Bloque bloque = nivel.getTablero().getBloqueEn(coor.getY(), coor.getX());

                if (bloque != null) {
                    bloque.interactuarLaser(laser, nivel.getTablero());
                }
            }

            double inicioX = coorEmisor.getX() * TAMANIO_BLOQUE;
            double inicioY = coorEmisor.getY() * TAMANIO_BLOQUE + (double) TAMANIO_BLOQUE / 2;

            double finX = inicioX + laser.getDireccion().getDeltaX() * TAMANIO_BLOQUE;
            double finY = inicioY + laser.getDireccion().getDeltaY() * TAMANIO_BLOQUE;

            Line lineaLaser = new Line(inicioX, inicioY, finX, finY);
            lineaLaser.setStroke(Color.RED);
            lineaLaser.setStrokeWidth(3);

            lasersPane.getChildren().add(lineaLaser);
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
            if (bloqueClickeado != null){
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
        for (Coordenada coor : bloque.getCoordenadasOcupadas()){
            nivel.getTablero().setBloqueEn(coor.getY() , coor.getX(), new BloqueVacio(x, y));
        }

        bloque.setCentroX(x);
        bloque.setCentroY(y);

        for(Coordenada coor : bloque.getCoordenadasOcupadas()){
            nivel.getTablero().setBloqueEn(coor.getY(), coor.getX(), bloque);
        }

        actualizarJuego();
    }

    private void actualizarJuego(){
        int indice = selectorNiveles.getSelectionModel().getSelectedIndex();
        Nivel nivelActual = juego.getNiveles().get(indice);

        actualizarTablero(nivelActual);
        actualizarLasers(nivelActual);

        if (nivelActual.todosObjetivosAlcanzados()) {
            estadoLabel.setText("¡Todos los objetivos han sido alcanzados! ¡Has ganado!");
        }

        nivelActual.actualizar();
    }
}