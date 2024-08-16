import java.util.ArrayList;

public class Partita{

    private Partita istanza;

    private Giocatore giocatore;
    private ArrayList<Nemico> nemici;
    private ArrayList<PowerUp> powerUp;
    private Livello livelloAttuale //da rivedere meglio 
    private int bolleSparate;
    private int bolleScoppiate;
    private int saltiEffettuati;

    //int[...](per i powerup)

    private boolean vinta;

    //metodi

    private Partita(){

    }

    
    public Giocatore getGiocatore(){return this.giocatore;}

    public static Ex2_Ricette getIstanza() {
        if (istanza == null)
            istanza = new Ex2_Ricette();
        return istanza;
    }
}