package Model;


import java.util.ArrayList;

public class Partita {


    //TODO: da rivedere il giocatore, si può mettere per primo nell'array ed eliminare il resto

    private ArrayList<Entita> entitaAttive; //lista delle entità presenti nella partita
    private ArrayList<Entita> entitaMorte;

    //counter per poter ottenere determinati powerUp
    private int saltiEffettuati;
    private int punteggio;

    private boolean vinta;
    Livello livello;
    Giocatore giocatore;

    /**
     * Costruttore della classe Partita
     * @param livello il livello della partita
     */
    public Partita(int livello){
        this.giocatore = new Giocatore();
        this.entitaAttive = new ArrayList<>();
        this.entitaMorte = new ArrayList<>();
        this.livello = new Livello(livello);

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
    public void svuotaEntita(){this.entitaAttive.clear();} // TODO: da rivedere

    public void addPunteggio(int n){punteggio += n;}

    public void checkCollision(){
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
                        case "PLAYER" -> this.addEntita(giocatore);
                    }
                }
            }
        }
    }
}