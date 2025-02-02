package model;

/**
 * Classe che rappresenta un Tile del gioco.
 * Un Tile è un singolo blocco del livello, che può essere di diversi tipi.
 */
public class Tile {

    /**
     * Enumerazione che rappresenta i possibili tipi di Tile con le relative caratteristiche.
     */
    public enum TileType {
        // riguardanti il terreno
        EMPTY(false, false),
        PLATFORM(false,true),
        WALL(true,true),
        TP_ENTRY(false, false), // quelle nei buchi
        TP_EXIT(false, false), // quelle in alto

        //riguardanti spawn del player
        PLAYER_SPAWN(false, false),
        WATER(false, false),
        THUNDER(false, false),
        //riguardanti spawn dei powerup
        POWERUP_SPAWN(false, false),

        // NEMICI
        ZENCHAN_SPAWN(false, false),
        BANEBOU_SPAWN(false, false),
        MIGHTA_SPAWN(false, false),
        HIDEGON_SPAWN(false, false),
        PULPUL_SPAWN(false, false),
        MONSTA_SPAWN(false, false);

        private boolean solid;
        private boolean walkable;

        /**
         * Costruttore della classe TileType.
         * @param solid true se il Tile è solido, false altrimenti
         * @param walkable true se il Tile è percorribile, false altrimenti
         */
        TileType(boolean solid, boolean walkable){
            this.solid = solid;
            this.walkable = walkable;
        }

        /**
         * Metodo che ritorna true se il Tile è solido, false altrimenti.
         * @return true se il Tile è solido, false altrimenti
         */
        public boolean isSolid(){return solid;}

        /**
         * Metodo che ritorna true se il Tile è percorribile, false altrimenti.
         * @return true se il Tile è percorribile, false altrimenti
         */
        public boolean isWalkable(){return walkable;}
    }

    private TileType type;
    private int x;
    private int y;

    /**
     * Costruttore della classe Tile.
     * @param t il tipo di Tile
     * @param x la coordinata x del Tile
     * @param y la coordinata y del Tile
     */
    public Tile(TileType t, int x, int y){
        this.x = x;
        this.y = y;
        type = t;
    }

    /**
     * Metodo che ritorna il tipo di Tile.
     * @return il tipo di Tile
     */
    public TileType getType(){return type;}

    /**
     * Metodo che ritorna la coordinata x del Tile.
     * @return intero che rappresenta la coordinata x del Tile
     */
    public int getX(){return x;}

    /**
     * Metodo che ritorna la coordinata y del Tile.
     * @return intero che rappresenta la coordinata y del Tile
     */
    public int getY(){return y;}

    /**
     * Metodo che imposta il tipo di Tile.
     * @param t il tipo di Tile
     */
    public void setType(TileType t){type = t;}
}
