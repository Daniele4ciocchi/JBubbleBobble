package View;

import Model.Tile;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import Controller.AudioManager;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


//TODO: questa in teoria è la view del gioco di per se
//FIXME: tutta da rifare

public class GameView  {
    private JFrame frame = new JFrame("Bubble Bobble MVC Game");
    private PartitaView partita ;
    private TopPanel topPanel;
    private JLabel scoreLabel;
    private JButton pauseButton;
    private JButton exitButton;
    //AudioManager audioManager = new AudioManager();

    private static Font customFont;


    public GameView() {
        // Create frame
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(586, 485);
        frame.setVisible(true);
        frame.setFocusable(true);
        frame.requestFocusInWindow();

        
        try {
            customFont = Font.createFont(Font.PLAIN, new File("JBubbleBobble" + File.separator + "src" + File.separator + "resources" + File.separator + "fonts" + File.separator + "BubbleBobble.ttf"));
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            customFont = new Font("Serif", Font.PLAIN, 24); // Fallback font
        }
        

    }
    public void addPartitaPanel(PartitaView partita) {
        this.partita = partita;
        frame.add(partita);
    }
    public void addTopPanel(TopPanel TopPanel) {
        this.topPanel = TopPanel;
        frame.add(TopPanel, BorderLayout.NORTH);
    }
    
    public void addKeyListener(KeyAdapter keyAdapter) {
        frame.addKeyListener(keyAdapter);
    }
    public void addPauseListener(ActionListener listener) {
        pauseButton.addActionListener(listener);
    }
    public void addExitListener(ActionListener listener) {
        exitButton.addActionListener(listener);
    }

    public PartitaView getPanel() {
        return partita;
    }
    public TopPanel getTopPanel() {
        return topPanel;
    }

    public JFrame getFrame() {
        return frame;
    }

    public static Font getFont() {
        return customFont;
    }
    
    public void updateScore(int score) {
        scoreLabel.setText("Score: " + score);
    }
}
