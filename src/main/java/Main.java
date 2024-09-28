import model.Juego;
import model.Nivel;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Juego juego1 = new Juego();
        for(Nivel nivel : juego1.getNiveles()){
            System.out.println(nivel);
        }
    }
}
