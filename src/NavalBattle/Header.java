package NavalBattle;

import NavalBattle.Login.PerfilSelectorPanel;

import javax.swing.*;
import java.awt.*;

public class Header extends JPanel {

    private String username;
    private JLabel avatar;


    public Header(Color colorBackground){
        this.setPreferredSize(new Dimension(1018,50));
        this.setBackground(colorBackground);
        this.setOpaque(true);
        this.setLayout(new GridBagLayout());
    }
    public void setImage(){
        GridBagConstraints gbc = new GridBagConstraints();
        avatar = new JLabel();
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(avatar,gbc);
    }
}

