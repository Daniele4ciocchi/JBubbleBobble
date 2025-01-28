package model;

import java.io.File;

public class BollaAcqua extends Bolla {

    int direction;
    int counterDirection = 0;

    private String[] sprites = {
        baseSpritePath + "misc" + File.separator + "image_238.png",
        baseSpritePath + "misc" + File.separator + "image_239.png",
        baseSpritePath + "misc" + File.separator + "image_240.png",
    };

    public BollaAcqua(int x, int y) {
        super(x, y, 1, 2, 0);
    }

    @Override
    public void move(Livello l) {
        if (counterDirection <= 0){
            direction = (int) (Math.random() * 4);
            counterDirection = (int) (Math.random() * 50);
        }
        if (counterDirection%2 == 0) {
            counterDirection--;
            return;
        } 
        switch (direction) {
            case 0:
                if (l.isEmpty(this.getX(), this.getY() + 1) 
                    && !l.isTPExit(this.getX(),this.getY() +1 )) {
                    this.movimentoY = 1;
                    this.setPosizione(this.getX(),this.getY() + this.getMovimentoY());
                }else {
                    counterDirection = 0;
                }
                break;
            case 1:
                if (l.isEmpty(this.getX() + 1, this.getY())) {
                    this.setPosizione(this.getX() + getMovimentoX(), this.getY());
                }
                break;
            case 2:
                if (l.isEmpty(this.getX() - 1, this.getY())) {
                    this.setPosizione(this.getX() - getMovimentoX(), this.getY());
                }
                break;
            case 3:

                if (l.isEmpty(this.getX(), this.getY() - 1) 
                    && !l.isTPEntry(this.getX(),this.getY() -1 )) {
                    
                    this.movimentoY = 3;
                    this.setPosizione(this.getX(),this.getY() - this.getMovimentoY());
                }else {
                    counterDirection = 0;
                }
                break;
        }
        
        counterDirection--;
        setChanged();
        notifyObservers();
    }

    public String getSpritePath(){
        spriteCounter++;
        if (spriteCounter == spriteChangeRate) {
            spriteCounter = 0;
            // spriteIndex = (spriteIndex+1) % 9;
            if ((spriteIndex+1) % 3 == 8) {
                spriteIndex = 6;
            } else {
                spriteIndex = (spriteIndex+1) % 3;
            }
        }
        return sprites[spriteIndex];
    }
}
