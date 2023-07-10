package NavalBattle.ModelGame;


import NavalBattle.GameZone.WaterZone;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ModelNavalBatlle {

    private ShipClass[] shipsCpu,shipsUser;

    private String[] images;

    private Integer turno;

    private ArrayList hitsCpu,hitsUser;




    public ModelNavalBatlle(){

        shipsCpu = new ShipClass[10];
        shipsUser = new ShipClass[10];


        hitsCpu = new ArrayList<>();
        images  = new String[]{"/agua.png", "/tocado.png"};
        //o turno usuario, 1 turno cpu
        turno =  0;

    }

    public ShipClass[] getShips() {
        return shipsUser;
    }

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
                System.out.println("coordenadas Y: " + shipsCpu[i].getCoordinates());
                System.out.println("orienatcion : " + shipsCpu[i].getOrientation());
                System.out.println();
            }
        }
    }
    private boolean checkCoordinates(int fila, int column, String typeShip) {
        String coordinatesSearch = "" + fila + "" + column;

        ShipClass[] ships = (typeShip.equals("cpu")) ? shipsCpu : shipsUser;

        for (ShipClass ship : ships) {
            if (ship != null && ship.getCoordinates().contains(coordinatesSearch)) {
                return false;
            }
        }

        return true;
    }


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
