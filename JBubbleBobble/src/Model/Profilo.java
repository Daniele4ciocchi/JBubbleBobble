package Model;

import java.awt.Image;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Profilo implements Serializable{
    private static final long serialVersionUID = 1L;

    private String nickname; // nome utente, immutabile e deciso nel costruttore
    private Image avatar; // immagine profilo, decisa nel costruttore
    private ArrayList<Partita> partite = new ArrayList<Partita>(); // storico partite
    private static Profilo instance;


    private int livello; // livello del profilo, 


    // COSTRUTTORE
    private Profilo(){
    }

    public static Profilo getInstance() {
        if (instance == null) {
            instance = new Profilo();
        }
        return instance;
    }

    // GETTERS
    // Restituisce il nickname del profilo sottoforma di String
    public String getNickname() {
        return nickname;
    }

    // Restituisce l'avatar del giocatore come variabile Image
    public Image getAvatar() {
        return avatar;
    }

    // Restituisce l'ArrayList di Partite giocate con questo profilo
    public ArrayList<Partita> getPartite() {
        return partite;
    }

    // Restituisce il numero di partite vinte
    public int getVinte() {
        return (int) partite.stream().filter(Partita::isVinta).count();
    }

    // Restituisce il numero di partite perse
    public int getPerse() {
        return (int) partite.stream().filter(x -> !x.isVinta()).count();
    }

    // Restituisce il livello del profilo
    public int getLivello() {
        return livello;
    }


    // SETTERS
    // Modifica l'avatar del giocatore, fornendo una nuova immagine in parametro
    public void setAvatar(Image a) {
        avatar = a;
    }

    public void setNickname(String n) {
        nickname = n;
    }

    // Incrementa di 1 il livello del profilo
    public void levelUp() {
        livello++;
    }

    // Aggiunge una nuova partita allo storico del profilo + controlla la somma dei punteggi dell'utente per eventuale level-up
    public void addPartita(Partita p) {
        partite.add(p);
        if (p.getPunteggio() > 1000 * livello) levelUp();
    }

    public String getPuntiTotali() {
        return Integer.toString(partite.stream().mapToInt(Partita::getPunteggio).sum());
    }

    // Salva il profilo in un file
    public void saveProfilo(String filePath) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(this);
        }
    }

    // Carica un profilo da un file
    public static Profilo loadProfilo(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (Profilo) ois.readObject();
        }
    }
}
