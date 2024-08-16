import java.util.ArrayList;

public class Partita{
    //variabile per garantire che ci sia una sola istanza di partita
    private static Partita istanza;

    //giocatore principale 
    private Giocatore giocatore;
    //nemici all'interno della partita in corso 
    private ArrayList<Nemico> nemici; 
    //powerup presenti all'interno della partita in corso 
    private ArrayList<PowerUp> powerUp;

    private Livello livelloAttuale //da rivedere meglio 
    
    //counter per poter ottenere determinati powerUp
    private int bolleSparate;
    private int bolleScoppiate;
    private int saltiEffettuati;

    //int[...](per i powerup)

    private boolean vinta;

    //metodi

    private Partita(){
        //da inserire robe
    }

    public static Partita getIstanza() {
        if (istanza == null)
            istanza = new Partita();
        return istanza;
    }
    
    public Giocatore getGiocatore(){return this.giocatore;}

    public getNemici
}