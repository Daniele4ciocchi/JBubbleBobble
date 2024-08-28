package Model;

import java.util.ArrayList;

// SINGLETON? BUILDER PATTERN?
public class Livello {

    /*
     * Variabile statica che memorizza la sola e unica istanza attiva di Livello, ossia il Livello che sta attualmente
     * venendo giocato e visualizzato a schermo durante la partita.
     */
    private static Livello INSTANCE;

    /*
     * ArrayList che contiene tutte le Entita presenti nel livello, come il Giocatore, i Nemici e le Bolle.
     */
    private ArrayList<Entita> entita;

    /*
     * Array bidimensionale di Tile che rappresenta la griglia del livello.
     */
    private Tile[][] grid;

    /*
        Il numero di livello è un intero che determina cose come la disposizione delle piattaforme, le coordinate
        di spawn delle entità, ...
     */
    private int levelNum;

    // COSTRUTTORE DELLA CLASSE
    private Livello(LivelloBuilder lb){
        this.entita = lb.entita;
        this.grid = lb.grid;
    }

    public static Livello getInstance(){
        if (INSTANCE == null){
            throw new IllegalStateException("Usa la classe LivelloBuilder per instanziare un nuovo Livello!");
        }
        return INSTANCE;
    }

    /*
     * LivelloBuilder si occupa di gestire la costruzione dell'unica istanza di Livello (Singleton pattern) e della
     * conformazione della grid.
     */
    public static class LivelloBuilder{
        private int levelNum;
        private ArrayList<Entita> entita;
        private Tile[][] grid;

        public LivelloBuilder setLevelNum(int ln){
            this.levelNum = ln;
            return this;
        }

        public LivelloBuilder buildGrid(){
            grid = new Tile[26][36];
            switch (this.levelNum) {
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
            }
            return this;
        }

        public Livello build(){
            if (INSTANCE == null){
                INSTANCE = new Livello(this);
            }
            return INSTANCE;
        }
    }

    // Getter del numero del livello
    public int getLevelNum(){
        return levelNum;
    }

    // Metodo per aggiungere un'entità all'interno dell'array
    public void addEntita(Entita entita){
        this.entita.add(entita);
    }
    public void removeEntita(Entita entita) {this.entita.remove(entita);}

}
