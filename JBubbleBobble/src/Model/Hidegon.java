package model;

import java.io.File;

public class Hidegon extends Nemico{
    public Hidegon(int x, int y){
        super(x, y, 4, 15);
        setSprites();
    }
    
    public void setSprites(){
        walkingSpritesPath[0] += "hidegons"+File.separator+"image_41.png";
        walkingSpritesPath[1] += "hidegons"+File.separator+"image_42.png";

        bubbledSpritesPath[0] += "hidegons"+File.separator+"image_12.png";
        bubbledSpritesPath[1] += "hidegons"+File.separator+"image_13.png";
        bubbledSpritesPath[2] += "hidegons"+File.separator+"image_14.png";
        
        deathSpritePath += "zen-chan"+File.separator+"500.png";
    }

    public void move(int gx, int gy, Livello l) {
        super.move(gx, gy, l);
    }

    public Bolla shoot(){
        return new Fireball(getGoingRight()? this.getX()+getEntitysize()+20 : this.getX()-getEntitysize()-5, this.getY(), 6, 1, getGoingRight(), 20);
    }
}
