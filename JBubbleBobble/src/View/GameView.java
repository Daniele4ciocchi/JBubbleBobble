package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;


//TODO: questa in teoria è la view del gioco di per se
//FIXME: tutta da rifare

public class GameView  {
    private JFrame frame = new JFrame("Bubble Bobble MVC Game");
    private JPanel panel = new JPanel();;
    private JLabel scoreLabel;
    private JButton pauseButton;
    private JButton exitButton;


    public GameView() {
        // Create frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setVisible(true);

        panel.setLayout(new BorderLayout());

        frame.add(panel);
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


    public void drawLevel(Graphics g, int[][] layout) {
        for (int y = 0; y < layout.length; y++) {
            for (int x = 0; x < layout[y].length; x++) {
                if (layout[y][x] == 1) {
                    g.setColor(Color.DARK_GRAY);
                    g.fillRect(x * 32, y * 32, 32, 32);
                }
            }
        }
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

    public JPanel getPanel() {
        return panel;
    }

    public void updateScore(int score) {
        scoreLabel.setText("Score: " + score);
    }
}
