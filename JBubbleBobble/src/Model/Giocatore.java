package Model;

import java.util.ArrayList;
import java.util.Observer;

//da fare singleton
public class Giocatore extends Entita{

    private int life;
    private boolean direction; //true = destra, false = sinistra
    private ArrayList<Observer> observers;


    public Giocatore(){
        super(25, 5, 1, 1, 1);
        this.life = 3;
        this.direction = true;

        //TODO: implementare Observe Observable (non so come)
        observers = new ArrayList<>();
    }

    public int getLife(){return this.life; }

    public void addlife(){this.life++;}

    public void resetPosizione(){super.setPosizione(25, 5);}

    public Bolla shoot(){
        return new Bolla(this.direction? this.getX()+1 : this.getX()-1, this.getY(), 1, 1, this.direction);
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
