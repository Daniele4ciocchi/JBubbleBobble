package Model;

public class Fireball extends Bolla {

    public Fireball(int posx, int posy, int velocitax, int velocitay, boolean goingRight, int range) {
        super(posx, posy, velocitax, velocitay, goingRight);
        this.setRange(range);
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
            popTime--;
            if (l.isTPExit(getX(),getY() + getEntitysize() ))popTime = 0;
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
        if (getNemico() != null) { getNemico().setPosizione(getX(), getY()); }
        
        setChanged();
        notifyObservers();
    }
    
}
