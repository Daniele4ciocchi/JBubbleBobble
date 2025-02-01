package model;

import java.io.File;

/**
 * Classe che rappresenta una bolla semplice che può essere sparata dal Giocatore.
 */
public class BollaSemplice extends Bolla {

    private int spriteChangeRate = 5;
    private String[] sprites = {
        baseSpritePath + "bubbles" + File.separator + "b1.png",
        baseSpritePath + "bubbles" + File.separator + "b2.png",
        baseSpritePath + "bubbles" + File.separator + "b3.png",
        baseSpritePath + "bubbles" + File.separator + "b4.png",
        baseSpritePath + "bubbles" + File.separator + "b5.png",
        baseSpritePath + "bubbles" + File.separator + "b6.png",

        baseSpritePath + "bubbles" + File.separator + "b7.png",
        baseSpritePath + "bubbles" + File.separator + "b8.png",
        baseSpritePath + "bubbles" + File.separator + "b9.png" // arrivato qui, torno a b7
    };

    /**
     * Costruttore della classe BollaSemplice.
     * @param posx posizione iniziale sull'asse x
     * @param posy posizione iniziale sull'asse y
     * @param velocitax velocità iniziale sull'asse x
     * @param velocitay velocità iniziale sull'asse y
     * @param goingRight direzione iniziale
     * @param range range di movimento della bolla
     */
    public BollaSemplice(int posx, int posy, int velocitax, int velocitay, boolean goingRight, int range) {
        super(posx, posy, velocitax, velocitay, goingRight);
        this.setRange(range);
    }

    /**
     * Metodo che permette alla bolla di muoversi all'interno del livello.
     * @param l livello in cui la bolla si trova
     */
    @Override 
    public void move(Livello l) {
        
        if (range != 0){
            if (getGoingRight()) {
                if (l.isEmpty(getX() + getMovimentoX(), getY())) {
                    setPosizione(getX() + getMovimentoX(), getY());
                } 
            } else {
                if (l.isEmpty(getX() - getMovimentoX(), getY())) {
                    setPosizione(getX() - getMovimentoX(), getY());
                }
            }
            range--;
        }else if (range == 0){
            if (popTime%2 == 0) {
                popTime--;
                return;
            }
            popTime--;
            if (l.isTPExit(getX(),getY() + getEntitysize() )) popTime = 0;
            if (!l.isEmpty(getX(), getY() + getEntitysize())) {
                if (getGoingRight() && l.isEmpty(getX() + getMovimentoX(), getY())) {
                    setPosizione(getX() + getMovimentoX()/2, getY());
                } else if (!getGoingRight() && l.isEmpty(getX() - getMovimentoX(), getY())) {
                    setPosizione(getX() - getMovimentoX()/2, getY());
                }
            }
            if (l.isEmpty(getX(), getY() + getEntitysize())) {
                setPosizione(getX(), getY() + 1);
            }
        }
        if (getNemico() != null) { getNemico().setPosizione(getX(), getY()); }
        
        setChanged();
        notifyObservers();
    }
    
    /**
     * Metodo che restituisce il percorso dello sprite da visualizzare.
     * @return percorso dello sprite
     */
    public String getSpritePath(){
        spriteCounter++;
        if (spriteCounter == spriteChangeRate) {
            spriteCounter = 0;
            if ((spriteIndex+1) % 9 == 8) {
                spriteIndex = 6;
            } else {
                spriteIndex = (spriteIndex+1) % 9;
            }
        }
        return sprites[spriteIndex];
    }

}
