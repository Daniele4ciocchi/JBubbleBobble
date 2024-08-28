package Model;

import java.util.ArrayList;
import java.util.Observer;

//da fare singleton
public class Giocatore extends Entita{
    private Profilo profilo;
<<<<<<< Updated upstream
    private static Giocatore INSTANCE;
=======
    private Livello livello;
    private ArrayList<Observer> observers;
>>>>>>> Stashed changes


//    public Giocatore(Profilo profilo){
//        this.profilo = profilo;
//        this.setHp(3);
//    }

    public Giocatore(){}

    public static Giocatore getInstance(){
        if(INSTANCE == null) {
            INSTANCE = new Giocatore();
        }
        return INSTANCE;
    }


    public void setProfilo(Profilo p){
        this.profilo = p;
    }

    public void moveLeft(){

    }

    public void moveRight(){

    }

    public void sparaBolle(){
        livello.addEntita(new Bolla());
    }

    public void salta() {

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
        for (Observer o : observers){}
    }


}
