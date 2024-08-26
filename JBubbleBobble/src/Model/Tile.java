package Model;

public class Tile {
    private TileType type;
    private boolean solid;
    private int x, y;

    public Tile(TileType t, boolean solid, int x, int y){
        type = t;
        this.solid = solid;
        this.x = x;
        this.y = y;
    }

    //getters
    public TileType getType(){
        return type;
    }

    public boolean isSolid(){
        return solid;
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

    public void setSolid(boolean s){
        solid = s;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }


    
}
