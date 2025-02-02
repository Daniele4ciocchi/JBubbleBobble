package model;

import java.io.File;

/**
 * Classe che rappresenta un Hidegon, un nemico del gioco.
 */
public class Hidegon extends Nemico {

    /**
     * Costruttore della classe Hidegon.
     * @param x coordinata x iniziale dell'Hidegon
     * @param y coordinata y iniziale dell'Hidegon
     */
    public Hidegon(int x, int y) {
        super(x, y, 4, 15);
        setSprites();
    }
    
    private void setSprites() {
        walkingSpritesPath[0] += "hidegons"+File.separator+"image_41.png";
        walkingSpritesPath[1] += "hidegons"+File.separator+"image_42.png";

        bubbledSpritesPath[0] += "hidegons"+File.separator+"image_12.png";
        bubbledSpritesPath[1] += "hidegons"+File.separator+"image_13.png";
        bubbledSpritesPath[2] += "hidegons"+File.separator+"image_14.png";
        
        deathSpritePath += "zen-chan"+File.separator+"500.png";
    }

    /*
     * Metodo che si occupa del movimento dell'Hidegon, richiamando la move della superclasse Nemico.
     */
    public void move(int gx, int gy, Livello l) {
        super.move(gx, gy, l);
    }

    /*
     * Metodo che si occupa del movimento orizzontale dell'Hidegon, richiamando la moveOrizzontale della superclasse Nemico.
     * @return una nuova istanza di Fireball
     */
    public Bolla shoot() {
        return new Fireball(isGoingRight()? this.getX()+getEntitysize()+20 : this.getX()-getEntitysize()-5, this.getY(), 3, 1, isGoingRight(), 40);
    }
}
