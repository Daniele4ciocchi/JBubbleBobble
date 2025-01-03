package Model;

public class BollaSemplice extends Bolla {

    public BollaSemplice(int posx, int posy, int velocitax, int velocitay, boolean goingRight) {
        super(posx, posy, velocitax, velocitay, goingRight);
        
    }

    @Override
    public void move() {
        if (this.range !=0){
            if (this.getGoingRight()) {
                this.setPosizione(this.getX() + this.getMovimentoX(), this.getY());
            } else {
                this.setPosizione(this.getX() - this.getMovimentoX(), this.getY());
            }
            this.range--;
        }else{
            this.setPosizione(this.getX(), this.getY() + this.getMovimentoY());
        }
        System.out.println(range);
    }
    
}
