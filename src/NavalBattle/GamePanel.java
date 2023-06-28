package NavalBattle;
import NavalBattle.GameZone.WaterZone;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel {

    private ArrayList <WaterZone> waterZones;



    public  GamePanel(int height, int width) {
        waterZones = new ArrayList<>();
        setLayout(new  GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        for(int row = 0;row< height; row++ ){
            for(int column = 0;column<width;column++){
                gbc.gridx = column;
                gbc.gridy = row;

                waterZones.add(new WaterZone());
                add(waterZones.get(column),gbc);

            }
        }

    }
}
