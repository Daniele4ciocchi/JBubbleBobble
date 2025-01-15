package Model;

import java.io.File;
import java.util.ArrayList;
import java.util.Observer;

//da fare singleton
public class Giocatore extends Personaggio{
    private int life = 3;

    // sprites
    private final String idleSpritePath = baseSpritePath + "bubblun" + File.separator + "image_90.png";         // fermo
    private final String fallingSpritePath = baseSpritePath + "bubblun" + File.separator + "image_84.png";      // caduta
    private final String jumpingSpritePath = baseSpritePath + "bubblun" + File.separator + "image_70.png";      // salto
    private final String shootingSpritePath = baseSpritePath + "bubblun" + File.separator + "image_79.png";    // sparo
    private  String[] walkingSpritesPath = {                                                                // camminata
        baseSpritePath + "bubblun" + File.separator + "image_87.png",
        baseSpritePath + "bubblun" + File.separator + "image_89.png"
    };                                      
    private final String[] deathSpritesPaths = {
        baseSpritePath + "bubblun" + File.separator + "image_58.png",
        baseSpritePath + "bubblun" + File.separator + "image_57.png",
        baseSpritePath + "bubblun" + File.separator + "image_64.png",
        baseSpritePath + "bubblun" + File.separator + "image_65.png"
    };
    private boolean falling;  // per lo sprite di caduta
    private boolean shooting; // per sprite con bocca aperta
    private int invincibilita = 0;

    public Giocatore(){
        super(5, 1, 10, 20, -7, 20);
    }

    public int getLife(){return this.life;}
    public int getInvincibilita(){return this.invincibilita;}
    
    public void setInvincibilita(int i){this.invincibilita = i;}
    public void addLife(){this.life++;}
    public void removeLife(){this.life--;}
    public void resetPosizione(){super.setPosizione(5*getEntitysize(), 1*getEntitysize());}

    public void setFalling(boolean b){this.falling = b;}
    public boolean isFalling(){return this.falling;}
    public void setShooting(boolean b){this.shooting = b;}
    public boolean isShooting(){return this.shooting;} 

    // ritorna il PATH dello sprite, in base allo stato del giocatore
    public String getSpritePath(){
        if (super.getMovimentoX() == 0 && super.getMovimentoY() == 0) return idleSpritePath; // FERMO
        else if (this.isFalling()) return fallingSpritePath; // CADENDO
        else if (super.getMovimentoY() > 0) return jumpingSpritePath; // SALTANDO
        else if (this.isShooting()) return shootingSpritePath; // SPARANDO
        else if (this.isDead()) {
            spriteCounter++;
            if (spriteCounter == spriteChangeRate) {
                spriteCounter = 0; 
                spriteIndex = (spriteIndex+1) % 4;
            }
            return deathSpritesPaths[spriteIndex];
        }
        else { // CAMMINANDO
            spriteCounter++;
            if (spriteCounter == spriteChangeRate) {
                spriteCounter = 0; 
                spriteIndex = (spriteIndex+1) % 2;
            }
            return walkingSpritesPath[spriteIndex];
        }
    }

    @Override
    public void moveLeft(Livello l){
        if(!isDead()) {
        super.moveLeft(l);
        setShooting(false);
        }
    }

    @Override
    public void moveRight(Livello l){ 
        if(!isDead()) {
            super.moveRight(l);
            setShooting(false);
        }
    }

    @Override
    public void jump(){ 
        if(!isDead()) {
            super.jump();
            setShooting(false);
        }
    }

    public Bolla shoot(){
        this.setShooting(true);
        return new BollaSemplice(getGoingRight()? this.getX()+getEntitysize()+20 : this.getX()-getEntitysize()-5, this.getY(), 6, 1, getGoingRight(), 20);
    }


    public void die(){
        setShooting(false);
        this.dead = true;
        setChanged();
        notifyObservers();
    }

    public void respawn(){
        if (this.life != 0){
            this.dead = false;
            this.removeLife();
            this.resetPosizione();
        } else return; // TODO: GAME OVER (idea: lvl17?)
        
        setChanged();
        notifyObservers();
    }
}
