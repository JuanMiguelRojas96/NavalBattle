package NavalBattle;



import javax.swing.*;
import java.awt.*;

public class Header extends JPanel {


    private JLabel avatar, userName;


    public Header(Color colorBackground){
        this.setPreferredSize(new Dimension(1018,50));
        this.setBackground(colorBackground);
        this.setOpaque(true);
        this.setLayout(new GridBagLayout());
    }
    public void setImage(ImageIcon image){
        GridBagConstraints gbc = new GridBagConstraints();
        avatar = new JLabel(image);
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(avatar,gbc);
    }
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

