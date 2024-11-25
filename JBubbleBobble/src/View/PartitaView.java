package View;

import Model.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class PartitaView extends JPanel {
    private Tile[][] grid;
    private BufferedImage image;

    private final int tileSize = 16; // Dimensione di ogni cella

    public PartitaView(Tile[][] grid, String path) {
        super();
        this.grid = grid;

        // Caricamento delle immagini
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Errore nel caricamento delle immagini!");
        }

        setPreferredSize(new Dimension(grid.length * tileSize, grid[0].length * tileSize));
        setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        int gridHeight = grid.length;       // Numero di righe
        int gridWidth = grid[0].length;    // Numero di colonne

        for (int row = 0; row < gridHeight; row++) {
            for (int col = 0; col < gridWidth; col++) {
                int x = col * tileSize;  // Posizione orizzontale rimane invariata
                int y = (gridHeight - 1 - row) * tileSize; // Inverti l'asse verticale

                if (grid[row][col].getType() == Tile.TileType.WALL || grid[row][col].getType() == Tile.TileType.PLATFORM) {
                    g2d.drawImage(image, x, y, tileSize, tileSize, null);
                } else {
                    g2d.setColor(Color.BLACK);
                    g2d.fillRect(x, y, tileSize, tileSize);
                }
            }
        }
    }

}
