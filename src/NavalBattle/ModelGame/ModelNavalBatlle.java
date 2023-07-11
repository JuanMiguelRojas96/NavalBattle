package NavalBattle.ModelGame;




import NavalBattle.GameZone.WaterZone;



import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.util.*;
import java.util.TimerTask;

;



/**
 * The ModelNavalBatlle class represents the model of the program and is responsible for handling all the game logic.
 * It receives coordinates from the front end, performs the necessary verifications,
 * and sends the obtained data back to the front end.
 */
public class ModelNavalBatlle {

    private ShipClass[] shipsCpu,shipsUser;

    private String[] images;

    private String move, lastMove;

    private Boolean change;

    private Integer turno,status;

    private ArrayList hitsCpu,hitsUser,hitsAux;




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

        hitsUser = new ArrayList<>();
        hitsCpu = new ArrayList<>();
        hitsAux = new ArrayList<>();
        images  = new String[]{"/agua.png", "/tocado.png"};
        //o turno usuario, 1 turno cpu
        turno =  0;
        move = "";
        status = 0;
        lastMove = "";
        change = false;



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

    public Integer getTurno() {
        return turno;
    }

    public void setTurno(Integer turno) {
        this.turno = turno;
    }
    public ArrayList getHitsCpu() {
        return hitsCpu;
    }

    public void setMove(int row,int colum,int orientation) {
        this.move = row+""+colum+""+orientation;
    }

    public void setLastMove(int row,int colum,int orientation) {
        this.lastMove = row+""+colum+""+orientation;
    }

    public String getMove() {
        return move;
    }

    public String getLastMove() {
        return lastMove;
    }

    public void setChange(Boolean change) {
        this.change = change;
    }

    public Boolean getChange() {
        return change;
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



    public void imprimirShips() {
        for (int i = 0; i < shipsCpu.length; i++) {
            if (shipsCpu[i] != null) {
                System.out.println("Barco " + (i+1) + ":");
                System.out.println("Nombre: " + shipsCpu[i].getTypeShip());
                System.out.println("Longitud: " + shipsCpu[i].getSize());
                System.out.println("Posición X: " + shipsUser[i].getcoordinateX());
                System.out.println("Posición Y: " + shipsCpu[i].getcoordinateY());
                System.out.println("coordenadas Y: " + shipsUser[i].getCoordinates());
                System.out.println("orienatcion : " + shipsCpu[i].getOrientation());
                System.out.println();
            }
        }
    }

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
            turno = 0;
            System.out.println("turno 0");
        }
        else{
            image = images[0];
            turno = 1;
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

        String image = getImage(zoneName,  "cpu");

        ShipClass ship = checkSunken(zoneName);

        if (ship != null) {
            ShipClass shipTrue = getCoordinatesSunken(ship);
            if (shipTrue != null) {
                turno = 1;
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
                   /* System.out.println("Se encontró una coincidencia");
                    System.out.println(ship.getCoordinates());*/
                    System.out.println("funciona");
                    return ship;
                }
            }
        }
        System.out.println("no funciona");
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
            System.out.println("se hundio");
        }

        if (isSunken) {
            return ship;
        } else {
            return null;
        }
    }

    public String checkBreak(int row, int colum, int orientation) {
        String coordinates = "";
        String coordinatesAux = "";
        boolean shouldBreak = false;

        System.out.println("normal");

        for (int i = 0; i < 4; i++) {
            int checkX = row + (orientation == 0 ? i : 0);
            int checkY = colum + (orientation == 0 ? 0 : i);
            coordinates = checkX + " " + checkY;
            coordinatesAux = checkX+""+checkY;
            System.out.println(coordinates);
            System.out.println("entro el barco");
            hitsUser.add(coordinatesAux);
            hitsAux.add(coordinates);
            if (!checkCoordinates(checkX, checkY, "user")) {
                System.out.println(coordinates);
                ShipClass ship = checkSunken(coordinates);
                    if (getCoordinatesSunken(ship)!=null){
                        System.out.println("barco hundido");
                        System.out.println(hitsUser);
                        setChange(false);
                        status = 2;
                        turno = 0;
                        shouldBreak = true;

                    }else {
                        status = 1;
                        this.setLastMove(checkX,checkY,orientation);
                        turno = 1;
                    }
            }else {
                System.out.println("no golpeo");
                if(status==1){
                    shouldBreak = true;
                    turno = 0;
                }
                else {
                    status = 0;
                    turno = 0;
                    shouldBreak = true;
                }

                shouldBreak = true;
            }

            if (shouldBreak) {
                break;
            }
        }

        return coordinates;
    }


    public String checkBreakreverse(int row, int colum,int orientation){
        String coordinates = "";
        String coordinatesAux = "";
        boolean shouldBreak = false;
        System.out.println("reverse");

        for (int i = 0; i < 4; i++) {
            int checkX = row - (orientation == 0 ? i : 0);
            int checkY = colum - (orientation == 0 ? 0 : i);
            coordinates = checkX + " " + checkY;
            coordinatesAux = checkX+""+checkY;
            System.out.println(coordinates);
            System.out.println(coordinates+""+"reverse");
            System.out.println(row);
            System.out.println(colum);
            hitsUser.add(coordinatesAux);
            hitsAux.add(coordinates);

            if (!checkCoordinates(checkX, checkY, "user")) {
                ShipClass ship = checkSunken(coordinates);
                System.out.println(ship.getCoordinates());
                    if (getCoordinatesSunken(ship)!=null) {
                        System.out.println("hundido");
                        setChange(false);
                        System.out.println(hitsUser);
                        status = 2;
                        turno = 0;
                        shouldBreak = true;
                    }else {
                        System.out.println("golpeo pero no se hundio");
                        status = 1;
                        this.setLastMove(checkX,checkY,orientation);
            }
            }else {
                System.out.println("no golpeo");
                if(status==1){
                    shouldBreak = true;
                    setChange(true);
                    turno = 0;

                }else {
                    status = 0;
                }


                shouldBreak = true;
            }

            if (shouldBreak) {
                break;
            }
        }

        return coordinates;
    }




    public ArrayList movesCpu(){
        System.out.println("entra ala funcion");
        Random random = new Random();
        int posX;
        int posY;
        int orientation;

        do{ //primer tiro
            if(this.getMove().equals("") && status==0){
                System.out.println("entro condicion1 ");
                posX = random.nextInt(9);
                posY = random.nextInt(9);
                orientation = random.nextInt(2);
                this.setMove(posX,posY,orientation);
                String lastCoordinate = checkBreak(posX,posY,orientation);

                System.out.println(this.getMove());
                //hubo tiro pero no dio golpe o se hundio un barco
            }else if(this.getMove()!="" && status==2){
                posX = random.nextInt(9);
                posY = random.nextInt(9);
                orientation = random.nextInt(2);
                this.setMove(posX,posY,orientation);
                String lastCoordinate = checkBreak(posX,posY,orientation);
                String row = String.valueOf(lastCoordinate.charAt(0));
                String column = String.valueOf(lastCoordinate.charAt(2));
                System.out.println("entro condicion 2 ");
                System.out.println(this.getLastMove());
                //hubo tiro y le dio golpe pero no lo hundio
            }else if(this.getMove()!="" && status==0){
                posX = random.nextInt(9);
                posY = random.nextInt(9);
                orientation = random.nextInt(2);
                this.setMove(posX,posY,orientation);
                String lastCoordinate = checkBreak(posX,posY,orientation);
                String row = String.valueOf(lastCoordinate.charAt(0));
                String column = String.valueOf(lastCoordinate.charAt(2));
                System.out.println("entro condicion 3 ");
                System.out.println(this.getLastMove());
                //hubo tiro y le dio golpe pero no lo hundio
            } else if(this.getMove()!="" && status==1){
                String move = this.getMove();
                String rowMove = String.valueOf(move.charAt(0));
                String columnMove = String.valueOf(move.charAt(1));
                String orientationMove = String.valueOf(move.charAt(2));
                int rowIntMove = Integer.parseInt(rowMove);
                int columnIntMove = Integer.parseInt(columnMove);
                int orientationIntMove = Integer.parseInt(orientationMove);
                System.out.println("entro condicion 4 ");
                System.out.println(this.getMove());

                String lastMove = this.getLastMove();
                String rowLast = String.valueOf(lastMove.charAt(0));
                String columLast = String.valueOf(lastMove.charAt(1));
                String orientationLast = String.valueOf(lastMove.charAt(2));
                int rowIntMoveLast = Integer.parseInt(rowLast);
                int columnIntMoveLast = Integer.parseInt(columLast);
                int orientationIntMoveLast = Integer.parseInt(orientationLast);
                // si es vertical  y no tiene que cambiar la orientacion
                if(orientationIntMoveLast==0 && this.getChange()==false){
                    //si termino en la misma posicion
                    System.out.println("entro sin cambio");
                    checkBreakreverse(rowIntMoveLast,columnIntMoveLast,orientationIntMove);
                // s es horizontal y no tiene que cambiar la orientacion
                }else if (orientationIntMoveLast==1 && this.getChange()==false){
                    System.out.println("entro sin cambio");
                    checkBreakreverse(rowIntMoveLast,columnIntMoveLast,orientationIntMove);
                }else if(orientationIntMoveLast==0 && this.getChange()==true){
                    System.out.println("entro con cambio");
                    checkBreak(rowIntMoveLast,columnIntMoveLast,1);
                }else if(orientationIntMoveLast==1 && this.getChange()==true){
                    System.out.println("entro con cambio");
                    checkBreak(rowIntMoveLast,columnIntMoveLast,0);
                }
            }

        }while (turno==1);
        System.out.println(hitsUser);
        System.out.println(status);
        return hitsUser;


    }

    /*public void  changeShift(){
        switch (status){
            case 0: turno = 1;
                   break;

            case 1: turno = 0;
                   break;
            case 2: turno = 0;
                break;
        }
    }*/


}
