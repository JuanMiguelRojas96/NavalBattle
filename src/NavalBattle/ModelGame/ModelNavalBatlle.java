package NavalBattle.ModelGame;


import java.util.Random;

public class ModelNavalBatlle {
    private  ShipClass aircraftCarrier,bombardier;
    private ShipClass[] ships;


    public ShipClass[] getShips() {
        return ships;
    }

    public ModelNavalBatlle(){

        ships = new ShipClass[10];

    }

    public void generateShips(){

        aircraftCarrier = new ShipClass(2, "aircraftCarrier");
        bombardier = new ShipClass(4,"bombardier");

        generateCoordinates(aircraftCarrier);
        generateCoordinates(bombardier);

        ships[0] = aircraftCarrier;
        ships[1] = bombardier;

    }
    private void generateCoordinates(ShipClass ship) {
        Random random = new Random();
        int length = ship.getSize();
        int maxX = 10 - length;
        int maxY = 10;

        int posX;
        int posY;

        boolean isValid;
        do {
            posX = random.nextInt(maxX + 1);
            posY = random.nextInt(maxY);

            isValid = true;
            for (int i = 0; i < length; i++) {
                int checkX = posX + i;
                if (ships[checkX] != null && ships[checkX].getcoordinateY() == posY) {
                    isValid = false;
                    break;
                }
            }
        } while (!isValid);

        ship.setcoordinateX(posX);
        ship.setcoordinateY(posY);
    }

}
