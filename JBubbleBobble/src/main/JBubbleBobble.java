package main;

import model.*;
import view.MenuView;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.LineBorder;

import controller.MenuController;

public class JBubbleBobble {
    

    public static void main(String[] args) {
        
        MenuView menuview = new MenuView();
        MenuController menucontroller = new MenuController(menuview);
       
    }

    
}