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
public class SpecialItem extends Item{
    public enum Tipologia{
        CARAMELLA(1000), ANELLO(1000), SNEAKER(100);

        private final int PUNTI;

        Tipologia(int p){
            this.PUNTI = p;
        }

    }
    public enum Colore{
        ROSA, BLU, GIALLO, ARANCIONE, ROSSO, EMPTY
    }
    public enum Effetto{
        BOLLE_RANGE_UP,     // aumento del range delle bolle
        BOLLE_VEL_UP,       // aumento della velocità delle bolle
        BOLLE_FIRERATE_UP,  // aumento della velocità di fuoco del giocatore
        BONUS_MOV,          // 10 punti per ogni pixel di movimento (da capire come implementarla)
        BONUS_SALTO,        // 500 punti per salto
        BONUS_SPARO,        // 100 punti per sparo
        SNEAKER_BUFF,       // +velocita, salto, gravita
        NULL
    }

    private Tipologia tipologia;
    private Colore colore;
    private Effetto effetto;

    public SpecialItem(int posx, int posy, int velocitaX, int velocitaY, int gravita, Tipologia tipologia, Colore colore){
        super(posx, posy, velocitaX, velocitaY, gravita);
        this.tipologia = tipologia;
        this.colore = colore;

        this.effetto = switch (this.tipologia) {
            case CARAMELLA -> switch (this.colore) {
                case ROSA   -> Effetto.BOLLE_RANGE_UP;
                case BLU    -> Effetto.BOLLE_VEL_UP;
                case GIALLO -> Effetto.BOLLE_FIRERATE_UP;
                default -> Effetto.NULL;
            };
            case ANELLO -> switch (this.colore) {
                case ROSA   -> Effetto.BONUS_SALTO;
                case ROSSO  -> Effetto.BONUS_SPARO;
                case BLU    -> Effetto.BONUS_MOV;
                default     -> Effetto.NULL;
            };
            case SNEAKER ->  Effetto.SNEAKER_BUFF;
            default -> Effetto.NULL;
        };

        super.idleSpritePath = "JBubbleBobble" + File.separator
            + "src" + File.separator 
            + "resources" + File.separator 
            + "sprites" + File.separator 
            + "items" + File.separator 
            + switch (tipologia) {
                case CARAMELLA     -> switch(colore){
                    case ROSA   -> "image_68.png";
                    case BLU    -> "image_71.png";
                    case GIALLO -> "image_59.png";
                    default     -> "";
                };
                case ANELLO  -> switch(colore){
                    case ROSA   -> "image_68.png";
                    case ROSSO  -> "image_71.png";
                    case BLU    -> "image_59.png";
                    default     -> "";
                };
                case SNEAKER      -> "";
                // case default -> "SPRITE VUOTO DA DECIDERE";
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
}
