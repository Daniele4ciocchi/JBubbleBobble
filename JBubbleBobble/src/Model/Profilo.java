
import java.awt.Image;
import java.util.ArrayList;

class Profilo {
    private final String NICKNAME; // nome utente, deciso nel costruttore
    private Image avatar; // immagine profilo, decisa nel costruttore
    private ArrayList<Partita> partite; // storico partite

    /*
        Ogni profilo nasce livello 1.
        Il livello viene incrementato tramite levelUp() quando il giocatore
        supera determinate soglie di punti guadagnati durante le sue partite.
        Tale controllo è effettuato durante addPartita().
    */
    private int livello; // livello del profilo, 


    // COSTRUTTORE
    public Profilo(String n, Image a){
        NICKNAME = n;
        avatar = a;
    }


    // Restituisce il nickname del profilo sottoforma di String
    public String getNickname(){
        return NICKNAME;
    }

    // Restituisce l'avatar del giocatore come variabile Image
    public Image getAvatar(){
        return avatar;
    }

    // Restituisce l'ArrayList di Partite giocate con questo profilo
    public ArrayList<Partita> getPartite(){
        return partite;
    }

    // Restituisce il numero di partite vinte
    public int getVinte(){
        return (int)partite.stream().filter(x -> x.isVinta()).count();
    }

    // Restituisce il numero di partite perse
    public int getPerse(){
        return (int)partite.stream().filter(x -> x.isVinta() == false).count();
    }

    // Restituisce il livello del profilo
    public int getLivello(){
        return livello;
    }

    // Modifica l'avatar del giocatore, fornendo una nuova immagine in parametro
    public void setAvatar(Image a){
        avatar = a;
    }

    // Incrementa di 1 il livello del profilo
    public void levelUp(){
        livello++;
    }

    // Aggiunge una nuova partita allo storico del profilo + controlla la somma dei punteggi dell'utente per eventuale level-up
    public void addPartita(Partita p){
        partite.add(p);
        
        // TODO: controllo levelup, pensare a una funzione matematica adatta (score necessario esponenziale?)
    }
}
