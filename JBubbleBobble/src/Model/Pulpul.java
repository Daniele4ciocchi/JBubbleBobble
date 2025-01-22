package Model;

import java.io.File;

public class Pulpul extends Nemico{
    private boolean goingUp = true;

    public Pulpul(int x, int y){
        super(x, y, 5, 3);
        setSprites();
    }
    
    public void setSprites(){
        walkingSpritesPath[0] += "pulpul"+File.separator+"image_407.png";
        walkingSpritesPath[1] += "pulpul"+File.separator+"image_408.png";
        // walkingSpritesPath[2] += "pulpul"+File.separator+"image_409.png";

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
        if (goingRight) {
            if (!l.isWalkable(posx+getEntitysize(), posy)) {
                posx+=movimentoX;
            } else {
                // setMovimentoX(movimentoX==7? ++movimentoX : movimentoX);
                goingRight = false;
            }
        } else {
            if (!l.isWalkable(posx-getEntitysize(), posy)) {
                posx-=movimentoX;
            } else {
                // setMovimentoX(movimentoX==7? ++movimentoX : movimentoX);
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
