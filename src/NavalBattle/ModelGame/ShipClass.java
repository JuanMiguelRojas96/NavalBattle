package NavalBattle.ModelGame;

public class ShipClass {
    private int size;
    private int coordinateX;

    private int coordinateY;


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
