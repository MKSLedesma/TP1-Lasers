package model;

public class Laser {
    private int x;
    private int y;
    private Direccion direccion;
    private Tablero tablero;
    private boolean estaActivo;

    public Laser(int x, int y, Direccion direccionInicial, Tablero tableroInicial) {
        this.direccion = direccionInicial;
        this.x = x;
        this.y = y;
        this.tablero = tableroInicial;
        estaActivo = true;
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

    public void reflejar() {
        // Implementar lógica
    }

    public void dividir() {
        // Implementar lógica
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public boolean EstaActivo() { return estaActivo; }
}
