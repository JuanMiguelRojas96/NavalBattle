package NavalBattle.Login;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The PerfilSelectorPanel class represents a panel for selecting a profile avatar.
 * It allows the user to choose from a set of predefined avatars.
 */
public class PerfilSelectorPanel extends JPanel {
  private ImageIcon perfilSeleccionado;
  private JButton[] botonesPerfil;

  /**
   * Constructor for the PerfilSelectorPanel class.
   * Sets up the panel layout and creates the avatar selection buttons.
   */
  public PerfilSelectorPanel() {
    setLayout(new GridLayout(1, 3));

    // Predefined avatars
    String[] fotos = {"/resources/characteres/luffy.png", "/resources/characteres/zoro.png", "/resources/characteres/nami.png"};

    //Initialize the buttons that will contain the avatars
    botonesPerfil = new JButton[3];

    // Create buttons and add them to the panel
    for (int i = 0; i < 3; i++) {
      botonesPerfil[i] = new JButton(new ImageIcon(getClass().getResource(fotos[i])));
      botonesPerfil[i].setBackground(new Color(29, 157, 203));
      botonesPerfil[i].setBorder(BorderFactory.createLineBorder(new Color(1,130,180),5));
      botonesPerfil[i].setPreferredSize(new Dimension(102, 121));
      add(botonesPerfil[i]);

      final ImageIcon buttonIcon = (ImageIcon) botonesPerfil[i].getIcon();

      botonesPerfil[i].addMouseListener(new Escucha(buttonIcon));
    }
  }

  /**
   * Returns the selected profile avatar.
   * @return The selected avatar image.
   */
  public ImageIcon getPerfilSeleccionado() {
    return perfilSeleccionado;
  }

  /**
   * The ButtonClickListener class is an inner class that extends MouseAdapter
   * and handles the click event on an avatar selection button.
   */
  private class Escucha extends MouseAdapter {
    private final ImageIcon buttonIcon;
    public Escucha(ImageIcon buttonIcon) {
      this.buttonIcon = buttonIcon;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
      perfilSeleccionado = buttonIcon;
      JButton botonSeleccionado = (JButton) e.getSource();
      botonSeleccionado.setBackground(new Color(118, 124, 118));
      for (JButton boton : botonesPerfil) {
        if (boton != botonSeleccionado) {
          boton.setBackground(new Color(29, 157, 203));
        }
      }
    }
  }
}