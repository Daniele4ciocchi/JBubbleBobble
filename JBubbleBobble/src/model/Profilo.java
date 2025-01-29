package model;

import java.awt.Image;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
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
    private int avatar; // immagine profilo, decisa nel costruttore
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
    public int getAvatar() {
        return avatar;
    }

    // Restituisce l'ArrayList di Partite giocate con questo profilo
    public ArrayList<Partita> getPartite() {
        return partite;
    }

    public int getNumeroPartite() {
        try {
            return (int) Files.readAllLines(Paths.get("global_scores.txt")).stream()
                .map(line -> line.split(":"))
                .filter(arr -> arr[0].equals(nickname))
                .count();
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
        
    }

    // Restituisce il numero di partite vinte
    public int getVinte() {
        try {
            return (int) Files.readAllLines(Paths.get("global_scores.txt")).stream()
                .map(line -> line.split(":"))
                .filter(arr -> arr[0].equals(nickname))
                .filter(arr -> arr[2].equals("VINTA"))
                .count();
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    // Restituisce il numero di partite perse
    public int getPerse() {
        try {
            return (int) Files.readAllLines(Paths.get("global_scores.txt")).stream()
                .map(line -> line.split(":"))
                .filter(arr -> arr[0].equals(nickname))
                .filter(arr -> arr[2].equals("PERSA"))
                .count();
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getHighScore() {
        return getBestScores().stream()
            .map(line -> line.split(":"))
            .mapToInt(arr -> Integer.parseInt(arr[1]))
            .max()
            .orElse(0);
    }


    // SETTERS
    // Modifica l'avatar del giocatore, fornendo una nuova immagine in parametro
    public void setAvatar(int a) {
        avatar = a;
        try {
            File file = new File("profiles.txt");
            if (!file.exists()) {
            file.createNewFile();
            }

            List<String> lines = Files.readAllLines(file.toPath());
            boolean exists = false;
            for (int i = 0; i < lines.size(); i++) {
            String[] parts = lines.get(i).split(":");
            if (parts[0].equals(this.nickname)) {
                lines.set(i, this.nickname + ":" + this.avatar);
                exists = true;
                break;
            }
            }

            if (!exists) {
            lines.add(this.nickname + ":" + this.avatar);
            }

            Files.write(file.toPath(), lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setNickname(String n) {
        nickname = n;
    }

    public static void setHighScore(int hs) {
        if (hs > highScore)highScore = hs;
    }

    public String getLivelloProfilo() {
        try {
            int totScore = Files.readAllLines(Paths.get("global_scores.txt")).stream()
                .map(line -> line.split(":"))
                .filter(arr -> arr[0].equals(nickname))
                .mapToInt(arr -> Integer.parseInt(arr[1]))
                .sum();
            livelloProfilo = totScore / 10000; // Example calculation for level
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Integer.toString(livelloProfilo);
    }


    // Aggiunge una nuova partita allo storico del profilo + controlla la somma dei punteggi dell'utente per eventuale level-up
    public void addPartita(Partita p) {
        partite.add(p);

    }

    public String getPuntiTotali() {
        try {
            int totScore = Files.readAllLines(Paths.get("global_scores.txt")).stream()
                .map(line -> line.split(":"))
                .filter(arr -> arr[0].equals(nickname))
                .mapToInt(arr -> Integer.parseInt(arr[1]))
                .sum();
            return Integer.toString(totScore);
        } catch (IOException e) {
            e.printStackTrace();
            return "0";
        }
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
