package Model;


import java.awt.*;
import java.util.ArrayList;

public class Partita {


    //TODO: nothing

    private ArrayList<Entita> entitaAttive; //lista delle entità presenti nella partita
    private ArrayList<Entita> entitaMorte;

    //counter per poter ottenere determinati powerUp
    private int saltiEffettuati;
    private int punteggio;

    private boolean vinta;
    Livello livello;


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

    //metodi getter
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
                        case "ZENCHAN" -> this.addEntita(new Nemico(Nemico.TipologiaNemico.ZENCHAN, i, j));
                        case "BANEBOU" -> this.addEntita(new Nemico(Nemico.TipologiaNemico.BANEBOU, i, j));
                        case "MIGHTA" -> this.addEntita(new Nemico(Nemico.TipologiaNemico.MIGHTA, i, j));
                        case "HIDEGON" -> this.addEntita(new Nemico(Nemico.TipologiaNemico.HIDEGON, i, j));
                        case "PULPUL" -> this.addEntita(new Nemico(Nemico.TipologiaNemico.PULPUL, i, j));
                        case "MONSTA" -> this.addEntita(new Nemico(Nemico.TipologiaNemico.MONSTA, i, j));
                        case "PLAYER" -> entitaAttive.getFirst().setPosizione(i,j);
                    }
                }
            }
        }
    }
}