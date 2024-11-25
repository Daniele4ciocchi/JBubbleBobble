package View;

import Model.Tile;

import javax.imageio.ImageIO;
import javax.swing.*;
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

    public void setBackgroundImage(BufferedImage image) {
        JLabel background = new JLabel(new ImageIcon(image));
        panel.add(background, BorderLayout.CENTER);
    }

    public static BufferedImage createImage(Tile[][] grid, String imagePath) throws IOException {
        int tileWidth = 32; // Width of each tile
        int tileHeight = 32; // Height of each tile

        // Create a BufferedImage with the size of the grid
        BufferedImage finalImage = new BufferedImage(grid[0].length * tileWidth, grid.length * tileHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = finalImage.createGraphics();

        // Load the input image
        BufferedImage tileImage = ImageIO.read(new File(imagePath));

        // Iterate through the grid and draw the appropriate image
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j].getType() == Tile.TileType.WALL || grid[i][j].getType() == Tile.TileType.PLATFORM) {
                    g2d.drawImage(tileImage, j * tileWidth, i * tileHeight, tileWidth, tileHeight, null);
                } else {
                    g2d.setColor(Color.BLACK);
                    g2d.fillRect(j * tileWidth, i * tileHeight, tileWidth, tileHeight);
                }
            }
        }

        g2d.dispose();
        return finalImage;
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
