package model;

import java.util.Random;

/**
 * Classe che rappresenta un generico oggetto contenente punti del gioco (PointItem).
 */
public class PointItem extends Item {

    /**
     * Enumerazione che rappresenta le possibili tipologie di oggetti contenenti punti.
     */
    public enum Tipologia{
        BANANA(500, 50), 
        PERSIMMON(1000, 40), 
        PEACH(2000, 30), 
        WATERMELON(4000,20), 
        EGGPLANT(8000,10), 
        TURNIP(16000,5), 
        DIAMOND(32000,1);

        private final int PUNTI;
        private final int DROP_RATE;

        /**
         * Costruttore della classe Tipologia.
         * @param p il numero di punti associato alla tipologia
         * @param dr il drop rate associato alla tipologia
         */
        Tipologia(int p, int dr){
            this.PUNTI = p;
            this.DROP_RATE = dr;
        }

        /**
         * Metodo che ritorna il numero di punti associato alla tipologia.
         * @return il numero di punti associato alla tipologia
         */
        public int getPunti(){return this.PUNTI;}

        /**
         * Metodo che ritorna il drop rate associato alla tipologia.
         * @return il drop rate associato alla tipologia
         */
        public int getDropRate(){return this.DROP_RATE;}
    }

    private Tipologia tipologia;
    
    /**
     * Costruttore della classe PointItem.
     * il costruttore genera randomicamente un PointItem tramite il drop rate delle tipologie
     * @param posx la coordinata x dell'oggetto contenente punti
     * @param posy la coordinata y dell'oggetto contenente punti
     */
    public PointItem(int posx, int posy) {
        super(posx, posy, 0, 0, -7);
        
        //generazione tipologia
        Random rand = new Random();
        int randomValue = rand.nextInt(50) + 1; // Genera un numero tra 1 e 100
        for (Tipologia t : Tipologia.values()) {
            if (randomValue <= t.getDropRate()) {
                tipologia = t;
            }
        }
        
        // attribuzione punti, in base alla tipologia estratta randomicamente
        this.points = tipologia.getPunti();

        // settaggio sprite 
        this.idleSpritePath +=  switch (tipologia) {
                                    case BANANA     -> "image_68.png";
                                    case PERSIMMON  -> "image_71.png";
                                    case PEACH      -> "image_59.png";
                                    case WATERMELON -> "image_70.png";
                                    case EGGPLANT      -> "image_64.png";
                                    case TURNIP  -> "image_62.png";
                                    case DIAMOND    -> "image_11.png";
                                };
    }

    /**
     * Metodo che ritorna la tipologia dell'oggetto contenente punti.
     * @return la tipologia dell'oggetto contenente punti
     */
    public Tipologia getTipologia(){
        return this.tipologia;
    }

    /**
     * Metodo che ritorna il percorso dello sprite dell'oggetto contenente punti.
     * @return stringa contenente il percorso dello sprite dell'oggetto contenente punti
     */
    @Override
    public String getSpritePath(){
        return this.idleSpritePath;
    }
}
