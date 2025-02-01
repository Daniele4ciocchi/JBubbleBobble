package model;

import java.io.File;

/**
 * Classe che rappresenta il nemico Banebou.
 */
public class Banebou extends Nemico{

    /**
     * Costruttore della classe Banebou.
     * @param x la coordinata x di Banebou
     * @param y la coordinata y di Banebou
     */
    public Banebou(int x, int y){
        super(x, y, 2, 17);
        setSprites();
    }
    
    private void setSprites(){
        walkingSpritesPath[0] += "banebou"+File.separator+"image_4.png";
        walkingSpritesPath[1] += "banebou"+File.separator+"image_4.png";

        bubbledSpritesPath[0] += "banebou"+File.separator+"image_14.png"; 
        bubbledSpritesPath[1] += "banebou"+File.separator+"image_15.png"; 
        bubbledSpritesPath[2] += "banebou"+File.separator+"image_16.png"; 

        deathSpritePath += "zen-chan"+File.separator+"500.png";
    }

    /**
     * Metodo che muove Banebou.
     * @param gx la coordinata x del giocatore
     * @param gy la coordinata y del giocatore
     * @param l il livello in cui si muove Banebou
     */
    public void move(int gx, int gy, Livello l) {
        if (!watered){
            if (this.getX() < gx) {
                if (currentWaitTime == 0){
                    if (l.isWalkable(this.getX(), this.getY()-1)) jump();
                    this.setGoingRight(true);
                    moveRight(l);
                    currentWaitTime = waitTime;
                } else currentWaitTime--;
            }
            if (this.getX() > gx) {
                if (currentWaitTime == 0){
                    if (l.isWalkable(this.getX(), this.getY()-1)) jump();
                    this.setGoingRight(false);
                    moveLeft(l);
                    currentWaitTime = waitTime;
                } else currentWaitTime--;
            }
            if (this.getY() < gy && l.isWalkable(this.getX(), this.getY()-1)) jump();
        }
    }
}             
