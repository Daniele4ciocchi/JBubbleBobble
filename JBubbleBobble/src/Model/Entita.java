abstract class Entita {
    private int hp; // health points rimanenti 
    private int maxHp; // health points massimi
    private int velocita; // velocita movimento (float?)

    // variabili per le coordinate
    private int posX;
    private int posY;

    // getter di hp
    public int getHp(){
        return hp;
    }

    // getter di maxHp
    public int getMaxHp(){
        return maxHp;
    }

    // getter di velocita
    public int getVelocita(){
        return velocita;
    }

    // getter di posX
    public int getPosX(){
        return posX;
    }

    // getter di posY
    public int getPosY(){
        return posY;
    }

    // setter di velocita
    public void setVelocita(int v){
        velocita = v;
    }
}