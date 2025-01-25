package model;

import java.io.File;

public class Fireball extends Bolla {

    private String[] sprites = {
        baseSpritePath + "hidegons" + File.separator + "image_1.png",
        baseSpritePath + "hidegons" + File.separator + "image_2.png"
    };

    public Fireball(int posx, int posy, int velocitax, int velocitay, boolean goingRight, int range) {
        super(posx, posy, velocitax, velocitay, goingRight);
        this.setRange(range);
    }

    @Override 
    public void move(Livello l) {
        if (range !=0){
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
           popTime = 0;
        }
        if (getNemico() != null) { getNemico().setPosizione(getX(), getY()); }
        
        setChanged();
        notifyObservers();
    }
    
    public String getSpritePath(){
        spriteCounter++;
        if (spriteCounter == spriteChangeRate) {
            spriteCounter = 0;
            if ((spriteIndex+1) % 2 == 1) {
                spriteIndex = 1;
            } else {
                spriteIndex = (spriteIndex+1) % 2;
            }
        }
        return sprites[spriteIndex];
    }
}
