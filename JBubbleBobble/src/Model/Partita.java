package Model;


import java.awt.*;
import java.util.ArrayList;

public class Partita {


    //TODO: nothing

    private ArrayList<Entita> entitaAttive; //lista delle entità presenti nella partita
    private ArrayList<Entita> entitaMorte;

    //counter per poter ottenere determinati powerUp
    private int punteggio;

    private boolean vinta;
    private Livello livello;

    private int score;
    private int nemiciUccisi;
    private int saltiEffettuati;
    private int bolleSparate;
    private int bolleScoppiate;
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
     * Costruttore della classe Partita,
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
     * Costruttore della classe Partita,
     * associato alla funzione di Continua Partita
     * @param password password relativa al livello interessato
     */
    public Partita(String password){
        this.entitaAttive = new ArrayList<>();
        this.entitaMorte = new ArrayList<>();
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
            default -> 1;
        });
        this.saltiEffettuati = 0; 
        this.vinta = false;
    }

    // getter
    public ArrayList<Entita> getEntita(){return this.entitaAttive;}
    public ArrayList<Entita> getEntitaMorte(){return this.entitaMorte;}

    public int getSaltiEffettuati(){return this.saltiEffettuati;}
    public int getPunteggio(){return this.punteggio;}
    public Livello getLivello(){return this.livello;}
    public boolean isVinta(){return this.vinta;}

    public void addSaltoEffettuato(){this.saltiEffettuati++;}
    public void setVinta() {vinta = true;}

    public void addEntita(Entita entita){this.entitaAttive.add(entita);}
    public void removeEntita(Entita entita) {
        this.entitaAttive.remove(entita);
        this.entitaMorte.add(entita);
    }
    public void svuotaEntita(){
        this.entitaAttive.removeIf(x -> !x.equals(entitaAttive.getFirst()));

    }

    public void addPunteggio(int n){punteggio += n;}

    public boolean checkCollision() {
        int minDistance = 10; // Minimum distance in pixels

        for (int i = 0; i < entitaAttive.size(); i++) {
            Entita e1 = entitaAttive.get(i);
            Rectangle rect1 = new Rectangle(e1.getX(), e1.getY(), e1.getWidth(), e1.getHeight());

            for (int j = i + 1; j < entitaAttive.size(); j++) {
                Entita e2 = entitaAttive.get(j);
                Rectangle rect2 = new Rectangle(e2.getX(), e2.getY(), e2.getWidth(), e2.getHeight());

                if (rect1.intersects(rect2)) {
                    int dx = Math.abs(e1.getX() - e2.getX());
                    int dy = Math.abs(e1.getY() - e2.getY());

                    if (dx < minDistance && dy < minDistance) {
                        return true;
                    }
                }
            }

        }
        return false;
    }

    public void posizionaEntita(){
        Tile[][] grid = this.livello.getGrid();
        for (int i = 0;i<26;i++){
            for (int j = 0;j<36;j++){
                if (grid[i][j].getType().toString().contains("_SPAWN")){
                    switch (grid[i][j].getType().toString().replace("_SPAWN", "")){
                        case "ZENCHAN" -> this.addEntita(new Nemico(Nemico.Tipologia.ZENCHAN, i, j));
                        case "BANEBOU" -> this.addEntita(new Nemico(Nemico.Tipologia.BANEBOU, i, j));
                        case "MIGHTA" -> this.addEntita(new Nemico(Nemico.Tipologia.MIGHTA, i, j));
                        case "HIDEGON" -> this.addEntita(new Nemico(Nemico.Tipologia.HIDEGON, i, j));
                        case "PULPUL" -> this.addEntita(new Nemico(Nemico.Tipologia.PULPUL, i, j));
                        case "MONSTA" -> this.addEntita(new Nemico(Nemico.Tipologia.MONSTA, i, j));
                    }
                }
            }
        }
    }

    // ======== POWERUPS ========

    // controllo dei requisiti degli spawn powerup
    // NOTA: i controlli dei valori sono così per assicurare che venga creato un SOLO powerup
    // NOTA: forse va usato .addEntita(), da decidere  
    private SpecialItem spawnPowerUps(int sx, int sy){
        // OMBRELLI
        /* if (nemiciUccisi == 15){
            nemiciUccisi++;
            return new SpecialItem(sx,sy,0,0,0,SpecialItem.Tipologia.OMBRELLO,SpecialItem.Colore.ARANCIONE);
        }
        else if (nemiciUccisi == 26){
            nemiciUccisi++;
            return new SpecialItem(sx,sy,0,0,0,SpecialItem.Tipologia.OMBRELLO,SpecialItem.Colore.ROSSO);
        }
        else if (nemiciUccisi == 37){
            nemiciUccisi = 0;
            return new SpecialItem(sx,sy,0,0,0,SpecialItem.Tipologia.OMBRELLO,SpecialItem.Colore.ROSA);
        } */

        // CARAMELLE
        if (saltiEffettuati == 35){
            bolleSparate = 0;
            return new SpecialItem(sx,sy,0,0,0,SpecialItem.Tipologia.CARAMELLA,SpecialItem.Colore.ROSA);
        }
        else if (bolleSparate == 35){
            saltiEffettuati = 0;
            return new SpecialItem(sx,sy,0,0,0,SpecialItem.Tipologia.CARAMELLA,SpecialItem.Colore.GIALLO);
        }
        else if (bolleScoppiate == 35){
            bolleScoppiate = 0;
            return new SpecialItem(sx,sy,0,0,0,SpecialItem.Tipologia.CARAMELLA,SpecialItem.Colore.BLU);
        }

        // ANELLI
        else if (caramelleRosaMangiate == 3){
            caramelleRosaMangiate = 0;
            return new SpecialItem(sx,sy,0,0,0,SpecialItem.Tipologia.ANELLO,SpecialItem.Colore.ROSA);
        }
        else if (caramelleRosseMangiate == 3){
            caramelleRosseMangiate = 0;
            return new SpecialItem(sx,sy,0,0,0,SpecialItem.Tipologia.ANELLO,SpecialItem.Colore.ROSSO);
        }
        else if (caramelleBluMangiate == 3){
            caramelleBluMangiate = 0;
            return new SpecialItem(sx,sy,0,0,0,SpecialItem.Tipologia.ANELLO,SpecialItem.Colore.BLU);
        }

        else{
            return null;
        }
    }

    public void applyGravity(Entita e) {
        // Incrementa velocità verticale con un limite massimo
        int velocitaMassima = 10;
        e.setMovimentoY(Math.min(e.getMovimentoY() + e.getGravita(), velocitaMassima));
    
        // Calcola la nuova posizione
        int newY = e.getY() + e.getMovimentoY();
    
        // Controlla il tipo di tile sotto l'entità
        Tile tileBelow = livello.getTile(e.getX(), newY + e.getHeight());
        if (tileBelow.getType().isWalkable() || tileBelow.getType().isSolid()) {
            e.setPosizione(e.getX(), newY); // Nessuna collisione, aggiorna la posizione
        } else {
            // Collisione con il suolo, ferma la caduta
            e.setPosizione(e.getX(), (newY / 32) * 32);
            e.setMovimentoY(0);
        }
    }

    // powerup raccolto! metodo che si occupa di applicarne gli effetti
    private void usePowerUp(Entita p){
        if (!(p instanceof SpecialItem || p instanceof PointItem)) return;
        else if (p instanceof PointItem) addPunteggio(((PointItem) p).getPoints());
        else if (p instanceof SpecialItem) {
            switch (((SpecialItem) p).getEffetto()){
                case BOLLE_RANGE_UP -> BOLLE_RANGE_UP = true;
                case BOLLE_VEL_UP -> BOLLE_VEL_UP = true;
                case BOLLE_FIRERATE_UP -> BOLLE_FIRERATE_UP = true;
                case BONUS_MOV -> BONUS_MOV = true;
                case BONUS_SALTO -> BONUS_SALTO = true;
                case BONUS_SPARO -> BONUS_SPARO = true;
                case SNEAKER_BUFF -> SNEAKER_BUFF = true;
                case NULL -> System.out.println("Effetto non trovato!");
            }
        }
    }
}