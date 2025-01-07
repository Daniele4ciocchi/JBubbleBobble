package Model;

import java.io.File;

public class Pulpul extends Nemico{

    public Pulpul(int x, int y){
        super(x, y, 3, 3);
        setSprites();
        
    }
    
    public void setSprites(){
        walkingSpritesPath[0] += "pulpul"+File.separator+"image_407.png";
        walkingSpritesPath[1] += "pulpul"+File.separator+"image_408.png";

        bubbledSpritesPath[0] += "zen-chan"+File.separator+"image_498.png"; //
        bubbledSpritesPath[1] += "zen-chan"+File.separator+"image_499.png"; // mettere quelli giusti
        bubbledSpritesPath[2] += "zen-chan"+File.separator+"image_500.png"; //
    }

    // movimento unico di Pulpul, TODO: da fare!
    public void move(int gx, int gy, Livello l) {
        
    }
}
