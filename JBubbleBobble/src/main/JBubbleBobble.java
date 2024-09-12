package main;

import Controller.MenuController;

import javax.swing.*;

public class JBubbleBobble {
    public static JFrame frame;

    public static void main(String[] args) {
        // Creazione del frame
        frame = new JFrame("Esempio di JPanel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Creazione del controller del menu
        MenuController menuController = new MenuController();
        frame.setContentPane(menuController.getView().getPanel());

        // Visualizzazione del frame
        frame.setVisible(true);
    }
}