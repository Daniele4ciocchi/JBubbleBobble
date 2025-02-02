package view;

import javax.swing.*;

import model.Profilo;

import java.awt.*;

/**
 * Classe che rappresenta il pannello superiore del gioco.
 */
public class TopPanel extends JPanel {
    private JLabel textScoreLabel;
    private JLabel scoreLabel;
    private JLabel textHighScoreLabel;
    private JLabel highScoreLabel;
    private JPanel textPanel;
    private JPanel scorePanel;

    /**
     * Costruttore della classe TopPanel.
     */
    public TopPanel() {
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
        highScoreLabel = new JLabel(Profilo.getInstance().getHighScore() + "");

        scoreLabel.setHorizontalAlignment(SwingConstants.LEFT);
        highScoreLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        
        scoreLabel.setForeground(Color.WHITE);
        highScoreLabel.setForeground(Color.WHITE);

        scoreLabel.setFont(font);
        highScoreLabel.setFont(font);

        scorePanel.add(scoreLabel, BorderLayout.WEST);
        scorePanel.add(highScoreLabel, BorderLayout.EAST);

        add(scorePanel, BorderLayout.CENTER);
    }

    /**
     * Metodo che aggiorna il punteggio.
     * @param score
     */
    public void updateScore(int score) {
        scoreLabel.setText(Integer.toString(score));
    }

}