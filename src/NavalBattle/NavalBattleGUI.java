package NavalBattle;

import NavalBattle.GameZone.WaterZone;

import javax.swing.*;
import java.awt.*;

/**
 * This class is used for ...
 * @autor Carlos Felipe Montoya carlos.felipe.montoya@correounivalle.edu.co
 * @version v.1.0.0 date:21/03/2023
 */
public class NavalBattleGUI extends JFrame {

    private Header headerProject;
    private  GamePanel panelUser, panelCpu;


    /**
     * Constructor of GUI class
     */
    public NavalBattleGUI(){
        initGUI();

        //Default JFrame configuration
        this.setTitle("The Title app");
        /*this.setSize(200,100);*/
        this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

    /**
     * This method is used to set up the default JComponent Configuration,
     * create Listener and control Objects used for the GUI class
     */
    private void initGUI() {
        //Set up JFrame Container's Layout
        /*this.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();*/

        //Create Listener Object and Control Object
        //Set up JComponents
        //headerProject = new Header("Header ...", Color.BLACK);

        //this.add(headerProject,BorderLayout.NORTH); //Change this line if you change JFrame Container's Layout
        panelUser = new GamePanel(2,2);
        panelUser.setPreferredSize(new Dimension(600,300));
        /*gbc.gridx = 0;
        gbc.gridy = 0;*/
        this.add(panelUser);



    }

    /**
     * Main process of the Java program
     * @param args Object used in order to send input data from command line when
     *             the program is execute by console.
     */
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            NavalBattleGUI miProjectNavalBattleGUI = new NavalBattleGUI();
        });
    }

    /**
     * inner class that extends an Adapter Class or implements Listeners used by GUI class
     */
    private class Escucha {

    }
}
