package View;

import javax.swing.*;
import java.awt.*;


public class PannelloSuperiore extends JPanel {
    private JLabel scoreLabel;
    private JButton pauseMenuButton;

    public PannelloSuperiore() {
        // Set layout for the panel
        setLayout(new BorderLayout());

        // Initialize the score label
        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Initialize the pause menu button
        pauseMenuButton = new JButton("Pause Menu");

        // Add components to the panel
        add(scoreLabel, BorderLayout.CENTER);
        add(pauseMenuButton, BorderLayout.EAST);
    }

    // Method to update the score
    public void updateScore(int score) {
        scoreLabel.setText("Score: " + score);
    }

    // Getter for the pause menu button
    public JButton getPauseMenuButton() {
        return pauseMenuButton;
    }
}