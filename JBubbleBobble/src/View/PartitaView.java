package View;

import Model.Bolla;
import Model.Entita;
import Model.Giocatore;
import Model.Nemico;
import Model.Tile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class PartitaView extends JPanel implements Observer {
    private Tile[][] grid;
    private ArrayList<Entita> entita;
    private BufferedImage image;

    private final int tileSize; // Dimensione di ogni cella

    public PartitaView(int tileSize) {
        super();
        this.tileSize = tileSize;
        this.entita = new ArrayList<Entita>();


        setPreferredSize(new Dimension(36 * tileSize, 26 * tileSize));
        setBackground(Color.BLACK);
    }

    public void setEntita(ArrayList<Entita> entita) {
        this.entita = entita;
    }

    public void setGrid(Tile[][] grid) {
        this.grid = grid;
    }

    public void setPath(String path) {
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Errore nel caricamento delle immagini!");
        }

    }


    public void paintLivello(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        int gridHeight = grid.length;      // Numero di righe
        int gridWidth = grid[0].length;    // Numero di colonne

        for (int row = 0; row < gridHeight; row++) {
            for (int col = 0; col < gridWidth; col++) {
                int x = col * tileSize;  // Posizione orizzontale rimane invariata
                int y = (gridHeight - 1 - row) * tileSize;
                //int y = row * tileSize;  

                if (grid[row][col].getType() == Tile.TileType.WALL || grid[row][col].getType() == Tile.TileType.PLATFORM) {
                    g2d.drawImage(image, x, y, tileSize, tileSize, null);
                } else {
                    g2d.setColor(Color.BLACK);
                    g2d.fillRect(x, y, tileSize, tileSize);
                }
            }
        }
    }

    public void paintEntita(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        int gridHeight = grid.length ;       // Numero di righe
        //int gridWidth = grid[0].length;    // Numero di colonne

        // Inserimento entità
        for (Entita e : entita) {
            int y = (((gridHeight - 1) * tileSize) - e.getY()) ;
            BufferedImage giocatore;
            BufferedImage bolla;
            BufferedImage nemico;
            if (e instanceof Model.Giocatore) {
                try {
                    giocatore = ImageIO.read(new File(((Giocatore)(e)).getSpritePath()));
                    if (!((Giocatore)(e)).getGoingRight()) {
                        g2d.drawImage(giocatore, e.getX(), y, e.getEntitysize(), e.getEntitysize(), null);
                    } else {
                        g2d.drawImage(giocatore, e.getX() + e.getEntitysize(), y, -e.getEntitysize(), e.getEntitysize(), null);
                    }
                    //g2d.drawImage(gio, e.getX(), y, e.getEntitysize(), e.getEntitysize(), null);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            } else if(e instanceof Model.Bolla){
                // try {
                //     bolla = ImageIO.read(new File(((Bolla)(e)).getSpritePath()));
                //     if (!((Bolla)(e)).getGoingRight()) {
                //         g2d.drawImage(bolla, e.getX(), y, e.getEntitysize(), e.getEntitysize(), null);
                //     } else {
                //         g2d.drawImage(bolla, e.getX() + e.getEntitysize(), y, -e.getEntitysize(), e.getEntitysize(), null);
                //     }
                //     //g2d.drawImage(gio, e.getX(), y, e.getEntitysize(), e.getEntitysize(), null);
                // } catch (IOException ioException) {
                //     ioException.printStackTrace();
                // }
                g2d.setColor(Color.GREEN);
                g2d.fillOval(e.getX(), y, e.getEntitysize(), e.getEntitysize());
            } else{
                try {
                    nemico = ImageIO.read(new File(((Nemico)(e)).getSpritePath()));
                    if (!((Nemico)(e)).getGoingRight()) {
                        g2d.drawImage(nemico, e.getX(), y, e.getEntitysize(), e.getEntitysize(), null);
                    } else {
                        g2d.drawImage(nemico, e.getX() + e.getEntitysize(), y, -e.getEntitysize(), e.getEntitysize(), null);
                    }
                    //g2d.drawImage(gio, e.getX(), y, e.getEntitysize(), e.getEntitysize(), null);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }

            
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintLivello(g);
        paintEntita(g);
    }

    

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
}