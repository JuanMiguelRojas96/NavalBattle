package NavalBattle;
import NavalBattle.GameZone.WaterZone;

import javax.swing.*;
import java.awt.*;



public class GamePanel extends JPanel {

    private WaterZone[][] waterZones;


    public GamePanel(int height, int width) {
        this.setPreferredSize(new Dimension(width*50,height*50));
        this.setBackground(new Color(157, 221, 220));

        setLayout(new GridBagLayout());
        waterZones = new WaterZone[height][width];

        GridBagConstraints gbc = new GridBagConstraints();


        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                WaterZone waterZone = new WaterZone();
                waterZones[row][column] = waterZone;
                waterZones[row][column].setName(""+row+","+column);
                waterZones[row][column].setOpaque(false);
                gbc.gridx = column;
                gbc.gridy = row;
                gbc.fill = GridBagConstraints.BOTH;
                gbc.weightx = 1.0;
                gbc.weighty = 1.0;

                add(waterZones[row][column], gbc);

            }
        }
    }
}