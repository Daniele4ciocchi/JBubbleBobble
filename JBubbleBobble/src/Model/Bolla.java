package Model;

import java.util.Observer;

public class Bolla extends Entita{

    // in questo caso hp viene utilizzato per definire il tempo di vita della bolla
    final int MAXHP = 20;
    private boolean floating = false;
    private Nemico nemico;

    public Bolla(){
        super.setHp(MAXHP);
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
