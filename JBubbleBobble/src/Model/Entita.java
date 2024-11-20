package Model;


import java.util.Observable;
import java.util.Observer;

abstract public class Entita extends Observable {

    private double posx;
    private double posy;
    private boolean alive;
    private double velocitaX;
    private double velocitaY;
    private double gravita;

    /* vecchio codice
    private int hp; // health points rimanenti
    private int maxHp; // health points massimi
    private int velocitaX; // velocita movimento (float?)
    private int velocitaY;

    private final int gravita = 1; // gravita
    private final int forzaSalto = -15; // forza salto

    // le seguenti sono da aggiungere in fase di view
    private final int height = 32; // altezza
    private final int width = 32; // larghezza

    // variabili per le coordinate
    private int posX;
    private int posY;
    public int setHp;
    */

    public Entita(double posx, double posy, double velocitaX, double velocitaY, double gravita){
        this.posx = posx;
        this.posy = posy;
        this.alive = true;
        this.velocitaX = velocitaX;
        this.velocitaY = velocitaY;
        this.gravita = gravita;
    }

    public double getPosx(){ return posx;}
    public double getPosy(){ return posy;}
    public boolean isAlive(){ return alive;}
    public double getVelocitaX(){ return velocitaX;}
    public double getVelocitaY(){ return velocitaY;}
    public double getGravita(){ return gravita;}

    /**
     * Metodo per muovere l'entità verso sinistra
     */
    public void moveLeft();

    /**
     * Metodo per muovere l'entità verso destra
     */
    public void moveRight();

    /**
     * metodo per far saltare l'entità
     */
    public void jump();

    //TODO: da completare
    public void applyGravity(){

    }

    public void dead(){this.alive = false;}

    /**
     * Setta la posizione dell'entità
     * @param x coordinata x
     * @param y coordinata y
     */
    public void setPosizione(double x, double y){
        this.posx = x;
        this.posy = y;
    }

    /**
     * Setta la velocità dell'entità
     * @param x velocità x
     * @param y velocità y
     */
    public void setVelocita(double x, double y) {
        this.velocitaX = x;
        this.velocitaY = y;
    }
    /*
    public void applyGravity() {
        this.setVelocitaY(this.getVelocitaY() + this.getGravita());
        int newY = getPosY() + getVelocitaY();

        // Controllo collisione con il terreno o piattaforme
        if (Livello.getInstance().getTile(this.getPosX(), newY + getHeight()).getType().isWalkable()) {
            this.setPosizione(this.getPosX(),newY);
        } else {
            // Se colpisce il terreno, ferma la caduta
            setPosizione(this.getPosX(),(newY / 32) * 32);
            this.setVelocitaY(0);
        }
    }
    */

    @Override
    public abstract void addObserver(Observer o);
    @Override
    public abstract void deleteObserver(Observer o);
    @Override
    public abstract void notifyObservers();

}