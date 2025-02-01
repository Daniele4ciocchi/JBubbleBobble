package model;

import java.io.File;
import java.io.Serializable;

public class Giocatore extends Personaggio implements Serializable{
    private int life = 3;
    private int passi = 0;

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

    // booleani buff specialitem
    private boolean BOLLE_RANGE_UP;
    private boolean BOLLE_VEL_UP;
    private boolean BOLLE_FIRERATE_UP; // va fatto nel game controller
    private boolean BONUS_MOV; //
    private boolean BONUS_SALTO;
    private boolean BONUS_SPARO;
    private boolean SNEAKER_BUFF;

    public Giocatore(){
        super(5, 1, 4, 15, -3, 17);
    }

    public int getLife(){return this.life;}
    public int getPassi(){return this.passi;}
    public int getInvincibilita(){return this.invincibilita;}
    public boolean getBolleFirerate(){return BOLLE_FIRERATE_UP;}
    public boolean getBonusMov(){return BONUS_MOV;}
    public boolean getBonusSalto(){return BONUS_SALTO;}
    public boolean getBonusSparo(){return BONUS_SPARO;}
    public boolean getSneakerBuff(){return SNEAKER_BUFF;}
    
    public void setPassi(int p){this.passi = p;}
    public void setInvincibilita(int i){this.invincibilita = i;}
    public void addLife(){this.life++;}
    public void removeLife(){this.life--;}
    public void resetPosizione(){
        super.setPosizione(5*getEntitysize(), 1*getEntitysize());
        this.watered = false;
    }

    public void setFalling(boolean b){this.falling = b;}
    public boolean isFalling(){return this.falling;}
    public void setShooting(boolean b){this.shooting = b;}
    public boolean isShooting(){return this.shooting;} 

    // ritorna il PATH dello sprite, in base allo stato del giocatore
    public String getSpritePath(){
        if (super.getMovimentoX() == 0 && super.getMovimentoY() == 0) return idleSpritePath; // FERMO
        else if (this.isDead()) {
            spriteCounter++;
            if (spriteCounter == spriteChangeRate) {
                spriteCounter = 0; 
                spriteIndex = (spriteIndex+1) % 4;
            }
            return deathSpritesPaths[spriteIndex];
        }
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
            return walkingSpritesPath[spriteIndex%2];
        }
    }

    @Override
    public void moveLeft(Livello l){
        if(!isDead()) {
            super.moveLeft(l);
            setShooting(false);
            passi+=getMovimentoX();
        }
    }

    @Override
    public void moveRight(Livello l){ 
        if(!isDead()) {
            super.moveRight(l);
            setShooting(false);
            passi+=getMovimentoX();
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
        return new BollaSemplice(
            getGoingRight()? this.getX()+getEntitysize()+20 : this.getX()-getEntitysize()-5,
            this.getY(), 
            BOLLE_VEL_UP?7:2, 
            1, 
            getGoingRight(), 
            BOLLE_RANGE_UP?100:50
        );
    }

    public void die(){
        BOLLE_RANGE_UP = false;
        BOLLE_VEL_UP = false;
        BOLLE_FIRERATE_UP = false;
        BONUS_MOV = false;
        BONUS_SALTO = false;
        BONUS_SPARO = false;
        SNEAKER_BUFF = false;
        this.setMovimentoX(4);

        setShooting(false);
        this.die();
        setChanged();
        notifyObservers();
    }

    public void respawn(){
        if (this.life != 0){
            this.undie();
            this.removeLife();
            this.resetPosizione();
        } else return;
        setChanged();
        notifyObservers();
    }

    public void applyEffetto(SpecialItem.Effetto e){
        switch(e){
            case BOLLE_RANGE_UP -> BOLLE_RANGE_UP = true;
            case BOLLE_VEL_UP -> BOLLE_VEL_UP = true;
            case BOLLE_FIRERATE_UP -> BOLLE_FIRERATE_UP = true;
            case BONUS_MOV -> BONUS_MOV = true;
            case BONUS_SALTO -> BONUS_SALTO = true;
            case BONUS_SPARO -> BONUS_SPARO = true;
            case SNEAKER_BUFF -> {
                SNEAKER_BUFF = true;
                this.setMovimentoX(6);
            }
        }
    }
}
