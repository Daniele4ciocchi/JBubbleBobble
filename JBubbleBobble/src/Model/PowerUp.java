package Model;

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
        - lampade(LANTERN)(2000punti):
            - blu:      -> effetto di tutti e 3 gli anelli
            - rossa:    -> effetto di tutte e 3 le caramelle e tutti e 3 gli anelli
            - rosa:     -> 6000 punti + tutti i nemici muoiono
            - gialla:   -> effetto di tutte 3 le caramelle

        - croci(CROSS)(3000):
            - blu: Collect 10 / 11 / 12 / 13 special items -> skip 3 lvl
            - gialla: Collect 10 / 12 / 14 / 16 point items -> skip 5 lvl
        - scarpa(SNEAKER)(100): Run across the full length of the screen 15 times. -> +velocita, salto, gravita
*/
public class PowerUp extends Entita{
    public enum Tipologia{
        CARAMELLA, OMBRELLO, ANELLO, LANTERNA
    }
    public enum Colore{
        ROSA, BLU, GIALLO, ARANCIONE, ROSSO
    }
    public enum Effetto{
        POINTS, 
        BOLLE_RANGE_UP, // aumento del range delle bolle
        BOLLE_VEL_UP,   // aumento della velocità delle bolle
        BOLLE_FIRERATE_UP, // aumento della velocità di fuoco del giocatore
        BONUS_MOV,       // 10 punti per ogni pixel di movimento (da capire come implementarla)
        BONUS_SALTO,    // 500 punti per salto
        BONUS_SPARO,    // 100 punti per sparo
        SKIP_LVL,        // skip di 3/5/7 livelli
        SNEAKER          // +velocita, salto, gravita
    }

    private Tipologia tipologia;
    private Colore colore;
    private Effetto effetto;

    public PowerUp(int posx, int posy, int velocitaX, int velocitaY, int gravita, Tipologia tipologia, Colore colore){
        super(posx, posy, velocitaX, velocitaY, gravita);
        this.tipologia = tipologia;
        this.colore = colore;
        this.effetto = switch (this.tipologia) {
            case CARAMELLA -> switch (this.colore) {
                case ROSA   -> Effetto.BOLLE_RANGE_UP;
                case BLU    -> Effetto.BOLLE_VEL_UP;
                case GIALLO -> Effetto.BOLLE_FIRERATE_UP;
                default     -> Effetto.POINTS;
            };
            case OMBRELLO -> Effetto.SKIP_LVL; // 3, 5, 7 viene deciso in GameController, controllando il colore dell'ombrello
            case ANELLO -> switch (this.colore) {
                case ROSA   -> Effetto.BONUS_SALTO;
                case ROSSO  -> Effetto.BONUS_SPARO;
                case BLU    -> Effetto.BONUS_MOV;
                default     -> Effetto.POINTS;
            };
            case LANTERNA -> Effetto.POINTS;
        };
    }
    

    // restituisce lo score del powerup come intero
    public int getPoints(){
        switch (this.tipologia){
            case CARAMELLA:
                return 1000;
            case OMBRELLO:
                return 200;
            case ANELLO:
                return 1000;
            case LANTERNA:
                return 2000;
            default:
                return 0;
        }
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
