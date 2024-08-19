import java.util.ArrayList;

public class Partita{
 
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

    private Partita(Giocatore giocatore){
        this.giocatore = giocatore;
        this.nemici = new ArrayList<Nemico>;
        this.powerUp = new ArrayList<PowerUp>;

        this.bolleSparate = 0;
        this.bolleScoppiate = 0;
        this.saltiEffettuati = 0; 
        this.vinta = false;
    }

    //metodi getter
    
    public Giocatore getGiocatore(){return this.giocatore;}
    public Nemico getNemici(){return this.nemici;}
    public PowerUp getPowerUp(){return this.powerUp;}
    public Livello getLivello(){return this.livello;}

    public int getBolleScoppiate(){return this.bolleScoppiate;}
    public int getBolleSparate(){return this.bolleSparate;}
    public int getSaltiEffettuati(){return this.saltiEffettuati;}

    //metodi setter

    public void addBolleScoppiate(){this.bolleScoppiate++;}
    public void addBolleSparate(){this.bolleSparate++;}
    public void addSaltiEffettuati(){this.saltiEffettuati++;}

    //aggiungere e rimuovere nemici dalla partita
    public void addNemico(Nemico nemico){this.nemici.add(nemico);}
    public void removeNemico(Nemico nemico){this.nemici.remove(nemico);}

    //aggiungere e rimuovere powerUp dalla partita
    public void addPowerUp(PowerUp powerUp){this.powerUp.add(powerUp);}
    public void removePowerUp(PowerUp powerUp){this.powerUp.remove(powerUp);}

    public boolean isVinta(){return this.vinta;}

}