package Model;

import java.io.File;

public class Banebou extends Nemico{
    public Banebou(int x, int y){
        super(x, y, 5, 17);
        setSprites();
    }
    
    public void setSprites(){
        walkingSpritesPath[0] += "banebou"+File.separator+"image_4.png";
        walkingSpritesPath[1] += "banebou"+File.separator+"image_4.png";

        bubbledSpritesPath[0] += "banebou"+File.separator+"image_14.png"; 
        bubbledSpritesPath[1] += "banebou"+File.separator+"image_15.png"; 
        bubbledSpritesPath[2] += "banebou"+File.separator+"image_16.png"; 

        deathSpritePath += "zen-chan"+File.separator+"500.png";
    }

    // si muove come Zen-Chan, ma salta 'n botto
    public void move(int gx, int gy, Livello l) {
        if (!watered){
            if (this.getX() < gx) {
                if (currentWaitTime == 0){
                    if (l.isWalkable(this.getX(), this.getY()-1)) jump();
                    goingRight = true;
                    moveRight(l);
                    currentWaitTime = waitTime;
                } else currentWaitTime--;
            }
            if (this.getX() > gx) {
                if (currentWaitTime == 0){
                    if (l.isWalkable(this.getX(), this.getY()-1)) jump();
                    goingRight = false;
                    moveLeft(l);
                    currentWaitTime = waitTime;
                } else currentWaitTime--;
            }
            if (this.getY() < gy && l.isWalkable(this.getX(), this.getY()-1)) jump();
        }
    }
}             
