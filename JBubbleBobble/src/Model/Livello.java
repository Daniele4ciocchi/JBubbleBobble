package Model;

public class Livello {
    /*
        PLAYERSPAWN_X e PLAYERSPAWN_Y indicano rispettivamente le coordinate X, Y di dove dovrà spawnare
        il giocatore all'inizio del livello e al suo respawn

        //TODO: capire se i livelli hanno diverse posizioni di spawn del giocatore (GIOCARE IL GIOCO)
        //TODO: capire se le coordinate di spawn sono FINAL o no
     */
    private final int PLAYERSPAWN_X = 0;
    private final int PLAYERSPAWN_Y = 0;

    private Tile[][] grid;
    /*
        Il numero di livello è un intero che determina cose come la disposizione delle piattaforme, le coordinate
        di spawn delle entità, ...
     */
    private int levelNum;

    // COSTRUTTORE
    public Livello(int ln){
        levelNum = ln;
        costruisciGrid();
    }

    // Restituisce il valore di PLAYERSPAWN_X
    public int getPLAYERSPAWN_X(){
        return PLAYERSPAWN_X;
    }

    // Restituisce il valore di PLAYERSPAWN_Y
    public int getPLAYERSPAWN_Y(){
        return PLAYERSPAWN_Y;
    }

    // Restituisce il numero del livello
    public int getLevelNum(){
        return levelNum;
    }

    // Imposta il valore di PLAYERSPAWN_X. Questo metodo è accessibile solo dal costruttore.
    private void setPLAYERSPAWN_X(int ln){
        // TODO: giocare il gioco originale per capire l'implementazione
    }

    // Imposta il valore di PLAYERSPAWN_Y. Questo metodo è accessibile solo dal costruttore.
    private void setPLAYERSPAWN_Y(int ln){
        // TODO: giocare il gioco originale per capire l'implementazione

    }


    /*
        Metodo chiamato dal costruttore che costruirà una grid di Tile in base al levelNum indicato.
        Tramite uno switch-case, decide la configurazione delle tile.
     */
    private void costruisciGrid(){
        switch (levelNum) {
            case 1 -> {
                // costruzione di un livello 1
            }

            case 2 -> {
                // costruzione di un livello 2
            }

            case 3 -> {
                // costruzione di un livello 3
            }

            case 4 -> {
                // costruzione di un livello 4
            }

            case 5 -> {
                // costruzione di un livello 5
            }

            case 6 -> {
                // costruzione di un livello 6
            }

            case 7 -> {
                // costruzione di un livello 7
            }

            case 8 -> {
                // costruzione di un livello 8
            }

            case 9 -> {
                // costruzione di un livello 9
            }

            case 10 -> {
                // costruzione di un livello 10
            }

            case 11 -> {
                // costruzione di un livello 11
            }

            case 12 -> {
                // costruzione di un livello 12
            }

            case 13 -> {
                // costruzione di un livello 13
            }

            case 14 -> {
                // costruzione di un livello 14
            }

            case 15 -> {
                // costruzione di un livello 15
            }

            case 16 -> {
                // costruzione di un livello 16
            }
        } //asdfasdfasdfgg
    }

}
