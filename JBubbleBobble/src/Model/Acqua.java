package Model;

import java.io.File;
import java.util.ArrayList;

import Model.Livello;

public class Acqua extends Entita{
    public class Goccia extends Entita{
        
        public Goccia(int x, int y) {
            super(x, y, 10, 0, 0);
        }
    }

    private ArrayList<Goccia> gocce = new ArrayList<Goccia>();
    private boolean goingRight = true;

    private int lunghezza = 20;

    public Acqua(int x, int y) {
        super(x/16, y/16, 1, 0, 0);
        for (int i = 0; i < lunghezza; i++) {
            gocce.add(new Goccia(x/16, y/16));
        }
    }

    public void moveBody() {
        for (int i = lunghezza - 1; i >= 1; i--) {
            Goccia prec = gocce.get(i-1);
            gocce.get(i).setPosizione(prec.getX(), prec.getY());
        }
    }
    
    public void move(Livello l) {
        Goccia testa = gocce.get(0);
        
        if (!l.isTPEntry(testa.getX(), testa.getY())){
            if (l.isEmpty(testa.getX(), testa.getY() - 5)) {
                testa.setPosizione(testa.getX(), testa.getY() - 5);

            }else if (goingRight) {
                if (l.isEmpty(testa.getX() + testa.getMovimentoX(), testa.getY())){
                    testa.setPosizione(testa.getX() + testa.getMovimentoX(), testa.getY());
                }else {goingRight = false;}

            }else if (!goingRight) {
                if (l.isEmpty(testa.getX() - testa.getMovimentoX(), testa.getY())){ 
                    testa.setPosizione(testa.getX() - testa.getMovimentoX(), testa.getY());
                }else {goingRight = true;}
            }
        }
        setPosizione(gocce.get(lunghezza-1).getX(), gocce.get(lunghezza-1).getY());
        moveBody();

        setChanged();
        notifyObservers();
    }

    public ArrayList<Goccia> getGocce() {
        return gocce;
    }

    public String getSpritePath() {
        return "JBubbleBobble" + File.separator + "src" + File.separator + "resources" + File.separator +  "blocks" + File.separator + "super blocks" + File.separator + "block_98.png";
    }
    
    
}