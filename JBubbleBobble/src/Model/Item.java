package Model;

import java.io.File;

public abstract class Item extends Entita {
    protected int points;

    public Item(int posx, int posy, int velocitaX, int velocitaY, int gravita) {
        super(posx, posy, velocitaX, velocitaY, gravita);
        idleSpritePath += "items" + File.separator;
        deathSpritePath += "items" + File.separator;
    }
    
    public String getSpritePath(){
        if (this.isDead()) return this.idleSpritePath;
        else return this.deathSpritePath;
    }

    public int getPoints() {
        return points;
    }
}
