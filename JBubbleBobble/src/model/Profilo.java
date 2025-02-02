package model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe che rappresenta il profilo di un giocatore, contenente informazioni come 
 * il nickname, l'avatar, la cronologia delle partite e il livello del profilo.
 */
public class Profilo {
    private static Profilo profilo; 

    private String nickname; 
    private int avatar; 
    private ArrayList<Partita> partite = new ArrayList<Partita>(); 
    private int livelloProfilo; 
    private static int highScore = 0;

    /**
     * Metodo per il pattern Singleton, restituisce l'istanza del profilo
     * e se non esiste ne crea una nuova.
     *
     * @return l'istanza del profilo
     */
    public static Profilo getInstance() {
        if (profilo == null) {
            profilo = new Profilo();
        }
        return profilo;
    }

    /**
     * Metodo che restituisce il nickname
     * @return nickname, una stringa contenente il nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Metodo che restituisce l'avatar
     * @param s il nickname del profilo
     * @return intero che rappresenta l'avatar del profilo
     */
    public int getAvatar(String s) {
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get("profiles.txt"));

            for (String line : lines) {
                String[] parts = line.split(":");
                if (parts[0].equals(s)) {
                    return Integer.parseInt(parts[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
        return avatar;
    }

    /**
     * Metodo che restituisce le partite registrate in questa sessione di gioco
     * @return ArrayList di partite
     */
    public ArrayList<Partita> getPartite() {
        return partite;
    }

    /**
     * Metodo che restituisce il numero di partite giocate dal profilo attuale 
     * @return intero che rappresenta il numero di partite giocate
     */
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

    /**
     * Metodo che restituisce il numero di partite vinte dal profilo attuale
     * @return intero che rappresenta il numero di partite vinte
     */
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

    /**
     * Metodo che restituisce il numero di partite perse dal profilo attuale
     * @return intero che rappresenta il numero di partite perse
     */
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

    /**
     * Metodo che restituisce il punteggio più alto generale
     * @return intero che rappresenta il punteggio più alto
     */
    public int getHighScore() {
        return getBestScores().stream()
            .map(line -> line.split(":"))
            .mapToInt(arr -> Integer.parseInt(arr[1]))
            .max()
            .orElse(0);
    }

    /**
     * Metodo che imposta l'avatar del profilo attuale e lo salva nel file profiles.txt
     * @param a l'avatar da impostare sottoforma di intero
     */
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

    /**
     * Metodo che imposta il nickname del profilo attuale
     * @param n il nickname da impostare
     */
    public void setNickname(String n) {
        nickname = n;
    }

    /**
     * Metodo che imposta il punteggio più alto generale
     * @param hs il punteggio da impostare
     */
    public static void setHighScore(int hs) {
        if (hs > highScore)highScore = hs;
    }

    /**
     * Metodo che restituisce il livello del profilo attuale
     * @return intero che rappresenta il livello del profilo
     */
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

    /**
     * Metodo che aggiunge una partita alla sessione attuale di gioco
     * @param p la partita da aggiungere
     */
    public void addPartita(Partita p) {partite.add(p);}

    /**
     * Metodo che controlla su global_scores.txt il numero di punti ottenuti dal profilo attuale
     * @return intero che rappresenta il numero di punti totali
     */
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

    /**
     * Metodo che restituisce i migliori 10 punteggi globali
     * @return ArrayList di stringhe contenenti i migliori 10 punteggi
     */
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

    /**
     * Metodo per eliminare tutti i punteggi dal file global_scores.txt
     */
    public void clearGlobalScores() {
        try {
            Files.write(Paths.get("global_scores.txt"), new ArrayList<String>());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
