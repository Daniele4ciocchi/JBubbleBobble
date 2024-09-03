package Model;

import java.util.ArrayList;
import java.util.Observer;

public class Bolla extends Entita implements Runnable{


    private boolean floating = false;
    private Nemico nemico;


    private ArrayList<Observer> observers;

    //costruttore
    public Bolla(){
        super.partita.addEntita(this);
        this.setHp(20);
        this.run();
    }

    //metodo per far scoppiare la bolla automaticamente dopo un determinato periodo di tempo
    @Override
    public void run() {
        try {
            // Il thread "dorme" per il tempo specificato, simulando la durata della bolla
            Thread.sleep(this.getHp());
            if (this.getHp() == this.getHp()-1)this.setFloating();
            scoppia();
        } catch (InterruptedException e) {
            System.out.println(this + " è stata interrotta.");
        }
    }

    //ritorna il nemico all'interno della bolla
    public Nemico getNemico(){
        return this.nemico;
    }

    //ritorna lo stato della bolla
    public boolean isFloating(){
        return floating;
    }

    //imposta lo stato della bolla
    public void setFloating(){
        this.floating = true;
    }

    //imposta la cattura del nemico 
    public void catturaNemico(Nemico nemico){
        if(floating == false){
            this.nemico = nemico;
            this.nemico.setBubbled();
        }
    }
    //metodo per far scoppiare la bolla
    public void scoppia(){
        partita.removeEntita(this);
        if (nemico != null){
            partita.removeEntita(nemico);
        }
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
