package Model;

import java.io.File;
import java.util.ArrayList;
import java.util.Observer;

//da fare singleton
public class Giocatore extends Personaggio{

    private int life;

    // sprites
    private final String fallingSpritePath = baseSpritePath + "bubblun" + File.separator + "image_84.png";              // caduta
    private final String jumpingSpritePath = baseSpritePath + "bubblun" + File.separator + "image_70.png";               // salto
    private final String shootingSpritesPath = baseSpritePath + "bubblun" + File.separator + "image_79.png";              // sparo bolla
    private  String[] walkingSpritePath = {
         baseSpritePath + "bubblun" + File.separator + "image_87.png",
         baseSpritePath + "bubblun" + File.separator + "image_89.png"
    }; // camminata (da alternare)

    private boolean falling;

    public Giocatore(){
        super(5, 1, 10, 20, -7, 18);
        
        this.life = 3;

        idleSpritePath += "bubblun" + File.separator + "image_90.png";
        deathSpritePath += "bubblun" + File.separator + "image_57.png";
    }
              
    public int getLife(){return this.life;}
    public void addLife(){this.life++;}
    public void removeLife(){this.life--;}
    public void resetPosizione(){super.setPosizione(5*getEntitysize(), 1*getEntitysize());}

    public Bolla shoot(){
        return new BollaSemplice(getGoingRight()? this.getX()+getEntitysize() : this.getX()-getEntitysize(), this.getY(), 3, 1, getGoingRight());
    }
    
    public void setFalling(boolean b){this.falling = b;}
    public boolean isFalling(){return this.falling;}

    // ritorna il PATH dello sprite, in base allo stato del giocatore in gioco
    public String getSpritePath(){
        if (super.getMovimentoX() == 0 && super.getMovimentoY() == 0) return idleSpritePath;
        else if (this.isFalling()) return fallingSpritePath;
        else if (super.getMovimentoY() > 0) return jumpingSpritePath;
        else {
            if (spriteIndex == 0) {
                spriteIndex = 1;
                return walkingSpritePath[0];
            }
            else {
                spriteIndex = 0;
                return walkingSpritePath[1];
            }
        }
    }

    // TODO: implementare override di .die() che decrementa vite e resetta posizione, e se life == 0, game over
}
