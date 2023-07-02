package NavalBattle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelLogin extends JPanel {
  private String username;
  private JTextField usertext;
  private JLabel textLabel;
  private JButton login;
  private Escucha escucha;


  public PanelLogin(){
    this.setPreferredSize(new Dimension(300,150));
    this.setLayout(new GridBagLayout());
    this.setVisible(true);
    this.setBackground(new Color(29, 157, 203));
    this.setBorder(BorderFactory.createLineBorder(new Color(5, 102, 134),5));
    GridBagConstraints gbc = new GridBagConstraints();

    escucha = new Escucha();

    textLabel = new JLabel("Ingresa tu Nombre, Capitán");
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    this.add(textLabel,gbc);


    usertext = new JTextField(10);
    usertext.setPreferredSize(new Dimension(150,50));
    usertext.setBackground(new Color(1,130,180));
    usertext.setBorder(BorderFactory.createLineBorder(new Color(29, 157, 203),10));
    usertext.setFont(new Font("Pirata One",Font.BOLD,15));
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    this.add(usertext,gbc);

    login = new JButton("!A Luchar¡");
    login.setBackground(new Color(170, 183, 197));
    login.addActionListener(escucha);
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    this.add(login,gbc);
  }

  private class Escucha implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == login){
       username = usertext.getText();
       setVisible(false);
      }
    }
  }
  public String getUsername(){
    return username;
  }
}

