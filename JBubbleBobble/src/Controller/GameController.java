package Controller;

import Model.Partita;
import View.GameView;
import View.MenuView;

import javax.swing.*;

//TODO: qua dobbiamo decide una cosa 
// bisogna capire se dobbiamo far partire il gioco da qua
// bidogna strutturare la classe e capire cosa fa

public class GameController implements Runnable {

    private Partita partita;
    private GameView view;
    private boolean running;

    public GameController(Partita partita, GameView view) {
        this.partita = partita;
        this.view = view;
        this.running = false;
    }

    public void startGame() {
        running = true;
        new Thread(this).start();
    }

    public void stopGame() {
        running = false;
    }

    private void startGameLoop() {
        Timer timer = new Timer(16, e -> updateGame());
        timer.start();
    }

    private void updateGame() {
        if (leftPressed) {
            player.move(-5);
        } else if (rightPressed) {
            player.move(5);
        }

        player.applyGravity(level);
        view.repaint();
    }

    private void updateGame() {
        // Update game logic, e.g., move entities, check collisions
        partita.update();
    }

    private void renderGame() {
        // Render the game state to the view
        view.repaint();
    }
}