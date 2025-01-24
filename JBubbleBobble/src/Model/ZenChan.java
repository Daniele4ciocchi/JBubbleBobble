package model;

import java.io.File;

import model.Acqua.Goccia;

public class ZenChan extends Nemico{
    public ZenChan(int x, int y){
        super(x, y, 7, 16);
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

    // movimento unico di Zen-Chan
    public void move(int gx, int gy, Livello l) {
        super.move(gx, gy, l);
    }

    
}
