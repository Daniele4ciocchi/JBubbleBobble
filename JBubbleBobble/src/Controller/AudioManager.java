package Controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.*;

/**
 * Classe che gestisce l'audio del gioco
 */
public class AudioManager {


    private static AudioManager instance;
    private Clip clip;
    private Clip mainsong;
    private FloatControl gainControl;
    private String sound;
    private final String path = "JBubbleBobble" + File.separator + "src" + File.separator + "resources" + File.separator + "songs" + File.separator;
    private final String music = path + "MainTheme.wav";

    /**
     * Metodo che restituisce l'istanza della classe
     *
     * @return Istanza della classe
     */
    public static AudioManager getInstance() {
        if (instance == null)
            instance = new AudioManager();
        return instance;
    }

    /**
     * Costruttore privato
     */
    private AudioManager() {
        //gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

    }

    public void playMainTheme(){playMusic(music);}

    public void playMusic(String filePath) {
        try {
            // Carica il file audio
            File audioFile = new File(filePath);
            if (!audioFile.exists()) {
                System.out.println("Il file audio non esiste: " + filePath);
                return;
            }

            // Ottieni il clip
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);

            // Imposta il loop continuo e avvia
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
            System.out.println("Riproduzione in loop avviata...");
        } catch (Exception e) {
            System.out.println("Errore durante la riproduzione: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void play(){
        mainsong.start();
    }

    public void playSound(String s){
        try {
            InputStream in = new BufferedInputStream(new FileInputStream(path + s + ".wav"));
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(in);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e1) {
            e1.printStackTrace();
        }
    }

    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
            System.out.println("Riproduzione interrotta.");
        } else {
            System.out.println("Nessuna riproduzione in corso.");
        }
    }

    public void setVolume(float volume){
        gainControl.setValue(volume);
    }

    public double getVolume(){
        return gainControl.getValue();
    }
}
