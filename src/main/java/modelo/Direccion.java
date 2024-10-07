package modelo;

public enum Direccion {
    NE(1, -1), // Noreste
    NW(-1, -1), // Noroeste
    SE(1, 1), // Sureste
    SW(-1, 1); // Suroeste

    private final int deltaX;
    private final int deltaY;

    Direccion(int deltaX, int deltaY) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    public int getDeltaX() {
        return deltaX;
    }

    public int getDeltaY() {
        return deltaY;
    }
}
