package Model;

public class Tile {
    public enum TileType {
        EMPTY(0, 0),
        PLATFORM(),
        WALL();
        private boolean solid;
        private boolean walkable;


        TileType(boolean solid, boolean walkable){
            if (this==EMPTY)
        }

    }
    private TileType type;
    private boolean playerspawn;
    private boolean enemyspawn;
    private boolean powerupspawn;

    public Tile(TileType t, boolean solid, int x, int y){
        type = t;

    }

    //getters
    public TileType getType(){
        return type;
    }

    public boolean isSolid(){
        return solid;
    }


    //setters
    public void setType(TileType t){
        type = t;
    }

    public void setSolid(boolean s){
        solid = s;
    }

}
