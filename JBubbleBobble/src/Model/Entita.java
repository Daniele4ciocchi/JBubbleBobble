package Model;


import java.util.Observable;
import java.util.Observer;

abstract public class Entita extends Observable {

    private int posx;
    private int posy;
    private boolean alive;

    private int movimentoX;
    private int movimentoY;

    private int gravita = -1; //negativa in quanto verso il basso
    private int jumpForce = 5;

    private int entitysize = 16;


    public Entita(int posx, int posy, int velocitaX, int velocitaY, int gravita){
        this.posx = posx*entitysize;
        this.posy = posy*entitysize;
        this.alive = true;
        this.movimentoX = velocitaX;
        this.movimentoY = velocitaY;
        this.gravita = gravita;
    }

    public int getX(){ return posx;}
    public int getY(){ return posy;}
    public boolean isAlive(){ return alive;}
    public int getMovimentoX(){ return movimentoX;}
    public int getMovimentoY(){ return movimentoY;}
    public int getGravita(){ return gravita;}
    public int getEntitysize(){ return entitysize;}

    public void setMovimentoY(int i) {this.movimentoY = i;}
    public void setMovimentoX(int i) {this.movimentoX = i;}
    public void setGravita(int i) {this.gravita = i;}
    public void setEntitysize(int i) {this.entitysize = i;}
    
    public void setPosizione(int x, int y){
        this.posx = x;
        this.posy = y;
    }

    public void moveLeft() {
        posx-=movimentoX;
        setChanged();
        notifyObservers();
    }

    public void moveRight() {
        posx += movimentoX;
        setChanged();
        notifyObservers();
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