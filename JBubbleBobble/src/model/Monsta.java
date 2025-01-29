package model;

import java.io.File;

public class Monsta extends Nemico {
    private boolean goingUp = true;

    public Monsta(int x, int y){
        super(x, y, 2, 2);
        setSprites();
    }
    
    public void setSprites(){
        walkingSpritesPath[0] += "monsta"+File.separator+"image_443.png";
        walkingSpritesPath[1] += "monsta"+File.separator+"image_444.png";

        bubbledSpritesPath[0] += "monsta"+File.separator+"image_432.png"; 
        bubbledSpritesPath[1] += "monsta"+File.separator+"image_433.png";
        bubbledSpritesPath[2] += "monsta"+File.separator+"image_434.png";

        deathSpritePath += "zen-chan"+File.separator+"500.png";
    }

    // si muove sempre in diagonale, e quando incontra tile non-walkable, cambia direzione orizzontale/verticale
    public void move(int gx, int gy, Livello l) {
        moveOrizzontale(l);
        moveVerticale(l);
        setChanged();
        notifyObservers();
    }

    public void moveOrizzontale(Livello l){
        if (goingRight) {
            if (!l.isWalkable(posx+getEntitysize(), posy)) {
                posx+=movimentoX;
            } else {
                goingRight = false;
            }
        } else {
            if (!l.isWalkable(posx-getEntitysize(), posy)) {
                posx-=movimentoX;
            } else {
                goingRight = true;
            }
        }
    }
    
    public void moveVerticale(Livello l) {
        try {
            if (goingUp) {
                if (!l.isWalkable(posx, posy+getMovimentoY()) && !l.isTPExit(posx, posy+getMovimentoY())) {
                    posy+=movimentoX;
                } else {
                    goingUp = false;
                }
            } else {
                if (!l.isWalkable(posx, posy-(getEntitysize()/2))){
                    posy-=movimentoX;
                } else {
                    goingUp = true;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            setPosizione(posx, 24*16);
        }
    }
}
