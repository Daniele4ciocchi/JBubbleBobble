package Model;

import java.util.ArrayList;
import java.util.Observer;

//da fare singleton
public class Giocatore extends Entita{

    private int life;

    public Giocatore(){
        super(5, 1, 10, 20, -7);
        this.life = 3;
    }
              
    public int getLife(){return this.life; }

    public void addlife(){this.life++;}

    public void resetPosizione(){super.setPosizione(5, 1);}

    public Bolla shoot(){
        return new Bolla(super.getGoingRight()? this.getX()+1 : this.getX()-1, this.getY(), 1, 1, super.getGoingRight());
    }
    
}
