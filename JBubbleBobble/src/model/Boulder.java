package model;

import java.io.File;

/**
 * Classe che rappresenta un masso sparato da Mighta.
 */
public class Boulder extends Bolla {

    private String[] sprites = {
        baseSpritePath + "mighta" + File.separator + "image_16.png",
        baseSpritePath + "mighta" + File.separator + "image_17.png",
        baseSpritePath + "mighta" + File.separator + "image_18.png",
        baseSpritePath + "mighta" + File.separator + "image_19.png"
    };

    /**
     * Costruttore del masso.
     * @param posx posizione iniziale sull'asse x
     * @param posy posizione iniziale sull'asse y
     * @param velocitax velocità sull'asse x
     * @param velocitay velocità sull'asse y
     * @param goingRight direzione iniziale
     * @param range distanza massima che il proiettile può percorrere
     */
    public Boulder(int posx, int posy, int velocitax, int velocitay, boolean goingRight, int range) {
        super(posx, posy, velocitax, velocitay, goingRight);
        this.setRange(range);
    }

    /**
     * Metodo che fa muovere il masso.
     * @param l livello in cui si trova il masso
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
        } else if (range == 0) popTime = 0;
        setChanged();
        notifyObservers();
    }
    
    /**
     * Metodo che restituisce il percorso dello sprite da mostrare.
     * @return percorso dello sprite
     */
    public String getSpritePath(){
        spriteCounter++;
        if (spriteCounter == spriteChangeRate) {
            spriteCounter = 0;
            if ((spriteIndex+1) % 2 == 1) {
                spriteIndex = 1;
            } else spriteIndex = (spriteIndex+1) % 2;
        }
        return sprites[spriteIndex];
    }
}
