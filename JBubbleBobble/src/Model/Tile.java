package Model;

public class Tile {
    public enum TileType {

        EMPTY(false, false),
        PLATFORM(false, false),
        WALL(false, false);

        private boolean solid;
        private boolean walkable;

        TileType(boolean solid, boolean walkable){
            this.solid = solid;
            this.walkable = walkable;
        }

        public boolean isSolid() {
            return solid;
        }

        public boolean isWalkable() {
            return walkable;
        }
    }

    private TileType type;
    private boolean playerspawn;
    private boolean enemyspawn;
    private boolean powerupspawn;

    public Tile(TileType t){
        type = t;
    }

    //getters
    public TileType getType(){
        return type;
    }


    //setters
    public void setType(TileType t){
        type = t;
    }


}
