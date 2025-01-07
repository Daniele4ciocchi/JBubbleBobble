package Model;

import java.io.File;

public class Hidegon extends Nemico{
    public Hidegon(int x, int y){
        super(x, y, 3, 1);
        setSprites();
        
    }
    
    public void setSprites(){
        walkingSpritesPath[0] += "hidegons"+File.separator+"image_40.png";
        walkingSpritesPath[1] += "banebou"+File.separator+"image_41.png";

        bubbledSpritesPath[0] += "zen-chan"+File.separator+"image_498.png"; //
        bubbledSpritesPath[1] += "zen-chan"+File.separator+"image_499.png"; // mettere quelli giusti
        bubbledSpritesPath[2] += "zen-chan"+File.separator+"image_500.png"; //
    }

    // movimento unico di Hidegon, TODO: da fare!
    public void move(int gx, int gy, Livello l) {
        
    }
}
