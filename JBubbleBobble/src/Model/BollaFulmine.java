package Model;

import java.io.File;

public class BollaFulmine extends Bolla {

    int direction;
    int counterDirection = 0;

    private String[] sprites = {
        baseSpritePath + "misc" + File.separator + "image_226.png",
        baseSpritePath + "misc" + File.separator + "image_227.png",
        baseSpritePath + "misc" + File.separator + "image_228.png",
    };

    public BollaFulmine(int x, int y) {
        super(x, y, 1, 0, -1);
    }

    @Override
    public void move(Livello l) {
        if (counterDirection <= 0){
            direction = (int) (Math.random() * 3);
            counterDirection = (int) (Math.random() * 20);
        }
        
        switch (direction) {
            case 0:
                if (l.isEmpty(this.getX(), this.getY() + getMovimentoY()) && l.isEmpty(this.getX(), this.getY() + 1)) {
                    this.setMovimentoY(1);
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
        }
        if (l.isWalkable(getX(), getY() -1)) {
            this.setPosizione(this.getX(), this.getY() + getMovimentoY());
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
