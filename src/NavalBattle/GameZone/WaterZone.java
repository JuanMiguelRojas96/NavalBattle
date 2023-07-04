package NavalBattle.GameZone;
import javax.swing.*;
import java.awt.*;
public class WaterZone extends  JLabel {
    private  Image water;


    public  WaterZone( ){
        this.water = new ImageIcon(getClass().getResource("/resources/water.png")).getImage();

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(water, 0,0,getWidth(),getHeight(),this);
    }
}

