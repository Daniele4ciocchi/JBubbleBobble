package Model;

import classiTolte.GiocatoreController;

import java.util.ArrayList;
import java.util.Observer;

//da fare singleton
public class Giocatore extends Entita{

    private int life;
    private ArrayList<Observer> observers;

    public Giocatore(){
        super(25, 5, 1, 1, 1);
        this.life = 3;

        //TODO: implementare Observe Observable (non so come)
        observers = new ArrayList<>();
    }

    public int getLife(){return this.life; }

    public void addlife(){this.life++;}

    public void resetPosizione(){
        super.setPosizione(25, 5);
    }

    public void shoot(){

    }


    //metodi per observer
    @Override
    public void addObserver(Observer o){
        observers.add(o);
    }
    @Override
    public void deleteObserver(Observer o){
        observers.remove(o);
    }
    @Override
    public void notifyObservers(){
        for (Observer o : observers){
            o.update(this,o);
        }
    }


}
