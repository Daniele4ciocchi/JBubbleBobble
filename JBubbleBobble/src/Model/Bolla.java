package Model;

import java.util.ArrayList;
import java.util.Observer;

public class Bolla extends Entita implements Runnable {


    private boolean floating = false;
    private Nemico nemico;
    private int countDown = 20;
    private boolean direction;

    private ArrayList<Observer> observers;

    //costruttore
    public Bolla(double posx, double posy, double velocitax, double velocitay, boolean direction) {
        super(posx, posy, velocitax, velocitay, 0);
        this.direction = direction;
        this.run();
    }

    /**
     * Metodo per iniziare il conto alla rovescia della bolla
     */
    @Override
    public void run() {
        try {
            // Il thread "dorme" per il tempo specificato, simulando la durata della bolla
            Thread.sleep(this.countDown);
            //TODO: 2 step floating (50% del max time) e pop! controllare la direzione
            if (this.countDown == 0) this.setFloating();

        } catch (InterruptedException e) {
            System.out.println(this + " è stata interrotta.");
        }
    }

    public Nemico getNemico(){ return this.nemico;}

    public boolean isFloating(){return floating;}

    public void setFloating() {this.floating = true;}

    /**
     * Metodo per catturare un nemico nella bolla
     * @param nemico il nemico da catturare
     */
    public void catturaNemico(Nemico nemico) {
        if(!floating){
            this.nemico = nemico;
            this.nemico.setBubbled(true);

        }
    }

//
//    public void scoppia() {
//        partita.getModel().removeEntita(this);
//        if (nemico != null){
//            partita.getModel().removeEntita(nemico);
//        }
//    }

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
