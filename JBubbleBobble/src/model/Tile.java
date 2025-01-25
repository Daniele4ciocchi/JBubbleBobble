package model;

public class Tile {
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

        TileType(boolean solid, boolean walkable){
            this.solid = solid;
            this.walkable = walkable;
        }
        public boolean isSolid(){return solid;}
        public boolean isWalkable(){return walkable;}
    }

    private TileType type;
    private int x;
    private int y;

    public Tile(TileType t, int x, int y){
        this.x = x;
        this.y = y;
        type = t;
    }

    //getters
    public TileType getType(){
        return type;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }


    //setters
    public void setType(TileType t){
        type = t;
    }


}
