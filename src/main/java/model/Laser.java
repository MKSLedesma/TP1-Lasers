package model;

public class Laser {
    private int x;
    private int y;
    private Direccion direccion;
    private Tablero tablero;
    private boolean estaActivo;

    public Laser(int x, int y, Direccion direccion, Tablero tablero) {
        this.x = x;
        this.y = y;
        this.direccion = direccion;
        this.tablero = tablero;
        this.estaActivo = true;
    }

    public void emitirDesde(int xInicial, int yInicial) {
        this.x = xInicial;
        this.y = yInicial;
        estaActivo = true;
        emitir();
    }

    public void emitir() {
        while (estaActivo) {
            mover();
            comprobarInteraccion();
        }
    }

    private void comprobarInteraccion() {
        Interfaz elemento = tablero.obtenerValor(x, y);
        if (elemento != null) {
            elemento.interactuarLaser(this);
        }

        if (tablero.alcanzoObjetivo(x, y)) {
            System.out.println("¡Llegaste al objetivo!");
            estaActivo = false;
        }
    }

    private void mover() {
        switch (direccion) {
            case SE -> { x += 1; y += 1; }
            case SW -> { x -= 1; y += 1; }
            case NE -> { x += 1; y -= 1; }
            case NW -> { x -= 1; y -= 1; }
        }
    }

    public void detener() {
        estaActivo = false;
    }

    public void reflejar() { // Para implementar el reflejo del laser (Si apunta a SE, va hacia NW, etc...)
        switch (direccion) {
            case SE -> direccion = Direccion.NW;
            case SW -> direccion = Direccion.NE;
            case NE -> direccion = Direccion.SW;
            case NW -> direccion = Direccion.SE;
        }
    }

    public void dividir() {
        Laser laserDividido1 = new Laser(x, y, Direccion.NE, tablero);
        Laser laserDividido2 = new Laser(x, y, Direccion.NW, tablero);

        laserDividido1.emitirDesde(x, y);
        laserDividido2.emitirDesde(x, y);

        detener();
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public boolean EstaActivo() { return estaActivo; }
}
