package View;

import javax.swing.*;

import Model.Profilo;

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
        textPanel.setLayout(new BorderLayout());

        textScoreLabel = new JLabel("SCORE");
        textHighScoreLabel = new JLabel("HIGH SCORE");

        textScoreLabel.setHorizontalAlignment(SwingConstants.LEFT);
        textHighScoreLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        
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
        scorePanel.setLayout(new BorderLayout());

        scoreLabel = new JLabel("0");
        highScoreLabel = new JLabel(Profilo.getProfilo().getHighScore() + "");

        scoreLabel.setHorizontalAlignment(SwingConstants.LEFT);
        highScoreLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        
        scoreLabel.setForeground(Color.WHITE);
        highScoreLabel.setForeground(Color.WHITE);

        scoreLabel.setFont(font);
        highScoreLabel.setFont(font);

        scorePanel.add(scoreLabel, BorderLayout.WEST);
        scorePanel.add(highScoreLabel, BorderLayout.EAST);

        add(scorePanel, BorderLayout.CENTER);

        // Initialize the pause menu button
        pauseMenuButton = new JButton("||");
        pauseMenuButton.setFont(font);
        pauseMenuButton.setBackground(Color.BLACK);


        //add(pauseMenuButton, BorderLayout.EAST);
    }

    // Method to update the score
    public void updateScore(int score) {
        scoreLabel.setText(Integer.toString(score));
    }

    // Getter for the pause menu button
    public JButton getPauseMenuButton() {
        return pauseMenuButton;
    }
}