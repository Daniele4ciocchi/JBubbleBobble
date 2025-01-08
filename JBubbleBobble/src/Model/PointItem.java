package Model;

import java.io.File;
import java.util.Observer;
import java.util.Random;

public class PointItem extends Item {
    public enum Tipologia{
        BANANA(500, 50), 
        PERSIMMON(1000, 40), 
        PEACH(2000, 30), 
        WATERMELON(4000,20), 
        GRAPE(8000,10), 
        PINEAPPLE(16000,5), 
        DIAMOND(32000,1);

        private final int PUNTI;
        private final int DROP_RATE;

        Tipologia(int p, int dr){
            this.PUNTI = p;
            this.DROP_RATE = dr;
        }

        public int getPunti(){return this.PUNTI;}
        public int getDropRate(){return this.DROP_RATE;}
    }

    private Tipologia tipologia;

    
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
        this.dead = false;
        
        // attribuzione punti, in base alla tipologia estratta randomicamente
        this.points = tipologia.getPunti();

        // settaggio sprite 
        this.idleSpritePath +=  switch (tipologia) {
                                    case BANANA     -> "image_68.png";
                                    case PERSIMMON  -> "image_71.png";
                                    case PEACH      -> "image_59.png";
                                    case WATERMELON -> "image_70.png";
                                    case GRAPE      -> "image_69.png"; // da cambiare
                                    case DIAMOND    -> "image_1.png"; // da cambiare
                                    case PINEAPPLE  -> "image_2.png"; // da cambiare
                                    // case default -> "SPRITE VUOTO DA DECIDERE";
                                };
    }

    public Tipologia getTipologia(){
        return this.tipologia;
    }

    @Override
    public String getSpritePath(){
        return this.idleSpritePath;
    }

}
