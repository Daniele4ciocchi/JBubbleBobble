package Model;

public class Nemico extends Entita{
    public enum TipologiaNemico{
        TIZIO, CAIO, SEMPRONIO
        //TODO: inserire le tipologie reali di nemici
    }

    private final TipologiaNemico TIPOLOGIA;
    private boolean bubbled;

    // COSTRUTTORE
    public Nemico(TipologiaNemico t){
        TIPOLOGIA = t;
        bubbled = false;
    }

    // Restituisce la Tipologia di questo nemico
    public TipologiaNemico getTipologia(){
        return TIPOLOGIA;
    }

    // Restituisce true se il nemico è in una bolla, false altrimenti
    public boolean isBubbled(){
        return bubbled;
    }

    // Switcha lo stato di bubbled da true a false, o viceversa
    public void toggleBubbled(){
        bubbled = !bubbled;
    }

}
