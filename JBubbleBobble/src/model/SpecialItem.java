package model;

import java.io.File;

/**
 * Classe che rappresenta un oggetto speciale del gioco (SpecialItem).
 */
public class SpecialItem extends Item {

    /**
     * Enumerazione che rappresenta le possibili tipologie di oggetti speciali.
     */
    public enum Tipologia{
        CANDY(1000),
        RING(1000), 
        SNEAKER(100),
        UMBRELLA(200),
        CLOCK(200),
        CHACKNHEART(3000);

        private final int PUNTI;

        /**
         * Costruttore della classe Tipologia.
         * @param p il numero di punti associato alla tipologia
         */
        Tipologia(int p){
            this.PUNTI = p;
        }
    }

    /**
     * Enumerazione che rappresenta i possibili colori degli oggetti speciali.
     */
    public enum Colore{
        PINK, BLUE, YELLOW, ORANGE, RED, EMPTY
    }

    /**
     * Enumerazione che rappresenta i possibili effetti degli oggetti speciali.
     */
    public enum Effetto{
        BOLLE_RANGE_UP,     // aumento del range delle bolle
        BOLLE_VEL_UP,       // aumento della velocità delle bolle
        BOLLE_FIRERATE_UP,  // aumento della velocità di fuoco del giocatore

        BONUS_MOV,          // 10 punti per ogni pixel di movimento (da capire come implementarla)
        BONUS_SALTO,        // 500 punti per salto
        BONUS_SPARO,        // 100 punti per sparo

        SNEAKER_BUFF,       // +velocita, salto, gravita

        SKIP_LVL3,          // skippa 3 livelli
        SKIP_LVL5,          // skippa 5 livelli
        SKIP_LVL7,          // skippa 7 livelli

        FREEZE,             // blocca i nemici per un po' di tempo

        CHACKNHEART,        // giocatore invincibile(?) + nemici freezati + kill al contatto

        NULL
    }

    private Tipologia tipologia;
    private Colore colore;
    private Effetto effetto;

    /**
     * Costruttore della classe SpecialItem.
     * Viene generato uno SpecialItem e viene impopstato il suo path in base alla tipologia e al colore.
     * @param posx la coordinata x dell'oggetto speciale
     * @param posy la coordinata y dell'oggetto speciale
     * @param tipologia la tipologia dell'oggetto speciale
     * @param colore il colore dell'oggetto speciale
     */
    public SpecialItem(int posx, int posy, Tipologia tipologia, Colore colore){
        super(posx, posy, 0, 0, -3);
        this.tipologia = tipologia;
        this.colore = colore; //NB: EMPTY se voglio sneaker

        this.effetto = switch (this.tipologia) {
            case CANDY -> switch (this.colore) {
                case PINK   -> Effetto.BOLLE_RANGE_UP;      
                case BLUE    -> Effetto.BOLLE_VEL_UP;       
                case YELLOW -> Effetto.BOLLE_FIRERATE_UP;   
                default -> Effetto.NULL;
            };
            case RING -> switch (this.colore) {
                case PINK   -> Effetto.BONUS_SALTO;         
                case RED  -> Effetto.BONUS_SPARO;           
                case BLUE    -> Effetto.BONUS_MOV;          
                default     -> Effetto.NULL;
            };
            case SNEAKER ->  Effetto.SNEAKER_BUFF;          
            case UMBRELLA -> switch (this.colore) {
                case ORANGE -> Effetto.SKIP_LVL3;           
                case RED     -> Effetto.SKIP_LVL5;          
                case PINK    -> Effetto.SKIP_LVL7;          
                default -> Effetto.NULL;
            };
            case CLOCK -> Effetto.FREEZE;                   
            case CHACKNHEART -> Effetto.CHACKNHEART;
            default -> Effetto.NULL;
        };

        super.idleSpritePath = baseSpritePath + "items" + File.separator 
            + switch (tipologia) {
                case CANDY  -> switch(colore){
                    case PINK       -> "pink_candy.png";   // PINK CANDY
                    case BLUE       -> "blue_candy.png";   // BLUE CANDY
                    case YELLOW     -> "yellow_candy.png"; // YELLOW CANDY
                    default     -> "";
                };
                case RING  -> switch(colore){
                    case PINK       -> "pink_ring.png";   // PINK RING
                    case RED        -> "red_ring.png";    // RED RING
                    case BLUE       -> "blue_ring.png";   // BLUE RING
                    default     -> "";
                };
                case UMBRELLA -> switch(colore){
                    case ORANGE     -> "image_48.png";    // ORANGE UMBRELLA
                    case RED        -> "image_47.png";    // RED UMBRELLA
                    case PINK       -> "image_46.png";    // PINK UMBRELLA
                    default     -> "";
                };
                case SNEAKER      -> "sneaker.png";         // SNEAKER
                case CLOCK        -> "clock.png";           // CLOCK
                case CHACKNHEART  -> "chacknheart.png";     // CHACKNHEART
            };
            this.points = tipologia.PUNTI;
    }
    
    /**
     * Metodo che restituisce la tipologia dell'oggetto speciale.
     * @return la tipologia dell'oggetto speciale
     */
    public Tipologia getTipologia(){
        return this.tipologia;
    }

    /**
     * Metodo che restituisce il colore dell'oggetto speciale.
     * @return il colore dell'oggetto speciale
     */
    public Colore getColore(){
        return this.colore;
    }

    /**
     * Metodo che restituisce l'effetto dell'oggetto speciale.
     * @return l'effetto dell'oggetto speciale
     */
    public Effetto getEffetto(){
        return this.effetto;
    }

    /**
     * Metodo che restituisce il percorso dello sprite dell'oggetto speciale.
     * @return il percorso dello sprite dell'oggetto speciale
     */
    @Override
    public String getSpritePath(){
        return this.idleSpritePath;
    }
}
