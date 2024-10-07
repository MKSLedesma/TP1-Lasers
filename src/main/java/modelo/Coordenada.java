package modelo;

import java.util.Objects;

public class Coordenada {
    private final int x;
    private final int y;

    public Coordenada(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // Metodo para comparar coordenadas
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Coordenada)) return false;
        Coordenada coor = (Coordenada) obj;
        return this.x == coor.x && this.y == coor.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

