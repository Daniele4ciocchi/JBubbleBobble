package Model;


import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/*
 ======================= GUIDA SPRITE =======================
  ENTITA
	Giocatore
		idleSprite
		fallingSprite
		jumpingSprite
		shootingSprite
		walkingSprites[]
		deathSprite
		
	Nemico
		idleSprite
		walkingSprites[]
		bubbledSprites[]
		deathSprite (punti)
		
	SpecialItem
		idleSprite
		deathSprite? (punti)
		
	PointItem
		idleSprite
		deathSprite? (punti)
		
	Bolla
		idleSprites[] (da bolla piccola a bolla grande)
		[ DA DECIDERE GLI ALTRI ]
 */

abstract public class Entita extends Observable {

    private int posx;
    private int posy;
    private boolean alive;

    private int movimentoX;
    private int movimentoY;

    private int gravita; //negativa in quanto verso il basso
    private int jumpForce;

    private int entitysize = 16;

    // campi per gli SPRITE
    private boolean goingRight; // "devo flippare lo sprite?"
    //protected String idleSpritePath; // lo sprite da usare da fermo
    //protected String deathSpritePath;

    private int animationTimer = 10;
    private int currentAnimationTimer = 0;
    

    public Entita(int posx, int posy, int velocitaX, int jumpForce, int gravita){
        this.posx = posx*entitysize;
        this.posy = posy*entitysize;
        this.alive = true;
        this.movimentoX = velocitaX;
        this.jumpForce = jumpForce;
        this.gravita = gravita;
        this.goingRight = true;
    }

    public int getX(){ return posx;}
    public int getY(){ return posy;}
    public boolean isAlive(){ return alive;}
    public int getMovimentoX(){ return movimentoX;}
    public int getMovimentoY(){ return movimentoY;}
    public int getGravita(){ return gravita;}
    public int getEntitysize(){ return entitysize;}
    public boolean getGoingRight(){ return goingRight;}

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

    public void moveLeft(Livello l) {
        if (l.isEmpty(posx-movimentoX, posy)){
            posx-=movimentoX;
            setChanged();
            notifyObservers();
            goingRight = false;
        }
    }

    public void moveRight(Livello l) {
        if (l.isEmpty(posx+movimentoX, posy)){
            posx += movimentoX;
            setChanged();
            notifyObservers();
            goingRight = true;
        }
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
        return this.getClass() + ": x = " + posx + " y = " + posy;
    }
}