package Model;

import Controller.GiocatoreController;
import View.GiocatoreView;

import java.util.ArrayList;
import java.util.Observer;

//da fare singleton
public class Giocatore extends Entita{

    //istanza singleton
    private static Giocatore instance;

    private Profilo profilo;
    private ArrayList<Observer> observers;
    private GiocatoreController controller;

    private Giocatore(){
        //inizializzo i valori del giocatore
        super.setPosizione(25, 5);
        super.setHp(3);
        super.setVelocitaX(1);
        super.setVelocitaY(1);

        observers = new ArrayList<>();
    }

    public static Giocatore getInstance(){
        if(instance == null) {
            instance = new Giocatore();
        }
        return instance;
    }

    //metodi set
    public void setProfilo(Profilo p){
        this.profilo = p;
    }


    public void resetPosizione(){
        super.setPosizione(25, 5);
    }

    public void sparaBolle(){
        notifyObservers();
    }

    public void esplodiBolla(Bolla bolla){
        bolla.scoppia();
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
    public void notifyObservers(Entita e){
        for (Observer o : observers){
            o.update(this, e);
        }
    }


}
