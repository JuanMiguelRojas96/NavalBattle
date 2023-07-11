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
import java.util.ArrayList;
import java.util.TimerTask;
import java.util.Timer;

/**
 * The NavalBattleGUI class represents the graphical user interface of the Naval Battle game.
 * It provides the main window and controls the layout and behavior of the game components.
 *
 * @author Juan Miguel Rojas Noriega juan.noriega@correounivalle.edu.co
 * @author Jean Heyller Palomino jean.palomino@correounivalle.edu.co
 * @author Christian Daniel Villegas christian.villegas@correounivalle.edu.co
 * @version v.1.0.0 date: 09/07/2023
 */
public class NavalBattleGUI extends JFrame {
    private Header headerProject;
    private  GamePanel panelUser, panelCpu;
    private PanelLogin panelLogin;
    private ModelNavalBatlle modelNavalBatlle;
    private ShipClass[] ships;
    private  Escucha escucha;

    private Boolean press;

    private Timer timer;

    private int turn;

    /**
     * Constructor for the NavalBattleGUI class.
     * Initializes the graphical user interface and sets up the default JFrame configuration.
     */
    public NavalBattleGUI(){
        initGUI();
        this.setTitle("The Title app");
        this.setSize(1050,600);
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(1,138,180));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Initializes the graphical user interface by setting up the default JComponent configuration,
     * creating listeners, and controlling objects used in the GUI.
     */
    private void initGUI() {
        this.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        turn = 0;
        press = false;

        escucha = new Escucha();
        modelNavalBatlle = new ModelNavalBatlle();
        ships = new ShipClass[2];

        //Panel Login configuration and initialization
        panelLogin = new PanelLogin(this);
        this.add(panelLogin);

        //Configuration and initialization of the Header, which contains the User Profile.
        headerProject = new Header(Color.BLACK);
        headerProject.setVisible(false);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(headerProject,gbc);

        //Configuration and initialization of the User Panel, where our boats will be.
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

        //Configuration and initialization of the CPU Panel, where the enemy ships will be.
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

    /**
     * Retrieves the visible components of the GUI and starts the game.
     * Sets the header image and username based on the login panel input.
     */
    public void getComponentesVisibles() {
        play();
        headerProject.setImage(panelLogin.getAvatarLogin());
        headerProject.setUserName(panelLogin.getUsername());
        headerProject.setVisible(true);
        panelUser.setVisible(true);
        panelCpu.setVisible(true);
    }

    /**
     * Adds a click listener to all WaterZone components in the CPU panel.
     */
    public void clickWaterZone() {
        Component[] components = panelCpu.getComponents();
        for (Component component : components) {
            if (component instanceof WaterZone) {
                WaterZone waterZone = (WaterZone) component;
                waterZone.addMouseListener(escucha);

            }
        }
    }

    /**
     * Marks the WaterZone components of a sunken ship in the CPU panel.
     *
     * @param ship The ship that has been sunk.
     */
    public void setSonken(ShipClass ship) {
        Component[] components = panelCpu.getComponents();
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

    public void setSonkenUser(ShipClass ship) {
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

    /**
     * Starts the game by generating the ships, displaying them in the user panel,
     * and attaching click listeners to the water zones in the CPU panel.
     */
    public void play() {
        modelNavalBatlle.generateShips();
        ships = modelNavalBatlle.getShips();
        modelNavalBatlle.imprimirShips();
        modelNavalBatlle.movesCpu();
        //this.setImage("5 4");

        //modelNavalBatlle.checkBreak(4,5,0);

        Component[] components = panelUser.getComponents();
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

    }


    public void  setImage(String coordinates) {
        String row = String.valueOf(coordinates.charAt(0));
        String column = String.valueOf(coordinates.charAt(2));
        Component[] components = panelUser.getComponents();
        for (Component component : components) {
            if (component instanceof WaterZone) {
                WaterZone waterZone = (WaterZone) component;
                String image = modelNavalBatlle.getImage(coordinates,  "user");
                ShipClass ship = modelNavalBatlle.checkSunken(coordinates);
                if (ship != null) {
                    ShipClass shipTrue = modelNavalBatlle.getCoordinatesSunken(ship);
                    if (shipTrue != null) {
                        //return shipTrue;
                    } else {
                        if (waterZone.getName().equals(row + "," + column)) {
                            ImageIcon shipIcon = new ImageIcon(getClass().getResource("/resources/shipStates/" + image));
                            waterZone.setImageIcon(shipIcon);

                        }
                    }
                } else {
                    if (waterZone.getName().equals(row + "," + column)) {
                        ImageIcon shipIcon = new ImageIcon(getClass().getResource("/resources/shipStates/" + image));
                        waterZone.setImageIcon(shipIcon);

                    }
                }
            }
        }
    }

    public void printArary(ArrayList moves){
        for(int i=0;i<moves.size();i++){
            String coordiantes = (String) moves.get(i);
            String row = String.valueOf(coordiantes.charAt(0));
            String column = String.valueOf(coordiantes.charAt(1));
            ShipClass ship = modelNavalBatlle.checkSunken(row+" "+column);
            if (ship != null) {
                ShipClass shipTrue = modelNavalBatlle.getCoordinatesSunken(ship);
                if (shipTrue != null) {
                    setSonkenUser(ship);
                }
            }else {
                setImage(row+" "+column);
            }
        }
    }

    /*public void ejecutarModelNavalBatlle() {
        if (turn == 0 && !press) {
            timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    ArrayList coordinates = modelNavalBatlle.movesCpu();
                    printArary(coordinates);
                    turn = modelNavalBatlle.getTurno();
                    timer.cancel();
                }
            };
            timer.schedule(task, 3000); // 7000 milisegundos = 7 segundos
        }
    }*/


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
     * The Escucha class is an inner class that extends an Adapter Class or implements Listeners
     * used by the NavalBattleGUI class for handling events.
     */
    private class Escucha  implements ActionListener, MouseListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        }
        @Override
        public void mouseClicked(MouseEvent e) {
            Component component = e.getComponent();
            if (component instanceof WaterZone) {
                WaterZone waterZone = (WaterZone) component;
                if(turn==0){
                    ShipClass ship = modelNavalBatlle.handleWaterZoneClick(waterZone);
                    if(ship!=null){
                        setSonken(ship);
                    }
                    turn = modelNavalBatlle.getTurno();
                    System.out.println(turn);

                }else {
                    ArrayList coordinates = modelNavalBatlle.movesCpu();
                    printArary(coordinates);
                    turn = modelNavalBatlle.getTurno();
                    System.out.println(turn);
                }
            }

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
