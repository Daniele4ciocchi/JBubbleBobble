package model;

import model.Acqua.Goccia;

public abstract class Personaggio extends Entita{
    protected String[] walkingSpritesPath = { baseSpritePath, baseSpritePath };
    protected boolean watered = false;
    private Goccia g;
    protected int jumpForce;
    protected int spriteIndex;

    public Personaggio(int posx, int posy, int velocitaX, int velocitaY, int gravita, int jumpforce) {
        super(posx, posy, velocitaX, velocitaY, gravita);
        this.jumpForce = jumpforce;
    }

    public boolean getWatered() { return watered; }
    public void setWatered(boolean w, Goccia g) { 
        watered = w;
        this.g = g;
    }

    public void move(){
        this.setPosizione(g.getX(), g.getY());
    }

    public void jump() {
        setMovimentoY(this.jumpForce);
        
        setChanged();
        notifyObservers();
    }
    
    public void moveLeft(Livello l) {
        if (l.isEmpty(getX()-getMovimentoX(), getY()) && !watered){
            setPosizione(getX()-getMovimentoX(), getY());
            setGoingRight(false);
            setChanged();
            notifyObservers();
        }
    }

    public void moveRight(Livello l) {
        if (l.isEmpty(getX()+getMovimentoX()+5, getY()) && !watered){
            setPosizione(getX()+getMovimentoX(), getY());
            setGoingRight(true);

            setChanged();
            notifyObservers();

        }
    }
}
