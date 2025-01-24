package controller;

import model.Partita;
import model.Profilo;
import view.GameView;
import view.MenuView;
import view.PartitaView;
import view.ProfiloView;

import java.util.ArrayList;

public class MenuController {

    private ArrayList<Partita> partite;
    private MenuView view;

    public MenuController(MenuView view) {
        this.view = view;
        initView();
    }

    private void initView() {
        view.addNuovaPartitaListener(e -> nuovaPartita());
        view.addContinuaPartitaListener(e -> continuaPartita());
        view.addVisualizzaProfiloListener(e -> getProfiloStats());
    }

    /**
     * Questa scelta del menu permette al giocatore di iniziare una nuova partita da zero.
     */
    public void nuovaPartita() {
        GameView  gameView = new GameView();
        Partita partita = new Partita();
        GameController controller = new GameController(partita, gameView);
        // Logica per iniziare una nuova partita
    }

    /**
     * Questa scelta del menu permette al giocatore di inserire una password che potrà conoscere solo se è già arrivato
     * a uno specifico livello facendoci GAME OVER.
     */
    public void continuaPartita() {
        String password = view.getPasswordInput(); // Assumendo che ci sia un metodo per ottenere la password dalla vista
        GameView  gameView = new GameView();
        Partita partita = new Partita(password);
        GameController controller = new GameController(partita, gameView);
        // Logica per continuare la partita con la password
    }

    /**
     * Questa scelta del menu permette al giocatore di visualizzare le statistiche del proprio profilo.
     */
    public void getProfiloStats() {
        //per ora ok, ma sta roba va cambiata
        Profilo profiloView = Profilo.getInstance();
        ProfiloView view = new ProfiloView(profiloView);
        
    }

    public MenuView getView() {
        return view;
    }

}