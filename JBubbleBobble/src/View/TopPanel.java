package View;

import javax.swing.*;
import java.awt.*;


public class TopPanel extends JPanel {
    private JLabel scoreLabel;
    private JButton pauseMenuButton;

    public TopPanel() {
        // Set layout for the panel
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        // Initialize the score label
        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        scoreLabel.setForeground(Color.WHITE);
        Font font = GameView.getFont().deriveFont(30f);
        //Font newFont = new Font("Arial", Font.BOLD, 20);
        
        scoreLabel.setFont(font);
        // Initialize the pause menu button
        pauseMenuButton = new JButton("||");
        pauseMenuButton.setFont(font);
        pauseMenuButton.setBackground(Color.BLACK);

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