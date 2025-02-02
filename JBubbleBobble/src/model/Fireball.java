package model;

import java.io.File;

/**
 * Classe che rappresenta una fireball sparata da Mighta.
 */
public class Fireball extends Bolla {

    private String[] sprites = {
        baseSpritePath + "hidegons" + File.separator + "image_1.png",
        baseSpritePath + "hidegons" + File.separator + "image_2.png"
    };

    /**
     * Costruttore della classe Fireball.
     * @param posx posizione x iniziale della fireball
     * @param posy posizione y iniziale della fireball
     * @param velocitax velocità di movimento sull'asse x
     * @param velocitay velocità di movimento sull'asse y
     * @param goingRight booleano che indica se la fireball si sta muovendo verso destra o no
     * @param range range della fireball
     */
    public Fireball(int posx, int posy, int velocitax, int velocitay, boolean goingRight, int range) {
        super(posx, posy, velocitax, velocitay, goingRight);
        this.setRange(range);
    }

    /**
     * Metodo che muove la fireball.
     * @param l livello su cui la fireball si sta muovendo
     */
    @Override 
    public void move(Livello l) {
        if (range !=0){
            if (isGoingRight()) {
                if (l.isEmpty(getX() + getMovimentoX(), getY())) {
                    setPosizione(getX() + getMovimentoX(), getY());
                }
            } else {
                if (l.isEmpty(getX() - getMovimentoX(), getY())) {
                    setPosizione(getX() - getMovimentoX(), getY());
                }
            }
            range--;
        }else if (range == 0) popTime = 0;
        if (getNemico() != null) getNemico().setPosizione(getX(), getY());
        setChanged();
        notifyObservers();
    }
    
    /**
     * Metodo che restituisce il percorso dello sprite della fireball.
     * @return percorso dello sprite della fireball
     */
    public String getSpritePath(){
        spriteCounter++;
        if (spriteCounter == spriteChangeRate) {
            spriteCounter = 0;
            if ((spriteIndex+1) % 2 == 1) spriteIndex = 1;
            else spriteIndex = (spriteIndex+1) % 2;
        }
        return sprites[spriteIndex];
    }
}
