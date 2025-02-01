package model;

import java.io.File;

/**
 * Classe che rappresenta una bolla d'acqua.
 */
public class BollaAcqua extends Bolla {

    private int direction;
    private int counterDirection = 0;

    private String[] sprites = {
        baseSpritePath + "misc" + File.separator + "image_238.png",
        baseSpritePath + "misc" + File.separator + "image_239.png",
        baseSpritePath + "misc" + File.separator + "image_240.png",
    };

    /**
     * Costruttore della classe BollaAcqua.
     * @param x posizione x iniziale
     * @param y posizione y iniziale
     */
    public BollaAcqua(int x, int y) {
        super(x, y, 1, 2, 0);
    }

    /**
     * Metodo che fa muovere la bolla d'acqua.
     * @param l livello in cui si trova la bolla
     */
    @Override
    public void move(Livello l) {
        if (counterDirection <= 0){
            direction = (int) (Math.random() * 4);
            counterDirection = (int) (Math.random() * 50);
        }
        if (counterDirection%2 == 0) {
            counterDirection--;
            return;
        } 
        switch (direction) {
            case 0:
                if (l.isEmpty(this.getX(), this.getY() + 1) 
                    && !l.isTPExit(this.getX(),this.getY() +1 )) {
                    this.setMovimentoY(1);
                    this.setPosizione(this.getX(),this.getY() + this.getMovimentoY());
                }else {
                    counterDirection = 0;
                }
                break;
            case 1:
                if (l.isEmpty(this.getX() + 1, this.getY())) {
                    this.setPosizione(this.getX() + getMovimentoX(), this.getY());
                }
                break;
            case 2:
                if (l.isEmpty(this.getX() - 1, this.getY())) {
                    this.setPosizione(this.getX() - getMovimentoX(), this.getY());
                }
                break;
            case 3:

                if (l.isEmpty(this.getX(), this.getY() - 1) 
                    && !l.isTPEntry(this.getX(),this.getY() -1 )) {
                    
                    this.setPosizione(this.getX(),this.getY() - 1);
                }else {
                    counterDirection = 0;
                }
                break;
        }
        
        counterDirection--;
        setChanged();
        notifyObservers();
    }

    /**
     * Metodo che restituisce il percorso dello sprite da visualizzare.
     * @return percorso dello sprite
     */
    public String getSpritePath(){
        spriteCounter++;
        if (spriteCounter == spriteChangeRate) {
            spriteCounter = 0;
            if ((spriteIndex+1) % 3 == 8) {
                spriteIndex = 6;
            } else {
                spriteIndex = (spriteIndex+1) % 3;
            }
        }
        return sprites[spriteIndex];
    }
}
