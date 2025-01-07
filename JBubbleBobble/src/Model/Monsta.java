package Model;

import java.io.File;

public class Monsta extends Nemico {
    public Monsta(int x, int y){
        super(x, y, 3, 2);
        setSprites();
        
    }
    
    public void setSprites(){
        walkingSpritesPath[0] += "monsta"+File.separator+"image_443.png";
        walkingSpritesPath[1] += "monsta"+File.separator+"image_444.png";

        bubbledSpritesPath[0] += "zen-chan"+File.separator+"image_498.png"; //
        bubbledSpritesPath[1] += "zen-chan"+File.separator+"image_499.png"; // mettere quelli giusti
        bubbledSpritesPath[2] += "zen-chan"+File.separator+"image_500.png"; //
    }

    // movimento unico di Pulpul, TODO: da fare!
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
