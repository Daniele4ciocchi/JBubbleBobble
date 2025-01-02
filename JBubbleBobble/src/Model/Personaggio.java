package Model;

public abstract class Personaggio extends Entita{
    protected String[] walkingSpritesPath = { baseSpritePath, baseSpritePath };

    public Personaggio(int posx, int posy, int velocitaX, int velocitaY, int gravita) {
        super(posx, posy, velocitaX, velocitaY, gravita);
    }
}
