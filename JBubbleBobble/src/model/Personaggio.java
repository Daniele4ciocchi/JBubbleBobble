package model;

import model.Acqua.Goccia;

/**
 * Classe che rappresenta un generico personaggio del gioco 
 * e che ne delinea caratteristiche e comportamenti condivisi tra tutte le sue sottoclassi.
 */
public abstract class Personaggio extends Entita{

    private boolean watered = false;
    private Goccia g;
    private int jumpForce;

    /**???
     * Array contenente i percorsi ai due sprite, su cui ciclare per ottenere l'animazione risultante, del personaggio quando cammina.
     */
    protected String[] walkingSpritesPath = { baseSpritePath, baseSpritePath };

    /**???
     * Array contenente i percorsi ai tre sprite, su cui ciclare per ottenere l'animazione risultante, del personaggio quando è intrappolato in una bolla.
     */
    protected int spriteIndex;

    /**
     * Costruttore della classe Personaggio.
     * @param posx la coordinata x del personaggio
     * @param posy la coordinata y del personaggio
     * @param velocitaX la velocità orizzontale del personaggio
     * @param velocitaY la velocità verticale del personaggio
     * @param gravita la gravità del personaggio
     * @param jumpforce la forza del salto del personaggio
     */
    public Personaggio(int posx, int posy, int velocitaX, int velocitaY, int gravita, int jumpforce) {
        super(posx, posy, velocitaX, velocitaY, gravita);
        this.jumpForce = jumpforce;
    }

    /**
     * Metodo che ritorna lo stato di watered (trascinato dall'acqua) del personaggio.
     * @return true se il personaggio è trascinato dall'acqua, false altrimenti.
     */
    public boolean getWatered() { return watered; }

    /**
     * Metodo che imposta lo stato di watered (trascinato dall'acqua) del personaggio.
     * @param w true se il personaggio è trascinato dall'acqua, false altrimenti.
     * @param g la goccia d'acqua che trascina il personaggio
     */
    public void setWatered(boolean w, Goccia g) { 
        watered = w;
        this.g = g;
    }

    /**
     * Metodo che trasporta il giocatore in base alla posizione della goccia d'acqua.
     */
    public void move(){
        this.setPosizione(g.getX(), g.getY());
    }

    /**
     * Metodo che permette al personaggio di saltae in aria.
     */
    public void jump() {
        setMovimentoY(this.jumpForce);
        
        setChanged();
        notifyObservers();
    }
    
    /**
     * Metodo che permette al personaggio di muoversi verso sinistra.
     * @param l il livello in cui si muove il personaggio
     */
    public void moveLeft(Livello l) {
        if (l.isEmpty(getX()-getMovimentoX(), getY()) && !watered){
            setPosizione(getX()-getMovimentoX(), getY());
            setGoingRight(false);
            setChanged();
            notifyObservers();
        }
    }

    /**
     * Metodo che permette al personaggio di muoversi verso destra.
     * @param l il livello in cui si muove il personaggio
     */
    public void moveRight(Livello l) {
        if (l.isEmpty(getX()+getMovimentoX()+5, getY()) && !watered){
            setPosizione(getX()+getMovimentoX(), getY());
            setGoingRight(true);

            setChanged();
            notifyObservers();

        }
    }
}
