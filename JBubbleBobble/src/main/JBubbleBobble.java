package main;

import view.MenuView;

import controller.MenuController;

public class JBubbleBobble {
    

    public static void main(String[] args) {
        
        MenuView menuview = new MenuView();
        MenuController menucontroller = new MenuController(menuview);
       
    }

    
}