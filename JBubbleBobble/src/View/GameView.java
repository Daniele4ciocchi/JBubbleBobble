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
    private PartitaView gioco ;
    private PannelloSuperiore topPanel;
    private JLabel scoreLabel;
    private JButton pauseButton;
    private JButton exitButton;
    //AudioManager audioManager = new AudioManager();


    public GameView() {
        // Create frame
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(586, 464);
        frame.setVisible(true);
        frame.setFocusable(true);
        frame.requestFocusInWindow();
        //audioManager.loadAudio("JBubbleBobble/src/Model/data/Super Mario Bros. medley.mp3");


    }
    public void addPanel(PartitaView panel) {
        this.gioco = panel;
        frame.add(panel);
    }
    public void addTopPanel(PannelloSuperiore panel) {
        this.topPanel = panel;
        frame.add(panel);
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
        return gioco;
    }
    public PannelloSuperiore getTopPanel() {
        return topPanel;
    }
    
    public void updateScore(int score) {
        scoreLabel.setText("Score: " + score);
    }
}
