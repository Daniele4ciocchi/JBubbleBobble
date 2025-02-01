package model;

import java.io.File;

public class Pulpul extends Nemico{
    private boolean goingUp = true;

    public Pulpul(int x, int y){
        super(x, y, 1, 3);
        setSprites();
    }
    
    public void setSprites(){
        walkingSpritesPath[0] += "pulpul"+File.separator+"image_407.png";
        walkingSpritesPath[1] += "pulpul"+File.separator+"image_408.png";

        bubbledSpritesPath[0] += "pulpul"+File.separator+"image_417.png"; 
        bubbledSpritesPath[1] += "pulpul"+File.separator+"image_418.png"; 
        bubbledSpritesPath[2] += "pulpul"+File.separator+"image_419.png"; 

        deathSpritePath += "zen-chan"+File.separator+"500.png";
    }

    public void move(int gx, int gy, Livello l) {
        moveOrizzontale(l);
        moveVerticale(l);
        setChanged();
        notifyObservers();
    }

    public void moveOrizzontale(Livello l){
        if (getGoingRight()) {
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
    
    public void moveVerticale(Livello l) {
        try {
            if (goingUp) {
                if (!l.isWalkable(getX(), getY()+getMovimentoY()) && !l.isTPExit(getX(), getY()+getMovimentoY())) setPosizione(getX(), getY() + getMovimentoY());
                else goingUp = false;
            } else {
                if (!l.isWalkable(getX(), getY()-(getEntitysize()/2))) setPosizione( getX(), getY() - getMovimentoY());
                else goingUp = true;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            setPosizione(getX(), 24*16);
        }
    }
}
