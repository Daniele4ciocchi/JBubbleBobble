package Model;

import Controller.PartitaController;
import View.PartitaView;

public class Menu {
    private Profilo profilo;

    public Menu() {
        if (Profilo.getInstance() != null) { // esiste un profilo utente
            profilo = Profilo.getInstance();
        }
        else { // NON esiste un profilo utente: è il primo avvio del gioco

        }
    }

    public void setProfilo(Profilo p) {
        profilo = p;
    }

    public Profilo getProfilo() {
        return profilo;
    }

    public Partita nuovaPartita() {

        return new Partita();

    }

    /*
     * Questa scelta del menu permette al giocatore di inserire una password che potrà conoscere solo se è già arrivato
     * a uno specifico livello facendoci GAME OVER.
     * @param password: stringa alfanumerica di 5 caratteri che identifica uno specifico livello da cui iniziare
     * una nuova partita
     */
    public Partita caricaPartita(String password) {
        int ln;
        switch (password) {
            case "LIVELLO1" -> ln = 1;
            case "LIVELLO2" -> ln = 2;
            case "LIVELLO3" -> ln = 3;
            case "LIVELLO4" -> ln = 4;
            case "LIVELLO5" -> ln = 5;

            default -> ln = 1;
        }
        Partita x = new Partita();
        x.setLivello(ln);
        return x;
    }
}
