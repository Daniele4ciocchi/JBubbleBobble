package Model;

import Controller.PartitaController;

import java.util.Observable;
import java.util.Observer;

abstract public class Entita extends Observable {
    private int hp; // health points rimanenti 
    private int maxHp; // health points massimi
    private int velocitaX; // velocita movimento (float?)
    private int velocitaY;

    private final int gravita = 1; // gravita
    private final int forzaSalto = -15; // forza salto
    private final int height = 32; // altezza
    private final int width = 32; // larghezza

    public PartitaController partita = PartitaController.getInstance();

    // variabili per le coordinate
    private int posX;
    private int posY;
    public int setHp;

    // getter di hp
    public int getHp(){
        return hp;
    }

    // getter di maxHp
    public int getMaxHp(){
        return maxHp;
    }

    // getter di velocita
    public int getVelocitaX(){
        return velocitaX;
    }
    public int getVelocitaY() { return velocitaY; }

    // getter di posX
    public int getPosX(){
        return posX;
    }

    // getter di posY
    public int getPosY(){
        return posY;
    }

    // getter di salto
    public int getGravita(){return gravita;}

    public int getHeight() { return height; }
    public int getWidth() { return width; }

    public int getForzaSalto() {return forzaSalto;}

    //mpdifica hp
    public void setHp(int hp){
        this.hp = hp;
    }

    // Modifica la velocita dell'entita nel valore (long) fornito
    public void setVelocitaX(int v){
        this.velocitaX = v;
    }
    public void setVelocitaY(int v) { this.velocitaY = v; }

    //Modifica la posizione dell'elemento 
    public void setPosizione(int x, int y){
        posX = x;
        posY = y;
    }

    //metodo che toglie gli hp
    public void damage(int damage){
        this.hp--;
    }
    public void restore(int restore) {this.hp++;}

    //metodi che muovono l'entità

    public void moveLeft(){
        posX = posX - velocitaX;
    }

    public void moveRight(){
        posX = posX + velocitaX;
    }

    public void salta() {
        if (!Livello.getInstance().getTile(this.getPosX(), this.getPosY() + getHeight() + 1).getType().isWalkable()) {
            this.setVelocitaY(this.getForzaSalto());
        }
    }

    public void applyGravity() {
        this.setVelocitaY(this.getVelocitaY() + this.getGravita());
        int newY = getPosY() + getVelocitaY();

        // Controllo collisione con il terreno o piattaforme
        if (Livello.getInstance().getTile(this.getPosX(), newY + getHeight()).getType().isWalkable()) {
            this.setPosizione(this.getPosX(),newY);
        } else {
            // Se colpisce il terreno, ferma la caduta
            setPosizione(this.getPosX(),(newY / 32) * 32);
            this.setVelocitaY(0);
        }
    }


    @Override
    public abstract void addObserver(Observer o);
    @Override
    public abstract void deleteObserver(Observer o);
    @Override
    public abstract void notifyObservers();
}