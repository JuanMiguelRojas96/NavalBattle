package NavalBattle;

import NavalBattle.GameZone.WaterZone;
import NavalBattle.Login.PanelLogin;
import NavalBattle.ModelGame.ModelNavalBatlle;
import NavalBattle.ModelGame.ShipClass;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;

/**
 * This class is used for ...
 * @autor Carlos Felipe Montoya carlos.felipe.montoya@correounivalle.edu.co
 * @version v.1.0.0 date:21/03/2023
 */
public class NavalBattleGUI extends JFrame {

    private Header headerProject;
    private  GamePanel panelUser, panelCpu;
    private PanelLogin panelLogin;

    private ModelNavalBatlle modelNavalBatlle;

    private ShipClass[] ships;
    private  Escucha escucha;




    /**
     * Constructor of GUI class
     */
    public NavalBattleGUI(){
        initGUI();

        //Default JFrame configuration
        this.setTitle("The Title app");
        this.setSize(1050,600);
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(1,138,180));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

    /**
     * This method is used to set up the default JComponent Configuration,
     * create Listener and control Objects used for the GUI class
     */
    private void initGUI() {
        //Set up JFrame Container's Layout
        this.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        escucha = new Escucha();
        modelNavalBatlle = new ModelNavalBatlle();
        ships = new ShipClass[2];

        //Create Listener Object and Control Object


        //Set up JComponents
        panelLogin = new PanelLogin(this);
        this.add(panelLogin);

        headerProject = new Header(Color.BLACK);
        headerProject.setVisible(false);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(headerProject,gbc);

        panelUser = new GamePanel(10,10);
        panelUser.setVisible(false);
        TitledBorder titledBorder = BorderFactory.createTitledBorder(null ,"PANEL USUARIO", TitledBorder.CENTER,
            TitledBorder.DEFAULT_JUSTIFICATION , new Font("Stencil",Font.PLAIN+Font.BOLD,20),Color.BLACK);
        LineBorder lineBorder = new LineBorder(Color.BLACK);
        panelUser.setBorder(BorderFactory.createCompoundBorder(lineBorder,titledBorder));
        gbc.gridx =0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        this.add(panelUser,gbc);



        panelCpu = new GamePanel(10,10);
        panelCpu.setVisible(false);
        TitledBorder titledBorder1 =BorderFactory.createTitledBorder(null ,"PANEL CPU", TitledBorder.CENTER,
            TitledBorder.DEFAULT_JUSTIFICATION , new Font("Stencil",Font.PLAIN+Font.BOLD,20),Color.BLACK);
        panelCpu.setBorder(BorderFactory.createCompoundBorder(lineBorder,titledBorder1));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        this.add(panelCpu,gbc);

    }

    public void getComponentesVisibles() {
        play();
        headerProject.setImage(panelLogin.getAvatarLogin());
        headerProject.setUserName(panelLogin.getUsername());
        headerProject.setVisible(true);
        panelUser.setVisible(true);
        panelCpu.setVisible(true);
    }



    public void clickWaterZone() {
        Component[] components = panelUser.getComponents();
        for (Component component : components) {
            if (component instanceof WaterZone) {
                WaterZone waterZone = (WaterZone) component;
                waterZone.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        ShipClass ship = modelNavalBatlle.handleWaterZoneClick(waterZone);
                        if(ship!=null){
                            setSonken(ship);
                        }


                    }
                });
            }
        }
    }


    //
    /*public void handleWaterZoneClick(WaterZone waterZone) {
        String string = waterZone.getName();
        String image = modelNavalBatlle.getImage(string);
        System.out.println(image);
        ImageIcon shipIcon = new ImageIcon(getClass().getResource("/resources/" + image));
        String row = String.valueOf(string.charAt(0));
        String column = String.valueOf(string.charAt(2));
        if (waterZone.getName().equals(row + "," + column)) {
            waterZone.setImageIcon(shipIcon);
        }
    }*/

    public void setSonken(ShipClass ship) {
        Component[] components = panelUser.getComponents();
        for (Component component : components) {
            if (component instanceof WaterZone) {
                WaterZone waterZone = (WaterZone) component;

                ImageIcon imageIcon = new ImageIcon(getClass().getResource("/resources/shipStates/hundido.png"));

                for (int i = 0; i < ship.getSize(); i++) {

                    if(ship.getOrientation()== "H"){
                        if (waterZone.getName().equals(ship.getcoordinateX() + "," + (ship.getcoordinateY() + i))) {
                            waterZone.setImageIcon(imageIcon);
                        }
                    }else{
                        if (waterZone.getName().equals((ship.getcoordinateX() +i) + "," + ship.getcoordinateY())) {
                            waterZone.setImageIcon(imageIcon);
                        }
                    }


                }
            }
        }
    }

    public void play() {
        modelNavalBatlle.generateShips();
        ships = modelNavalBatlle.getShips();
        Component[] components = panelUser.getComponents();
        modelNavalBatlle.imprimirShips();
        /*System.out.println(ships);
        System.out.println(components);*/
        clickWaterZone();

        for (Component component : components) {
            if (component instanceof WaterZone) {
                WaterZone waterZone = (WaterZone) component;

                for (ShipClass ship : ships) {
                    if (ship != null) {
                        for (int i = 0; i < ship.getSize(); i++) {
                            ImageIcon shipIcon = new ImageIcon(getClass().getResource("/resources/" +
                                    ship.getTypeShip()+ship.getOrientation() + "/" + (i + 1) + ".png"));
                            if(ship.getOrientation()== "H"){
                                if (waterZone.getName().equals(ship.getcoordinateX() + "," + (ship.getcoordinateY() + i))) {
                                    waterZone.setImageIcon(shipIcon);
                                }
                            }else{
                                if (waterZone.getName().equals((ship.getcoordinateX() +i) + "," + ship.getcoordinateY())) {
                                    waterZone.setImageIcon(shipIcon);
                                }
                            }


                        }
                    }
                }
            }
        }

        /*panelUser.revalidate();
        panelUser.repaint();
        System.out.println("funciono");*/
    }


    /**
     * Main process of the Java program
     * @param args Object used in order to send input data from command line when
     * the program is execute by console.
     */
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            NavalBattleGUI miProjectNavalBattleGUI = new NavalBattleGUI();
        });
    }

    /**
     * inner class that extends an Adapter Class or implements Listeners used by GUI class
     */
    private class Escucha  implements ActionListener, MouseListener {

        @Override
        public void actionPerformed(ActionEvent e) {
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}
