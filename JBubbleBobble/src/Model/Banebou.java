package Model;

import java.io.File;

public class Banebou extends Nemico{
    public Banebou(int x, int y){
        super(x, y, 3, 2);
        setSprites();
        
    }
    
    public void setSprites(){
        walkingSpritesPath[0] += "banebou"+File.separator+"image_4.png";
        walkingSpritesPath[1] += "banebou"+File.separator+"image_4.png";

        bubbledSpritesPath[0] += "zen-chan"+File.separator+"image_498.png"; //
        bubbledSpritesPath[1] += "zen-chan"+File.separator+"image_499.png"; // mettere quelli giusti
        bubbledSpritesPath[2] += "zen-chan"+File.separator+"image_500.png"; //
    }

    // movimento unico di Banebou, TODO: da fare!
    public void move(int gx, int gy, Livello l) {
        
    }
}
