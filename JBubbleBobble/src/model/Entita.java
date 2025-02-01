package model;

import java.io.File;
import java.util.Observable;

/**
 * Classe astratta che rappresenta un'entità generica del gioco.
 */
abstract public class Entita extends Observable {

    private int posx;
    private int posy;
    private boolean dead;
    private int movimentoX;
    private int movimentoY;
    private int gravita; 
    private static int entitysize = 16;
    private boolean goingRight;

    /**
     * Path base per gli sprite.
     */
    protected String baseSpritePath = "JBubbleBobble" + File.separator + "src" + File.separator + "resources" + File.separator + "sprites" + File.separator;
    
    /**
     * Path per lo sprite da usare da fermo.
     */
    protected String idleSpritePath = baseSpritePath; 
    
    /**
     * Path per gli sprite da usare mentre l'entità muore.
     */
    protected String deathSpritePath = baseSpritePath; 
    
    /**
     * intero utilizzato per gestire il cambiamento degli sprite.
     */
    protected int spriteIndex;

    /**
     * intero utilizzato per gestire il tempo di cambiamento degli sprite.
     */
    protected int spriteCounter;

    /**
     * intero utilizzato per gestire il rate di cambiamento degli sprite.
     */
    protected final int spriteChangeRate = 8;

    /**
     * Costruttore della classe Entita.
     * @param posx posizione x iniziale dell'entità
     * @param posy posizione y iniziale dell'entità
     * @param velocitaX velocità di movimento sull'asse x
     * @param velocitaY velocità di movimento sull'asse y
     * @param gravita gravità dell'entità
     */
    public Entita(int posx, int posy, int velocitaX, int velocitaY, int gravita){
        this.posx = posx*entitysize;
        this.posy = posy*entitysize;
        this.movimentoX = velocitaX;
        this.gravita = gravita;
        this.goingRight = true;
    }

    /**
     * Metodo che restituisce.
     * @return
     */
    public int getX(){return posx;}
    public int getY(){return posy;}
    public boolean isDead(){return dead;}
    public int getMovimentoX(){return movimentoX;}
    public int getMovimentoY(){return movimentoY;}
    public int getGravita(){return gravita;}
    public static int getEntitysize(){return entitysize;}
    public boolean getGoingRight(){return goingRight;}

    public void setMovimentoY(int i) {this.movimentoY = i;}
    public void setMovimentoX(int i) {this.movimentoX = i;}
    public void setGravita(int i) {this.gravita = i;}
    public void setEntitysize(int i) {this.entitysize = i;}
    public void setGoingRight(boolean b) {this.goingRight = b;}
    
    public void setPosizione(int x, int y){
        this.posx = x;
        this.posy = y;
        setChanged();
        notifyObservers();
    }

    public void die(){
        this.dead = true;
    }

    @Override
    public String toString(){
        return this.getClass() + ": x = " + posx + " y = " + posy;
    }
}