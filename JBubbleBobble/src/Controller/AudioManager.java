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
        playMusic(music);
    }

    /**
     * Metodo che riproduce la musica di sottofondo
     *
     * @param filename Percorso del file audio
     */
    public void playMusic(String filename) {
        try {
            InputStream in = new BufferedInputStream(new FileInputStream(filename));
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(in);
            mainsong = AudioSystem.getClip();
            mainsong.open(audioIn);
            mainsong.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    playMusic(filename);
                }
            });
            
            mainsong.start();
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e1) {
            e1.printStackTrace();
        }
        gainControl = (FloatControl) mainsong.getControl(FloatControl.Type.MASTER_GAIN);
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

    /**
     * Metodo che ferma la musica di sottofondo
     */
    public void stop() {
        mainsong.stop();
    }

    public void setVolume(float volume){
        gainControl.setValue(volume);
    }

    public double getVolume(){
        return gainControl.getValue();
    }
}
