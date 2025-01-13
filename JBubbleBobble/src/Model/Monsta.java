package Model;

import java.io.File;

public class Monsta extends Nemico {
    private boolean goingUp = true;

    public Monsta(int x, int y){
        super(x, y, 3, 2);
        setSprites();
    }
    
    public void setSprites(){
        walkingSpritesPath[0] += "monsta"+File.separator+"image_443.png";
        walkingSpritesPath[1] += "monsta"+File.separator+"image_444.png";

        bubbledSpritesPath[0] += "monsta"+File.separator+"image_432.png"; 
        bubbledSpritesPath[1] += "monsta"+File.separator+"image_433.png"; 
        bubbledSpritesPath[2] += "monsta"+File.separator+"image_434.png";

        deathSpritePath += "zen-chan"+File.separator+"500.png";
    }

    // movimento unico di Monsta, TODO: da fare!
    // IDEA: Monsta si muove sempre in diagonale, e quando incontra tile non walkable, cambia direzione orizzontale/verticale
    public void move(int gx, int gy, Livello l) {
        // if (this.getX() < gx) {
        //     if (currentWaitTime == 0){
        //         goingRight = true;
        //         moveRight(l);
        //         currentWaitTime = waitTime;
        //     } else currentWaitTime--;
        // }
        // if (this.getX() > gx) {
        //     if (currentWaitTime == 0){
        //         goingRight = false;
        //         moveLeft(l);
        //         currentWaitTime = waitTime;
        //     } else currentWaitTime--;
        // }
        // else if (l.isWalkable(this.getX(), this.getY() - 1)) {
        //     setPosizione(this.getX(), this.getY() - 1);
        // }
        // else if (l.isWalkable(this.getX(), this.getY() + 1)) {
        //     setPosizione(this.getX(), this.getY() + 1);
        // }

        // if (goingUp) {
        //     if (l.isWalkable(this.getX() - 1, this.getY() - 1)) {
        //         setPosizione(this.getX() - 1, this.getY() - 1);
        //     } else {
        //         goingUp = false;
        //     }
        // } else {
        //     if (l.isWalkable(this.getX() + 1, this.getY() + 1)) {
        //         setPosizione(this.getX() + 1, this.getY() + 1);
        //     } else {
        //         goingUp = true;
        //     }
        // }

        // if (goingUp) { // se sta andando verso l'alto
        //     if (l.isEmpty(posx-movimentoX, posy-movimentoX)) { // se la prossima posizione è vuota
        //         posx-=movimentoX;
        //         posy-=movimentoX;
        //         setChanged();
        //         notifyObservers();
        //         moveLaterale(l);
        //     } else { // se la prossima posizione non è vuota
        //         goingUp = false;
        //         moveLaterale(l);
        //     }
        // } else { // se sta andando verso il basso
        //     if (l.isEmpty(posx+movimentoX, posy+movimentoX)) { // se la prossima posizione è vuota
        //         posx+=movimentoX;
        //         posy+=movimentoX;
        //         setChanged();
        //         notifyObservers();
        //         moveLaterale(l);
        //     } else { // se la prossima posizione non è vuota
        //         goingUp = true;
        //         moveLaterale(l);
        //     }
        // }

        if (goingUp && goingRight) { // andando in alto a DX
            // controllo SU
            if (l.isWalkable(getX(), getY()+1)) {
                posy += movimentoX;
                goingUp = true;
            } else {
                goingUp = false;
            }

            // controllo DX
            if (l.isWalkable(getX()+1, getY())) {
                posx += movimentoX;
                goingRight = true;
            } else {
                goingRight = false;
            }
        } else if (goingUp && !goingRight) { // andando in alto a SX
            // controllo SU
            if (l.isWalkable(getX(), getY()+1)) { 
                posy += movimentoX;
                goingUp = true;
            } else { 
                goingUp = false;
            }
            
            // controllo SX
            if (l.isWalkable(getX()-1, getY())){
                posx -= movimentoX;
                goingRight = false;
            } else {
                goingRight = true;
            }

        } else if (!goingUp && goingRight) { // andando in basso a DX
            // controllo GIU
            if (l.isWalkable(getX(), getY()-1)){
                posy += movimentoX;
                goingUp = false;
            } else {
                goingUp = true;
            }

            // controllo DX
            if (l.isWalkable(getX()+1, getY())) {
                posx += movimentoX;
                goingRight = true;
            } else {
                goingRight = false;
            }
        } else { // andando in basso a SX
            // controllo GIU
            if (l.isWalkable(getX(), getY()-1)){
                posy += movimentoX;
                goingUp = false;
            } else {
                goingUp = true;
            }

            // controllo SX
            if (l.isWalkable(getX()-1, getY())){
                posx -= movimentoX;
                goingRight = false;
            } else {
                goingRight = true;
            }
        }
    }

    public void moveLaterale(Livello l){
        if (goingRight) {
            if (l.isEmpty(posx+movimentoX, posy)) {
                posx+=movimentoX;
                setChanged();
                notifyObservers();
            } else {
                goingRight = false;
            }
        } else {
            if (l.isEmpty(posx-movimentoX, posy)) {
                posx-=movimentoX;
                setChanged();
                notifyObservers();
            } else {
                goingRight = true;
            }
        }
    }

    
    public void moveUp(Livello l) {
        // if (l.isWalkable(this.getX(), this.getY() - 1)) {
        //     setPosizione(this.getX(), this.getY() - 1);
        // }
        if (l.isEmpty(posx-movimentoX, posy)){
            posy-=movimentoX;
            setChanged();
            notifyObservers();
            goingUp = true;
        }
    }

    public void moveDown(Livello l) {
        // if (l.isWalkable(this.getX(), this.getY() + 1)) {
        //     setPosizione(this.getX(), this.getY() + 1);
        // }
        if (l.isEmpty(posx-movimentoX, posy)){
            posy-=movimentoX;
            setChanged();
            notifyObservers();
            goingUp = false;
        }
    }
}
