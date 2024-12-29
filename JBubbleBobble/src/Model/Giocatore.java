package Model;

import java.util.ArrayList;
import java.util.Observer;

//da fare singleton
public class Giocatore extends Entita{

    private int life;
    private boolean direction; //true = destra, false = sinistra
    private ArrayList<Observer> observers;


    public Giocatore(){
        super(6, 5, 10, 15, -5);
        this.life = 3;
        this.direction = true;

    }

    public int getLife(){return this.life; }

    public void addlife(){this.life++;}

    public void resetPosizione(){super.setPosizione(5, 1);}

    public Bolla shoot(){
        return new Bolla(this.direction? this.getX()+1 : this.getX()-1, this.getY(), 1, 1, this.direction);
    }

    
}
