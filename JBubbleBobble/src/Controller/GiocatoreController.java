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
            case KeyEvent.VK_LEFT:
                model.moveLeft();
                break;
            case KeyEvent.VK_RIGHT:
                model.moveRight();
                break;
            case KeyEvent.VK_SPACE:
                model.salta();
                break;
            case KeyEvent.VK_ENTER:
                model.sparaBolle();
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
