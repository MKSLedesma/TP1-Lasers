package modelo;

import modelo.bloques.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LectorNivel {
    public static Nivel construirNivel(File dirNivel) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(dirNivel));
        String linea;
        int coordenadaY = 0;
        ArrayList<ArrayList<Bloque>> arrayTablero = new ArrayList<>();
        List<Emisor> emisores = new ArrayList<>();
        List<Objetivo> objetivos = new ArrayList<>();

        while ((linea = br.readLine()) != null && !linea.isEmpty()) {
            ArrayList<Bloque> fila = new ArrayList<>();
            int coordenadaX = 0;
            for (char c : linea.toCharArray()) {
                switch (c) {
                    case ' ':
                        fila.add(null); // Posición vacía
                        break;
                    case '.':
                        fila.add(new BloqueVacio(coordenadaX, coordenadaY));
                        break;
                    case 'R':
                        fila.add(new BloqueEspejo(coordenadaX, coordenadaY));
                        break;
                    case 'F':
                        fila.add(new BloqueOpacoFijo(coordenadaX, coordenadaY));
                        break;
                    case 'B':
                        fila.add(new BloqueOpacoMovil(coordenadaX, coordenadaY));
                        break;
                    case 'G':
                        fila.add(new BloqueVidrio(coordenadaX, coordenadaY));
                        break;
                    case 'C':
                        fila.add(new BloqueCristal(coordenadaX, coordenadaY));
                        break;
                }
                coordenadaX ++;
            }
            arrayTablero.add(fila);
            coordenadaY ++;
        }

        // Crear el tablero
        Tablero tablero = new Tablero(arrayTablero.size(), arrayTablero.get(0).size());
        for (ArrayList<Bloque> fila : arrayTablero) {
            for (Bloque bloque : fila) {
                if (bloque != null){
                    tablero.addBloque(bloque);
                }
            }
        }

        // Leer emisores y objetivos
        while ((linea = br.readLine()) != null) {
            String[] aux = linea.split(" ");
            if (aux[0].equals("E")) {
                int posX = Integer.parseInt(aux[1]);
                int posY = Integer.parseInt(aux[2]);
                Direccion dir = Direccion.valueOf(aux[3]);
                Emisor emisor = new Emisor(posX, posY, dir);
                emisores.add(emisor);
            } else if (aux[0].equals("G")) {
                int posX = Integer.parseInt(aux[1]);
                int posY = Integer.parseInt(aux[2]);
                Objetivo objetivo = new Objetivo(posX, posY);
                objetivos.add(objetivo);
            }
        }

        br.close();
        return new Nivel(emisores, objetivos, tablero);
    }
}
