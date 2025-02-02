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
     * Restituisce la coordinata x dell'entità.
     * @return coordinata x dell'entità
     */
    public int getX() {
        return posx;
    }

    /**
     * Restituisce la coordinata y dell'entità.
     * @return coordinata y dell'entità
     */
    public int getY() {
        return posy;
    }

    /**
     * Restituisce il booleano che indica se l'entità è morta o meno.
     * @return booleano che indica la morte
     */
    public boolean isDead() {
        return dead;
    }

    /**
     * Restituisce l'intero che indica la velocità di movimento sull'asse x dell'entità.
     * @return velocità di movimento sull'asse x dell'entità
     */
    public int getMovimentoX() {
        return movimentoX;
    }

    /**
     * Restituisce l'intero che indica la velocità di movimento sull'asse y dell'entità.
     * @return velocità di movimento sull'asse y dell'entità
     */
    public int getMovimentoY() {
        return movimentoY;
    }

    /**
     * Restituisce l'intero che indica la gravità dell'entità.
     * @return gravità dell'entità
     */
    public int getGravita() {
        return gravita;
    }

    /**
     * Restituisce la grandezza dell'entità.
     * @return grandezza dell'entità
     */
    public static int getEntitysize() {
        return entitysize;
    }

    /**
     * Restituisce il booleano che indica se l'entità è rivolta verso destra o meno.
     * @return booleano che indica se l'entità è rivolta verso destra o meno
     */
    public boolean isGoingRight() {
        return goingRight;
    }

    /**
     * Imposta il nuovo valore di movimentoX
     * @param i nuovo valore di movimentoX
     */
    public void setMovimentoX(int i) {
        this.movimentoX = i;
    }

    /**
     * Imposta il nuovo valore di movimentoY
     * @param i nuovo valore di movimentoY
     */
    public void setMovimentoY(int i) {
        this.movimentoY = i;
    }
    
    /**
     * Imposta il nuovo valore di gravità
     * @param i nuovo valore di gravità
     */
    public void setGravita(int i) {
        this.gravita = i;
    }

    /**
     * Imposta il booleano che indica se l'entità è rivolta verso destra o meno.
     * @param b nuovo booleano che indica se l'entità è rivolta verso destra o meno
     */
    public void setGoingRight(boolean b) {
        this.goingRight = b;
    }

    /**
     * Imposta la posizione dell'entità.
     * @param x nuova coordinata x dell'entità
     * @param y nuova coordinata y dell'entità
     */
    public void setPosizione(int x, int y) {
        this.posx = x;
        this.posy = y;
        setChanged();
        notifyObservers();
    }

    /**
     * Fa morire l'entità, impostando il booleano dead a true.
     */
    public void die(){
        this.dead = true;
    }

    /**
     * Fa resuscitare l'entità, impostando il booleano dead a false.
     */
    public void undie(){
        this.dead = false;
    }
}