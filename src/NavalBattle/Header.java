package NavalBattle;



import javax.swing.*;
import java.awt.*;

/**
 * The Header class represents a header panel in the game.
 * It extends the JPanel class and contains an avatar and username label.
 */
public class Header extends JPanel {
    private JLabel avatar, userName;

    /**
     * Constructor for the Header class.
     * Sets up the header panel with the specified background color.
     *
     * @param colorBackground The background color of the header panel.
     */
    public Header(Color colorBackground){
        this.setPreferredSize(new Dimension(1018,50));
        this.setBackground(colorBackground);
        this.setOpaque(true);
        this.setLayout(new GridBagLayout());
    }

    /**
     * Sets the image for the avatar label.
     *
     * @param image The ImageIcon to set as the avatar image.
     */
    public void setImage(ImageIcon image){
        GridBagConstraints gbc = new GridBagConstraints();
        avatar = new JLabel(image);
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(avatar,gbc);
    }

    /**
     * Sets the username label.
     *
     * @param user The username to display.
     */
    public void  setUserName(String user) {
        GridBagConstraints gbc = new GridBagConstraints();
        userName = new JLabel(user);
        userName.setFont(new Font("Matura MT Script Capitals",Font.BOLD,25));
        userName.setForeground(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(userName,gbc);
    }
}

