package model;

import model.bloques.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LectorNivel {
    private Tablero tablero;
    private List<Emisor> emisores;
    private List<Objetivo> objetivos;

    public LectorNivel(InputStream pathNivel){
        tablero = new Tablero();
        emisores = new ArrayList<>();
        objetivos = new ArrayList<>();
        cargarNivel(pathNivel);
    }

    public void cargarNivel(InputStream pathNivel){
        try (BufferedReader br = new BufferedReader(new InputStreamReader(pathNivel))){
            List<String> seccionBloques = new ArrayList<>();
            List<String> seccionEmisoresObjetivos = new ArrayList<>();
            List<String> seccionActual = seccionBloques;

            String line;
            while((line = br.readLine()) != null){
                if (line.isEmpty()){
                    seccionActual = seccionEmisoresObjetivos;
                    continue;
                }
                seccionActual.add(line);
            }

            procesarSeccionBloques(seccionBloques);
            procesarSeccionEmisoresObjetivos(seccionEmisoresObjetivos);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void procesarSeccionBloques(List<String> seccionBloques){
        int fila = 0;

        for (String linea : seccionBloques) {
            for (int columna = 0; columna < linea.length(); columna++) {
                char simbolo = linea.charAt(columna);

                if (simbolo == '.'){
                    Celda celdaVacia = new Celda(fila, columna);
                    celdaVacia.setPiso(true);
                    tablero.agregarCelda(celdaVacia);
                }
                else if (simbolo != ' '){
                    Celda celdaBloque = new Celda(fila, columna);
                    celdaBloque.setPiso(true);
                    Bloque bloque = crearBloquePorSimbolo(simbolo);
                    List<Posicion> posiciones = new ArrayList<>();
                    Posicion centro = new Posicion(columna * 2 + 1, fila * 2 + 1);
                    Posicion arriba = new Posicion(columna * 2 + 1, fila * 2);
                    Posicion abajo = new Posicion(columna * 2 + 1, fila * 2 + 2);
                    Posicion izquierda = new Posicion(columna * 2, fila * 2 + 1);
                    Posicion derecha = new Posicion(columna * 2 + 2, fila * 2 + 1);

                    posiciones.add(centro);
                    posiciones.add(arriba);
                    posiciones.add(abajo);
                    posiciones.add(izquierda);
                    posiciones.add(derecha);
                    bloque.setPosiciones(posiciones);

                    celdaBloque.setBloque(bloque);
                    tablero.agregarCelda(celdaBloque);
                }
            }
            fila++;
        }
    }

    private Bloque crearBloquePorSimbolo(char simbolo){
        return switch (simbolo) {
            case 'F' -> new BloqueOpacoFijo();
            case 'B' -> new BloqueOpaco();
            case 'R' -> new BloqueEspejo();
            case 'G' -> new BloqueVidrio();
            case 'C' -> new BloqueCristal();
            default -> throw new IllegalArgumentException("El simbolo '" + simbolo + "' no existe");
        };
    }

    private void procesarSeccionEmisoresObjetivos(List<String> seccionEmisoresObjetivos){
        for (String linea : seccionEmisoresObjetivos) {
            if (linea.isEmpty()){
                continue;
            }
            String[] partes = linea.split(" ");
            switch (partes[0]){
                case "E":
                    int x = Integer.parseInt(partes[1]);
                    int y = Integer.parseInt(partes[2]);
                    Direccion direccion = Direccion.crearPorString(partes[3]);
                    Emisor emisor = new Emisor(new Posicion(x, y), direccion);
                    emisores.add(emisor);
                    break;
                case "G":
                    int xObj = Integer.parseInt(partes[1]);
                    int yObj = Integer.parseInt(partes[2]);
                    Objetivo objetivo = new Objetivo(new Posicion(xObj, yObj));
                    objetivos.add(objetivo);
                    break;
                default:
                    break;
            }
        }
    }

    public Tablero getTablero() {
        return tablero;
    }

    public List<Emisor> getEmisores() {
        return emisores;
    }

    public List<Objetivo> getObjetivos(List<Objetivo> objetivos) {
        return objetivos;
    }
}