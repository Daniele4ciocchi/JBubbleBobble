package Model;

import java.io.File;

public class ZenChan extends Nemico{
    public ZenChan(int x, int y){
        super(x, y, 5, 5);
        setSprites();
        
    }
    
    public void setSprites(){
        walkingSpritesPath[0] += "zen-chan"+File.separator+"image_507.png";
        walkingSpritesPath[1] += "zen-chan"+File.separator+"image_508.png";

        bubbledSpritesPath[0] += "zen-chan"+File.separator+"image_498.png";
        bubbledSpritesPath[1] += "zen-chan"+File.separator+"image_499.png";
        bubbledSpritesPath[2] += "zen-chan"+File.separator+"image_500.png";

        deathSpritePath += "zen-chan"+File.separator+"500.png";
    }

    // movimento unico di Zen-Chan
    public void move(int gx, int gy, Livello l) {
        if (this.getX() < gx) {
            if (currentWaitTime == 0){
                goingRight = true;
                moveRight(l);
                currentWaitTime = waitTime;
            } else currentWaitTime--;
        }
        if (this.getX() > gx) {
            if (currentWaitTime == 0){
                goingRight = false;
                moveLeft(l);
                currentWaitTime = waitTime;
            } else currentWaitTime--;
        }
        if (this.getY() < gy && l.isWalkable(this.getX(), this.getY()-1)) jump();
    }
}
