package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


//TODO: questa in teoria è la view del gioco di per se
//FIXME: tutta da rifare

public class GameView {
    private JPanel panel;
    private JLabel scoreLabel;
    private JButton pauseButton;
    private JButton exitButton;


    public PartitaView() {

        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Create and add components
        scoreLabel = new JLabel("Score: 0");
        pauseButton = new JButton("Pause");
        exitButton = new JButton("Exit");

        JPanel topPanel = new JPanel();
        topPanel.add(scoreLabel);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(pauseButton);
        bottomPanel.add(exitButton);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        // Add action listeners

    }

    public void addPauseListener(ActionListener listener) {
        pauseButton.addActionListener(listener);
    }
    public void addExitListener(ActionListener listener) {
        exitButton.addActionListener(listener);
    }

    public JPanel getPanel() {
        return panel;
    }

    public void updateScore(int score) {
        scoreLabel.setText("Score: " + score);
    }
}
