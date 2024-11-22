package Model;


import java.util.Observable;
import java.util.Observer;

abstract public class Entita extends Observable {

    private int posx;
    private int posy;
    private boolean alive;
    private int velocitaX;
    private int velocitaY;
    private int gravita;
    private int width;
    private int height;


    public Entita(int posx, int posy, int velocitaX, int velocitaY, int gravita){
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
    public int getVelocitaY(){ return velocitaY;}
    public int getGravita(){ return gravita;}
    public int getWidth(){ return width;}
    public int getHeight(){ return height;}

    public void setVelocitaY(int i) {this.velocitaY = i;}
    public void setVelocitaX(int i) {this.velocitaX = i;}
    public void setGravita(int i) {this.gravita = i;}
    public void setWidth(int i) {this.width = i;}
    public void setHeight(int i) {this.height = i;}




    public void moveLeft() {
        posx -= velocitaX;
    }

    public void moveRight() {
        posx += velocitaX;
    }

    //TODO: controllo da fare nel controller dove se un'entità is on the floor
    //      allora può fare il jump

    public void jump() {
        setVelocitaY(-10);
    }


    //TODO: serve? (secondo me no)
    public void dead(){this.alive = false;}

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