package model;

import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.Observer;
import java.util.Random;
import java.util.Timer;

import model.Acqua.Goccia;

public abstract class Nemico extends Personaggio implements Runnable{
    // campi per movimento diversificato dei nemici
    protected int waitTime; // intero da 1 a 10, generato nel costruttore, usato come "ritardo"
    protected int currentWaitTime;

    protected int deathCounter = 50;

    protected boolean bubbled;

    protected String[] bubbledSpritesPath = {baseSpritePath, baseSpritePath, baseSpritePath }; // 3 sprite

    public Nemico(int x, int y, int velocita, int salto){
        super(x, y, velocita, salto, -7, salto);

        // generazione random del carattere del nemico
        Random rand = new Random();
        waitTime = rand.nextInt(5) + 1;
    }

    public boolean isBubbled(){return bubbled;}
    public void setBubbled(boolean b){this.bubbled = b;}
    public int getWaitTime() {return this.waitTime;}
    public int getDeathCounter() {return this.deathCounter;}
    
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try { Thread.sleep(100);
            } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        }
        super.setChanged();
        super.notifyObservers();
    }

    public void move(int gx, int gy, Livello l){
        if (!watered){
            if (this.getX() < gx) {
                if (currentWaitTime == 0){
                    goingRight = true;
                    moveRight(l);
                    currentWaitTime = waitTime;
                } else currentWaitTime--;
            }
            if (this.getX() > gx) {
                if (currentWaitTime == 0){
                    goingRight = false;
                    moveLeft(l);
                    currentWaitTime = waitTime;
                } else currentWaitTime--;
            }
            if (this.getY()+32 < gy && l.isWalkable(this.getX(), this.getY()-1)) jump();
        }
    }

    // ritorna lo sprite adatto relativamente allo stato di questo nemico
    public String getSpritePath(){
        if (dead) return deathSpritePath; // MORTE (score ottenuto, es: 500!)
        if (bubbled) {                    // BUBBLED
            spriteCounter++;
            if (spriteCounter == spriteChangeRate) {
                spriteCounter = 0;
                spriteIndex = (spriteIndex+1) % 3;
            }
            return bubbledSpritesPath[spriteIndex%3];
        }
        if (super.getMovimentoX() == 0 && super.getMovimentoY() == 0) return idleSpritePath; // FERMO
        else {                                     // WALKING
            spriteCounter++;
            if (spriteCounter == spriteChangeRate) {
                spriteCounter = 0; 
                spriteIndex = (spriteIndex+1) % 2;
            }
            return walkingSpritesPath[spriteIndex%2];
        }
    }

    public PointItem dying(){
        deathCounter--;
        dead = true;
        return deathCounter == 0?  new PointItem(posx/16,posy/16):null;
    }
}

