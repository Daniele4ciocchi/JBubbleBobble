package Model;

import java.io.File;
import java.util.ArrayList;
import java.util.Observer;

//da fare singleton
public class Giocatore extends Entita{

    private int life;

    // sprites
    private final String idleSpritePath = "JBubbleBobble" + File.separator + "src" + File.separator + "resources" + File.separator + "sprites" + File.separator + "bubblun" + File.separator + "image_90.png";                  // fermo
    private final String fallingSpritePath = "JBubbleBobble" + File.separator + "src" + File.separator + "resources" + File.separator + "sprites" + File.separator + "bubblun" + File.separator + "image_84.png";              // caduta
    private final String jumpingSpritePath = "JBubbleBobble" + File.separator + "src" + File.separator + "resources" + File.separator + "sprites" + File.separator + "bubblun" + File.separator + "image_70.png";               // salto
    private final String shootingSpritePath = "JBubbleBobble" + File.separator + "src" + File.separator + "resources" + File.separator + "sprites" + File.separator + "bubblun" + File.separator + "image_79.png";              // sparo bolla
    private  String[] walkingSpritePath = {
         "JBubbleBobble" + File.separator + "src" + File.separator + "resources" + File.separator + "sprites" + File.separator + "bubblun" + File.separator + "image_87.png",
         "JBubbleBobble" + File.separator + "src" + File.separator + "resources" + File.separator + "sprites" + File.separator + "bubblun" + File.separator + "image_89.png"
    }; // camminata (da alternare)
    private final String deathSpritePath = "JBubbleBobble" + File.separator + "src" + File.separator + "resources" + File.separator + "sprites" + File.separator + "bubblun" + File.separator + "image_57.png";                 // morte

    private int walkingSpriteIndex; // serve ad alternare i due sprite di camminata
    private boolean falling;

    public Giocatore(){
        super(5, 1, 10, 20, -7);
        this.life = 3;
    }
              
    public int getLife(){return this.life; }

    public void addLife(){this.life++;}

    public void removeLife(){this.life--;}

    public void resetPosizione(){super.setPosizione(5*getEntitysize(), 1*getEntitysize());}

    public Bolla shoot(){
        return new Bolla(super.getGoingRight()? this.getX()+1 : this.getX()-1, this.getY(), 1, 1, super.getGoingRight());
    }
    
    public void setFalling(boolean b){this.falling = b;}
    public boolean isFalling(){return this.falling;}

    // ritorna il PATH dello sprite, in base allo stato del giocatore in gioco
    public String getSpritePath(){
        if (super.getMovimentoX() == 0 && super.getMovimentoY() == 0) return idleSpritePath;
        else if (this.isFalling()) return fallingSpritePath;
        else if (super.getMovimentoY() > 0) return jumpingSpritePath;
        else {
            if (walkingSpriteIndex == 0) {
                walkingSpriteIndex = 1;
                return walkingSpritePath[0];
            }
            else {
                walkingSpriteIndex = 0;
                return walkingSpritePath[1];
            }
        }
    }
}
