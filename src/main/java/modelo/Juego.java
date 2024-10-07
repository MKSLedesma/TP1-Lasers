package modelo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Juego {
    private List<Nivel> niveles;
    private final File dirNiveles;

    public Juego() {
        niveles = new ArrayList<>();
        dirNiveles = new File("src//main//resources//levels");
    }

    public void cargarNiveles() {
        for (File dirNivel : Objects.requireNonNull(dirNiveles.listFiles())) {
            try {
                niveles.add(LectorNivel.construirNivel(dirNivel));
            } catch (IOException e) {
                System.out.println("Error al cargar el nivel: " + dirNivel.getName());
                e.printStackTrace();
            }
        }
    }

    public List<Nivel> getNiveles(){return niveles;}
}