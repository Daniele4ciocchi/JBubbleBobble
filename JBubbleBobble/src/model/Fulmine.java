package model;

import java.io.File;

/**
 * Classe che rappresenta un fulmine emesso da una BollaFulmine
 */
public class Fulmine extends Entita{

    /**
     * Costruttore della classe Fulmine.
     * @param x coordinata x iniziale del fulmine
     * @param y coordinata y iniziale del fulmine
     * @param direction direzione del fulmine (true = destra, false = sinistra)
     */
    public Fulmine(int x, int y, boolean direction) {
        super(x/16, y/16, 7, 0, 0);
        setGoingRight(direction);
    }

    /**
     * Metodo che si occupa del movimento del fulmine.
     * @param l livello su cui il fulmine si sta muovendo
     */
    public void move(Livello l) {
       if (isGoingRight() == true)setPosizione(getX() + getMovimentoX(), getY());
       else setPosizione(getX() - getMovimentoX(), getY());
    }

    /**
     * Metodo che restituisce il percorso dello sprite del fulmine.
     * @return percorso dello sprite del fulmine
     */
    public String getSpritePath() {
        return "JBubbleBobble" + File.separator + "src" + File.separator + "resources" + File.separator +  "sprites" + File.separator + "misc" + File.separator + "image_255.png";
    }
}