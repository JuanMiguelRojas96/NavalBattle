package NavalBattle.GameZone;
import javax.swing.*;
import java.awt.*;

/**
 * The WaterZone class represents a graphical component for displaying a water zone in the game.
 * It extends the JLabel class and overrides the paintComponent method to draw the water image.
 */
public class WaterZone extends  JLabel {
    private  Image water;

    /**
     * Constructor for the WaterZone class.
     * Loads the water image from resources and sets it as the initial image.
     */
    public  WaterZone( ){
        this.water = new ImageIcon(getClass().getResource("/resources/shipStates/water.png")).getImage();
    }

    /**
     * Sets the image icon for the water zone.
     *
     * @param icon The ImageIcon to set as the image of the water zone.
     */
    public void setImageIcon(ImageIcon icon) {
        this.water = icon.getImage();
        repaint();
    }

    /**
     * Overrides the paintComponent method to draw the water image on the water zone.
     *
     * @param g The Graphics object used for painting.
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(water, 0,0,getWidth(),getHeight(),this);
    }
}

