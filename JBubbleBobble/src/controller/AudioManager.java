package controller;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.sound.sampled.*;
 
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

    public static synchronized AudioManager getInstance() {
        if (instance == null) {
            instance = new AudioManager();
        }
        return instance;
    }

    public static void startInstance() {
        getInstance().executorService.submit(getInstance());
    }

    @Override
    public void run() {
        playMusic();
    }

    public void playMainTheme() {
        playMusic();
    }

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

    public void stopMusic() {
        if (mainsong != null && mainsong.isRunning()) {
            mainsong.stop();
        }
    }

    public void setVolume(float volume) {
        if (gainControl != null) {
            gainControl.setValue(volume);
        }
    }

    public double getVolume() {
        return gainControl != null ? gainControl.getValue() : 0;
    }
}