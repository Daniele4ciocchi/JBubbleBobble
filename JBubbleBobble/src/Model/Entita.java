package Model;


import java.util.Observable;
import java.util.Observer;

abstract public class Entita extends Observable {

    private int posx;
    private int posy;
    private boolean alive;

    private int velocitaX;
    private double velocitaY;

    private double gravita;
    private int jumpForce;
    private boolean isJumping = false;

    private int width;
    private int height;


    public Entita(int posx, int posy, int velocitaX, int velocitaY, double gravita){
        this.posx = posx;
        this.posy = posy;
        this.alive = true;
        this.velocitaX = velocitaX;
        this.velocitaY = velocitaY;
        this.gravita = gravita;
    }

    public int getX(){ return posx;}
    public int getY(){ return posy;}
    public boolean isAlive(){ return alive;}
    public int getVelocitaX(){ return velocitaX;}
    public double getVelocitaY(){ return velocitaY;}
    public double getGravita(){ return gravita;}
    public int getWidth(){ return width;}
    public int getHeight(){ return height;}

    public void setVelocitaY(double i) {this.velocitaY = i;}
    public void setVelocitaX(double i) {this.velocitaX = i;}
    public void setGravita(int i) {this.gravita = i;}
    public void setWidth(int i) {this.width = i;}
    public void setHeight(int i) {this.height = i;}


    public void moveLeft() {
        posx -= velocitaX;
        setChanged();
        notifyObservers();
    }

    public void moveRight() {
        posx += velocitaX;
        setChanged();
        notifyObservers();
    }

    //TODO: controllo da fare nel controller dove se un'entità is on the floor
    //      allora può fare il jump

    public void jump() {
        if (!isJumping) {
            isJumping = true;
            velocitaY = jumpForce;
        }
        setChanged();
        notifyObservers();
    }

    
    //TODO: serve? (secondo me no)
    public void dead(){
        this.alive = false;
        setChanged();
        notifyObservers();
    }

    /**
     * Setta la posizione dell'entità
     * @param x coordinata x
     * @param y coordinata y
     */
    public void setPosizione(int x, int y){
        this.posx = x;
        this.posy = y;
    }

    /**
     * Setta la velocità dell'entità
     * @param x velocità x
     * @param y velocità y
     */
    public void setVelocita(int x, int y) {
        this.velocitaX = x;
        this.velocitaY = y;
    }
    
}