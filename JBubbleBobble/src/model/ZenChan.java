package model;

import java.io.File;

public class ZenChan extends Nemico {

    public ZenChan(int x, int y){
        super(x, y, 3, 14);
        setSprites();
    }
    
    public void setSprites(){
        walkingSpritesPath[0] += "zen-chan"+File.separator+"image_507.png";
        walkingSpritesPath[1] += "zen-chan"+File.separator+"image_487.png";

        bubbledSpritesPath[0] += "zen-chan"+File.separator+"image_498.png";
        bubbledSpritesPath[1] += "zen-chan"+File.separator+"image_499.png";
        bubbledSpritesPath[2] += "zen-chan"+File.separator+"image_500.png";

        deathSpritePath += "zen-chan"+File.separator+"500.png";
    }

    public void move(int gx, int gy, Livello l) {
        super.move(gx, gy, l);
    }
}
