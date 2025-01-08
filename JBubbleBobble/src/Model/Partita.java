package Model;


import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public class Partita implements Serializable{
    private static final long serialVersionUID = 1L; // serve per il Serializable

    private ArrayList<Entita> entitaAttive; //lista delle entità presenti nella partita
    private ArrayList<Entita> entitaMorte;


    private boolean vinta;
    private Livello livello;

    private int score;
    private int nemiciUccisi;
    private int saltiEffettuati;
    private int bolleSparate;
    private int bolleScoppiate;
    private int bolleAcquaScoppiate;
    private int caramelleRosaMangiate;
    private int caramelleRosseMangiate;
    private int caramelleBluMangiate;

    // campi dei buff dei powerup, per info guarda in PowerUp.java
    private boolean BOLLE_RANGE_UP;
    private boolean BOLLE_VEL_UP;
    private boolean BOLLE_FIRERATE_UP;
    private boolean BONUS_MOV;
    private boolean BONUS_SALTO;
    private boolean BONUS_SPARO;
    private boolean SNEAKER_BUFF;

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
        this.vinta = false;
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
            default -> 1;
        });
        this.saltiEffettuati = 0; 
        this.vinta = false;
    }

    // getters
    public ArrayList<Entita> getEntita(){return this.entitaAttive;}
    public ArrayList<Entita> getEntitaMorte(){return this.entitaMorte;}
    public int getSaltiEffettuati(){return this.saltiEffettuati;}
    public int getScore(){return this.score;}
    public Livello getLivello(){return this.livello;}
    public boolean isVinta(){return this.vinta;}

    // metodi per le variabili di statistica per gli specialItem
    public void addSaltoEffettuato(){this.saltiEffettuati++;}
    public void addNemiciUccisi(){this.nemiciUccisi++;}
    public void addBollaSparata(){this.bolleSparate++;}
    public void addBollaScoppiata(){this.bolleScoppiate++;}
    public void addBollaAcquaScoppiata(){this.bolleAcquaScoppiate++;}

    public void setVinta() {vinta = true;}

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
                        case "ZENCHAN" -> new ZenChan(j, i);
                        case "BANEBOU" -> new Banebou(i, j);
                        case "MIGHTA" -> new Mighta(i, j);
                        case "HIDEGON" -> new Hidegon(i, j);
                        case "PULPUL" -> new Pulpul(i, j);
                        case "MONSTA" -> new Monsta(i, j);
                        default -> null;
                    });
                }
            }
        }
    }

    // ======================= SPECIALITEMS (powerups) =======================
    // qui controllo i requisiti di spawn
    // NOTA: i controlli dei valori sono così per assicurare che venga creato un SOLO powerup
    private SpecialItem spawnSpecialItem(int sx, int sy){
        // UMBRELLA
         if (bolleAcquaScoppiate == 15){
            nemiciUccisi++;
            return new SpecialItem(sx,sy,0,0,0,SpecialItem.Tipologia.UMBRELLA,SpecialItem.Colore.ORANGE);
        }
        else if (bolleAcquaScoppiate == 21){
            nemiciUccisi++;
            return new SpecialItem(sx,sy,0,0,0,SpecialItem.Tipologia.UMBRELLA,SpecialItem.Colore.RED);
        }
        else if (bolleAcquaScoppiate == 36){
            nemiciUccisi = 0;
            return new SpecialItem(sx,sy,0,0,0,SpecialItem.Tipologia.UMBRELLA,SpecialItem.Colore.PINK);
            }

        // CANDY
        if (saltiEffettuati == 35){
            bolleSparate = 0;
            return new SpecialItem(sx,sy,0,0,0,SpecialItem.Tipologia.CANDY,SpecialItem.Colore.PINK);
        }
        else if (bolleSparate == 35){
            saltiEffettuati = 0;
            return new SpecialItem(sx,sy,0,0,0,SpecialItem.Tipologia.CANDY,SpecialItem.Colore.YELLOW);
        }
        else if (bolleScoppiate == 35){
            bolleScoppiate = 0;
            return new SpecialItem(sx,sy,0,0,0,SpecialItem.Tipologia.CANDY,SpecialItem.Colore.BLUE);
        }

        // RING
        else if (caramelleRosaMangiate == 3){
            caramelleRosaMangiate = 0;
            return new SpecialItem(sx,sy,0,0,0,SpecialItem.Tipologia.RING,SpecialItem.Colore.PINK);
        }
        else if (caramelleRosseMangiate == 3){
            caramelleRosseMangiate = 0;
            return new SpecialItem(sx,sy,0,0,0,SpecialItem.Tipologia.RING,SpecialItem.Colore.RED);
        }
        else if (caramelleBluMangiate == 3){
            caramelleBluMangiate = 0;
            return new SpecialItem(sx,sy,0,0,0,SpecialItem.Tipologia.RING,SpecialItem.Colore.BLUE);
        }

        else{
            return null;
        }
    }

    public void gravita(Entita e) {        
        if (livello.isTPEntry(e.getX(),e.getY())) e.setPosizione(e.getX(),24*Entita.getEntitysize());

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
    private void usePowerUp(Entita p){
        if (!(p instanceof SpecialItem || p instanceof PointItem)) return;
        else if (p instanceof PointItem) addScore(((PointItem) p).getPoints());
        else if (p instanceof SpecialItem) {
            switch (((SpecialItem) p).getEffetto()){
                case BOLLE_RANGE_UP -> BOLLE_RANGE_UP = true;
                case BOLLE_VEL_UP -> BOLLE_VEL_UP = true;
                case BOLLE_FIRERATE_UP -> BOLLE_FIRERATE_UP = true;
                case BONUS_MOV -> BONUS_MOV = true;
                case BONUS_SALTO -> BONUS_SALTO = true;
                case BONUS_SPARO -> BONUS_SPARO = true;
                case SNEAKER_BUFF -> SNEAKER_BUFF = true;
                case SKIP_LVL3 -> livello.changeLevel(livello.getLevelNum()+3); //
                case SKIP_LVL5 -> livello.changeLevel(livello.getLevelNum()+5); // TODO: controllare che vada bene perchè non lo so :)
                case SKIP_LVL7 -> livello.changeLevel(livello.getLevelNum()+7); // 
                case NULL -> System.out.println("ERRORE: Effetto SpecialItem non trovato!");
            }
        }
    }
}