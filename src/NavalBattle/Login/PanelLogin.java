package NavalBattle.Login;

import NavalBattle.NavalBattleGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The PanelLogin class represents a login panel in the game.
 * It allows the user to enter their username and select an avatar.
 * The panel includes a login button that triggers the login process.
 */
public class PanelLogin extends JPanel {
  private String username;
  private JTextField userText;
  private JLabel textLabel;
  private JButton login;
  private Escucha escucha;
  private NavalBattleGUI navalBattleGUI;
  private PerfilSelectorPanel perfilSelectorPanel;


  /**
   * Constructor for the PanelLogin class.
   * Initializes the login panel with its components and sets up the layout.
   *
   * @param navalBattleGUI The NavalBattleGUI instance.
   */

  public PanelLogin(NavalBattleGUI navalBattleGUI){
    this.navalBattleGUI = navalBattleGUI;
    this.setPreferredSize(new Dimension(500,271));  //300x150
    this.setLayout(new GridBagLayout());
    this.setVisible(true);
    this.setBackground(new Color(29, 157, 203));
    this.setBorder(BorderFactory.createLineBorder(new Color(5, 102, 134),5));
    GridBagConstraints gbc = new GridBagConstraints();
    escucha = new Escucha();

    //Initializes the Profile selector.
    perfilSelectorPanel= new PerfilSelectorPanel();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    this.add(perfilSelectorPanel,gbc);


    //Initializes the label that requests the entry of the username.
    textLabel = new JLabel("Ingresa Tu Nombre, Capitán");
    textLabel.setFont(new Font("Matura MT Script Capitals",Font.BOLD,18));
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    this.add(textLabel,gbc);

    //Initializes the text field where we will enter our username.
    userText = new JTextField(10);
    userText.setHorizontalAlignment(SwingConstants.CENTER);
    userText.setPreferredSize(new Dimension(150,50));
    userText.setBackground(new Color(1,130,180));
    userText.setBorder(BorderFactory.createLineBorder(new Color(29, 157, 203),10));
    userText.setFont(new Font("Matura MT Script Capitals",Font.BOLD,18));
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    this.add(userText,gbc);

    //Initialize the button to start session.
    login = new JButton("!A Luchar¡");
    login.setFont(new Font("Matura MT Script Capitals",Font.BOLD,18));
    login.setBackground(new Color(170, 183, 197));
    login.addActionListener(escucha);
    gbc.gridx = 0;
    gbc.gridy = 3;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    this.add(login,gbc);
  }

  /**
   * The Escucha class is an inner class that implements the ActionListener interface.
   * It handles the login button click event and performs the login process.
   */
  private class Escucha implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == login && !userText.getText().trim().isEmpty() && perfilSelectorPanel.getPerfilSeleccionado() != null){
       username = userText.getText();
       setVisible(false);
       navalBattleGUI.getComponentesVisibles();
      }else{
        JOptionPane.showMessageDialog(null,"Ingrese un Usuario o Escoja un Personaje","Usuario Inválido",JOptionPane.INFORMATION_MESSAGE);
      }
    }
  }

  /**
   * Returns the username entered by the user.
   *
   * @return The username.
   */
  public String getUsername(){
    return username;
  }

  /**
   * Returns the selected avatar image.
   *
   * @return The avatar image.
   */
  public ImageIcon getAvatarLogin(){
    return perfilSelectorPanel.getPerfilSeleccionado();
  }

}

