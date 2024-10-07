package modelo;

public class Objetivo {
    private final Coordenada coordenada;
    private boolean estaActivo;

    public Objetivo(int x, int y) {
        this.coordenada = new Coordenada(x, y);
        this.estaActivo = false;
    }

    public Coordenada getPosicion() {
        return coordenada;
    }

    public boolean estaActivo() {
        return estaActivo;
    }

    public void setActivo(boolean activo) {
        this.estaActivo = activo;
    }
}

