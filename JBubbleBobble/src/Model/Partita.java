package Model;

import Controller.PartitaController;
import View.PartitaView;

import java.util.ArrayList;

public class Partita{

    public static Partita INSTANCE;

    private Giocatore giocatore; //giocatore principale
    private ArrayList<Entita> entita; //lista delle entità presenti nella partita

    //counter per poter ottenere determinati powerUp
    private int bolleSparate;
    private int bolleScoppiate;
    private int saltiEffettuati;
    private int punteggio;

    //MVC
    private PartitaController controller = PartitaController.getInstance();

    private boolean vinta;
    private Livello livello;



    private Partita(){
        this.giocatore = Giocatore.getInstance();
        this.entita = new ArrayList<>();

        this.bolleSparate = 0;
        this.bolleScoppiate = 0;
        this.saltiEffettuati = 0; 
        this.vinta = false;
    }

    public static Partita getInstance(){
        if (INSTANCE == null){
            throw new IllegalStateException("Usa la classe LivelloBuilder per instanziare un nuovo Livello!");
        }
        return INSTANCE;
    }

    //metodi getter
    public Giocatore getGiocatore(){return this.giocatore;}
    public ArrayList<Entita> getEntita(){return this.entita;}

    public int getBolleScoppiate(){return this.bolleScoppiate;}
    public int getBolleSparate(){return this.bolleSparate;}
    public int getSaltiEffettuati(){return this.saltiEffettuati;}
    public int getPunteggio(){return this.punteggio;}
    public Livello getLivello(){return this.livello;}

    //metodi setter
    public void addBollaScoppiata(){this.bolleScoppiate++;}
    public void addBollaSparata(){this.bolleSparate++;}
    public void addSaltoEffettuato(){this.saltiEffettuati++;}

    // Metodo per aggiungere un'entità all'interno dell'array
    public void addEntita(Entita entita){this.entita.add(entita);}
    public void removeEntita(Entita entita) {this.entita.remove(entita);}
    public void svuotaEntita(){this.entita.clear();}


    public boolean isVinta(){return this.vinta;}

    public void gioca(){

    }
}