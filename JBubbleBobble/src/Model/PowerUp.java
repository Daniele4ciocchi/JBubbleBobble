package Model;

import java.util.Observer;

public class PowerUp extends Entita{

    public enum TipologiaPowerUp{
        //caramelle (bolle), ampiezza lancio(a-l), velocità bolla(v-b), velocità lancio(v-l),
        //ombrelli (livelli), numero di livelli da skippare
        //anelli(punti),
        //lanterne
        //  -blu    (tutti gli anelli),
        //  -rossa  (tutte le caramelle e tutti gli anelli)
        //  -rosa   (6000 punti + nemici trasformati in diamanti)
        //  -gialla (tutte le caramelle)

        //tipo, colore, a-l, v-b, v-l, livelli, punti

        //caramelle
        CROSA("caramella", "rosa", 1, 0, 0, 0, 0),
        CBLU("caramella", "blu", 0, 1, 0, 0, 0),
        CGiALLA("caramella", "gialla", 0, 0, 1, 0, 0),
        //ombrelli
        OARANCIO("ombrello", "arancione", 0, 0, 0, 3, 0),
        OROSA("ombrello", "rosa", 0, 0, 0, 5, 0),
        OROSSO("ombrello", "rosso", 0, 0, 0, 7, 0),
        //anelli TODO: condizioni per i punti
        AROSA("anello", "rosa", 0, 0, 0, 0, 100),
        AROSSO("anello", "rosso", 0, 0, 0, 0, 100),
        ABLU("anello", "blu", 0, 0, 0, 0, 10),
        //lanterne TODO: LROSSA LBLU LROSA da rivedere
        LBLU("lanterna", "blu", 0, 0, 0, 0, 0),
        LROSSA("lanterna", "rossa", 1, 1, 1, 0, 0), //da rivedere
        LROSA("lanterna", "rosa", 0, 0, 0, 0, 0),
        LGIALLA("lanterna", "gialla", 1, 1, 1, 0, 0);

        ;
        private final String tipo;
        private final String colore;
        private final int a_l;
        private final int v_b;
        private final int v_l;
        private final int livelli;
        private final int punti;

        TipologiaPowerUp (String tipo, String colore, int a_l, int v_b, int v_l, int livelli, int punti){
            this.tipo = tipo;
            this.colore = colore;
            this.a_l = a_l;
            this.v_b = v_b;
            this.v_l = v_l;
            this.livelli = livelli;
            this.punti = punti;
        }
        public String getTipo(){return this.tipo;}
        public String getColore(){return this.colore;}
        public int getA_l(){return this.a_l;}
        public int getV_b(){return this.v_b;}
        public int getV_l(){return this.v_l;}
        public int getLivelli(){return this.livelli;}
        public int getPunti(){return this.punti;}
        

    }

    private TipologiaPowerUp TIPOLOGIA;


    //Observer pattern
    @Override
    public void addObserver(Observer o) {

    }

    @Override
    public void deleteObserver(Observer o) {

    }

    @Override
    public void notifyObservers() {

    }

}
