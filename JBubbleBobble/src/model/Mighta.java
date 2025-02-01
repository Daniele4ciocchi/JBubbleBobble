package model;

import java.io.File;

public class Mighta extends Nemico{
    public Mighta(int x, int y){
        super(x, y, 1, 2);
        setSprites();
    }
    
    public void setSprites(){
        walkingSpritesPath[0] += "mighta"+File.separator+"image_29.png";
        walkingSpritesPath[1] += "mighta"+File.separator+"image_30.png";

        bubbledSpritesPath[0] += "mighta"+File.separator+"image_11.png";
        bubbledSpritesPath[1] += "mighta"+File.separator+"image_12.png";
        bubbledSpritesPath[2] += "mighta"+File.separator+"image_13.png";

        deathSpritePath += "zen-chan"+File.separator+"500.png";
    }

    public void move(int gx, int gy, Livello l) {
        super.move(gx, gy, l);
    }

    public Bolla shoot(){
        return new Boulder(isGoingRight()? this.getX()+getEntitysize()+20 : this.getX()-getEntitysize()-5, this.getY(), 3, 1, isGoingRight(), 40);
    }
}
