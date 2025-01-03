package Model;

import java.util.ArrayList;
import java.util.Observer;
import java.util.concurrent.TimeUnit;

import javax.swing.Timer;

abstract public class Bolla extends Entita {


    private boolean floating = false;
    private Nemico nemico;
    protected int range;

    //costruttore
    public Bolla(int posx, int posy, int movimentoX, int movimentoY, boolean goingRight) {
        super(posx/16, posy/16, movimentoX, movimentoY, 0);
        this.range = 50;
        setGoingRight(goingRight);
        Timer timer = new Timer(32, e -> {move();});
        timer.start();
    }


    public abstract void move();

    public Nemico getNemico(){ return this.nemico;}

    public boolean isFloating(){return floating;}
    public int getRange() {return range;}

    public void setFloating() {this.floating = true;}
    public void setRange(int range) {this.range = range;}

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
