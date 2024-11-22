package Controller;

import Model.Entita;
import Model.Partita;
import View.GameView;
import View.MenuView;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

//TODO: qua dobbiamo decide una cosa 
// bisogna capire se dobbiamo far partire il gioco da qua
// bidogna strutturare la classe e capire cosa fa

public class GameController {

    private Partita partita;
    private GameView view;

    private boolean leftPressed = false;
    private boolean rightPressed = false;

    public GameController(Partita partita, GameView view) {
        this.partita = partita;
        this.view = view;
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
        Timer timer = new Timer(16, e -> updateGame());
        timer.start();
    }

    private void updateGame() {
        //TODO: indice del loop di gioco
        //  - far comparire tutte le entità
        //  - far muovere le entità
        //  - far sparire le entità

        partita.posizionaEntita();
        view.drawLevel(partita.getLivello().getGrid());
        if (leftPressed) {
            partita.getEntita().getFirst().moveLeft();
        } else if (rightPressed) {
            partita.getEntita().getFirst().moveRight();
        }
        for (Entita entita : partita.getEntita()) {
            partita.getLivello().applyGravity(entita);
        }

        //implementare view repaint
        view.repaint();
    }


    private void renderGame() {
        // Render the game state to the view
        view.repaint();
    }
}