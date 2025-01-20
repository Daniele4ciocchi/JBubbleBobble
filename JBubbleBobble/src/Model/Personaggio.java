package Model;

import Model.Acqua.Goccia;

public abstract class Personaggio extends Entita{
    protected String[] walkingSpritesPath = { baseSpritePath, baseSpritePath };
    protected boolean water;
    protected int jumpForce;
    protected int spriteIndex; // serve ad alternare i due sprite di camminata


    public Personaggio(int posx, int posy, int velocitaX, int velocitaY, int gravita, int jumpforce) {
        super(posx, posy, velocitaX, velocitaY, gravita);
        this.jumpForce = jumpforce;
    }

    //TODO: controllo da fare nel controller dove se un'entità is on the floor
    //      allora può fare il jump

    public void move(Goccia g) {
        water = true;
        setPosizione(g.getX(), g.getY());
       //if ( == 0) die();
            
    }

    public void jump() {
        this.movimentoY = this.jumpForce;
        setChanged();
        notifyObservers();
    }
    
    public void moveLeft(Livello l) {
        if (l.isEmpty(posx-movimentoX, posy)){
            posx-=movimentoX;
            setChanged();
            notifyObservers();
            goingRight = false;
        }
    }

    public void moveRight(Livello l) {
        if (l.isEmpty(posx+movimentoX+5, posy)){
            posx += movimentoX;
            setChanged();
            notifyObservers();
            goingRight = true;
        }
    }
}
