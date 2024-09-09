package Model;

import Controller.NemicoController;

import java.util.Observer;

public class Nemico extends Entita{

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

    private final TipologiaNemico TIPOLOGIA;

    private boolean bubbled;
    private NemicoController controller;

    // COSTRUTTORE
    public Nemico(TipologiaNemico t, int x, int y){
        TIPOLOGIA = t;
        bubbled = false;
        setPosizione(x, y);
    }

    //metodi getter
    // Restituisce la Tipologia di questo nemico
    public TipologiaNemico getTipologia(){
        return TIPOLOGIA;
    }

    // Restituisce true se il nemico è in una bolla, false altrimenti
    public boolean isBubbled(){
        return bubbled;
    }
    public void setBubbled(){this.bubbled = true;}

    // Switcha lo stato di bubbled da true a false, o viceversa
    public void toggleBubbled(){
        bubbled = !bubbled;
    }

    //movimento del nemico
    public void moveLeft(){
        setPosizione(getPosX()-TIPOLOGIA.getVelocita(), getPosY());
    }
    public void moveRight(){
        setPosizione(getPosX()+TIPOLOGIA.getVelocita(), getPosY());
    }
    public void salto(){
        setPosizione(getPosX(), getPosY()-TIPOLOGIA.getSalto());
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
