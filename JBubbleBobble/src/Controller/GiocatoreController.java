package Controller;

import Model.Giocatore;
import View.GiocatoreView;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GiocatoreController implements KeyListener {

    private Giocatore model;
    private GiocatoreView view;

    public GiocatoreController(){
        this.model = Giocatore.getInstance();
        this.view = new GiocatoreView();
    }


    //metodi per keylistener
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> {
                model.moveLeft();

            }
            case KeyEvent.VK_RIGHT -> {
                model.moveRight();
            }
            case KeyEvent.VK_Z -> model.salta();
            case KeyEvent.VK_X -> model.sparaBolle();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
