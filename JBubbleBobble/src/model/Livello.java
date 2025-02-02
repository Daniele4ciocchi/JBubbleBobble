package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Classe che rappresenta un livello del gioco.
 */
public class Livello {

    private int levelNum = 1;
    private int tilesize = 16;
    private Tile[][] grid;

    /**
     * Costruttore della classe Livello.
     * @param livello numero del livello da costruire, che verrà utilizzato per evincere la disposizione della griglia che lo compone
     */
    public Livello(int livello){
            this.levelNum = livello;
            costruisciGrid();
    }

    /**
     * Metodo che restituisce il numero attuale del livello.
     * @return numero del livello
     */
    public int getLevelNum() {
        return levelNum;
    }

    /**
     * Metodo che restituisce la griglia del livello, ossia una matrice bidimensionale di oggetti Tile.
     * @return griglia del livello
     */
    public Tile[][] getGrid() {
        return grid;
    }

    /**
     * Metodo che restituisce il percorso dello sprite del blocco del livello.
     * @return percorso dello sprite del blocco del livello
     */
    public String getTilePath() {
        return "JBubbleBobble" + File.separator + "src" 
            + File.separator + "resources" 
            + File.separator + "blocks" 
            + File.separator + "normal blocks" 
            + File.separator + "block_" 
            + levelNum + ".png";
    }

    /**
     * Metodo che restituisce la dimensione di un singolo blocco del livello. 
     * Si tratta di un singolo intero poiché i blocchi sono quadrati.
     * @return dimensione di un blocco del livello
     */
    public int getTilesize() {
        return tilesize;
    }

    /**
     * Incrementa il numero del livello di 1.
     */
    public void changeLevel() {
        this.levelNum++;
        costruisciGrid();
    }

    /**
     * Imposta il numero del livello a quello passato come parametro.
     * @param levelNum nuovo numero livello
     */
    public void changeLevel(int levelNum) {
        this.levelNum = levelNum;
        costruisciGrid();
    }

    /**
     * Metodo che costruisce la griglia del livello.
     * Considerando il numero del livello, compone autonomamente il filepath al file testuale che rappresenta la griglia del livello e la legge,
     * in base ai numeri/caratteri presenti nel file, ne costruisce muri, piattaforme, nemici e altro.
     */
    public void costruisciGrid() {
        grid = new Tile[26][36];
        int i = 0;
        try {
            File f = new File("JBubbleBobble" + File.separator + "src" + File.separator + "model" + File.separator + "data" + File.separator + "levels" + File.separator + levelNum + ".txt");
            Scanner myReader = new Scanner(f);
            while (myReader.hasNextLine()) {
                String nextToken = myReader.next();
                if (i >= 26){
                    break;
                }
                for (int j = 0; j < 36; j++) {
                    grid[25-i][j] = new Tile(switch (nextToken.charAt(j)) {
                        case '0' -> Tile.TileType.EMPTY;
                        case '1' -> Tile.TileType.WALL;
                        case '2' -> Tile.TileType.PLATFORM;
                        case '3' -> Tile.TileType.TP_ENTRY;
                        case '4' -> Tile.TileType.TP_EXIT;
                        case '5' -> Tile.TileType.WATER;
                        case '6' -> Tile.TileType.THUNDER;

                        case 'Z' -> Tile.TileType.ZENCHAN_SPAWN;
                        case 'B' -> Tile.TileType.BANEBOU_SPAWN;
                        case 'M' -> Tile.TileType.MIGHTA_SPAWN;
                        case 'H' -> Tile.TileType.HIDEGON_SPAWN;
                        case 'P' -> Tile.TileType.PULPUL_SPAWN;
                        case 'O' -> Tile.TileType.MONSTA_SPAWN;

                        default -> throw new IllegalArgumentException("Token non valido: " + nextToken);
                    }, i, j);
                }
                i++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("file mancante");
        }
    }

    /**
     * Metodo che indica se una determinata posizione della griglia è percorribile o meno.
     * @param x coordinata x della posizione da controllare
     * @param y coordinata y della posizione da controllare
     * @return booleano che indica se quel determinato Tile è percorribile o meno
     */
    public boolean isWalkable(int x, int y) {
        int tileX = x / tilesize;
        int tileY = y / tilesize;
        return grid[tileY][tileX].getType().isWalkable();
    }

    /**
     * Metodo che indica se una determinata posizione della griglia è solida o meno.
     * Solo i muri sono indicati come "solidi".
     * @param x coordinata x della posizione da controllare
     * @param y coordinata y della posizione da controllare
     * @return booleano che indica se quel determinato Tile è solido o meno
     */
    public boolean isSolid(int x, int y) {
        int tileX = x / tilesize;
        int tileY = y / tilesize;
        return grid[tileY][tileX].getType().isSolid();
    }

    /**
     * Metodo che indica se una determinata posizione della griglia è vuota o meno.
     * @param x coordinata x della posizione da controllare
     * @param y coordinata y della posizione da controllare
     * @return booleano che indica se quel determinato Tile è vuoto o meno
     */
    public boolean isEmpty(int x, int y) {
        int tileX = x / tilesize;
        int tileY = y / tilesize;
        return !(grid[tileY][tileX].getType().isSolid() || grid[tileY][tileX].getType().isWalkable());
    }

    /**
     * Metodo che indica se una determinata posizione della griglia è l'uscita di un "teletrasporto" o meno.
     * Con ciò è inteso un punto in cui le entità compaiono cadendo dall'alto. (in sintesi, l'effetto "pacman" verticale di BubbleBobble)
     * @param x coordinata x della posizione da controllare
     * @param y coordinata y della posizione da controllare
     * @return booleano che indica se quel determinato Tile è l'uscita di un teletrasporto o meno
     */
    public boolean isTPEntry(int x, int y) {
        int tileX = x / tilesize;
        int tileY = y / tilesize;
        return grid[tileY][tileX].getType() == Tile.TileType.TP_ENTRY;
    }

    /**
     * Metodo che indica se una determinata posizione della griglia è l'entrata di un "teletrasporto" o meno.
     * Con ciò è inteso un punto in cui le entità possono cadere dal livello (in sintesi, l'effetto "pacman" verticale di BubbleBobble)
     * @param x coordinata x della posizione da controllare
     * @param y coordinata y della posizione da controllare
     * @return booleano che indica se quel determinato Tile è un l'entrata di un teletrasporto o meno
     */
    public boolean isTPExit(int x, int y) {
        int tileX = x / tilesize;
        int tileY = y / tilesize;
        return grid[tileY][tileX].getType() == Tile.TileType.TP_EXIT;
    }
}
