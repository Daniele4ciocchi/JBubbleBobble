package Model;

import java.io.File;

public class BollaSemplice extends Bolla {

    private int spriteChangeRate = 2;
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

    public BollaSemplice(int posx, int posy, int velocitax, int velocitay, boolean goingRight, int range) {
        super(posx, posy, velocitax, velocitay, goingRight);
        this.setRange(range);
    }

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
