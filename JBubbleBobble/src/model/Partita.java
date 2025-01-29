package model;


import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import controller.AudioManager;

public class Partita implements Serializable{
    public enum Stato{
        IN_CORSO, PERSA, VINTA
    }

    private Stato stato;

    private Random random = new Random();
    private static final long serialVersionUID = 1L; // serve per il Serializable

    private ArrayList<Entita> entitaAttive; //lista delle entità presenti nella partita
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

    // campi dei buff dei powerup, per info guarda in PowerUp.java
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

        this.saltiEffettuati = 0;
        this.stato = Stato.IN_CORSO;
    }

    /**
     * Costruttore 2 della classe Partita,
     * associato alla funzione di Continua Partita
     * @param password password relativa al livello interessato
     */
    public Partita(String password){
        this.entitaAttive = new ArrayList<>();
        this.entitaMorte = new ArrayList<>();
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

        this.saltiEffettuati = 0; 
        this.stato = Stato.IN_CORSO;
    }

    // getters
    public ArrayList<Entita> getEntita(){return this.entitaAttive;}
    public ArrayList<Entita> getEntitaMorte(){return this.entitaMorte;}
    public int getSaltiEffettuati(){return this.saltiEffettuati;}
    public int getScore(){return this.score;}
    public Livello getLivello(){return this.livello;}
    public int getFreeze(){return FREEZE;}
    public boolean getSkip3(){return SKIP3;}
    public boolean getSkip5(){return SKIP5;}
    public boolean getSkip7(){return SKIP7;}
    public int getItemRaccolti(){return itemRaccolti;}
    public boolean getChacknHeart(){return chacknheart;}
    public Stato getStato(){return this.stato;}

    // metodi per le variabili di statistica per gli specialItem
    public void addSaltoEffettuato(){this.saltiEffettuati++;}
    public void addBollaSparata(){this.bolleSparate++;}
    public void addBollaScoppiata(){this.bolleScoppiate++;}
    public void addBollaFulmineScoppiata(){this.bolleFulmineScoppiate++;}
    public void addBollaAcquaScoppiata(){this.bolleAcquaScoppiate++;}
    public void addCaramellaRosaMangiata(){this.caramelleRosaMangiate++;}
    public void addCaramellaGialleMangiata(){this.caramelleGialleMangiate++;}
    public void addCaramellaBluMangiata(){this.caramelleBluMangiate++;}
    public void addItemRaccolto(){this.itemRaccolti++;}

    public void setFreeze(int b){FREEZE = b;}
    public void setSkip3(boolean b){SKIP3 = b;}
    public void setSkip5(boolean b){SKIP5 = b;}
    public void setSkip7(boolean b){SKIP7 = b;}
    public void setChacknHeart(boolean b){chacknheart = b;}
    public void setStato(Stato s){this.stato = s;}

    public void addEntita(Entita entita){this.entitaAttive.add(entita);}
    public void removeEntita(Entita entita) {
        this.entitaAttive.remove(entita);
        this.entitaMorte.add(entita);
    }
    public void svuotaEntita(){
        this.entitaAttive.removeIf(x -> !x.equals(entitaAttive.getFirst()));
    }

    public void addScore(int n){score += n;}

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

    // ======================= SPECIALITEMS (powerups) =======================
    // qui controllo i requisiti di spawn
    // NOTA: i controlli dei valori sono così per assicurare che venga creato un SOLO powerup
    public SpecialItem checkSpawnSpecialItem(){

        int sx = 0;
        int sy = 0;

        while(!livello.isEmpty(sx*16,sy*16)){
            sx = random.nextInt(34);
            sy = random.nextInt(24);
        }

        // UMBRELLA
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

        // CANDY
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

        // RING
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

        // CLOCK
        else if (bolleFulmineScoppiate == 6){
            bolleFulmineScoppiate = 0;
            return new SpecialItem(sx,sy,SpecialItem.Tipologia.CLOCK,SpecialItem.Colore.EMPTY);
        }

        // UMBRELLA
        else if (bolleAcquaScoppiate == 5){ //DOVREBBE ESSERE 5, ma 1 per debug facile
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
        
        // SNEAKER
        else if (((Giocatore)entitaAttive.getFirst()).getPassi() == 32*16*15){
            ((Giocatore)entitaAttive.getFirst()).setPassi(0);
            return new SpecialItem(sx,sy,SpecialItem.Tipologia.SNEAKER,SpecialItem.Colore.EMPTY);
        }
            
        // CHACKNHEART
        else if (itemRaccolti == 22){
            itemRaccolti = 0;
            return new SpecialItem(sx,sy,SpecialItem.Tipologia.CHACKNHEART,SpecialItem.Colore.EMPTY);
        }

        else{
            return null;
        }
    }

    public void gravita(Entita e) {    
        if ((getChacknHeart()||FREEZE>0) && e instanceof Nemico) return; 
        if (e instanceof Monsta || e instanceof Pulpul) return; // non applico mai la gravita a Monsta e Pulpul

        if (livello.isTPEntry(e.getX(),e.getY())){
            e.setPosizione(e.getX(),24*Entita.getEntitysize());
        }

        if (!livello.isWalkable(e.getX(), e.getY() - 1 )){
            if(!livello.isWalkable(e.getX(), e.getY() + e.getGravita()))e.setPosizione(e.getX(),e.getY() + e.getGravita());
            else e.setPosizione(e.getX(),e.getY() - 1 );
        }

        if (e.getMovimentoY()>0){
            if (e instanceof Giocatore) ((Giocatore)e).setFalling(true); // per lo sprite
            e.setPosizione(e.getX(),e.getY() + e.getMovimentoY());
            e.setMovimentoY(e.getMovimentoY()-1);
            if (livello.isSolid(e.getX(),e.getY() + e.getMovimentoY())) e.setMovimentoY(0);
            if (livello.isTPExit(e.getX(),e.getY() + e.getMovimentoY())) e.setMovimentoY(0);
        }

        if (e instanceof Giocatore && (livello.isSolid(e.getX(), e.getY()-1)||livello.isWalkable(e.getX(), e.getY()-1))) ((Giocatore)e).setFalling(false); // per lo sprite
    }

    // powerup raccolto! metodo che si occupa di applicarne gli effetti
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

    // aggiunge questa partita allo storico partite del giocatore
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
            bw.write(Profilo.getInstance().getNickname() + ":" + score + ":" + this.stato + ":" + Profilo.getInstance().getAvatar() + "\n");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        

       
    }
}