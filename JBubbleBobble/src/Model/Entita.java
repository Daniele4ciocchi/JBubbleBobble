package Model;

import java.util.Observable;
import java.util.Observer;

abstract class Entita extends Observable {
    private int hp; // health points rimanenti 
    private int maxHp; // health points massimi
    private int velocitaX; // velocita movimento (float?)
    private int velocitaY;
    private int gravita = 1; // gravita
    private int height; // altezza
    private int width; // larghezza

    public Partita partita = Partita.getInstance();

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

    @Override
    public abstract void addObserver(Observer o);
    @Override
    public abstract void deleteObserver(Observer o);
    @Override
    public abstract void notifyObservers();
}