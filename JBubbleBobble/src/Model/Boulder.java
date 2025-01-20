package Model;

import java.io.File;

public class Boulder extends Bolla {

    private String[] sprites = {
        baseSpritePath + "mighta" + File.separator + "image_16.png",
        baseSpritePath + "mighta" + File.separator + "image_17.png",
        baseSpritePath + "mighta" + File.separator + "image_18.png",
        baseSpritePath + "mighta" + File.separator + "image_19.png"
    };

    public Boulder(int posx, int posy, int velocitax, int velocitay, boolean goingRight, int range) {
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
