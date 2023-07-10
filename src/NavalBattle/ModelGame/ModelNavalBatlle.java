package NavalBattle.ModelGame;


import NavalBattle.GameZone.WaterZone;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


/**
 * The ModelNavalBatlle class represents the model of the program and is responsible for handling all the game logic.
 * It receives coordinates from the front end, performs the necessary verifications,
 * and sends the obtained data back to the front end.
 */
public class ModelNavalBatlle {

    private ShipClass[] shipsCpu,shipsUser;

    private String[] images;

    private Integer turno;

    private ArrayList hitsCpu,hitsUser;


    /**
     * Constructor for the modelNavalBatle class.
     * It initializes the shipsCpu and shipsUser arrays with a length of 10.
     * It creates an empty ArrayList for hitsCpu to store hit positions.
     * It initializes the images array with paths to water and hit images.
     * The turno variable is set to 0, representing the user's turn, and 1 represents the CPU's turn.
     */
    public ModelNavalBatlle(){

        shipsCpu = new ShipClass[10];
        shipsUser = new ShipClass[10];


        hitsCpu = new ArrayList<>();
        images  = new String[]{"/agua.png", "/tocado.png"};
        //o turno usuario, 1 turno cpu
        turno =  0;

    }
    /**
     * The getShips method returns the array of ships (shipsUser) of the ShipClass type.
     * It retrieves and returns the array containing the user's ships.
     *
     * @return The array of ships (shipsUser) of the ShipClass type representing the user's ships.
     */
    public ShipClass[] getShips() {
        return shipsUser;
    }

    /**
     * The generateShips method generates and assigns ships to the CPU and user.
     */
    public void generateShips(){

        String cpu = "cpu";
        String user = "user";


        String[] shipNames = { "aircraftCarrier", "submarine", "submarine", "bombardier", "bombardier",
                "bombardier", "frigate", "frigate", "frigate", "frigate" };

        for (int i = 0; i < shipNames.length; i++) {
            int  size = 0;
            if(shipNames[i]=="aircraftCarrier"){
                size = 4;
            }
            else if(shipNames[i]=="submarine"){
                size = 3;
            }
            else if(shipNames[i]=="bombardier"){
                size =2;
            }
            else {
                size =1;
            }
            ShipClass ship = new ShipClass(size, shipNames[i]);
            generateCoordinates(ship,cpu);

            shipsCpu[i] = ship;

            ShipClass userShip = new ShipClass(size, shipNames[i]);
            generateCoordinates(userShip,user);

            shipsUser[i] = userShip;
        }
    }



    /*public void imprimirShips() {
        for (int i = 0; i < shipsCpu.length; i++) {
            if (shipsCpu[i] != null) {
                System.out.println("Barco " + (i+1) + ":");
                System.out.println("Nombre: " + shipsCpu[i].getTypeShip());
                System.out.println("Longitud: " + shipsCpu[i].getSize());
                System.out.println("Posición X: " + shipsUser[i].getcoordinateX());
                System.out.println("Posición Y: " + shipsCpu[i].getcoordinateY());
                System.out.println("coordenadas Y: " + shipsCpu[i].getCoordinates());
                System.out.println("orienatcion : " + shipsCpu[i].getOrientation());
                System.out.println();
            }
        }
    }*/

    /**
     * The checkCoordinates method checks if the given coordinates are valid for placing a ship.
     *
     * @param row The row value of the coordinate.
     * @param column The column value of the coordinate.
     * @param typeShip The type of ship ("cpu" or "user").
     * @return true if the coordinates are valid for placing a ship, false otherwise.
     */
    private boolean checkCoordinates(int row, int column, String typeShip) {
        String coordinatesSearch = "" + row + "" + column;

        ShipClass[] ships = (typeShip.equals("cpu")) ? shipsCpu : shipsUser;

        for (ShipClass ship : ships) {
            if (ship != null && ship.getCoordinates().contains(coordinatesSearch)) {
                return false;
            }
        }

        return true;
    }

    /**
     * The generateCoordinates method generates and assigns coordinates for a ship.
     *
     * @param ship The ShipClass object for which coordinates are generated.
     * @param typeShip The type of ship ("cpu" or "user").
     */


    private void generateCoordinates(ShipClass ship,String typeShip) {
        Random random = new Random();
        int length = ship.getSize();
        int maxX = 11 - length;
        int maxY = 11 - length;

        int posX;
        int posY;
        int orientation;

        boolean isValid;

        do {
            isValid = true;
            posX = random.nextInt(maxX);
            posY = random.nextInt(maxY);
            orientation = random.nextInt(2);
            if (orientation == 0) {
                ship.setOrientation("V");
            } else {
                ship.setOrientation("H");
            }

            for (int i = 0; i < length; i++) {
                int checkX = posX + (orientation == 0 ? i : 0);
                int checkY = posY + (orientation == 0 ? 0 : i);
                if (!checkCoordinates(checkX, checkY,typeShip)) {
                    isValid = false;
                    System.out.println("no se pudo");
                    break;
                }
                //System.out.println("si se pudo");
            }
        } while (!isValid);

        ship.setcoordinateX(posX);
        ship.setcoordinateY(posY);

        for (int i = 0; i < length; i++) {
            int setX = posX + (orientation == 0 ? i : 0);
            int setY = posY + (orientation == 0 ? 0 : i);
            ship.setcoordinates(setX, setY);
        }
    }

    /**
     * The getImage method retrieves the image path based on the given coordinate and ship type.
     *
     * @param coordinate The coordinate string in the format "row_column".
     * @param typeShip The type of ship ("cpu" or "user").
     * @return The image path based on the coordinate and ship type.
     */
    public String getImage(String coordinate,String typeShip) {
        int row = Integer.parseInt(String.valueOf(coordinate.charAt(0)));
        int column = Integer.parseInt(String.valueOf(coordinate.charAt(2)));

        String image;

        if (!checkCoordinates(row, column,typeShip)) {

            image = images[1];
        }
        else{
            image = images[0];
        }
        return image;

    }

    /**
     * The handleWaterZoneClick method handles the click on a WaterZone object.
     *
     * @param waterZone The WaterZone object representing the clicked water zone.
     * @return The ShipClass object with accurate coordinates if a sunken ship is found, null otherwise.
     */

    public ShipClass handleWaterZoneClick(WaterZone waterZone) {
        String zoneName = waterZone.getName();
        System.out.println(zoneName);

        String row = String.valueOf(zoneName.charAt(0));
        String column = String.valueOf(zoneName.charAt(2));

        hitsCpu.add(row + column);

        String image = getImage(zoneName, (turno == 0) ? "cpu" : "user");

        ShipClass ship = checkSunken(zoneName);

        if (ship != null) {
            ShipClass shipTrue = getCoordinatesSunken(ship);
            if (shipTrue != null) {
                return shipTrue;
            } else {
                if (waterZone.getName().equals(row + "," + column)) {
                    ImageIcon shipIcon = new ImageIcon(getClass().getResource("/resources/shipStates/" + image));
                    waterZone.setImageIcon(shipIcon);
                }
            }
        } else {
            if (waterZone.getName().equals(row + "," + column)) {
                ImageIcon shipIcon = new ImageIcon(getClass().getResource("/resources/shipStates/" + image));
                waterZone.setImageIcon(shipIcon);
            }
        }

        return null;
    }


    /**
     * The checkSunken method checks if a ship is sunken at the specified zone.

     *
     * @param zoneName The name of the zone to check for a sunken ship.
     * @return The ShipClass object if a sunken ship is found, null otherwise.
     */
    public ShipClass checkSunken(String zoneName) {
        String row = String.valueOf(zoneName.charAt(0));
        String column = String.valueOf(zoneName.charAt(2));
        String coordinatesSearch = row + column;

        ShipClass[] ships = (turno == 0) ? shipsCpu : shipsUser;

        for (ShipClass ship : ships) {
            if (ship != null) {
                ArrayList<String> coordinates = ship.getCoordinates();
                if (coordinates.contains(coordinatesSearch)) {
                    // Imprime información de depuración
                    System.out.println("Se encontró una coincidencia");
                    System.out.println(ship.getCoordinates());
                    return ship;
                }
            }
        }

        return null;
    }

    /**
     * The getCoordinatesSunken method checks if all the coordinates of a ship have been hit, and it is sunken.
     *
     * @param ship The ShipClass object to check for sunken status.
     * @return The ShipClass object if it is sunken, null otherwise.
     */
    public ShipClass getCoordinatesSunken(ShipClass ship) {
        ArrayList<String> coordinates = ship.getCoordinates();
        boolean isSunken;

        if (turno == 0) {
            isSunken = hitsCpu.containsAll(coordinates);
        } else {
            isSunken = hitsUser.containsAll(coordinates);
        }

        if (isSunken) {
            return ship;
        } else {
            return null;
        }
    }


}
