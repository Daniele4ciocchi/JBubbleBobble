package model;

import java.io.File;
import java.util.ArrayList;

import model.Livello;

public class Fulmine extends Entita{

    public Fulmine(int x, int y, boolean direction) {
        super(x/16, y/16, 14, 0, 0);
        setGoingRight(direction);
    }

    
    public void move(Livello l) {
       if (getGoingRight() == true)setPosizione(getX() + getMovimentoX(), getY());
       else setPosizione(getX() - getMovimentoX(), getY());
    }

   
    public String getSpritePath() {
        return "JBubbleBobble" + File.separator + "src" + File.separator + "resources" + File.separator +  "sprites" + File.separator + "misc" + File.separator + "image_255.png";
    }
    
    
}