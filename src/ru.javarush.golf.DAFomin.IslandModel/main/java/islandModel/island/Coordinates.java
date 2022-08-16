package islandModel.island;


public class Coordinates {

    private int ordinateX;
    private int ordinateY;

    public Coordinates(int ordinateX, int ordinateY) {
        this.ordinateX = ordinateX;
        this.ordinateY = ordinateY;
    }

    public int getOrdinateX() {
        return ordinateX;
    }

    public int getOrdinateY() {
        return ordinateY;
    }


    public String showCoordinate() {
        String stringCoordinate = "Координаты локации " + getOrdinateX() + ": " + getOrdinateY() + " ";
        return stringCoordinate;
    }
}
