package model.bloques;

public class BloqueOpaco extends Bloque{
    public BloqueOpaco(String tipo) {
        super("B", true);
    }

    public BloqueOpaco(String tipo, boolean esMovible) {
        super("F", esMovible);
    }
}
