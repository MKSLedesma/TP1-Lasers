package modelo;

public class Emisor {
    private final Coordenada coordenada;
    private final Direccion direccion;

    public Emisor(int x, int y, Direccion direccion) {
        this.coordenada = new Coordenada(x, y);
        this.direccion = direccion;
    }

    public Coordenada getCoordenada() {
        return coordenada;
    }

    // Emite un nuevo láser.
    public Laser emitirLaser() {
        return new Laser(coordenada.getX(), coordenada.getY(), direccion, this);
    }
}
