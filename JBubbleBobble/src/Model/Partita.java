package Model;

import Controller.PartitaController;
import View.PartitaView;

import java.util.ArrayList;

public class Partita{

    public static Partita INSTANCE;

    //giocatore principale 
    private Giocatore giocatore;
    //lista delle entità presenti nella partita
    private ArrayList<Entita> entita;


    //counter per poter ottenere determinati powerUp
    private int bolleSparate;
    private int bolleScoppiate;
    private int saltiEffettuati;
    private int punteggio;

    private PartitaController controller;
    private PartitaView view;

    private boolean vinta;
    private Livello livello;

    //metodi

    private Partita(){
        this.giocatore = Giocatore.getInstance();
        this.entita = new ArrayList<>();

        this.controller = new PartitaController();
        this.view = new PartitaView();

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

    //metodi setter

    public void addBollaScoppiata(){this.bolleScoppiate++;}
    public void addBollaSparata(){this.bolleSparate++;}
    public void addSaltoEffettuato(){this.saltiEffettuati++;}

    // Metodo per aggiungere un'entità all'interno dell'array
    public void addEntita(Entita entita){
        this.entita.add(entita);
    }
    public void removeEntita(Entita entita) {this.entita.remove(entita);}

    public boolean isVinta(){return this.vinta;}

}