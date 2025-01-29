package main;

import model.*;
import view.MenuView;

import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;

import javax.swing.*;

import controller.MenuController;

public class JBubbleBobble {
    public static JFrame frame;

    public static void main(String[] args) {
        // Gestione del profilo
        // Profilo.getInstance();
        // try {
        //     Profilo.getInstance().loadProfilo("JBubbleBobble" + File.separator + "src" + File.separator + "profiles.txt");
        // } catch (ClassNotFoundException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // } catch (IOException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }

        // Creazione del frame
        frame = new JFrame("Esempio di JPanel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setUndecorated(true);
        frame.setShape(new RoundRectangle2D.Double(0, 0, frame.getWidth(), frame.getHeight(), 100, 100)); // Set rounded corners

        // Creazione del controller del menu
        MenuView menuview = new MenuView();
        MenuController menucontroller = new MenuController(menuview);
        frame.setContentPane(menucontroller.getview().getPanel());

        // Visualizzazione del frame
        frame.setVisible(true);

        // TEST: stampo a terminale tutti i migliori 10 score
        System.out.println(Profilo.getInstance().getBestScores());
    }
}