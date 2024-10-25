package modelo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Juego {
    private final List<Nivel> niveles;
    private final List<File> archivosNiveles;

    public Juego() {
        niveles = new ArrayList<>();
        archivosNiveles = List.of(Objects.requireNonNull((new File("src//main//resources//levels")).listFiles()));
    }

    public void cargarNiveles() {
        for (File dirNivel : archivosNiveles) {
            try {
                niveles.add(LectorNivel.construirNivel(dirNivel));
            } catch (IOException e) {
                System.out.println("Error al cargar el nivel: " + dirNivel.getName());
            }
        }
    }

    public void recargarNivel(int indice) {
        File archivoNivel = getArchivoNivel(indice);
        try {
            Nivel nivelRecargado = LectorNivel.construirNivel(archivoNivel);
            getNiveles().set(indice, nivelRecargado);
        } catch (IOException e) {
            System.out.println("Error al recargar el nivel: " + e.getMessage());
        }
    }

    public List<Nivel> getNiveles(){return niveles;}

    public File getArchivoNivel(int indice) {
        return archivosNiveles.get(indice);
    }
}