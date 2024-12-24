package Controller;

import Model.Entita;
import Model.Partita;
import Model.PowerUp;
import View.GameView;
import View.MenuView;
import View.PartitaView;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

//TODO: qua dobbiamo decide una cosa
// bisogna capire se dobbiamo far partire il gioco da qua
// bidogna strutturare la classe e capire cosa fa

// TODO: implementare le seguenti funzioni
//  - loop di gioco
//  - controller dei tasti premuti
//  avvio della view (in realta non so se sta in una funzione

public class GameController {

    private Partita partita;
    private GameView view;

    private boolean leftPressed = false;
    private boolean rightPressed = false;

    

    public GameController(Partita partita, GameView view) {
        this.partita = partita;
        this.view = view;
        view.addPanel(new PartitaView(partita.getLivello().getGrid(),partita.getLivello().getTilePath()));
        startGameLoop();

    }

    private void setupKeyBindings() {
        view.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    leftPressed = true;
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    rightPressed = true;
                } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    partita.getEntita().getFirst().jump();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    leftPressed = false;
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    rightPressed = false;
                }
            }
        });
    }

    private void startGameLoop() {
        Timer timer = new Timer(16, e -> {
            try {
                updateGame();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        timer.start();
    }

    public void checkPlayerMovement(){
        if (leftPressed) {
            partita.getEntita().getFirst().moveLeft();
        } else if (rightPressed) {
            partita.getEntita().getFirst().moveRight();
        }
    }

    
    private void updateGame() throws IOException {
        //TODO: indice del loop di gioco
        //  - far comparire tutte le entità
        //  - far muovere le entità (forse questo è un compito di partita)
        //  - far sparire le entità (e anche questo) partita.clear()
        //  - costruire lo sfondo del livello (compito della view)
        //  - posizionare le entità (compito della view)
        //  -


        //view.setBackgroundImage(view.createImage(partita.getLivello().getGrid(),partita.getLivello().getTilePath()));
        partita.posizionaEntita();


        //controllo movimento giocatore
        checkPlayerMovement();

        view.getPanel().repaint();
    }


    private void renderGame() {
        // Render the game state to the view
        view.getPanel().repaint();
    }

    

}