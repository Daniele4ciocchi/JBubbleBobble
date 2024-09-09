package Model;

import Controller.GiocatoreController;
import View.GiocatoreView;

import java.util.ArrayList;
import java.util.Observer;

//da fare singleton
public class Giocatore extends Entita{

    //istanza singleton
    private static Giocatore instance;

    //attributi
    private Profilo profilo;

    private ArrayList<Observer> observers;

    //controller e view
    private GiocatoreController controller;



    //costruttore privato in quanto singleton
    private Giocatore(){
        //inizializzo i valori del giocatore
        super.setHp(3);
        super.setVelocitaX(1);
        super.setVelocitaY(1);

        partita.addEntita(this);;

        //grazie a questo metodo il controller può ricevere input dall'utente(da capire meglio)
        //this.view.addKeyListener(controller);

        //inizializzo l'arraylist di observers
        observers = new ArrayList<>();
    }

    //creare una sola istanza di Giocatore
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




    public void sparaBolle(){
        new Bolla();
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
    public void notifyObservers(){
        for (Observer o : observers){
            o.update(this, null);
        }
    }


}
