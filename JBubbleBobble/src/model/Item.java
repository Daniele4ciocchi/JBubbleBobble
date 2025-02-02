package model;

import java.io.File;

/**
 * Classe che rappresenta un generico Item, ossia un oggetto del gioco.
 */
public abstract class Item extends Entita {

    /**
     * Indica il punteggio che l'Item fornisce al giocatore una volta raccolto.
     */
    protected int points;

    /**
     * Costruttore della classe Item, utilizzato dalle sottoclassi concrete PointItem e SpecialItem tramite super().
     * @param posx posizione x iniziale dell'Item
     * @param posy posizione y iniziale dell'Item
     * @param velocitaX velocità di movimento sull'asse x
     * @param velocitaY velocità di movimento sull'asse y
     * @param gravita gravità dell'Item
     */
    public Item(int posx, int posy, int velocitaX, int velocitaY, int gravita) {
        super(posx, posy, velocitaX, velocitaY, gravita);
        idleSpritePath += "items" + File.separator;
        deathSpritePath += "items" + File.separator;
    }
    
    /**
     * Metodo che restituisce il percorso dello sprite dell'Item.
     * @return percorso dello sprite dell'Item sottoforma di stringa
     */
    public String getSpritePath(){
        if (this.isDead()) return this.idleSpritePath;
        else return this.deathSpritePath;
    }

    /**
     * Metodo che restituisce il punteggio che l'Item fornisce al giocatore una volta raccolto.
     * @return punteggio dell'Item
     */
    public int getPoints() {
        return points;
    }
}
