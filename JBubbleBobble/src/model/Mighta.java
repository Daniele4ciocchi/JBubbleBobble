package model;

import java.io.File;

/**
 * Classe che rappresenta un nemico Mighta, un nemico del gioco.
 */
public class Mighta extends Nemico {

    /**
     * Costruttore della classe Mighta.
     * @param x coordinata x iniziale del Mighta
     * @param y coordinata y iniziale del Mighta
     */
    public Mighta(int x, int y) {
        super(x, y, 1, 2);
        setSprites();
    }
    
    private void setSprites(){
        walkingSpritesPath[0] += "mighta"+File.separator+"image_29.png";
        walkingSpritesPath[1] += "mighta"+File.separator+"image_30.png";

        bubbledSpritesPath[0] += "mighta"+File.separator+"image_11.png";
        bubbledSpritesPath[1] += "mighta"+File.separator+"image_12.png";
        bubbledSpritesPath[2] += "mighta"+File.separator+"image_13.png";

        deathSpritePath += "zen-chan"+File.separator+"500.png";
    }

    /*
     * Metodo che permette a Mighta di poter sparare un masso rovente.
     * @return una nuova istanza di Boulder
     */
    public Bolla shoot(){
        return new Boulder(isGoingRight()? this.getX()+getEntitysize()+20 : this.getX()-getEntitysize()-5, this.getY(), 3, 1, isGoingRight(), 40);
    }
}
