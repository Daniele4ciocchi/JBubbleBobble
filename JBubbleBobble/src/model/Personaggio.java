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
        this.movimentoY = this.jumpForce;
        
        setChanged();
        notifyObservers();
    }
    
    public void moveLeft(Livello l) {
        if (l.isEmpty(posx-movimentoX, posy) && !watered){
            posx-=movimentoX;
            setChanged();
            notifyObservers();
            goingRight = false;
        }
    }

    public void moveRight(Livello l) {
        if (l.isEmpty(posx+movimentoX+5, posy) && !watered){
                posx += movimentoX;
                setChanged();
                notifyObservers();
                goingRight = true;
        }
    }
}
