package Model;

import java.util.Observer;

public class Bolla extends Entita implements Runnable{


    private boolean floating = false;
    private Nemico nemico;
    private Livello livello;

    //costruttore
    public Bolla(Livello livello){
        this.livello = livello;
        this.setHp(20);
        this.run();
    }

    //metodo per far scoppiare la bolla automaticamente dopo un determinato periodo di tempo
    @Override
    public void run() {
        try {
            // Il thread "dorme" per il tempo specificato, simulando la durata della bolla
            Thread.sleep(this.getHp());
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
        livello.removeEntita(this);
        if (nemico != null){
            livello.removeEntita(nemico);
        }
    }

    //Observer pattern
    @Override
    public void addObserver(Observer o) {

    }

    @Override
    public void deleteObserver(Observer o) {

    }

    @Override
    public void notifyObservers() {

    }
}
