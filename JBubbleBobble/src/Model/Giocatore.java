package Model;

import java.util.ArrayList;
import java.util.Observer;

//da fare singleton
public class Giocatore extends Entita{
    private Profilo profilo;
    private static Giocatore INSTANCE;
    private Livello livello;
    private ArrayList<Observer> observers;


    public Giocatore(){

    }

    public static Giocatore getInstance(){
        if(INSTANCE == null) {
            INSTANCE = new Giocatore();
        }
        return INSTANCE;
    }


    public void setProfilo(Profilo p){
        this.profilo = p;
    }

    //azioni giocatore
    public void moveLeft(){

    }

    public void moveRight(){

    }

    public void salta() {

    }

    public void sparaBolle(){
        livello.addEntita(new Bolla());
    }
    public void esplodiBolla(Bolla bolla){
        livello.removeEntita(bolla);
    }



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
            o.update(this, null);
        }
    }


}
