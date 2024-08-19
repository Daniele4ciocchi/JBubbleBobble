
import java.util.ArrayList;

class Profilo {
    private String nickname; // nome utente, deciso nel costruttore
    private Image avatar; // immagine profilo, decisa nel costruttore
    private ArrayList<Partita> partite; // storico partite

    /*
        Ogni profilo inizia dal livello 1.
        Il livello viene incrementato tramite levelUp() quando il giocatore
        supera determinate soglie di punti guadagnati durante le sue partite.
        Tale controllo è effettuato durante addPartita().
    */
    private int livello; // livello del profilo, 

    public Profilo(String n, Image a){
        nickname = n;
        avatar = a;
    }


    public String getNickname(){
        return nickname;
    }

    public Image getAvatar(){
        return avatar;
    }

    public ArrayList<Partita> getPartite(){
        return partite;
    }

    public int getVinte(){
        return partite.stream().filter(x -> x.isVinta()).count();
    }

    public int getPerse(){
        return partite.stream().filter(x -> x.isVinta() == false).count();
    }

    public int getLivello(){
        return livello;
    }

    // incrementa di 1 il livello del profilo
    public void levelUp(){
        livello++;
    }

    // aggiunge una nuova partita allo storico del profilo + controlla la somma dei punteggi dell'utente
    public void addPartita(Partita p){
        partite.add(p);
        
        // TODO: controllo levelup, pensare a una funzione matematica
    }
}
