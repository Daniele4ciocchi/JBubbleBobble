package model;

import java.io.File;
import java.io.Serializable;

/**
 * Classe che rappresenta il giocatore.
 */
public class Giocatore extends Personaggio  {

    private int life = 3;
    private int passi = 0;
    private final String idleSpritePath = baseSpritePath + "bubblun" + File.separator + "image_90.png";
    private final String fallingSpritePath = baseSpritePath + "bubblun" + File.separator + "image_84.png";
    private final String jumpingSpritePath = baseSpritePath + "bubblun" + File.separator + "image_70.png";
    private final String shootingSpritePath = baseSpritePath + "bubblun" + File.separator + "image_79.png";    
    private  String[] walkingSpritesPath = {
        baseSpritePath + "bubblun" + File.separator + "image_87.png",
        baseSpritePath + "bubblun" + File.separator + "image_89.png"
    };                                      
    private final String[] deathSpritesPaths = {
        baseSpritePath + "bubblun" + File.separator + "image_58.png",
        baseSpritePath + "bubblun" + File.separator + "image_57.png",
        baseSpritePath + "bubblun" + File.separator + "image_64.png",
        baseSpritePath + "bubblun" + File.separator + "image_65.png"
    };
    private boolean falling;
    private boolean shooting;
    private int invincibilita = 0;
    private boolean BOLLE_RANGE_UP;
    private boolean BOLLE_VEL_UP;
    private boolean BOLLE_FIRERATE_UP;
    private boolean BONUS_MOV;
    private boolean BONUS_SALTO;
    private boolean BONUS_SPARO;
    private boolean SNEAKER_BUFF;

    /**
     * Costruttore della classe Giocatore.
     */
    public Giocatore() {
        super(5, 1, 4, 15, -3, 17);
    }

    /**
     * Restituisce il numero di vite rimanenti del giocatore.
     * @return il numero di vite rimanenti del giocatore
     */
    public int getLife() {
        return this.life;
    }

    /**
     * Restituisce il numero di passi fatti dal giocatore.
     * @return il numero di passi fatti dal giocatore
     */
    public int getPassi() {
        return this.passi;
    }

    /**
     * Restituisce il tempo residuo di invincibilità del giocatore.
     * @return un intero che rappresenta il tempo residuo di invincibilità del giocatore
     */
    public int getInvincibilita() {
        return this.invincibilita;
    }

    /**
     * Restituisce true se il giocatore ha l'effetto BOLLE_FIRERATE_UP attivo, false altrimenti.
     * @return true se il giocatore ha l'effetto BOLLE_FIRERATE_UP attivo, false altrimenti
     */
    public boolean getBolleFirerate() {
        return BOLLE_FIRERATE_UP;
    }

    /**
     * Restituisce true se il giocatore ha l'effetto BONUS_MOV attivo, false altrimenti.
     * @return true se il giocatore ha l'effetto BONUS_MOV attivo, false altrimenti
     */
    public boolean getBonusMov() {
        return BONUS_MOV;
    }

    /**
     * Restituisce true se il giocatore ha l'effetto BONUS_SALTO attivo, false altrimenti.
     * @return true se il giocatore ha l'effetto BONUS_SALTO attivo, false altrimenti
     */
    public boolean getBonusSalto() {
        return BONUS_SALTO;
    }

    /**
     * Restituisce true se il giocatore ha l'effetto BONUS_SPARO attivo, false altrimenti.
     * @return true se il giocatore ha l'effetto BONUS_SPARO attivo, false altrimenti
     */
    public boolean getBonusSparo() { 
        return BONUS_SPARO;
    }

    /**
     * Restituisce true se il giocatore ha l'effetto SNEAKER_BUFF attivo, false altrimenti.
     * @return true se il giocatore ha l'effetto SNEAKER_BUFF attivo, false altrimenti
     */
    public boolean getSneakerBuff() {
        return SNEAKER_BUFF;
    }
    
    /**
     * Serve a impostare il numero di passi del giocatore, aggiornati nel gameloop ad ogni sua iterazione.
     * @param p true se il giocatore ha l'effetto BOLLE_RANGE_UP attivo, false altrimenti
     */
    public void setPassi(int p) {
        this.passi = p;
    }

    /**
     * Serve a impostare il tempo residuo di invincibilità del giocatore.
     * @param i intero che rappresenta il tempo residuo di invincibilità del giocatore
     */
    public void setInvincibilita(int i) {
        this.invincibilita = i;
    }

    /**
     * Serve a incrementare di 1 il numero di vite del giocatore.
     */
    public void addLife() {
        this.life++;
    }

    /**
     * Serve a decrementare di 1 il numero di vite del giocatore.
     */
    public void removeLife() {
        this.life--;
    }

    /**
     * Serve a resettare la posizione del giocatore.
     */
    public void resetPosizione() {
        super.setPosizione(5*getEntitysize(), 1*getEntitysize());
        setWatered(false, null);;
    }

    /**
     * Serve a impostare il booleano falling, che indica lo stato di "caduta" del giocatore.
     */
    public void setFalling(boolean b) {
        this.falling = b;
    }

    /* 
     * Restituisce true se il giocatore è in caduta, false altrimenti.
     */
    public boolean isFalling() {
        return this.falling;
    }

    /**
     * Serve a impostare il booleano shooting, che indica lo stato di "sparo" del giocatore, utile per la gestione del relativo sprite dedicato.
     */
    public void setShooting(boolean b) {
        this.shooting = b;
    }

    /**
     * Restituisce true se il giocatore sta sparando, false altrimenti.
     */
    public boolean isShooting() {
        return this.shooting;
    } 

    /**
     * Restituisce il percorso dello sprite del giocatore, dipendente dal proprio stato attuale. (ad esempio se sta camminando, saltando, sparando, ecc.)
     * @return percorso dello sprite del giocatore sottoforma di stringa
     */
    public String getSpritePath(){
        if (super.getMovimentoX() == 0 && super.getMovimentoY() == 0) return idleSpritePath;
        else if (this.isDead()) {
            spriteCounter++;
            if (spriteCounter == spriteChangeRate) {
                spriteCounter = 0; 
                spriteIndex = (spriteIndex+1) % 4;
            }
            return deathSpritesPaths[spriteIndex];
        }
        else if (this.isFalling()) return fallingSpritePath;
        else if (super.getMovimentoY() > 0) return jumpingSpritePath;
        else if (this.isShooting()) return shootingSpritePath;
        else if (this.isDead()) {
            spriteCounter++;
            if (spriteCounter == spriteChangeRate) {
                spriteCounter = 0; 
                spriteIndex = (spriteIndex+1) % 4;
            }
            return deathSpritesPaths[spriteIndex];
        }
        else {
            spriteCounter++;
            if (spriteCounter == spriteChangeRate) {
                spriteCounter = 0; 
                spriteIndex = (spriteIndex+1) % 2;
            }
            return walkingSpritesPath[spriteIndex%2];
        }
    }

    /**
     * Metodo che fa muovere il giocatore verso sinistra.
     * @param l livello su cui il giocatore si sta muovendo
     */
    @Override
    public void moveLeft(Livello l){
        if(!isDead()) {
            super.moveLeft(l);
            setShooting(false);
            passi+=getMovimentoX();
        }
    }

    /**
     * Metodo che fa muovere il giocatore verso destra.
     * @param l livello su cui il giocatore si sta muovendo
     */
    @Override
    public void moveRight(Livello l){ 
        if(!isDead()) {
            super.moveRight(l);
            setShooting(false);
            passi+=getMovimentoX();
        }
    }

    /**
     * Metodo per far saltare il giocatore.
     */
    @Override
    public void jump(){ 
        if(!isDead()) {
            super.jump();
            setShooting(false);
        }
    }

    /**
     * Metodo che permette al giocatore di sparare una nuova bolla.
     * @return una nuova istanza di BollaSemplice
     */
    public Bolla shoot(){
        this.setShooting(true);
        return new BollaSemplice(
            isGoingRight()? this.getX()+getEntitysize()+20 : this.getX()-getEntitysize()-5,
            this.getY(), 
            BOLLE_VEL_UP?7:2, 
            1, 
            isGoingRight(), 
            BOLLE_RANGE_UP?100:50
        );
    }

    /**
     * Metodo che inizializza il processo di morte del giocatore, resettando i suoi effetti e impostando la velocità di movimento a 4.
     */
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
        super.die();
        setChanged();
        notifyObservers();
    }

    /**
     * Metodo che permette al giocatore di "resuscitare" dopo la morte, decrementando il numero di vite e resettando la sua posizione.
     * In caso non dovesse più avere vite, il metodo non fa nulla.
     */
    public void respawn(){
        if (this.life != 0){
            this.undie();
            this.removeLife();
            this.resetPosizione();
        } else return;
        setChanged();
        notifyObservers();
    }

    /**
     * Metodo che applica un effetto di uno SpecialItem al giocatore, modificando il relativo booleano che ne indica l'attivazione.
     * @param e effetto da applicare, ottenuto tramite il metodo getEffetto() di SpecialItem
     */
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
