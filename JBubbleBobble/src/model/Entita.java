package model;


import java.io.File;
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

    protected int posx;
    protected int posy;
    protected boolean dead; // se l'entità è morta

    protected int movimentoX;
    protected int movimentoY;

    private int gravita; //negativa in quanto verso il basso

    private static int entitysize = 16;

    // campi per gli SPRITE
    protected boolean goingRight; // "devo flippare lo sprite?"
    protected String baseSpritePath = "JBubbleBobble" + File.separator + "src" + File.separator + "resources" + File.separator + "sprites" + File.separator;
    protected String idleSpritePath = baseSpritePath; // lo sprite da usare da fermo
    protected String deathSpritePath = baseSpritePath; // lo sprite da usare quando l'entità muore (= animazione di morte)
    
    protected int spriteIndex;
    protected int spriteCounter;
    protected final int spriteChangeRate = 8;

    private int animationTimer = 10;
    private int currentAnimationTimer;

    public Entita(int posx, int posy, int velocitaX, int velocitaY, int gravita){
        this.posx = posx*entitysize;
        this.posy = posy*entitysize;
        this.movimentoX = velocitaX;
        this.gravita = gravita;
        this.goingRight = true;
    }

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