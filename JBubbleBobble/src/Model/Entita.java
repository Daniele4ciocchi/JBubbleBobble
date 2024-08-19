package Model;

abstract class Entita {
    private int hp; // health points rimanenti 
    private int maxHp; // health points massimi
    private long velocita; // velocita movimento (float?)

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
    public long getVelocita(){
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

    // Modifica la velocita dell'entita nel valore (long) fornito
    public void setVelocita(long v){
        velocita = v;
    }

    // Modifica la velocita fornendo in input la differenza richiesta (es: -1 abbassa la velocita' di 1)
    public void setVelocitaDiff(long v){
        velocita += v;
    }
}