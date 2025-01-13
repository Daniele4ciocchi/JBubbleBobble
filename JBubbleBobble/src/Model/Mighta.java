package Model;

import java.io.File;

public class Mighta extends Nemico{
    public Mighta(int x, int y){
        super(x, y, 2, 2);
        setSprites();
        
    }
    
    public void setSprites(){
        walkingSpritesPath[0] += "mighta"+File.separator+"image_39.png";
        walkingSpritesPath[1] += "banebou"+File.separator+"image_40.png";

        bubbledSpritesPath[0] += "zen-chan"+File.separator+"image_498.png"; //
        bubbledSpritesPath[1] += "zen-chan"+File.separator+"image_499.png"; // mettere quelli giusti
        bubbledSpritesPath[2] += "zen-chan"+File.separator+"image_500.png"; //

        deathSpritePath += "zen-chan"+File.separator+"500.png";
    }

    // movimento unico di Mighta, TODO: da fare!
    // 
    public void move(int gx, int gy, Livello l) {
        
    }
}
