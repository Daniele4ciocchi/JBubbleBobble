package main;

import Model.*;
import Controller.MenuController;
import View.MenuView;

import javax.swing.*;

public class JBubbleBobble {
    public static JFrame frame;

    public static void main(String[] args) {

        MenuView menuView = new MenuView();
        MenuController menuController = new MenuController( menuView);
    }

//
//        // Creazione del frame
//        frame = new JFrame("Esempio di JPanel");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(400, 300);
//
//
//        // Creazione del controller del menu
//        MenuController menuController = new MenuController();
//        frame.setContentPane(menuController.getView().getPanel());
//
//        // Visualizzazione del frame
//        frame.setVisible(true);
//
//
//        // creazione profilo di test
//        test1();
//    }
//
//    public static void test1(){
//        Profilo p = Profilo.getInstance();
//        p.setNickname("test");
//
//        Partita partita1 = new Partita(1); // partita vinta
//        partita1.addPunteggio(24011);
//        partita1.setVinta();
//
//        Partita partita2 = new Partita(1); // partita persa
//        partita1.addPunteggio(1000);
//
//        p.addPartita(partita1);
//        p.addPartita(partita2);
//    }
}