package model;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import controller.AudioManager;

/**
 * Classe Partita, rappresenta una partita in corso
 * Contiene tutti i metodi per gestire la partita, gli items e le entità
 */
public class Partita {

    /**
     * Enumerazione Stato, rappresenta lo stato attuale della partita.
     */
    public enum Stato{

        /**
         * Stato in cui la partita è IN CORSO.
         */
        IN_CORSO,
        
        /**
         * Stato in cui la partita è stata PERSA.
         */
        PERSA, 
        
        /**
         * Stato in cui la partita è stata VINTA.
         */
        VINTA
    }

    private Stato stato;
    private Random random = new Random();
    private ArrayList<Entita> entitaAttive; 
    private ArrayList<Entita> entitaMorte;
    private Livello livello;
    private int score;
    private int saltiEffettuati;
    private int bolleSparate;
    private int bolleScoppiate;
    private int bolleAcquaScoppiate;
    private int bolleFulmineScoppiate;
    private int caramelleRosaMangiate;
    private int caramelleGialleMangiate;
    private int caramelleBluMangiate;
    private int itemRaccolti;
    private int FREEZE;
    private boolean SKIP3;
    private boolean SKIP5;
    private boolean SKIP7;
    private boolean chacknheart;

    /**
     * Costruttore 1 della classe Partita,
     * associato alla funzione di Nuova Partita
     */
    public Partita(){
        this.livello = new Livello(1);
        this.entitaAttive = new ArrayList<>();
        this.entitaMorte = new ArrayList<>();
        entitaAttive.add(new Giocatore());
        this.stato = Stato.IN_CORSO;
    }

    /**
     * Costruttore 2 della classe Partita,
     * associato alla funzione di Continua Partita
     * @param password password relativa al livello da cui si vuole iniziare la partita
     */
    public Partita(String password){
        this.entitaAttive = new ArrayList<>();
        this.entitaMorte = new ArrayList<>();
        this.stato = Stato.IN_CORSO;
        entitaAttive.add(new Giocatore());
        this.livello = new Livello(switch (password) {
            case "LIVELLO01" -> 1;
            case "LIVELLO02" -> 2;
            case "LIVELLO03" -> 3;
            case "LIVELLO04" -> 4;
            case "LIVELLO05" -> 5;
            case "LIVELLO06" -> 6;
            case "LIVELLO07" -> 7;
            case "LIVELLO08" -> 8;
            case "LIVELLO09" -> 9;
            case "LIVELLO10" -> 10;
            case "LIVELLO11" -> 11;
            case "LIVELLO12" -> 12;
            case "LIVELLO13" -> 13;
            case "LIVELLO14" -> 14;
            case "LIVELLO15" -> 15;
            case "LIVELLO16" -> 16;
            case "GAMEOVER"  -> 104;
            case "WIN!!!"    -> 105;
            default -> 1;
        });
    }

    /**
     * Metodo che ritorna la lista delle entità che al momento si trovano nel livello
     * @return ArrayList delle entità attive
     */
    public ArrayList<Entita> getEntita(){return this.entitaAttive;}

    /**
     * Metodo che ritorna la lista delle entità che sono state eliminate
     * @return ArrayList delle entità non più attive
     */
    public ArrayList<Entita> getEntitaMorte(){return this.entitaMorte;}

    /**
     * Metodo che ritorna il numero di salti effettuati
     * @return numero di salti effettuati
     */
    public int getSaltiEffettuati(){return this.saltiEffettuati;}

    /**
     * Metodo che ritorna lo score atuale della partita in corso 
     * @return score attuale
     */
    public int getScore(){return this.score;}

    /**
     * Metodo che ritorna il livello attuale della partita in corso
     * @return livello attuale
     */
    public Livello getLivello(){return this.livello;}

    /**
     * Metodo per sapere se al momento il giocatore ha il powerup "FREEZE" attivo
     * @return true se il powerup è attivo, false altrimenti
     */
    public int getFreeze(){return FREEZE;}

    /**
     * Metodo per sapere se al prossimo livello bisogna skippare 3 livelli
     * @return true se il powerup è attivo, false altrimenti
     */
    public boolean getSkip3(){return SKIP3;}

    /**
     * Metodo per sapere se al prossimo livello bisogna skippare 5 livelli
     * @return true se il powerup è attivo, false altrimenti
     */
    public boolean getSkip5(){return SKIP5;}

    /**
     * Metodo per sapere se al prossimo livello bisogna skippare 7 livelli
     * @return true se il powerup è attivo, false altrimenti
     */
    public boolean getSkip7(){return SKIP7;}

    /**
     * Metodo che ritorna il numero di item raccolti
     * @return intero che indica il numero di item raccolti
     */
    public int getItemRaccolti(){return itemRaccolti;}

    /**
     * Metodo per sapere se al momento il giocatore ha il powerup "CHACKNHEART" attivo
     * @return true se il powerup è attivo, false altrimenti
     */
    public boolean getChacknHeart(){return chacknheart;}

    /**
     * Metodo che ritorna lo stato attuale della partita (VINTA, PERSA, IN CORSO)
     * @return stato attuale della partita
     */
    public Stato getStato(){return this.stato;}

    /**
     * Metodo per incrementare il numero di salti effettuati
     */
    public void addSaltoEffettuato(){this.saltiEffettuati++;}

    /**
     * Metodo per incrementare il numero di bolle sparate
     */
    public void addBollaSparata(){this.bolleSparate++;}

    /**
     * Metodo per incrementare il numero di bolle scoppiate
     */
    public void addBollaScoppiata(){this.bolleScoppiate++;}

    /**
     * Metodo per incrementare il numero di bolle fulmine scoppiate
     */
    public void addBollaFulmineScoppiata(){this.bolleFulmineScoppiate++;}

    /**
     * Metodo per incrementare il numero di bolle acqua scoppiate
     */
    public void addBollaAcquaScoppiata(){this.bolleAcquaScoppiate++;}

    /**
     * Metodo per incrementare il numero di caramelle rosa mangiate
     */
    public void addCaramellaRosaMangiata(){this.caramelleRosaMangiate++;}

    /**
     * Metodo per incrementare il numero di caramelle gialle mangiate
     */
    public void addCaramellaGialleMangiata(){this.caramelleGialleMangiate++;}

    /**
     * Metodo per incrementare il numero di caramelle blu mangiate
     */
    public void addCaramellaBluMangiata(){this.caramelleBluMangiate++;}

    /**
     * Metodo per incrementare il numero di item raccolti
     */
    public void addItemRaccolto(){this.itemRaccolti++;}

    /**
     * Metodo per impostare il powerup "FREEZE"
     * @param b true per impostare il powerup attivo, altrimenti false
     */
    public void setFreeze(int b){FREEZE = b;}

    /**
     * Metodo per impostare il campo "SKIP3" per saltare 3 livelli
     * @param b true per impostare il powerup attivo, altrimenti false
     */
    public void setSkip3(boolean b){SKIP3 = b;}

    /**
     * Metodo per impostare il campo "SKIP5" per saltare 5 livelli
     * @param b true per impostare il powerup attivo, altrimenti false
     */
    public void setSkip5(boolean b){SKIP5 = b;}

    /**
     * Metodo per impostare il campo "SKIP7" per saltare 7 livelli
     * @param b true per impostare il powerup attivo, altrimenti false
     */
    public void setSkip7(boolean b){SKIP7 = b;}

    /**
     * Metodo per impostare il powerup "CHACKNHEART"
     * @param b true per impostare il powerup attivo, altrimenti false
     */
    public void setChacknHeart(boolean b){chacknheart = b;}

    /**
     * Metodo per impostare lo stato della partita (VINTA, PERSa, IN CORSO)
     * @param s stato della partita
     */
    public void setStato(Stato s){this.stato = s;}

    /**
     * Metodo per aggiungere un'entità alla lista delle entità attive
     * @param entita entità da aggiungere
     */
    public void addEntita(Entita entita){this.entitaAttive.add(entita);}

    /**
     * Metodo per rimuovere un'entità dalla lista delle entità attive 
     * e aggiungerla a quella delle entità non attive
     * @param entita entità da rimuovere
     */
    public void removeEntita(Entita entita) {
        this.entitaAttive.remove(entita);
        this.entitaMorte.add(entita);
    }

    /**
     * Metodo per rimuovere tutte le entità dalla lista delle entità attive eccetto il giocatore
     */
    public void svuotaEntita(){ this.entitaAttive.removeIf(x -> !x.equals(entitaAttive.getFirst()));}

    /**
     * Metodo per aggiungere punti allo score attuale
     * @param n intero da aggiungere allo score
     */
    public void addScore(int n){score += n;}

    /**
     * Metodo per controllare se una determinata Entità collide con un'altra Entità
     * @param e1 entità da controllare
     * @return entità con cui collide, null altrimenti
     */
    public Entita checkCollision(Entita e1) {
        Rectangle rect1 = new Rectangle(e1.getX(), e1.getY(), e1.getEntitysize(), e1.getEntitysize());
        for (Entita e2 : entitaAttive) {
            if (e1.equals(e2)) continue;
            Rectangle rect2 = new Rectangle(e2.getX(), e2.getY(), e2.getEntitysize(), e2.getEntitysize());
            if (rect1.intersects(rect2)) {
                return e2;
            }
        }
        return (Entita)null;
    }

    /**
     * Metodo per inserire tutti i nemici nella partita in corso al cambio di un nuovo livello
     */
    public void posizionaEntita(){
        Tile[][] grid = this.livello.getGrid();
        for (int i = 0;i<26;i++){
            for (int j = 0;j<36;j++){
                if (grid[i][j].getType().toString().contains("_SPAWN")){
                    this.addEntita(switch (grid[i][j].getType().toString().replace("_SPAWN", "")){
                        case "ZENCHAN"  -> new ZenChan(j, i);
                        case "BANEBOU"  -> new Banebou(j, i);
                        case "MIGHTA"   -> new Mighta(j, i);
                        case "HIDEGON"  -> new Hidegon(j, i);
                        case "PULPUL"   -> new Pulpul(j, i);
                        case "MONSTA"   -> new Monsta(j, i);
                        default -> null;
                    });
                }
            }
        }
    }

    /**
     * Metodo che controlla le condizioni di spawn per far apparire un determinato SpecialItem
     * @return SpecialItem da spawnare, null altrimenti
     */
    public SpecialItem checkSpawnSpecialItem(){
        int sx = 0;
        int sy = 0;

        while(!livello.isEmpty(sx*16,sy*16)){
            sx = random.nextInt(34);
            sy = random.nextInt(24);
        }

        if (bolleAcquaScoppiate == 15){
            bolleAcquaScoppiate++;
            return new SpecialItem(sx,sy,SpecialItem.Tipologia.UMBRELLA,SpecialItem.Colore.ORANGE);
        }
        else if (bolleAcquaScoppiate == 21){
            bolleAcquaScoppiate++;
            return new SpecialItem(sx,sy,SpecialItem.Tipologia.UMBRELLA,SpecialItem.Colore.RED);
        }
        else if (bolleAcquaScoppiate == 36){
            bolleAcquaScoppiate = 0;
            return new SpecialItem(sx,sy,SpecialItem.Tipologia.UMBRELLA,SpecialItem.Colore.PINK);
        }

        if (saltiEffettuati == 35){
            saltiEffettuati = 0;
            return new SpecialItem(sx,sy,SpecialItem.Tipologia.CANDY,SpecialItem.Colore.PINK);
        }
        else if (bolleSparate == 35){
            bolleSparate = 0;
            return new SpecialItem(sx,sy,SpecialItem.Tipologia.CANDY,SpecialItem.Colore.YELLOW);
        }
        else if (bolleScoppiate == 35){
            bolleScoppiate = 0;
            return new SpecialItem(sx,sy,SpecialItem.Tipologia.CANDY,SpecialItem.Colore.BLUE);
        }

        else if (caramelleRosaMangiate == 3){
            caramelleRosaMangiate = 0;
            return new SpecialItem(sx,sy,SpecialItem.Tipologia.RING,SpecialItem.Colore.PINK);
        }
        else if (caramelleGialleMangiate == 3){
            caramelleGialleMangiate = 0;
            return new SpecialItem(sx,sy,SpecialItem.Tipologia.RING,SpecialItem.Colore.RED);
        }
        else if (caramelleBluMangiate == 3){
            caramelleBluMangiate = 0;
            return new SpecialItem(sx,sy,SpecialItem.Tipologia.RING,SpecialItem.Colore.BLUE);
        }

        else if (bolleFulmineScoppiate == 6){
            bolleFulmineScoppiate = 0;
            return new SpecialItem(sx,sy,SpecialItem.Tipologia.CLOCK,SpecialItem.Colore.EMPTY);
        }

        else if (bolleAcquaScoppiate == 5){
            bolleAcquaScoppiate++;
            return new SpecialItem(sx,sy,SpecialItem.Tipologia.UMBRELLA,SpecialItem.Colore.ORANGE);
        }
        else if (bolleAcquaScoppiate == 11){
            bolleAcquaScoppiate++;
            return new SpecialItem(sx,sy,SpecialItem.Tipologia.UMBRELLA,SpecialItem.Colore.RED);
        }
        else if (bolleAcquaScoppiate == 17){
            bolleAcquaScoppiate = 0;
            return new SpecialItem(sx,sy,SpecialItem.Tipologia.UMBRELLA,SpecialItem.Colore.PINK);
        }
        
        else if (((Giocatore)entitaAttive.getFirst()).getPassi() == 32*16*15){
            ((Giocatore)entitaAttive.getFirst()).setPassi(0);
            return new SpecialItem(sx,sy,SpecialItem.Tipologia.SNEAKER,SpecialItem.Colore.EMPTY);
        }
            
        else if (itemRaccolti == 22){
            itemRaccolti = 0;
            return new SpecialItem(sx,sy,SpecialItem.Tipologia.CHACKNHEART,SpecialItem.Colore.EMPTY);
        }

        else return null;
    }

    /**
     * Metodo che applica la gravità su una determinata entità
     * @param e entità su cui applicare la gravità
     */
    public void gravita(Entita e) {    
        if ((getChacknHeart()||FREEZE>0) && e instanceof Nemico) return; 
        if (e instanceof Monsta || e instanceof Pulpul) return; 

        if (livello.isTPEntry(e.getX(),e.getY())){
            e.setPosizione(e.getX(),24*Entita.getEntitysize());
        }

        if (!livello.isWalkable(e.getX(), e.getY() - 1 )){
            if(!livello.isWalkable(e.getX(), e.getY() + e.getGravita()))e.setPosizione(e.getX(),e.getY() + e.getGravita());
            else e.setPosizione(e.getX(),e.getY() - 1 );
        }

        if (e.getMovimentoY()>0){
            if (e instanceof Giocatore) ((Giocatore)e).setFalling(true); 
            e.setPosizione(e.getX(),e.getY() + e.getMovimentoY());
            e.setMovimentoY(e.getMovimentoY()-1);
            if (livello.isSolid(e.getX(),e.getY() + e.getMovimentoY())) e.setMovimentoY(0);
            if (livello.isTPExit(e.getX(),e.getY() + e.getMovimentoY())) e.setMovimentoY(0);
        }

        if (e instanceof Giocatore && (livello.isSolid(e.getX(), e.getY()-1)||livello.isWalkable(e.getX(), e.getY()-1))) ((Giocatore)e).setFalling(false); // per lo sprite
    }

    /**
     * Metodo per utilizzare un determinato SpecialItem e applicarne l'effetto nel caso di una collisione
     * @param p SpecialItem da utilizzare
     */
    public void useSpecialItem(SpecialItem p){
        addScore(p.getPoints());
        switch (p.getEffetto()){
            case SKIP_LVL3 -> setSkip3(true);
            case SKIP_LVL5 -> setSkip5(true);
            case SKIP_LVL7 -> setSkip7(true);
            case CHACKNHEART -> {
                setChacknHeart(true);
                ((Giocatore)entitaAttive.getFirst()).addLife();
            }
            case FREEZE    -> FREEZE = 200;
            case NULL -> System.out.println("ERRORE: Effetto SpecialItem non trovato!");
            default -> ((Giocatore)entitaAttive.getFirst()).applyEffetto(p.getEffetto());
        }
    }

    /**
     * Metodo che termina la partita in corso e imposta l'esito della partita
     * inoltre salva lo stato della partita su file txt per salvarne i dati
     */
    public void end(){
        Profilo.getInstance().addPartita(this);
        AudioManager.getInstance().stopMusic();
        Profilo.setHighScore(score);
        
        try {
            File file = new File("global_scores.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(Profilo.getInstance().getNickname() + ":" + score + ":" + this.stato + "\n");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}