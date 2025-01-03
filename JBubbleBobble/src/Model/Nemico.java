package Model;

import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.Observer;
import java.util.Random;
import java.util.Timer;

public class Nemico extends Personaggio implements Runnable{

    // Enumerazione delle tipologie di nemici con le relative caratteristiche
    public enum Tipologia{
        //NOME (VELOCITÀ(1-3, 4 per la modalità arrabbiato), SALTO(1-3), MOSSE, ATTACCO)
        ZENCHAN(5, 5, "", "contatto"),
        BANEBOU(3, 2, "", "contatto"),
        MIGHTA(2, 2, "lancia-rocce", "contatto"),
        HIDEGON(3,1, "palle-di-fuoco","contatto"),
        PULPUL(3, 3, "", "contatto"),
        MONSTA(3, 2, "", "contatto");

        private final int velocita;
        private final int salto;
        private final String mosse;
        private final String attacco;

        Tipologia(int velocita, int salto, String mosse, String attacco){
            this.velocita = velocita;
            this.salto = salto;
            this.mosse = mosse;
            this.attacco = attacco;
        }
        
        //getter
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
    
    private String[] bubbledSpritesPath; // animazione quando catturato in una bolla //TODO: vedere quanti sprite servono

    /**
     * Costruttore della classe Nemico
     * @param t la tipologia del nemico
     * @param x la posizione x del nemico
     * @param y la posizione y del nemico
     */
    public Nemico(Tipologia t, int x, int y){
        super(x, y, t.getVelocita(), t.getSalto(), -7);
        tipologia = t;
        bubbled = false;

        // generazione random del carattere del nemico
        Random rand = new Random();
        waitTime = rand.nextInt(5) + 1;

        drop = new PointItem(x, y, 0, 0, -5);

        // attribuzione walking sprite 
        // TODO: inserire anche gli altri sprite qui, e pensare di isolare lo switch in un metodo
        switch (t){
            case ZENCHAN -> {
                walkingSpritesPath[0] = "zen-chan"+File.separator+"image_507.png";
                walkingSpritesPath[1] = "zen-chan"+File.separator+"image_508.png";
            }
            case BANEBOU -> {
                walkingSpritesPath[0] = "banebou"+File.separator+"image_4.png";
                walkingSpritesPath[1] = "banebou"+File.separator+"image_4.png";
            }
            case MIGHTA -> {
                walkingSpritesPath[0] = "mighta"+File.separator+"image_39.png";
                walkingSpritesPath[1] = "banebou"+File.separator+"image_40.png";
            }
            case HIDEGON -> {
                walkingSpritesPath[0] = "hidegons"+File.separator+"image_40.png";
                walkingSpritesPath[1] = "banebou"+File.separator+"image_41.png";
            }
            case PULPUL -> {
                walkingSpritesPath[0] = "pulpul"+File.separator+"image_407.png";
                walkingSpritesPath[1] = "pulpul"+File.separator+"image_408.png";
            }
            case MONSTA -> {
                walkingSpritesPath[0] = "monsta"+File.separator+"image_443.png";
                walkingSpritesPath[1] = "monsta"+File.separator+"image_444.png";
            }
        }

    }

    public Tipologia getTipologia(){return tipologia;}
    public PointItem getPointItem(){return drop;}
    public boolean isBubbled(){return bubbled;}
    public void setBubbled(boolean b){this.bubbled = b;}
    public int getWaitTime() {return this.waitTime;}
    
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

    // osserva le coordinate di Giocatore, si muove di conseguenza con dei piccoli ostacoli mentali
    public void move(int gx, int gy, Livello l) {
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

