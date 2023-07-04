package NavalBattle.ModelGame;

public class ShipClass {
    private int size;
    private String typeShip;

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

}
