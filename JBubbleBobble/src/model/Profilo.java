package model;

import java.awt.Image;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Profilo{
    private static final long serialVersionUID = 1L; 
    private static Profilo profilo; // istanza singleton del profilo;

    private String nickname; // nome utente, immutabile e deciso nel costruttore
    private Image avatar; // immagine profilo, decisa nel costruttore
    private ArrayList<Partita> partite = new ArrayList<Partita>(); // cronologia partite
    
    private int livelloProfilo; // livello del profilo
    private static int highScore = 0;


    // COSTRUTTORE
    private Profilo(){}

    public static Profilo getInstance() {
        if (profilo == null) {
            profilo = new Profilo();
        }
        return profilo;
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
        return (int) partite.stream().filter(x -> x.getStato() == Partita.Stato.VINTA).count();
    }

    // Restituisce il numero di partite perse
    public int getPerse() {
        return (int) partite.stream().filter(x -> x.getStato() == Partita.Stato.PERSA).count();
    }

    public int getHighScore() {
        return highScore;
    }

    // Restituisce il livello del profilo
    public int getLivelloProfilo() {
        return livelloProfilo;
    }


    // SETTERS
    // Modifica l'avatar del giocatore, fornendo una nuova immagine in parametro
    public void setAvatar(Image a) {
        avatar = a;
    }

    public void setNickname(String n) {
        nickname = n;
    }

    public static void setHighScore(int hs) {
        if (hs > highScore)highScore = hs;
    }

    public void setLivelloProfilo(){
        
        try {
            final int[] totScore = {0};
            List<String> lines = Files.readAllLines(Paths.get("global_scores.txt"));
            lines.stream()
                .map(line -> line.split(":"))
                .filter(x -> x[0].equals(nickname))
                .forEach(x -> totScore[0] += Integer.parseInt(x[1]));
                livelloProfilo = totScore[0] / 10000;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Aggiunge una nuova partita allo storico del profilo + controlla la somma dei punteggi dell'utente per eventuale level-up
    public void addPartita(Partita p) {
        partite.add(p);
        //if (p.getScore() > 10000 * livelloProfilo) levelUp();
    }

    public String getPuntiTotali() {
        return Integer.toString(partite.stream().mapToInt(Partita::getScore).sum());
    }


    public ArrayList<String> getBestScores(){
        try {
            List<String> lines = Files.readAllLines(Paths.get("global_scores.txt"));
            return lines.stream()
                        .map(line -> line.split(":"))
                        .sorted((a, b) -> Integer.compare(Integer.parseInt(b[1]), Integer.parseInt(a[1])))
                        .limit(10)
                        .map(arr -> arr[0] + ":" + arr[1])
                        .collect(Collectors.toCollection(ArrayList::new));
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void clearGlobalScores() {
        try {
            Files.write(Paths.get("global_scores.txt"), new ArrayList<String>());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
