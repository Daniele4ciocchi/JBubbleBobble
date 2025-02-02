package model;

import java.io.File;

/**
 * Classe che rappresenta un Monsta, un nemico del gioco.
 */
public class Monsta extends Nemico {

    private boolean goingUp = true;

    /**
     * Costruttore della classe Monsta.
     * @param x coordinata x iniziale del Monsta
     * @param y coordinata y iniziale del Monsta
     */
    public Monsta(int x, int y){
        super(x, y, 2, 2);
        setSprites();
    }
    
    private void setSprites(){
        walkingSpritesPath[0] += "monsta"+File.separator+"image_443.png";
        walkingSpritesPath[1] += "monsta"+File.separator+"image_444.png";

        bubbledSpritesPath[0] += "monsta"+File.separator+"image_432.png"; 
        bubbledSpritesPath[1] += "monsta"+File.separator+"image_433.png";
        bubbledSpritesPath[2] += "monsta"+File.separator+"image_434.png";

        deathSpritePath += "zen-chan"+File.separator+"500.png";
    }

    /**
     * Metodo che si occupa del movimento del Monsta.
     * Questa tipologia di nemico si muove sempre diagonalmente (ossia sia verticalmente che orizzontalmente, simulteanamente),
     * alternando su/giù e destra/sinistra al momento di un contatto con una parete o una piattaforma.
     * @param l livello su cui il Monsta si sta muovendo
     */
    public void move(int gx, int gy, Livello l) {
        moveOrizzontale(l);
        moveVerticale(l);
        setChanged();
        notifyObservers();
    }

    /**
     * Metodo che si occupa del movimento orizzontale del Monsta.
     * Essenzialmente, Monsta si muove in orizzontale alternando destra/sinistra in base ai rimbalzi contro eventuali ostacoli.
     * @param l livello su cui il Monsta si sta muovendo
     */
    public void moveOrizzontale(Livello l){
        if (isGoingRight()) {
            if (!l.isWalkable(getX()+getEntitysize(), getY())) {
                setPosizione(getX() + getMovimentoX(),getY());
            } else {
                setGoingRight(false);
            }
        } else {
            if (!l.isWalkable(getX()-getEntitysize(), getY())) {
                setPosizione(getX() - getMovimentoX(),getY());
            } else {
                setGoingRight(true);
            }
        }
    }
    
    /**
     * Metodo che si occupa del movimento verticale del Monsta.
     * Essenzialmente, Monsta si muove in verticale alternando su/giù in base ai rimbalzi contro eventuali ostacoli.
     * @param l livello su cui il Monsta si sta muovendo
     */
    public void moveVerticale(Livello l) {
        try {
            if (goingUp) {
                if (!l.isWalkable(getX(), getY()+getMovimentoX()) && !l.isTPExit(getX(), getY()+getMovimentoX())) {
                    setPosizione(getX(), getY() + getMovimentoX());
                } else {
                    goingUp = false;
                }
            } else {
                if (!l.isWalkable(getX(), getY()-(getEntitysize()/2))){
                    setPosizione(getX(), getY() - getMovimentoX());
                } else {
                    goingUp = true;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            setPosizione(getX(), 24*16);
        }
    }
}
