package main;

import view.MenuView;

import controller.MenuController;

/**
 * Classe principale del gioco.
 */
public class JBubbleBobble {
    
    /**
     * Metodo main del gioco.
     * @param args argomenti passati da riga di comando
     */
    public static void main(String[] args) {
        
        MenuView menuview = new MenuView();
        MenuController menucontroller = new MenuController(menuview);
       
    }

    
}