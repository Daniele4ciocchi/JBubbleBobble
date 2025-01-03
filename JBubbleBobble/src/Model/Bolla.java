package Model;

import java.util.ArrayList;
import java.util.Observer;
import java.util.concurrent.TimeUnit;

import javax.swing.Timer;

abstract public class Bolla extends Entita {


    private boolean floating = false;
    private Nemico nemico;
    protected int countDown = 20;

    //costruttore
    public Bolla(int posx, int posy, int velocitax, int velocitay, boolean goingRight) {
        super(posx, posy, velocitax, velocitay, 0);
        setGoingRight(goingRight);
        Timer timer = new Timer(1000, e -> {move();});
    }


    public abstract void move();

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
