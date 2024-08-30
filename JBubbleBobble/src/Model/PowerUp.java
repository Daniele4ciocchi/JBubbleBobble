package Model;

import java.util.Observer;

public class PowerUp extends Entita{

    public enum TipologiaPowerUp{
        POWERUP1, POWERUP2, POWERUP_N
        //TODO: inserire le tipologie reali di powerup
    }

    private TipologiaPowerUp TIPOLOGIA;


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
