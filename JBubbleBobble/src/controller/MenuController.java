package controller;

import model.Partita;
import model.Profilo;
import view.GameView;
import view.MenuView;
import view.ProfiloView;
import java.util.ArrayList;

/**
 * Questa classe rappresenta il controller del menu principale del gioco.
 */
public class MenuController {

    private ArrayList<Partita> partite;
    private MenuView view;

    /**
     * Costruttore della classe MenuController.
     * @param view la vista del menu
     */
    public MenuController(MenuView view) {
        this.view = view;
        initview();
    }

    private void initview() {
        view.addNuovaPartitaListener(e -> nuovaPartita());
        view.addContinuaPartitaListener(e -> continuaPartita());
        view.addVisualizzaProfiloListener(e -> getProfiloStats());
    }

    /**
     * Questa scelta del menu permette al giocatore di iniziare una nuova partita da zero.
     */
    public void nuovaPartita() {
        Profilo.getInstance().setNickname(view.getUsernameInput());
        GameView  gameview = new GameView();
        Partita partita = new Partita();
        GameController controller = new GameController(partita, gameview);
    }

    /**
     * Questa scelta del menu permette al giocatore di inserire una password che potrà conoscere solo se è già arrivato
     * a uno specifico livello facendoci GAME OVER.
     */
    public void continuaPartita() {
        Profilo.getInstance().setNickname(view.getUsernameInput());
        String password = view.getPasswordInput();
        GameView  gameview = new GameView();
        Partita partita = new Partita(password);
        GameController controller = new GameController(partita, gameview);

    }

    /**
     * Questa scelta del menu permette al giocatore di visualizzare le statistiche del proprio profilo.
     */
    public void getProfiloStats() {
        Profilo.getInstance().setNickname(view.getUsernameInput());
        Profilo profiloview = Profilo.getInstance();
        ProfiloView view = new ProfiloView(profiloview, Profilo.getInstance().getBestScores());
        
    }

    /**
     * Metodo che restituisce la vista del menu.
     * @return la vista del menu
     */
    public MenuView getview() {
        return view;
    }

}