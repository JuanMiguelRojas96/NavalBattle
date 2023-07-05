package NavalBattle.ModelGame;


import java.util.ArrayList;
import java.util.Random;

public class ModelNavalBatlle {
    private  ShipClass aircraftCarrier,aircraftCarrier2,aircraftCarrier3,
            bombardier,submarine,submarine2,frigate,frigate2,frigate3,frigate4;
    private ShipClass[] ships;


    public ShipClass[] getShips() {
        return ships;
    }

    public ModelNavalBatlle(){

        ships = new ShipClass[10];

    }

    public void generateShips(){


        aircraftCarrier = new ShipClass(2, "aircraftCarrier");
        aircraftCarrier2 = new ShipClass(2, "aircraftCarrier");
        aircraftCarrier3 = new ShipClass(2, "aircraftCarrier");

        bombardier = new ShipClass(4,"bombardier");

        submarine = new ShipClass(3,"submarine");
        submarine2 = new ShipClass(3,"submarine");

        frigate = new ShipClass(1,"frigate");
        frigate2 = new ShipClass(1,"frigate");
        frigate3 = new ShipClass(1,"frigate");
        frigate4 = new ShipClass(1,"frigate");

        ships[0] = aircraftCarrier;
        ships[1] = aircraftCarrier2;
        ships[2] = aircraftCarrier3;


        ships[3] = bombardier;

        ships[4] = submarine;
        ships[5] = submarine2;

        ships[6] = frigate;
        ships[7] = frigate2;
        ships[8] = frigate3;
        ships[9] = frigate4;


        generateCoordinates( aircraftCarrier);
        generateCoordinates( aircraftCarrier2);
        generateCoordinates( aircraftCarrier3);

        generateCoordinates( bombardier);

        generateCoordinates( submarine);
        generateCoordinates( submarine2);

        generateCoordinates( frigate);
        generateCoordinates( frigate2);
        generateCoordinates( frigate3);
        generateCoordinates( frigate4);





    }

    public void imprimirShips() {
        for (int i = 0; i < ships.length; i++) {
            if (ships[i] != null) {
                System.out.println("Barco " + (i+1) + ":");
                System.out.println("Nombre: " + ships[i].getTypeShip());
                System.out.println("Longitud: " + ships[i].getSize());
                System.out.println("Posición X: " + ships[i].getcoordinateX());
                System.out.println("Posición Y: " + ships[i].getcoordinateY());
                System.out.println("coordenadas Y: " + ships[i].getCoordinates());
                System.out.println("orienatcion : " + ships[i].getOrientation());
                System.out.println();
            }
        }
    }
    private boolean checkCoordinates(int fila, int column) {
        String coordinatesSearch = "" + fila + "" + column;
        for (ShipClass ship : ships) {
            if (ship != null) {
                ArrayList<String> coordinates = ship.getCoordinates();
                System.out.println(coordinatesSearch);
                for (String coordinate : coordinates) { // Corrección: Especificar el tipo de datos para la variable `coordinate`
                    if (coordinate.equals(coordinatesSearch)) {// Corrección: Usar el método equals() para comparar cadenas
                        System.out.println("se repitio");
                        System.out.println(coordinate);
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private void generateCoordinates(ShipClass ship) {
        Random random = new Random();
        int length = ship.getSize();
        int maxX = 10 - length;
        int maxY = 10 - length;

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
                int checkX = posX + i;
                int checkY = posY + i;
                if (!checkCoordinates(checkX, checkY)) {
                    isValid = false;
                    System.out.println("no se pudo");
                    break;
                }
                System.out.println("si se pudo");
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




}
