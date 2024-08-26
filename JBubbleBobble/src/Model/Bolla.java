package Model;

public class Bolla extends Entita{

    final int MAXHP = 20; // da definire
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
}
