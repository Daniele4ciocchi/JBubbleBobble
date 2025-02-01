package model;

import java.io.File;
import java.util.ArrayList;

/**
 * Classe che rappresenta l'acqua nel gioco.
 */
public class Acqua extends Entita{

    /**
     * Classe che rappresenta una goccia d'acqua.
     */
    public class Goccia extends Entita{

        private Personaggio personaggio;
        
        /**
         * Costruttore della classe Goccia.
         * @param x la coordinata x della goccia 
         * @param y la coordinata y della goccia
         */
        private Goccia(int x, int y) {
            super(x, y, 10, 0, -4);
        }

        /**
         * Metodo che imposta il personaggio associato ad una singola goccia.
         * @param p il personaggio da associare alla goccia
         */
        public void setPersonaggio(Personaggio p) { personaggio = p;}

        /**
         * Metodo che ritorna il personaggio associato ad una goccia.
         * @return personaggio
         */
        public Personaggio getPersonaggio() { return personaggio; }
    }

    private ArrayList<Goccia> gocce = new ArrayList<Goccia>();
    private boolean goingRight = true;
    private int lunghezza = 20;

    /**
     * Costruttore della classe Acqua.
     * @param x la coordinata x dell'acqua
     * @param y la coordinata y dell'acqua
     */
    public Acqua(int x, int y) {
        super(x/16, y/16, 1, 0, 0);
        for (int i = 0; i < lunghezza; i++) {
            gocce.add(new Goccia(x/16, y/16));
            
        }
    }

    /**
     * Tramite questo metodo viene mossa ogni singola goccia in base
     * alla posizione della goccia precedente.
     * Si muove il corpo dell'acqua.
     */
    public void moveBody() {
        for (int i = lunghezza - 1; i >= 1; i--) {
            Goccia prec = gocce.get(i-1);
            gocce.get(i).setPosizione(prec.getX(), prec.getY());
            
        }
    }
    
    /**
     * Metodo che muove la testa dell'acqua, ovvero la prima goccia.
     * @param l il livello in cui si muove l'acqua
     */
    public void move(Livello l) {
        Goccia testa = gocce.get(0);
        
        if (!l.isTPEntry(testa.getX(), testa.getY())){
            if (l.isEmpty(testa.getX(), testa.getY() - 10)) {
                testa.setPosizione(testa.getX(), testa.getY() - 10);

            }else if (goingRight) {
                if (l.isEmpty(testa.getX() + testa.getMovimentoX(), testa.getY())){
                    testa.setPosizione(testa.getX() + testa.getMovimentoX(), testa.getY());
                }else {goingRight = false;}

            }else if (!goingRight) {
                if (l.isEmpty(testa.getX() - testa.getMovimentoX(), testa.getY())){ 
                    testa.setPosizione(testa.getX() - testa.getMovimentoX(), testa.getY());
                }else {goingRight = true;}
            }
        }
        setPosizione(gocce.get(lunghezza-1).getX(), gocce.get(lunghezza-1).getY());
        moveBody();

        setChanged();
        notifyObservers();
    }

    /**
     * Metodo che ritorna l'ArrayList delle gocce d'acqua.
     * @return ArrayList delle gocce
     */
    public ArrayList<Goccia> getGocce() {
        return gocce;
    }

    /**
     * Metodo che restituisce il percorso dello sprite dell'acqua.
     * @return il percorso dello sprite dell'acqua
     */    
    public String getSpritePath() {
        return "JBubbleBobble" + File.separator + "src" + File.separator + "resources" + File.separator +  "blocks" + File.separator + "super blocks" + File.separator + "block_98.png";
    }
}