package Model;


import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

abstract public class Entita extends Observable {

    private int posx;
    private int posy;
    private boolean alive;

    private int movimentoX;
    private int movimentoY;

    private int gravita; //negativa in quanto verso il basso
    private int jumpForce;

    private int entitysize = 16;
    private boolean direction; //true = destra, false = sinistra

    protected ArrayList<String> spritePath;

    public Entita(int posx, int posy, int velocitaX, int jumpForce, int gravita){
        this.posx = posx*entitysize;
        this.posy = posy*entitysize;
        this.alive = true;
        this.movimentoX = velocitaX;
        this.jumpForce = jumpForce;
        this.gravita = gravita;
        this.direction = true;
    }

    public int getX(){ return posx;}
    public int getY(){ return posy;}
    public boolean isAlive(){ return alive;}
    public int getMovimentoX(){ return movimentoX;}
    public int getMovimentoY(){ return movimentoY;}
    public int getGravita(){ return gravita;}
    public int getEntitysize(){ return entitysize;}
    public boolean getDirection(){ return direction;}

    public void setMovimentoY(int i) {this.movimentoY = i;}
    public void setMovimentoX(int i) {this.movimentoX = i;}
    public void setGravita(int i) {this.gravita = i;}
    public void setEntitysize(int i) {this.entitysize = i;}
    public void setDirection(boolean b) {this.direction = b;}
    
    public void setPosizione(int x, int y){
        this.posx = x;
        this.posy = y;
        setChanged();
        notifyObservers();
    }

    public void moveLeft() {
        posx-=movimentoX;
        setChanged();
        notifyObservers();
        direction = false;
    }

    public void moveRight() {
        posx += movimentoX;
        setChanged();
        notifyObservers();
        direction = true;
    }

    //TODO: controllo da fare nel controller dove se un'entità is on the floor
    //      allora può fare il jump

    public void jump() {
        this.movimentoY = this.jumpForce;
        setChanged();
        notifyObservers();
    }

    public void dead(){
        this.alive = false;
        setChanged();
        notifyObservers();
    }

    @Override
    public String toString(){
        return "Entita: x = " + posx + " y = " + posy;
    }
}