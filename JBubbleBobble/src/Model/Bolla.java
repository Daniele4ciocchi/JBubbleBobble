package Model;

import java.util.ArrayList;
import java.util.Observer;

abstract public class Bolla extends Entita implements Runnable {


    private boolean floating = false;
    private Nemico nemico;
    private int countDown = 20;

    //costruttore
    public Bolla(int posx, int posy, int velocitax, int velocitay, boolean goingRight) {
        super(posx, posy, velocitax, velocitay, 0);
        setGoingRight(goingRight);
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            // TODO: definire il comportamento dei nemici
            try {
                Thread.sleep(100); // Adjust the sleep time as needed
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        super.setChanged();
        super.notifyObservers();
    }

    public void move(){
        
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


}
