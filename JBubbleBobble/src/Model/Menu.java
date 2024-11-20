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

    public Profilo getProfilo() {
        return profilo;
    }

    /*
     * Scelta del menu che inizia una nuova partita dal livello 1.
     */
    public Partita nuovaPartita() {
        return new Partita(1);
    }

    /*
     * Questa scelta del menu permette al giocatore di inserire una password che potrà conoscere solo se è già arrivato
     * a uno specifico livello facendoci GAME OVER.
     * @param password: stringa alfanumerica di 5 caratteri che identifica uno specifico livello da cui iniziare
     * una nuova partita
     */
    public Partita continuaPartita(String password) {
        return new Partita(
                switch (password) {
                    case "LIVELLO1" -> 1;
                    case "LIVELLO2" -> 2;
                    case "LIVELLO3" -> 3;
                    case "LIVELLO4" -> 4;
                    case "LIVELLO5" -> 5;
                    case "LIVELLO6" -> 6;
                    case "LIVELLO7" -> 7;
                    case "LIVELLO8" -> 8;
                    case "LIVELLO9" -> 9;
                    case "LIVELL10" -> 10;
                    case "LIVELL11" -> 11;
                    case "LIVELL12" -> 12;
                    case "LIVELL13" -> 13;
                    case "LIVELL14" -> 14;
                    case "LIVELL15" -> 15;
                    case "LIVELL16" -> 16;
                    default -> 1;
                });
    }

}
