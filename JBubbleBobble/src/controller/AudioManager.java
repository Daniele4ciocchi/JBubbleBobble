package controller;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.sound.sampled.*;
 
/**
 * Classe che gestisce l'audio del gioco.
 */
public class AudioManager implements Runnable {

    private static AudioManager instance;
    private Clip mainsong;
    private FloatControl gainControl;
    private final String path = "JBubbleBobble" + File.separator + "src" + File.separator + "resources" + File.separator + "songs" + File.separator;
    private final String music = path + "MainTheme.wav";
    private final ExecutorService executorService;

    private AudioManager() {
        executorService = Executors.newCachedThreadPool();
    }

    /**
     * Metodo per il pattern Singleton, restituisce l'istanza dell'AudioManager
     * @return l'istanza dell'AudioManager
     */
    public static synchronized AudioManager getInstance() {
        if (instance == null) {
            instance = new AudioManager();
        }
        return instance;
    }

    /**
     * Metodo che avvia l'istanza dell'AudioManager
     */
    public static void startInstance() {
        getInstance().executorService.submit(getInstance());
    }

    /**
     * Metodo che avvia la musica del gioco
     */
    @Override
    public void run() {
        playMusic();
    }

    /**
     * Metodo che avvia il tema principale del gioco
     */
    public void playMainTheme() {
        playMusic();
    }

    /**
     * Metodo che avvia la musica
     */
    public void playMusic() {
        executorService.submit(() -> {
            try {
                File musicFile = new File(music);
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicFile);
                mainsong = AudioSystem.getClip();
                mainsong.open(audioStream);
                mainsong.loop(Clip.LOOP_CONTINUOUSLY);
                gainControl = (FloatControl) mainsong.getControl(FloatControl.Type.MASTER_GAIN);
                mainsong.loop(Clip.LOOP_CONTINUOUSLY);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Metodo che avvia un suono
     * @param soundFile il file audio da riprodurre
     */
    public void playSound(String soundFile) {
        executorService.submit(() -> {
            try {
                File sound = new File(path + soundFile + ".wav");
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(sound);
                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);
                clip.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Metodo che ferma la musica
     */
    public void stopMusic() {
        if (mainsong != null && mainsong.isRunning()) {
            mainsong.stop();
        }
    }

    /**
     * Metodo che ferma tutti i suoni
     * @param volume del gioco
     */
    public void setVolume(float volume) {
        if (gainControl != null) {
            gainControl.setValue(volume);
        }
    }

    /**
     * Metodo che restituisce il volume
     * @return il volume
     */
    public double getVolume() {
        return gainControl != null ? gainControl.getValue() : 0;
    }
}