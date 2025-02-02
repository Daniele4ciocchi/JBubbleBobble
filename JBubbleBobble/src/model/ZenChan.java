package model;

import java.io.File;

/**
 * Classe che rappresenta il nemico ZenChan.
 */
public class ZenChan extends Nemico {

    /**
     * Costruttore della classe ZenChan.
     * @param x la coordinata x di ZenChan
     * @param y la coordinata y di ZenChan
     */
    public ZenChan(int x, int y){
        super(x, y, 3, 14);
        setSprites();
    }
    
    /**
     * Metodo che imposta i path delle immagini per i vari stati di ZenChan.
     */
    public void setSprites(){
        walkingSpritesPath[0] += "zen-chan"+File.separator+"image_507.png";
        walkingSpritesPath[1] += "zen-chan"+File.separator+"image_487.png";

        bubbledSpritesPath[0] += "zen-chan"+File.separator+"image_498.png";
        bubbledSpritesPath[1] += "zen-chan"+File.separator+"image_499.png";
        bubbledSpritesPath[2] += "zen-chan"+File.separator+"image_500.png";

        deathSpritePath += "zen-chan"+File.separator+"500.png";
    }


}
