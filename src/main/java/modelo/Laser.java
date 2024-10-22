package modelo;

public class Laser {
    private int x;
    private int y;
    private Direccion direccion;
    private boolean estaActivo;
    private final Emisor emisor;

    public Laser(int inicioX, int inicioY, Direccion direccion, Emisor emisor) {
        this.x = inicioX;
        this.y = inicioY;
        this.direccion = direccion;
        this.estaActivo = true;
        this.emisor = emisor;
    }

    public Coordenada getCoordenada() {
        return new Coordenada(x, y);
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public boolean estaActivo() {
        return estaActivo;
    }

    public void mover() {
        if(estaActivo) {
            this.x += direccion.getDeltaX();
            this.y += direccion.getDeltaY();
        }
    }

    public void atravesar(int x, int y){
        this.x += x;
        this.y += y;
    }


    public void desactivar() {
        this.estaActivo = false;
    }

    public void setDireccion(Direccion nuevaDireccion) {
        this.direccion = nuevaDireccion;
    }

    public Emisor getEmisor(){return emisor;}
}

