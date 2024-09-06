package Model;

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

    }
}
