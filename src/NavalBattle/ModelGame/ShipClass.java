package NavalBattle.ModelGame;

import java.util.ArrayList;

public class ShipClass {
    private int size;
    private int coordinateX;

    private int coordinateY;

    private String orientation;

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public String getOrientation() {
        return orientation;
    }

    ArrayList<String> coordinates = new ArrayList<>();

    public void setcoordinates(int row, int column){
        coordinates.add(""+row+""+column);
    }

    public ArrayList<String> getCoordinates() {
        return coordinates;
    }

    private String typeShip;

    public String getTypeShip() {
        return typeShip;
    }

    public ShipClass(int size, String typeShip){
        this.size = size;
        this.typeShip = typeShip;
    }

    public int getSize(){
        return size;
    }

    public String getType(){
        return typeShip;
    }

    public void setcoordinateX(int coordinateX) {
        this.coordinateX = coordinateX;
    }

    public void setcoordinateY(int coordinateY) {
        this.coordinateY = coordinateY;
    }


    public int getcoordinateX() {
        return coordinateX;
    }

    public int getcoordinateY() {
        return coordinateY;
    }


}
