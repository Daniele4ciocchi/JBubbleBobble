package Controller;

import Model.Acqua;
import Model.Acqua.Goccia;
import Model.Bolla;
import Model.BollaSemplice;
import Model.BollaAcqua;
import Model.Entita;
import Model.Giocatore;
import Model.Nemico;
import Model.Partita;
import Model.PointItem;
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

public class GameController {

    private Partita partita;
    private GameView view;
    private Timer timer;

    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private boolean jump = false;
    private int nextLevelCounter = 250;
    private int counter = 0;
    private int bubbleCounter = 0;
    private int morteGiocatoreCounter = 0;
    private int invincibilitaGiocatoreCounter = 0;
    private int spriteBoccaApertaCounter = 5; // numero di frame in cui la bocca del giocatore rimane aperta
    
    public GameController(Partita partita, GameView view){
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

    private void setupKeyBindings(){
        Giocatore giocatore = (Giocatore) partita.getEntita().getFirst();
        view.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_SPACE) {
                    if (partita.getLivello().isWalkable(giocatore.getX(),giocatore.getY()-1)){
                        giocatore.jump();
                        partita.addSaltoEffettuato();
                    }
                } else if (e.getKeyChar() == 'j' || e.getKeyChar() == 'J') {
                    if(Math.abs(counter - bubbleCounter) > 10 && !giocatore.isDead()){
                        Bolla b = giocatore.shoot();
                        partita.addEntita(b);
                        b.addObserver(view.getPanel());
                        bubbleCounter = counter;
                        partita.addBollaSparata();
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

    public void checkPlayerMovement(){
        Giocatore giocatore = (Giocatore) partita.getEntita().getFirst();

        if (leftPressed) giocatore.moveLeft(partita.getLivello());
        else if (rightPressed) giocatore.moveRight(partita.getLivello());
        
    }

    public void counter(){ 
        counter = (counter == 1000000000) ? 0 : ++counter; 
    }

    public void spawnBubbles(){
        if (counter % 200 == 0 && partita.getEntita().stream().filter(e -> e instanceof BollaAcqua).count() < 3){
            for (int i = 0; i < partita.getLivello().getGrid().length; i++){
                for (int j = 0; j < partita.getLivello().getGrid()[0].length; j++){
                    if (partita.getLivello().getGrid()[i][j].getType() == Model.Tile.TileType.WATER){
                        BollaAcqua b = new BollaAcqua(j*16, i*16);
                        partita.addEntita(b);
                        b.addObserver(view.getPanel());
                    }
                }
            }
        }
    }

    public void applyGravity(){ 
        partita.getEntita().forEach(e -> partita.gravita(e)); 
    }

    public void checkPlayerCollision(){
        Entita collision = partita.checkCollision(partita.getEntita().getFirst());

        if (collision instanceof Nemico){
            ((Giocatore) (partita.getEntita().getFirst())).die();
            partita.addNemiciUccisi();
        }
        if (collision instanceof BollaSemplice){
            partita.addBollaScoppiata();
            partita.removeEntita(collision);
            if (((Bolla)collision).getNemico() != null){
                partita.addScore(500);
                ((Entita)((Bolla)collision).getNemico()).die();
            }
        }
        if (collision instanceof BollaAcqua){
            partita.addBollaAcquaScoppiata();
            partita.removeEntita(collision);
            partita.addEntita(new Acqua(collision.getX(), collision.getY()));
        }
        
        if (collision instanceof PointItem){
            partita.addScore(((PointItem)collision).getTipologia().getPunti());
            partita.removeEntita(collision);
        }
        view.getTopPanel().updateScore(partita.getScore());
    }

    public void checkEntityCollision(){
        //water
        for (Entita e : partita.getEntita()){
            if (e instanceof Acqua){
                for (Goccia g : ((Acqua)e).getGocce()){
                    Entita collision = partita.checkCollision(g);
                    if (collision instanceof Nemico){
                        ((Nemico)collision).move(g);
                        partita.removeEntita(collision);
                    }
                }
            }
        }
    }

    public void checkPlayerDead(){
        if (partita.getEntita().getFirst().isDead()){
            morteGiocatoreCounter++;
            if (morteGiocatoreCounter == 100){
                ((Giocatore)partita.getEntita().getFirst()).respawn();
                morteGiocatoreCounter = 0;
            }
        }
    }

    public void moveEnemies(){
        ArrayList<Entita> EntitaDaRimuovere = new ArrayList<Entita>();
        ArrayList<Entita> EntitaDaAggiungere = new ArrayList<Entita>();
        for (Entita e : partita.getEntita()){
            if (e instanceof Nemico){
                ((Nemico)e).move(partita.getEntita().getFirst().getX(), partita.getEntita().getFirst().getY(), partita.getLivello());
            }
        }
        for (Entita e : EntitaDaRimuovere)partita.removeEntita(e);
        for (Entita e : EntitaDaAggiungere)partita.addEntita(e);
    }

    public void moveBubbles(){
        ArrayList<Entita> EntitaDaRimuovere = new ArrayList<Entita>();
        ArrayList<Entita> EntitaDaAggiungere = new ArrayList<Entita>();
        for (Entita e : partita.getEntita()){
            
            if (e instanceof BollaSemplice){
                Entita e2 = partita.checkCollision(e);
                ((Bolla)e).move(partita.getLivello());

                if (((BollaSemplice)e).getPopTime() == 0){
                    if (((BollaSemplice)e).getNemico() != null){
                        EntitaDaAggiungere.add(((Bolla)e).scoppia());
                    }
                    EntitaDaRimuovere.add(e);
                }
                if (((BollaSemplice)e).getNemico() == null){
                    if (e2 instanceof Nemico){
                        ((BollaSemplice)e).catturaNemico(((Nemico)e2));
                        EntitaDaRimuovere.add(e2);
                    }
                }
            }else if (e instanceof BollaAcqua){
                ((BollaAcqua)e).move(partita.getLivello());
            }else if (e instanceof Acqua){
                ((Acqua)e).move(partita.getLivello());
                if (partita.getLivello().isTPEntry(e.getX(), e.getY())){
                    
                    EntitaDaRimuovere.add(e);
                }
            } 
        }
        for (Entita e : EntitaDaRimuovere)partita.removeEntita(e);
        for (Entita e : EntitaDaAggiungere)partita.addEntita(e);

    }

    public boolean checkEntityPresence(){ 
        return partita.getEntita().stream().filter(e ->( e instanceof Nemico) || (e instanceof Bolla && ((Bolla)e).getNemico() != null)).count() == 0;
    }
        
    public void goToNextLevel(){
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

    public void checkGameOver(){
        if (((Giocatore)partita.getEntita().getFirst()).getLife() == 0){   
            partita.getLivello().changeLevel(104);
            partita.svuotaEntita();
            view.getPanel().repaint();
            
        }
    }

    private void checkBoccaAperta(){
        if (((Giocatore) partita.getEntita().getFirst()).isShooting()){
            spriteBoccaApertaCounter--;
            if (spriteBoccaApertaCounter == 0){
                ((Giocatore)partita.getEntita().getFirst()).setShooting(false);
                spriteBoccaApertaCounter = 5;
            }
        }
    }

    public void checkDyingEnemies(){
        for (Entita e : partita.getEntitaMorte()){
            if (e instanceof Nemico){
                if (e.isDead() == true){
                    PointItem drop = ((Nemico)e).dying();
                    
                    if (drop != null){
                        partita.addEntita(drop);
                        drop.addObserver(view.getPanel());
                        
                    }
                }
            }
        }
    }

    private void startGameLoop(){
        timer = new Timer(32, e -> {gameLoop();});
        //AudioManager.getInstance();
        
        partita.posizionaEntita();
        view.getPanel().repaint();

        timer.start();
    }
    
    private void gameLoop(){

        //lista funzioni 
        counter();
        spawnBubbles();
        applyGravity();
        checkPlayerCollision();
        checkEntityCollision();
        checkBoccaAperta();
        checkPlayerDead();
        moveEnemies();
        moveBubbles();
        checkDyingEnemies();
        
        if (checkEntityPresence())goToNextLevel();

        checkGameOver();
        checkPlayerMovement();

    }

}