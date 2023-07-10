package NavalBattle.ModelGame;

import java.util.ArrayList;

/**
 * The ShipClass class represents a ship in the naval battle game.
 * It stores information about the ship's size, coordinates, orientation, and type.
 */
public class ShipClass {
    private int size;
    private int coordinateX;

    private int coordinateY;

    private String orientation;

    private String typeShip;



    /**
     * Creates a ShipClass object with the specified size and type.
     *
     * @param size     The size of the ship.
     * @param typeShip The type of the ship.
     */
    public ShipClass(int size, String typeShip){
        this.size = size;
        this.typeShip = typeShip;

    }

    ArrayList<String> coordinates = new ArrayList<>(); // List of coordinates occupied by the ship
    /**
     * Sets the orientation of the ship.
     *
     * @param orientation The orientation of the ship ("V" for vertical, "H" for horizontal).
     */
    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    /**
     * Returns the orientation of the ship.
     *
     * @return The orientation of the ship ("V" for vertical, "H" for horizontal).
     */
    public String getOrientation() {
        return orientation;
    }


    /**
     * Adds a coordinate to the ship's list of coordinates.
     *
     * @param row    The row of the coordinate.
     * @param column The column of the coordinate.
     */
    public void setcoordinates(int row, int column){
        coordinates.add(""+row+""+column);
    }

    /**
     * Returns the list of coordinates occupied by the ship.
     *
     * @return The list of coordinates occupied by the ship.
     */
    public ArrayList<String> getCoordinates() {
        return coordinates;
    }

    /**d
     * Returns the type of the ship.
     *
     * @return The type of the ship.
     */
    public String getTypeShip() {
        return typeShip;
    }

    /**
     * Returns the size of the ship.
     *
     * @return The size of the ship.
     */
    public int getSize(){
        return size;
    }

    /**
     * Returns the type of the ship.
     *
     * @return The type of the ship.
     */
    public String getType(){
        return typeShip;
    }

    /**
     * Sets the X-coordinate of the ship's starting position.
     *
     * @param coordinateX The X-coordinate of the ship's starting position.
     */
    public void setcoordinateX(int coordinateX) {
        this.coordinateX = coordinateX;
    }

    /**
     * Sets the Y-coordinate of the ship's starting position.
     *
     * @param coordinateY The Y-coordinate of the ship's starting position.
     */
    public void setcoordinateY(int coordinateY) {
        this.coordinateY = coordinateY;
    }

    /**
     * Returns the X-coordinate of the ship's starting position.
     *
     * @return The X-coordinate of the ship's starting position.
     */
    public int getcoordinateX() {
        return coordinateX;
    }
    /**
     * Returns the Y-coordinate of the ship's starting position.
     *
     * @return The Y-coordinate of the ship's starting position.
     */
    public int getcoordinateY() {
        return coordinateY;
    }


}
