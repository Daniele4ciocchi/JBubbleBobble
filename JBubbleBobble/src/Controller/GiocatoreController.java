package Controller;

import Model.Giocatore;
import View.GiocatoreView;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GiocatoreController implements KeyListener {
    //GiocatoreController viene istanziato dal model

    //salvataggio del model e del controller
    private Giocatore model;
    private GiocatoreView view;
    private boolean moveUp, moveDown, moveLeft, moveRight;

    //costruttore del controller
    public GiocatoreController(Giocatore model, GiocatoreView view){
        this.model = model;
        this.view = view;
        this.view.initialize();
    }


    //metodi per keylistener
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> model.moveLeft();
            case KeyEvent.VK_RIGHT -> model.moveRight();
            case KeyEvent.VK_SPACE -> model.salta();
            case KeyEvent.VK_ENTER -> model.sparaBolle();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
