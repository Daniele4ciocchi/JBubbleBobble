package Controller;

import Model.Partita;

import javax.swing.*;

public class PartitaController {

    private Partita partita = Partita.getInstance();

    public void Gioca(){
        Timer timer = new Timer(16, e -> updateGame());
        timer.start();
    }

    private void updateGame() {
        //ovviamente da rivedere
        if (leftPressed) {
            player.move(-5);
        } else if (rightPressed) {
            player.move(5);
        }

        player.applyGravity(level);

        view.repaint();
    }
}
