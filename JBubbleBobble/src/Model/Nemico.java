package Model;

import java.awt.Point;
import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Observer;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class Nemico extends Entita implements Runnable{

    // Enumerazione delle tipologie di nemici con le relative caratteristiche
    public enum Tipologia{
        //NOME (VELOCITÀ(1-3, 4 per la modalità arrabbiato), SALTO(1-3), MOSSE, ATTACCO)
        ZENCHAN("zen-chan",5, 5, "", "contatto"),
        BANEBOU("banebou",3, 2, "", "contatto"),
        MIGHTA("mighta",2, 2, "lancia-rocce", "contatto"),
        HIDEGON("hidegons",3,1, "palle-di-fuoco","contatto"),
        PULPUL("pulpul",3, 3, "", "contatto"),
        MONSTA("monsta",3, 2, "", "contatto");

        private final String nome;
        private final int velocita;
        private final int salto;
        private final String mosse;
        private final String attacco;

        Tipologia(String nome, int velocita, int salto, String mosse, String attacco){
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

    private Tipologia tipologia;
    private PointItem drop;

    // campi per movimento diversificato dei nemici
    private int waitTime; // intero da 1 a 10, generato nel costruttore, usato come "ritardo"
    private int currentWaitTime;

    private boolean bubbled;
    
    private ArrayList<String> walkingSpritePath; // gli sprite da usare per l'animazione di camminata
    private ArrayList<String> bubbledSpritePath; // sprite su cui ciclare quando il nemico è stato "bollato"

    /**
     * Costruttore della classe Nemico
     * @param t la tipologia del nemico
     * @param x la posizione x del nemico
     * @param y la posizione y del nemico
     */
    public Nemico(Tipologia t, int x, int y){
        super( x, y, t.getVelocita(), t.getSalto(), -7);
        tipologia = t;
        bubbled = false;

        // generazione random del carattere del nemico
        Random rand = new Random();
        waitTime = rand.nextInt(5) + 1;

        drop = new PointItem(x, y, 0, 0, 0);

        this.walkingSpritePath = new ArrayList<>();
        switch (t){
            case ZENCHAN -> {
                this.walkingSpritePath.add("JBubbleBobble"+File.separator+"src"+File.separator+"resources"+File.separator+"sprites"+File.separator+"zen-chan"+File.separator+"image_507.png");
                this.walkingSpritePath.add("JBubbleBobble"+File.separator+"src"+File.separator+"resources"+File.separator+"sprites"+File.separator+"zen-chan"+File.separator+"image_508.png");
            }
            case BANEBOU -> {
                this.walkingSpritePath.add("JBubbleBobble"+File.separator+"src"+File.separator+"resources"+File.separator+"sprites"+File.separator+"banebou"+File.separator+"image_4.png");
            }
            case MIGHTA -> {
                this.walkingSpritePath.add("JBubbleBobble"+File.separator+"src"+File.separator+"resources"+File.separator+"sprites"+File.separator+"mighta"+File.separator+"image_39.png");
                this.walkingSpritePath.add("JBubbleBobble"+File.separator+"src"+File.separator+"resources"+File.separator+"sprites"+File.separator+"mighta"+File.separator+"image_40.png");
            }
            case HIDEGON -> {
                this.walkingSpritePath.add("JBubbleBobble"+File.separator+"src"+File.separator+"resources"+File.separator+"sprites"+File.separator+"hidegons"+File.separator+"image_40.png");
                this.walkingSpritePath.add("JBubbleBobble"+File.separator+"src"+File.separator+"resources"+File.separator+"sprites"+File.separator+"hidegons"+File.separator+"image_41.png");
            }
            case PULPUL -> {
                this.walkingSpritePath.add("JBubbleBobble"+File.separator+"src"+File.separator+"resources"+File.separator+"sprites"+File.separator+"pulpul"+File.separator+"image_407.png");
                this.walkingSpritePath.add("JBubbleBobble"+File.separator+"src"+File.separator+"resources"+File.separator+"sprites"+File.separator+"pulpul"+File.separator+"image_408.png");
            }
            case MONSTA -> {
                this.walkingSpritePath.add("JBubbleBobble"+File.separator+"src"+File.separator+"resources"+File.separator+"sprites"+File.separator+"monsta"+File.separator+"image_443.png");
                this.walkingSpritePath.add("JBubbleBobble"+File.separator+"src"+File.separator+"resources"+File.separator+"sprites"+File.separator+"monsta"+File.separator+"image_444.png");
            }
        }

    }

    public Tipologia getTipologia(){
        return tipologia;
    }
    
    public PointItem getPointItem(){
        return drop;
    }

    public boolean isBubbled(){
        return bubbled;
    }
    public void setBubbled(boolean b){this.bubbled = b;}
    public int getWaitTime() {
        return this.waitTime;
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
        super.setChanged();
        super.notifyObservers();
    }

    // metodo che gestisce il movimento di questo nemico
    // prende in input la posizione x e la posizione y del Giocatore
    public void move(int gx, int gy, Livello l) {
        // controllo orizzontale di dove si trova il giocatore, si muove a dx/sx di conseguenza
        if (this.getX() < gx) {
            if (currentWaitTime == 0){
                goingRight = true;
                moveRight(l);
                currentWaitTime = waitTime;
            } else currentWaitTime--;
        }
        if (this.getX() > gx) {
            if (currentWaitTime == 0){
                goingRight = false;
                moveLeft(l);
                currentWaitTime = waitTime;
            }else currentWaitTime--;
        }
        if (this.getY() < gy) jump();
    }

}

