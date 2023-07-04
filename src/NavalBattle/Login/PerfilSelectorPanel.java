package NavalBattle.Login;

import NavalBattle.Header;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PerfilSelectorPanel extends JPanel {
  private ImageIcon perfilSeleccionado;
  private JButton[] botonesPerfil;

  public PerfilSelectorPanel() {
    setLayout(new GridLayout(1, 3));

    // Fotos predeterminadas
    String[] fotos = {"/resources/luffy.png", "/resources/zoro.png", "/resources/nami.png"};

    botonesPerfil = new JButton[3];

    // Crear botones y añadirlos al panel
    for (int i = 0; i < 3; i++) {
      botonesPerfil[i] = new JButton(new ImageIcon(getClass().getResource(fotos[i])));
      botonesPerfil[i].setBackground(new Color(29, 157, 203));
      botonesPerfil[i].setBorder(BorderFactory.createLineBorder(new Color(1,130,180),5));
      botonesPerfil[i].setPreferredSize(new Dimension(102, 121));
      add(botonesPerfil[i]);

      final ImageIcon buttonIcon = (ImageIcon) botonesPerfil[i].getIcon();

      botonesPerfil[i].addMouseListener(new MouseAdapter() {
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
      });
    }
  }


  public ImageIcon getPerfilSeleccionado() {
    return perfilSeleccionado;
  }
}