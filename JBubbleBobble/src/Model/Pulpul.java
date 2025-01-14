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
        if (goingUp) {
            if (!l.isWalkable(posx, posy+32)){
                posy+=movimentoX;
            } else {
                // setMovimentoX(movimentoX==7? ++movimentoX : movimentoX);
                goingUp = false;
            }
        } else {
            if (!l.isWalkable(posx, posy-(getEntitysize()/2))){
                posy-=movimentoX;
            } else {
                // setMovimentoX(movimentoX==7? ++movimentoX : movimentoX);
                goingUp = true;
            }
        }
    }

    // @Override // perchè ha 3 walking sprites...
    // public String getSpritePath(){
    //     if (dead) return deathSpritePath; // MORTE (score ottenuto, es: 500!)
    //     if (bubbled) {                    // BUBBLED
    //         spriteCounter++;
    //         if (spriteCounter == spriteChangeRate) {
    //             spriteCounter = 0;
    //             spriteIndex = (spriteIndex+1) % 3;
    //         }
    //         return bubbledSpritesPath[spriteIndex];
    //     }
    //     if (super.getMovimentoX() == 0 && super.getMovimentoY() == 0) return idleSpritePath; // FERMO
    //     else {                                     // WALKING
    //         spriteCounter++;
    //         if (spriteCounter == spriteChangeRate) {
    //             spriteCounter = 0; 
    //             spriteIndex = (spriteIndex+1) % 2;
    //         }
    //         return walkingSpritesPath[spriteIndex];
    //     }
    // }
}
