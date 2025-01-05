package Controller;

import Model.Bolla;
import Model.BollaSemplice;
import Model.Entita;
import Model.Giocatore;
import Model.Nemico;
import Model.Partita;
import Model.SpecialItem;
import View.GameView;
import View.MenuView;
import View.PartitaView;
import View.TopPanel;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
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
    private Timer timer;

    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private boolean jump = false;
    private int nextLevelCounter = 250;
    private int counter = 0;
    private int bubblecounter = 0;
    private int giocatorecounter = 0;
    

    public GameController(Partita partita, GameView view) {
        this.partita = partita;
        this.view = view;
        
        view.addTopPanel(new TopPanel());
        view.addPartitaPanel(new PartitaView());
        view.getPanel().setPartita(partita);

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
                } else if (e.getKeyChar() == 'j' || e.getKeyChar() == 'J') {
                    if(Math.abs(counter - bubblecounter) > 10 && !giocatore.isDead()){
                        Bolla b = giocatore.shoot();
                        partita.addEntita(b);
                        b.addObserver(view.getPanel());
                        bubblecounter = counter;
                    }
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
        timer = new Timer(32, e -> {gameLoop();});
        //AudioManager.getInstance();
        
        partita.posizionaEntita();
        view.getPanel().repaint();

        timer.start();
    }
    
    private void gameLoop(){
        
        counter = (counter == 1000000000) ? 0 : ++counter;

        //view.getPanel().setEntita(partita.getEntita());

        Entita collision = partita.checkCollision(partita.getEntita().getFirst());

        if (collision instanceof Nemico){
            ((Giocatore) (partita.getEntita().getFirst())).die();
        }
        if (collision instanceof Bolla){
            partita.removeEntita(collision);
            
        }

        if (partita.getEntita().getFirst().isDead()){
            giocatorecounter++;
            if (giocatorecounter == 100){
                ((Giocatore)partita.getEntita().getFirst()).respawn();
                giocatorecounter = 0;
            }

        }
        ArrayList<Entita> EntitaDaRimuovere = new ArrayList<Entita>();
        ArrayList<Entita> EntitaDaAggiungere = new ArrayList<Entita>();
        for (Entita e : partita.getEntita()){
            
            partita.applyGravity(e);
            if (e instanceof Nemico){
                ((Nemico)e).move(partita.getEntita().getFirst().getX(), partita.getEntita().getFirst().getY(), partita.getLivello());
            }
            if (e instanceof Bolla){
                Entita e2 = partita.checkCollision(e);
                ((Bolla)e).move(partita.getLivello());
                if (((Bolla)e).getPopTime() == 0){
                    if (((Bolla)e).getNemico() != null){
                        EntitaDaAggiungere.add(((Bolla)e).scoppia());
                    }
                    EntitaDaRimuovere.add(e);
                }
                if (((Bolla)e).getNemico() == null){
                    if (e2 instanceof Nemico){
                        ((BollaSemplice)e).catturaNemico(((Nemico)e2));
                        EntitaDaRimuovere.add(e2);
                    }
                }
            }
        } 

        for (Entita e : EntitaDaRimuovere)partita.removeEntita(e);
        for (Entita e : EntitaDaAggiungere)partita.addEntita(e);
        
        if (partita.getEntita().stream().filter(e ->( e instanceof Nemico) || (e instanceof Bolla && ((Bolla)e).getNemico() != null)).count() == 0){
            nextLevelCounter--;
            if (nextLevelCounter == 0){
                if (partita.getLivello().getLevelNum() == 104){
                    view.getFrame().dispose();
                    timer.stop();
                }
                partita.svuotaEntita();
                partita.getLivello().changeLevel();
                partita.posizionaEntita();
                ((Giocatore)partita.getEntita().getFirst()).resetPosizione();
                nextLevelCounter = 250;
                for (Entita e : partita.getEntita()){
                    e.addObserver(view.getPanel());
                }
                counter = 0;
                
            }
        }

        if (((Giocatore)partita.getEntita().getFirst()).getLife() == 0){   
            partita.getLivello().changeLevel(104);
            partita.svuotaEntita();
            view.getPanel().repaint();
            
        }
        //controllo movimento giocatore
        checkPlayerMovement();

    }

}