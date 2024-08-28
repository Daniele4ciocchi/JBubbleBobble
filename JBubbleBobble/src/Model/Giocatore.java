package Model;

//da fare singleton
public class Giocatore extends Entita{
    private Profilo profilo;
    private static Giocatore INSTANCE;


//    public Giocatore(Profilo profilo){
//        this.profilo = profilo;
//        this.setHp(3);
//    }

    public Giocatore(){}

    public static Giocatore getInstance(){
        if(INSTANCE == null) {
            INSTANCE = new Giocatore();
        }
        return INSTANCE;
    }


    public void setProfilo(Profilo p){
        this.profilo = p;
    }

    public void moveLeft(){

    }

    public void moveRight(){

    }

    public void sparaBolle(){
        livello.addEntita(new Bolla());
    }

    public void salta() {

    }
    public void esplodiBolla(Bolla bolla){
        //if giocatore tocca bolla
        bolle.remove(bolla);
    }
}
