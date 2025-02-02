package model;

import java.util.Random;

/**
 * Classe che rappresenta un generico nemico del gioco e che ne delinea caratteristiche e comportamenti condivisi tra tutte le sue sottoclassi.
 * Implementa l'interfaccia Runnable per permettere l'esecuzione di un thread dedicato per ogni nemico.
 */
public abstract class Nemico extends Personaggio implements Runnable{

    /*
     * Intero che indica il tempo di attesa prima di poter cambiare direzione, generato randomicamente nel costruttore e decrementato ad ogni ciclo di gioco.
     */
    protected int waitTime;

    /*
     * Intero che indica il tempo di attesa corrente prima di poter cambiare direzione.
     */
    protected int currentWaitTime;

    /*
     * Intero che indica il tempo di attesa successivo alla morte e precedente al posizionamento del suo drop.
     */
    protected int deathCounter = 50;

    /*
     * Indica se il nemico è stato intrappolato in una bolla del giocatore o meno.
     */
    protected boolean bubbled;

    /*
     * Array contenente i percorsi ai tre sprite, su cui ciclare per ottenere l'animazione risultante, del nemico quando è intrappolato in una bolla.
     */
    protected String[] bubbledSpritesPath = {baseSpritePath, baseSpritePath, baseSpritePath }; // 3 sprite

    /*
     * Costruttore della classe Nemico.
     */
    public Nemico(int x, int y, int velocita, int salto){
        super(x, y, velocita, salto, -3, salto);

        // generazione random del "carattere" del nemico
        Random rand = new Random();
        waitTime = rand.nextInt(5) + 1;
    }

    /*
     * Metodo che imposta lo stato di bubbled (intrappolato in una bolla) del nemico.
     * @param b true se il nemico è intrappolato in una bolla, false altrimenti.
     */
    public void setBubbled(boolean b) {
        this.bubbled = b;
    }

    /*
     * Metodo che ritorna lo stato di bubbled (intrappolato in una bolla) del nemico.
     * @return true se il nemico è intrappolato in una bolla, false altrimenti.
     */
    public boolean isBubbled() {
        return bubbled;
    }

    /*
     * Metodo che ritorna il tempo di attesa corrente prima di poter cambiare direzione.
     * @return tempo di attesa corrente
     */
    public int getWaitTime() {
        return this.waitTime;
    }

    /*
     * Metodo che ritorna il tempo di attesa corrente prima di poter cambiare direzione.
     * @return tempo di attesa corrente
     */
    public int getDeathCounter() {
        return this.deathCounter;
    }
    
    /*
     * Metodo che inizializza il thread dedicato a questo nemico, per poterne gestire la sua logica di comportamento indipendentemente.
     */
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try { Thread.sleep(100);
            } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        }
        super.setChanged();
        super.notifyObservers();
    }

    /*
     * Metodo che muove il nemico in base alla posizione del giocatore e al livello in cui si trova.
     * Osserva le coordinate del giocatore, e si muove di conseguenza.
     * In caso in cui il nemico stia venendo travolto in un flusso d'acqua, non si muove. (perché l'acqua lo trascina)
     * @param gx coordinata x del giocatore
     * @param gy coordinata y del giocatore
     * @param l livello su cui il nemico si sta muovendo
     */
    public void move(int gx, int gy, Livello l){
        if (!watered){
            if (this.getX() < gx) {
                if (currentWaitTime == 0){
                    setGoingRight(true);
                    moveRight(l);
                    currentWaitTime = waitTime;
                } else currentWaitTime--;
            }
            if (this.getX() > gx) {
                if (currentWaitTime == 0){
                    setGoingRight(false);
                    moveLeft(l);
                    currentWaitTime = waitTime;
                } else currentWaitTime--;
            }
            if (this.getY()+32 < gy && l.isWalkable(this.getX(), this.getY()-1)) jump();
        }
    }

    /*
     * Metodo che restituisce il percorso allo sprite relativo allo stato attuale del nemico.
     * @return percorso allo sprite relativo allo stato attuale del nemico, sotto forma di stringa
     */
    public String getSpritePath(){
        if (isDead()) return deathSpritePath; // MORTE (score ottenuto, es: 500!)
        if (bubbled) {                    // BUBBLED
            spriteCounter++;
            if (spriteCounter == spriteChangeRate) {
                spriteCounter = 0;
                spriteIndex = (spriteIndex+1) % 3;
            }
            return bubbledSpritesPath[spriteIndex%3];
        }
        if (super.getMovimentoX() == 0 && super.getMovimentoY() == 0) return idleSpritePath; // FERMO
        else {                                     // WALKING
            spriteCounter++;
            if (spriteCounter == spriteChangeRate) {
                spriteCounter = 0; 
                spriteIndex = (spriteIndex+1) % 2;
            }
            return walkingSpritesPath[spriteIndex%2];
        }
    }

    /*
     * Metodo che decrementa il contatore di morte del nemico, e lo fa effettivamente morire se il contatore raggiunge lo 0.
     * @return il punto in cui il nemico è morto, null altrimenti
     */
    public PointItem dying(){
        deathCounter--;
        die();
        return deathCounter == 0?  new PointItem(getX()/16,getY()/16):null;
    }
}

