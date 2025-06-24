package model;

import model.bloques.*;

import java.io.*;
import java.util.ArrayList;

public class LectorNivel {
    public static Nivel construirNivel(File dirNivel) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(dirNivel));
        String linea = "";
        int auxiliar = 0;
        ArrayList<ArrayList<Interfaz>> arrayTablero = new ArrayList<>();
        ArrayList<Emisor> emisores = new ArrayList<>();
        ArrayList<Objetivo> objetivos = new ArrayList<>();

        while (!(linea = br.readLine()).isEmpty()) {
            ArrayList<Interfaz> fila = new ArrayList<>();
            for (char c : linea.toCharArray()) {
                switch (c) {
                    case ' ':
                    case '.':
                        fila.add(new BloqueVacio(" "));
                        break;
                    case 'R':
                        fila.add(new BloqueEspejo("R"));
                        break;
                    case 'F':
                        fila.add(new BloqueOpaco("F"));
                        break;
                    case 'B':
                        fila.add(new BloqueOpacoMovil("B"));
                        break;
                    case 'G':
                        fila.add(new BloqueVidrio("G"));
                        break;
                    case 'C':
                        fila.add(new BloqueCristal("C"));
                        break;
                }
            }
            arrayTablero.add(fila);
        }

        Tablero tablero = new Tablero(arrayTablero);

        while ((linea = br.readLine()) != null) {
            String[] aux = (linea.split(" "));
            if (aux[0].equals("E")) {
                int x = Integer.parseInt(aux[1]);
                int y = Integer.parseInt(aux[2]);
                int rango = 2;
                Emisor emisor1 = new Emisor(x, y, Direccion.valueOf(aux[3]), rango);
                emisores.add(emisor1);
            } else if (aux[0].equals("G")) {
                int x = Integer.parseInt(aux[1]);
                int y = Integer.parseInt(aux[2]);
                Objetivo objetivo1 = new Objetivo(x, y);
                objetivos.add(objetivo1);
            }
        }
        return new Nivel(emisores, objetivos, tablero);
    }
}