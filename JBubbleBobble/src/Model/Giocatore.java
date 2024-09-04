package Model;

import Controller.GiocatoreController;
import View.GiocatoreView;

import java.util.ArrayList;
import java.util.Observer;

//da fare singleton
public class Giocatore extends Entita{

    //istanza singleton
    private static Giocatore INSTANCE;

    //attributi
    private Profilo profilo;

    private ArrayList<Observer> observers;

    //controller e view
    private GiocatoreController controller;
    private GiocatoreView view;


    //costruttore privato in quanto singleton
    private Giocatore(){
        //inizializzo i valori del giocatore
        super.setHp(3);
        super.setVelocita(1);

        partita.addEntita(this);

        //istanzio il controller e la view
        this.view = new GiocatoreView();
        this.controller = new GiocatoreController(this, this.view);

        //grazie a questo metodo il controller può ricevere input dall'utente(da capire meglio)
        //this.view.addKeyListener(controller);

        //inizializzo l'arraylist di observers
        observers = new ArrayList<>();
    }

    //creare una sola istanza di Giocatore
    public static Giocatore getInstance(){
        if(INSTANCE == null) {
            INSTANCE = new Giocatore();
        }
        return INSTANCE;
    }

    //metodi set
    public void setProfilo(Profilo p){
        this.profilo = p;
    }


    //azioni giocatore
    public void moveLeft(){
        this.setPosizione(getPosX()-super.getVelocitaX(), getPosY());
    }

    public void moveRight(){
        this.setPosizione(getPosX()+super.getVelocitaX(), getPosY());
    }

    //TODO: da implementare
    public void salta() {
        this.setPosizione(getPosX(), getPosY()-super.getSalto());
    }

    public void applyGravity() {
        this.setVelocitaY(this.getVelocitaY() + this.getGravita());
        int newY = getPosY() + getVelocitaY();

        // Controllo collisione con il terreno o piattaforme
        if (partita.getLivello().getTile(this.getPosX(), newY + getHeight()).getType().isWalkable()) {
            this.setPosizione(this.getPosX(),newY);
        } else {
            // Se colpisce il terreno, ferma la caduta
             setPosizione(this.getPosX(),(newY / 32) * 32);
            this.setVelocitaY(0);
        }
    }

    public void sparaBolle(){
        new Bolla();
    }

    public void esplodiBolla(Bolla bolla){
        bolla.scoppia();
    }


    //metodi per observer
    @Override
    public void addObserver(Observer o){
        observers.add(o);
    }
    @Override
    public void deleteObserver(Observer o){
        observers.remove(o);
    }
    @Override
    public void notifyObservers(){
        for (Observer o : observers){
            o.update(this, null);
        }
    }


}
