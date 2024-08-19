package Model;

public class Giocatore extends Entita{
    private Profilo profilo;
    int vite;

    public Giocatore(Profilo profilo){
        this.profilo = profilo;
        this.vite = 3;
    }

    public int getVite(){
        return this.vite;
    }

    public void muoviti(){

    }
    public void sparaBolle(){

    }
    public void salta() {

    }
}
