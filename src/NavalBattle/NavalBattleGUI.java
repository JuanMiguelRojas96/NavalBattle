package NavalBattle;

import NavalBattle.GameZone.WaterZone;

import javax.swing.*;
import javax.swing.border.TitledBorder;
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
        //this.setSize(1000,1000);
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
        this.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        //this.getContentPane().setLayout(new FlowLayout());


        //Create Listener Object and Control Object
        //Set up JComponents

          //Change this line if you change JFrame Container's Layout

        headerProject = new Header("Naval",Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(headerProject,gbc);

        panelUser = new GamePanel(10,10);
        panelUser.setBorder(BorderFactory.createTitledBorder(null ,"PANEL USUARIO", TitledBorder.CENTER,
                TitledBorder.DEFAULT_JUSTIFICATION , new Font("Stencil",Font.PLAIN+Font.BOLD,20),Color.BLACK));
        gbc.gridx =0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        this.add(panelUser,gbc);



        panelCpu = new GamePanel(10,10);
        panelCpu.setBorder(BorderFactory.createTitledBorder(null ,"PANEL CPU", TitledBorder.CENTER,
                TitledBorder.DEFAULT_JUSTIFICATION , new Font("Stencil",Font.PLAIN+Font.BOLD,20),Color.BLACK));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        this.add(panelCpu,gbc);












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
