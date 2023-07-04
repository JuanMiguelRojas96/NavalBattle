package NavalBattle.Login;

import NavalBattle.NavalBattleGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelLogin extends JPanel {
  private String username;
  private JTextField userText;
  private JLabel textLabel;
  private JButton login;
  private Escucha escucha;
  private NavalBattleGUI navalBattleGUI;
  private PerfilSelectorPanel perfilSelectorPanel;


  public PanelLogin(NavalBattleGUI navalBattleGUI){
    this.navalBattleGUI = navalBattleGUI;
    this.setPreferredSize(new Dimension(500,271));  //300x150
    this.setLayout(new GridBagLayout());
    this.setVisible(true);
    this.setBackground(new Color(29, 157, 203));
    this.setBorder(BorderFactory.createLineBorder(new Color(5, 102, 134),5));
    GridBagConstraints gbc = new GridBagConstraints();
    escucha = new Escucha();

    perfilSelectorPanel= new PerfilSelectorPanel();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    this.add(perfilSelectorPanel,gbc);


    textLabel = new JLabel("Ingresa Tu Nombre, Capitán");
    textLabel.setFont(new Font("Matura MT Script Capitals",Font.BOLD,18));
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    this.add(textLabel,gbc);


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

  private class Escucha implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == login && !userText.getText().trim().isEmpty() && perfilSelectorPanel.getPerfilSeleccionado() != null){
       username = userText.getText();
       setVisible(false);
       navalBattleGUI.getComponentesVisibles();
      }else{
        JOptionPane.showMessageDialog(null,"Ingrese un Usuario Válido","Usuario Inválido",JOptionPane.INFORMATION_MESSAGE);
      }
    }
  }
  public String getUsername(){
    return username;
  }
  public ImageIcon getAvatarLogin(){
    return perfilSelectorPanel.getPerfilSeleccionado();
  }
}

