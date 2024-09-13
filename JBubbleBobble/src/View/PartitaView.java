// src/View/PartitaView.java
package View;

import Controller.PartitaController;

import javax.swing.*;
import java.awt.*;

public class PartitaView {
    private JPanel panel;
    private JLabel scoreLabel;
    private JButton pauseButton;
    private JButton exitButton;
    private PartitaController controller;

    public PartitaView() {
        controller = PartitaController.getInstance();
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
        pauseButton.addActionListener(e -> controller.pause());
        exitButton.addActionListener(e -> controller.stop());
    }

    public JPanel getPanel() {
        return panel;
    }

    public void updateScore(int score) {
        scoreLabel.setText("Score: " + score);
    }
}