package NavalBattle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelLogin extends JPanel {
  private String username;
  private JTextField usertext;
  private JButton login;


  public PanelLogin(){
    this.setPreferredSize(new Dimension(600,600));
    //this.setLayout(new GridBagLayout());
    this.setVisible(true);
    usertext = new JTextField(10);
    this.add(usertext);
    login = new JButton("Login");
    this.add(login);
  }


  private class Escucha implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource()==login){
       username = usertext.getText();
      }
    }
  }
  public String getUsername(){
    return username;
  }
}
