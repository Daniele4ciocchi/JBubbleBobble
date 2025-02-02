package model;

import java.io.File;

/**
 * Classe che rappresenta il nemico Pulpul.
 */
public class Pulpul extends Nemico{
    private boolean goingUp = true;

    /**
     * Costruttore della classe Pulpul.
     * @param x la coordinata x di Pulpul
     * @param y la coordinata y di Pulpul
     */
    public Pulpul(int x, int y){
        super(x, y, 1, 3);
        setSprites();
    }
    
    /**
     * Metodo che imposta i path delle immagini per i vari stati di Pulpul.
     */
    public void setSprites(){
        walkingSpritesPath[0] += "pulpul"+File.separator+"image_407.png";
        walkingSpritesPath[1] += "pulpul"+File.separator+"image_408.png";

        bubbledSpritesPath[0] += "pulpul"+File.separator+"image_417.png"; 
        bubbledSpritesPath[1] += "pulpul"+File.separator+"image_418.png"; 
        bubbledSpritesPath[2] += "pulpul"+File.separator+"image_419.png"; 

        deathSpritePath += "zen-chan"+File.separator+"500.png";
    }

    /**
     * Metodo che muove Pulpul.
     * @param gx la coordinata x del giocatore
     * @param gy la coordinata y del giocatore
     * @param l il livello in cui si muove Pulpul
     */
    @Override
    public void move(int gx, int gy, Livello l) {
        moveOrizzontale(l);
        moveVerticale(l);
        setChanged();
        notifyObservers();
    }

    /**
     * Metodo che muove Pulpul in orizzontale.
     * @param l il livello in cui si muove Pulpul
     */
    public void moveOrizzontale(Livello l){
        if (isGoingRight()) {
            if (!l.isWalkable(getX()+getEntitysize(), getY())) setPosizione(getX() + getMovimentoX(),getY());
            else {
                setGoingRight(false);
                setMovimentoX((getMovimentoX()<=4)? getMovimentoX() + 1 : 1);
            }
        } else {
            if (!l.isWalkable(getX()-getEntitysize(), getY())) setPosizione(getX() - getMovimentoX(),getY());
            else {
                setGoingRight(true);
                setMovimentoX(getMovimentoX()<=4? getMovimentoX() + 1 : 1);
            }
        }
    }
    
    /**
     * Metodo che muove Pulpul in verticale.
     * @param l il livello in cui si muove Pulpul
     */
    public void moveVerticale(Livello l) {
        try {
            if (goingUp) {
                if (!l.isWalkable(getX(), getY()+getMovimentoX()) && !l.isTPExit(getX(), getY()+getMovimentoX())) setPosizione(getX(), getY() + getMovimentoX());
                else goingUp = false;
            } else {
                if (!l.isWalkable(getX(), getY()-(getEntitysize()/2))) setPosizione( getX(), getY() - getMovimentoX());
                else goingUp = true;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            setPosizione(getX(), 24*16);
        }
    }
}
