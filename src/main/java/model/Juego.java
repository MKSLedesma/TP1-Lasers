package model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Juego {
    private ArrayList<Nivel> niveles;

    public Juego() throws IOException {
        niveles = new ArrayList<>();
        for(File nivel : new File("src//main//resources//levels").listFiles()){
            niveles.add(LectorNivel.construirNivel(nivel));
        }
    }

    public ArrayList<Nivel> getNiveles(){
        return niveles;
    }
}
