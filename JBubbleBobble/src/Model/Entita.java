package Model;

import java.util.Observable;
import java.util.Observer;

abstract class Entita extends Observable {
    private int hp; // health points rimanenti 
    private int maxHp; // health points massimi
    private int velocita; // velocita movimento (float?)
    private double gravita = 0.5; // gravita

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
    public int getVelocita(){
        return velocita;
    }

    // getter di posX
    public int getPosX(){
        return posX;
    }

    // getter di posY
    public int getPosY(){
        return posY;
    }

    // getter di salto
    public double getGravita(){return gravita;}



    //mpdifica hp
    public void setHp(int hp){
        this.hp = hp;
    }

    // Modifica la velocita dell'entita nel valore (long) fornito
    public void setVelocita(int v){
        velocita = v;
    }

    // Modifica la velocita fornendo in input la differenza richiesta (es: -1 abbassa la velocita' di 1)
    public void setVelocitaDiff(int v){
        velocita += v;
    }

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