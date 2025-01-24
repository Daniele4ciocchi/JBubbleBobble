package model;

import java.util.ArrayList;
import java.util.Observer;
import java.util.concurrent.TimeUnit;

import javax.swing.Timer;

abstract public class Bolla extends Entita {


    private boolean floating = false;
    private Nemico nemico;
    protected int range;
    protected int popTime = 300;

    //costruttore
    public Bolla(int posx, int posy, int movimentoX, int movimentoY, boolean goingRight) {
        super(posx/16, posy/16, movimentoX, movimentoY, 0);
        setGoingRight(goingRight);
    }

    public Bolla(int posx, int posy, int movimentoX, int movimentoY, int gravita) {
        super(posx/16, posy/16, movimentoX, movimentoY, gravita);
        setGoingRight(true);
    }


    public abstract void move(Livello l);

    public Nemico getNemico(){ return this.nemico;}

    public boolean isFloating(){return floating;}
    public int getRange() {return range;}

    public void setFloating() {this.floating = true;}
    public void setRange(int range) {this.range = range;}
    public int getPopTime() {return popTime;}

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


    public Nemico scoppia() {
        this.nemico.setBubbled(false);
        this.nemico.setPosizione(this.getX(), this.getY());
        return nemico;
    }

}
