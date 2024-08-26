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


}
