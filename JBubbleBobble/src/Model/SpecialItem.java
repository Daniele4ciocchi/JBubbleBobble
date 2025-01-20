package Model;

import java.io.File;
import java.util.Observer;

/*  INFO: https://strategywiki.org/wiki/Bubble_Bobble/Special_items
        - caramelle(CANDY)(1000punti): modificano le bolle o il lancio delle bolle
            1 - rosa: giocatore spara 35 bolle      ->  le bolle vanno più lontano
            2 - blu:  giocatore scoppia 35 bolle    ->  le bolle vanno più veloci
            3 - gialle: il giocatore salta 35 volte ->  le bolle vengono lanciate più velocemente
        - anelli(RING)(1000punti): danno punti
            7 - rosa: giocatore mangia 3 caramelle rosa   -> i salti danno 500 punti
            8 - rosso: giocatore mangia 3 caramelle rosse -> le bolle sparate danno 100 punti
            9 - blu: giocatore mangia 3 caramelle blu     -> spostarsi di un pixel da 10 punti
        - croci(CROSS)(3000):
            4 - blu: Collect 15 special items -> skip 3 lvl
            5 - gialla: Collect 25 point items -> skip 5 lvl
        - 6, scarpa(SNEAKER)(100): Run across the full length of the screen 15 times. -> +velocita, salto, gravita
*/
public class SpecialItem extends Item {
    public enum Tipologia{
        CANDY(1000),
        RING(1000), 
        SNEAKER(100),
        UMBRELLA(200),
        CLOCK(200);

        private final int PUNTI;

        Tipologia(int p){
            this.PUNTI = p;
        }
    }

    public enum Colore{
        PINK, BLUE, YELLOW, ORANGE, RED, EMPTY
    }

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

        NULL
    }

    private Tipologia tipologia;
    private Colore colore;
    private Effetto effetto;

    public SpecialItem(int posx, int posy, Tipologia tipologia, Colore colore){
        super(posx, posy, 0, 0, -3);
        this.tipologia = tipologia;
        this.colore = colore; //NB: EMPTY se voglio sneaker!

        this.effetto = switch (this.tipologia) {
            case CANDY -> switch (this.colore) {
                case PINK   -> Effetto.BOLLE_RANGE_UP;      // 1
                case BLUE    -> Effetto.BOLLE_VEL_UP;       // 2
                case YELLOW -> Effetto.BOLLE_FIRERATE_UP;   // 3
                default -> Effetto.NULL;
            };
            case RING -> switch (this.colore) {
                case PINK   -> Effetto.BONUS_SALTO;         // 4
                case RED  -> Effetto.BONUS_SPARO;           // 5
                case BLUE    -> Effetto.BONUS_MOV;          // 6
                default     -> Effetto.NULL;
            };
            case SNEAKER ->  Effetto.SNEAKER_BUFF;          // 7
            case UMBRELLA -> switch (this.colore) {
                case ORANGE -> Effetto.SKIP_LVL3;           // 8
                case RED     -> Effetto.SKIP_LVL5;          // 9
                case PINK    -> Effetto.SKIP_LVL7;          // 10
                default -> Effetto.NULL;
            };
            case CLOCK -> Effetto.FREEZE;                   // 11
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
                case SNEAKER      -> "sneaker.png";       // SNEAKER
                case CLOCK      -> "clock.png";           // CLOCK
                // case 
            };
            this.points = tipologia.PUNTI;
            
    }
    
    public Tipologia getTipologia(){
        return this.tipologia;
    }

    public Colore getColore(){
        return this.colore;
    }

    public Effetto getEffetto(){
        return this.effetto;
    }

    @Override
    public String getSpritePath(){
        return this.idleSpritePath;
    }
}
