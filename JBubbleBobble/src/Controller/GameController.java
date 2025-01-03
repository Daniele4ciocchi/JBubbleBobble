package Controller;

import Model.Bolla;
import Model.Entita;
import Model.Giocatore;
import Model.Nemico;
import Model.Partita;
import Model.SpecialItem;
import View.GameView;
import View.MenuView;
import View.PartitaView;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

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
    private boolean jump = false;

    

    public GameController(Partita partita, GameView view) {
        this.partita = partita;
        this.view = view;
        
        view.addPanel(new PartitaView(partita.getLivello().getTilesize()));
        view.getPanel().setGrid(partita.getLivello().getGrid());
        view.getPanel().setPath(partita.getLivello().getTilePath());
        view.getPanel().setEntita(partita.getEntita());

        startGameLoop();
        setupKeyBindings();
        
        for (Entita e : partita.getEntita()) {
            e.addObserver(view.getPanel());
        }
        
    }

    private void setupKeyBindings() {
        Giocatore giocatore = (Giocatore) partita.getEntita().getFirst();
        view.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_SPACE) {
                    if (partita.getLivello().isWalkable(giocatore.getX(),giocatore.getY()-1)){
                
                        giocatore.jump();
                    }
                } else if (e.getKeyChar() == 'j') {
                    partita.addEntita(giocatore.shoot());
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyChar() == 'a') {
                    leftPressed = true;
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyChar() == 'd') {
                    rightPressed = true;
                } 
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyChar() == 'a') {
                    leftPressed = false;
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyChar() == 'd') {
                    rightPressed = false;
                } 
            }
        });
    }

    // forse va messo in entita oppure in livello idk
    public void checkPlayerMovement(){
        Giocatore giocatore = (Giocatore) partita.getEntita().getFirst();
        if (leftPressed) giocatore.moveLeft(partita.getLivello());
        else if (rightPressed) giocatore.moveRight(partita.getLivello());
    }


    private void startGameLoop() {
        Timer timer = new Timer(32, e -> {gameLoop();});
        
        partita.posizionaEntita();
        
        // for (Entita e : partita.getEntita()) {
        //     System.out.println(e);
        // }

        view.getPanel().setEntita(partita.getEntita());
        view.getPanel().repaint();

        timer.start();
    }
    
    private void gameLoop(){

        //impostare timer 0,016 secondi
        
        ///System.out.println("Game loop");
        //TODO: indice del loop di gioco
        //  - far comparire tutte le entità
        //  - far muovere le entità (forse questo è un compito di partita)
        //  - far sparire le entità (e anche questo) partita.clear()
        //  - costruire lo sfondo del livello (compito della view)
        //  - posizionare le entità (compito della view)
        //  -

        //view.getPanel().setEntita(partita.getEntita());

        if (partita.checkCollision(partita.getEntita().getFirst()) instanceof Nemico){
            ((Giocatore) (partita.getEntita().getFirst())).removeLife();
            ((Giocatore) (partita.getEntita().getFirst())).resetPosizione();
        }
    
        for (Entita e : partita.getEntita()){
            
            partita.applyGravity(e);
            if (e instanceof Nemico){
                ((Nemico)e).move(partita.getEntita().getFirst().getX(), partita.getEntita().getFirst().getY(), partita.getLivello());
            }
            if (e instanceof Bolla){
                ((Bolla)e).move(partita.getLivello());
            }
        } 
        

        
        //controllo movimento giocatore
        checkPlayerMovement();

    }

}