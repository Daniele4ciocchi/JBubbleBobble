package model;

/**
 * Classe astratta che rappresenta una bolla generica.
 */
abstract public class Bolla extends Entita {

    private boolean floating = false;
    private Nemico nemico;
    
    /**
     * Il range della bolla
     */
    protected int range;
    
    /**
     * Il tempo di scoppio della bolla
     */
    protected int popTime = 750;

    /**
     * Costruttore della classe Bolla.
     * @param posx posizione x iniziale
     * @param posy posizione y iniziale
     * @param movimentoX velocità sull'asse x
     * @param movimentoY velocità sull'asse y
     * @param goingRight direzione iniziale
     */
    public Bolla(int posx, int posy, int movimentoX, int movimentoY, boolean goingRight) {
        super(posx/16, posy/16, movimentoX, movimentoY, 0);
        setGoingRight(goingRight);
    }

    /**
     * Costruttore della classe Bolla.
     * @param posx posizione x iniziale
     * @param posy posizione y iniziale
     * @param movimentoX velocità sull'asse x
     * @param movimentoY velocità sull'asse y
     * @param gravita gravità
     */
    public Bolla(int posx, int posy, int movimentoX, int movimentoY, int gravita) {
        super(posx/16, posy/16, movimentoX, movimentoY, gravita);
        setGoingRight(true);
    }

    /**
     * Metodo per muovere la bolla implementato concretamente nelle sottoclassi
     * @param l livello in cui si trova la bolla
     */
    public abstract void move(Livello l);

    /** 
     * Metodo che restituisce il nemico catturato nella bolla
     * @return il nemico catturato nella bolla
     */
    public Nemico getNemico(){ return this.nemico;}

    /**
     * Metodo che restituisce il booleano che indica se la bolla è fluttuante
     * @return true se la bolla è fluttuante, false altrimenti
     */
    public boolean isFloating(){return floating;}

    /**
     * Metodo che restituisce il range orizzontale dello spostamento della bolla
     * @return il range della bolla
     */
    public int getRange() {return range;}

    /**
     * Metodo che imposta il booleano per indicare che la bolla è fluttuante 
     */
    public void setFloating() {this.floating = true;}

    /**
     * Metodo che imposta il range del movimento sull'asse x della bolla
     * @param range il range della bolla
     */
    public void setRange(int range) {this.range = range;}

    /**
     * Metodo che restituisce il tempo residuo della bolla
     * @return il tempo di vita della bolla fluttuante
     */
    public int getPopTime() {return popTime;}

    /**
     * Metodo per catturare un nemico nella bolla 
     * @param nemico il nemico da catturare
     */
    public void catturaNemico(Nemico nemico) {
        if(!floating){
            this.nemico = nemico;
            this.nemico.setBubbled(true);
        }
    }

    /**
     * Metodo per far scoppiare la bolla e liberare il nemico
     * nel caso in cui ce ne sia uno catturato
     * @return il nemico liberato
     */
    public Nemico scoppia() {
        this.nemico.setBubbled(false);
        this.nemico.setPosizione(this.getX(), this.getY());
        return nemico;
    }

}
