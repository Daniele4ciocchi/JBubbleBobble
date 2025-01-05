package Model;

import java.io.File;
import java.util.ArrayList;
import java.util.Observer;

//da fare singleton
public class Giocatore extends Personaggio{
    private int life = 3;

    // sprites
    private final String idleSpritePath = baseSpritePath + "bubblun" + File.separator + "image_90.png";
    private final String fallingSpritePath = baseSpritePath + "bubblun" + File.separator + "image_84.png";              // caduta
    private final String jumpingSpritePath = baseSpritePath + "bubblun" + File.separator + "image_70.png";               // salto
    private final String shootingSpritesPath = baseSpritePath + "bubblun" + File.separator + "image_79.png";              // sparo bolla
    private  String[] walkingSpritesPath = {
        baseSpritePath + "bubblun" + File.separator + "image_87.png",
        baseSpritePath + "bubblun" + File.separator + "image_89.png"
    }; // camminata (da alternare)
    
    //TODO: scegliere i 4 sprite di morte (rotolamento)
    private final String[] deathSpritesPaths = {
        baseSpritePath + "bubblun" + File.separator + "",
        baseSpritePath + "bubblun" + File.separator + "",
        baseSpritePath + "bubblun" + File.separator + "",
        baseSpritePath + "bubblun" + File.separator + "",
    };

    private boolean falling;

    public Giocatore(){
        super(5, 1, 10, 20, -7, 18);
    }
              
    public int getLife(){return this.life;}
    public void addLife(){this.life++;}
    public void removeLife(){this.life--;}
    public void resetPosizione(){super.setPosizione(5*getEntitysize(), 1*getEntitysize());}

    public Bolla shoot(){
        return new BollaSemplice(getGoingRight()? this.getX()+getEntitysize()+20 : this.getX()-getEntitysize()-5, this.getY(), 6, 1, getGoingRight(), 20);
    }
    
    public void setFalling(boolean b){this.falling = b;}
    public boolean isFalling(){return this.falling;}

    // ritorna il PATH dello sprite, in base allo stato del giocatore in gioco
    public String getSpritePath(){
        if (super.getMovimentoX() == 0 && super.getMovimentoY() == 0) return idleSpritePath; // FERMO
        else if (this.isFalling()) return fallingSpritePath; // CADENDO
        else if (super.getMovimentoY() > 0) return jumpingSpritePath; // SALTANDO
        else { // CAMMINANDO
            spriteCounter++;
            if (spriteCounter == spriteChangeRate) {
                spriteCounter = 0; 
                spriteIndex = (spriteIndex+1) % 2;
            }
            return walkingSpritesPath[spriteIndex];
        }
    }
    // TODO: implementare override di .die() che decrementa vite e resetta posizione, e se life == 0, game over

    @Override
    public void moveLeft(Livello l){ if(!isDead()) super.moveLeft(l);}

    @Override
    public void moveRight(Livello l){ if(!isDead()) super.moveRight(l);}

    @Override
    public void jump(){ if(!isDead()) super.jump();}

            


    public void die(){
        this.dead = true;
        this.removeLife();
        setChanged();
        notifyObservers();
    }

    public void respawn(){
        if (this.life != 0){
            this.dead = false;
            this.resetPosizione();
        } else return; // TODO: GAME OVER
        
        setChanged();
        notifyObservers();
    }
}
