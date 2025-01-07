package View;

import javax.swing.*;
import java.awt.*;


public class TopPanel extends JPanel {
    private JLabel textScoreLabel;
    private JLabel scoreLabel;
    private JLabel textHighScoreLabel;
    private JLabel highScoreLabel;

    private JButton pauseMenuButton;
    private JPanel textPanel;
    private JPanel scorePanel;

    public TopPanel() {
        // Set layout for the panel
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);


        textPanel = new JPanel();
        textPanel.setBackground(getBackground());

        textScoreLabel = new JLabel("Score: ");
        textHighScoreLabel = new JLabel("High Score: ");

        textScoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        textHighScoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        textScoreLabel.setForeground(Color.GREEN);
        textHighScoreLabel.setForeground(Color.RED);
        
        Font font = GameView.getFont().deriveFont(30f);

        textScoreLabel.setFont(font);
        textHighScoreLabel.setFont(font);

        textPanel.add(textScoreLabel, BorderLayout.WEST);
        textPanel.add(textHighScoreLabel, BorderLayout.EAST);

        add(textPanel, BorderLayout.NORTH);



        scorePanel = new JPanel();
        scorePanel.setBackground(getBackground());

        scoreLabel = new JLabel("Score: ");
        highScoreLabel = new JLabel("High Score: ");

        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        highScoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        scoreLabel.setForeground(Color.WHITE);
        highScoreLabel.setForeground(Color.WHITE);

        scoreLabel.setFont(font);
        highScoreLabel.setFont(font);

        scorePanel.add(scoreLabel, BorderLayout.WEST);
        scorePanel.add(highScoreLabel, BorderLayout.EAST);

        add(scorePanel, BorderLayout.NORTH);




        // Initialize the pause menu button
        pauseMenuButton = new JButton("||");
        pauseMenuButton.setFont(font);
        pauseMenuButton.setBackground(Color.BLACK);


        add(pauseMenuButton, BorderLayout.EAST);
    }

    // Method to update the score
    public void updateScore(int score) {
        scoreLabel.setText("Score: \n" + score);
    }

    // Getter for the pause menu button
    public JButton getPauseMenuButton() {
        return pauseMenuButton;
    }
}