package Model;

public class BollaSemplice extends Bolla {

    public BollaSemplice(int posx, int posy, int velocitax, int velocitay, boolean goingRight) {
        super(posx, posy, velocitax, velocitay, goingRight);
        
    }

    @Override
    public void move(Livello l) {
        if (range !=0){
            if (getGoingRight()) {
                if (l.isEmpty(getX() + getMovimentoX(), getY())) {
                    setPosizione(getX() + getMovimentoX(), getY());
                } 
            } else {
                if (l.isEmpty(getX() - getMovimentoX(), getY())) {
                    setPosizione(getX() - getMovimentoX(), getY());
                }
            }
            range--;
        }else if (range == 0){
            if (!l.isEmpty(getX(), getY() + getEntitysize())) {
                if (getGoingRight() && l.isEmpty(getX() + getMovimentoX(), getY())) {
                    setPosizione(getX() + getMovimentoX(), getY());
                } else if (!getGoingRight() && l.isEmpty(getX() - getMovimentoX(), getY())) {
                    setPosizione(getX() - getMovimentoX(), getY());
                }
            }
            if (l.isEmpty(getX(), getY() + getEntitysize())) {
                setPosizione(getX(), getY() + 1);
            }
        }
        setChanged();
        notifyObservers();
    }
    
}
