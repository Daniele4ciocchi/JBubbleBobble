package Model;

import java.util.Observer;
import java.util.Random;

public class Nemico extends Entita implements Runnable{

    // Enumerazione delle tipologie di nemici con le relative caratteristiche
    public enum TipologiaNemico{
        //NOME (VELOCITÀ(1-3, 4 per la modalità arrabbiato), SALTO(1-3), MOSSE, ATTACCO)
        ZENCHAN("zen-chan",2, 2, "", "contatto"),
        BANEBOU("banebou",3, 2, "", "contatto"),
        MIGHTA("mighta",2, 2, "lancia-rocce", "contatto"),
        HIDEGON("hidegons",3,1, "palle-di-fuoco","contatto"),
        PULPUL("pulpul",3, 3, "", "contatto"),
        MONSTA("monsta",3, 2, "", "contatto"),

        ;
        private final String nome;
        private final int velocita;
        private final int salto;
        private final String mosse;
        private final String attacco;

        TipologiaNemico(String nome, int velocita, int salto, String mosse, String attacco){
            this.nome = nome;
            this.velocita = velocita;
            this.salto = salto;
            this.mosse = mosse;
            this.attacco = attacco;

        }
        //getter
        public String getNome(){return this.nome;}
        public int getVelocita(){return this.velocita;}
        public int getSalto(){return this.salto;}
        public String getMosse(){return this.mosse;}
        public String getAttacco(){return this.attacco;}
    }

    private TipologiaNemico TIPOLOGIA;
    private int carattere; //intero da 1 a 10, generato nel costruttore, usato nei parametri di comportamento

    private boolean bubbled;

    /**
     * Costruttore della classe Nemico
     * @param t la tipologia del nemico
     * @param x la posizione x del nemico
     * @param y la posizione y del nemico
     */
    public Nemico(TipologiaNemico t, int x, int y){
        super( x, y, t.getVelocita(), t.getSalto(), 1);
        TIPOLOGIA = t;
        bubbled = false;

        Random rand = new Random();
        int carattere = rand.nextInt(10) + 1;
    }

    //metodi getter
    // Restituisce la Tipologia di questo nemico
    public TipologiaNemico getTipologia(){
        return TIPOLOGIA;
    }

    public boolean isBubbled(){
        return bubbled;
    }
    public void setBubbled(boolean b){this.bubbled = b;}
    public int getCarattere() {
        return this.carattere;
    }
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            // TODO: definire il comportamento dei nemici
            try {
                Thread.sleep(100); // Adjust the sleep time as needed
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    // metodo che gestisce il movimento di questo nemico
    // prende in input la posizione x e la posizione y del Giocatore
    public void move(int gx, int gy) {
        // controllo orizzontale di dove si trova il giocatore, si muove a dx/sx di conseguenza
        if (this.getX() < gx) {
            if (getCarattere() % 2 == 0) moveRight();
        } else if (this.getX() > gx) {
            getCarattere() % 2 == 0? moveLeft(): wait()
        } //todo da finire sta merda

        // controllo verticale di dove si trova il giocatore, salta di conseguenza
        if (this.getY() < gy) {
            if (getCarattere() % 2 == 0) jump();
        }
    }

    //Observer pattern
    @Override
    public void addObserver(Observer o) {

    }

    @Override
    public void notifyObservers() {

    }

    @Override
    public void deleteObserver(Observer o) {

    }
}
