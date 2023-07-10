package NavalBattle;
import NavalBattle.GameZone.WaterZone;

import javax.swing.*;
import java.awt.*;

/**
 * The GamePanel class represents a panel in the game that contains water zones.
 * It extends the JPanel class and dynamically creates a grid of WaterZone components.
 */
public class GamePanel extends JPanel {
    private WaterZone[][] waterZones;

    /**
     * Constructor for the GamePanel class.
     * Creates a grid of WaterZone components based on the specified height and width.
     *
     * @param height The number of rows in the grid.
     * @param width  The number of columns in the grid.
     */
    public GamePanel(int height, int width) {
        this.setPreferredSize(new Dimension(width*50,height*50));
        this.setBackground(new Color(157, 221, 220));

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        waterZones = new WaterZone[height][width];

        // Create WaterZone components and add them to the panel.
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