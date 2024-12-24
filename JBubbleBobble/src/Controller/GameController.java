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

    private int score = 0;
    private int nemiciUccisi = 0;
    private int saltiEffettuati = 0;
    private int bolleSparate = 0;
    private int bolleScoppiate = 0;
    private int caramelleRosaMangiate = 0;
    private int caramelleRosseMangiate = 0;
    private int caramelleBluMangiate = 0;

    // campi dei buff dei powerup
    private boolean BOLLE_RANGE_UP = false;
    private boolean BOLLE_VEL_UP = false;
    private boolean BOLLE_FIRERATE_UP = false;
    private boolean BONUS_MOV = false;
    private boolean BONUS_SALTO = false;
    private boolean BONUS_SPARO = false;

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

    // funzione invocata nel game loop al raggiungimento di un requisito per un powerup
    // NOTA: i controlli dei valori sono così per assicurare che venga creato un SOLO powerup
    private PowerUp spawnPowerUps(int sx, int sy){
        // OMBRELLI
        if (nemiciUccisi == 15){
            nemiciUccisi++;
            return new PowerUp(sx,sy,0,0,0,PowerUp.Tipologia.OMBRELLO,PowerUp.Colore.ARANCIONE);
        }
        else if (nemiciUccisi == 26){
            nemiciUccisi++;
            return new PowerUp(sx,sy,0,0,0,PowerUp.Tipologia.OMBRELLO,PowerUp.Colore.ROSSO);
        }
        else if (nemiciUccisi == 37){
            nemiciUccisi = 0;
            return new PowerUp(sx,sy,0,0,0,PowerUp.Tipologia.OMBRELLO,PowerUp.Colore.ROSA);
        }

        // CARAMELLE
        else if (saltiEffettuati == 35){
            saltiEffettuati = 0;
            return new PowerUp(sx,sy,0,0,0,PowerUp.Tipologia.CARAMELLA,PowerUp.Colore.GIALLO);
        }
        else if (bolleSparate == 35){
            bolleSparate = 0;
            return new PowerUp(sx,sy,0,0,0,PowerUp.Tipologia.CARAMELLA,PowerUp.Colore.ROSA);
        }
        else if (bolleScoppiate == 35){
            bolleScoppiate = 0;
            return new PowerUp(sx,sy,0,0,0,PowerUp.Tipologia.CARAMELLA,PowerUp.Colore.BLU);
        }

        // ANELLI
        else if (caramelleRosaMangiate == 3){
            caramelleRosaMangiate = 0;
            return new PowerUp(sx,sy,0,0,0,PowerUp.Tipologia.ANELLO,PowerUp.Colore.ROSA);
        }
        else if (caramelleRosseMangiate == 3){
            caramelleRosseMangiate = 0;
            return new PowerUp(sx,sy,0,0,0,PowerUp.Tipologia.ANELLO,PowerUp.Colore.ROSSO);
        }
        else if (caramelleBluMangiate == 3){
            caramelleBluMangiate = 0;
            return new PowerUp(sx,sy,0,0,0,PowerUp.Tipologia.ANELLO,PowerUp.Colore.BLU);
        }

        else{
            return null;
        }
    }

    // invocata alla raccolta del powerup per applicare il suo effetto
    private void usePowerUp(PowerUp p){
        
    }

}