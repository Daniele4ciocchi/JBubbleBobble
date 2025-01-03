package View;

import Model.Entita;
import Model.Giocatore;
import Model.Livello;
import Model.Partita;
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
import java.util.Optional;

public class PartitaView extends JPanel implements Observer {

    private Partita partita;
    
    BufferedImage image;

    public PartitaView() {
        super();
    }

    public void setParameters() {
        setPreferredSize(new Dimension(36 * partita.getLivello().getTilesize(), 26 * partita.getLivello().getTilesize()));
        setBackground(Color.BLACK);
    }
        


    public void paintLivello(Graphics g) {


        Graphics2D g2d = (Graphics2D) g;
        int gridHeight = partita.getLivello().getGrid().length;      // Numero di righe
        int gridWidth = partita.getLivello().getGrid()[0].length;    // Numero di colonne

        try {
            image = ImageIO.read(new File(partita.getLivello().getTilePath()));
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Errore nel caricamento delle immagini!");
        }

        for (int row = 0; row < gridHeight; row++) {
            for (int col = 0; col < gridWidth; col++) {
                int x = col * partita.getLivello().getTilesize();  // Posizione orizzontale rimane invariata
                int y = (gridHeight - 1 - row) * partita.getLivello().getTilesize();
                //int y = row * l.getTilesize();  

                if (partita.getLivello().getGrid()[row][col].getType() == Tile.TileType.WALL || partita.getLivello().getGrid()[row][col].getType() == Tile.TileType.PLATFORM) {
                    g2d.drawImage(image, x, y, partita.getLivello().getTilesize(), partita.getLivello().getTilesize(), null);
                } else {
                    g2d.setColor(Color.BLACK);
                    g2d.fillRect(x, y, partita.getLivello().getTilesize(), partita.getLivello().getTilesize());
                }
            }
        }
    }

    public void paintEntita(Graphics g) {
        
        Graphics2D g2d = (Graphics2D) g;
        int gridHeight = partita.getLivello().getGrid().length ;       // Numero di righe
        //int gridWidth = grid[0].length;           // Numero di colonne

        // Inserimento entità
        for (Entita e : partita.getEntita()) {
            int y = (((gridHeight - 1) * partita.getLivello().getTilesize()) - e.getY()) ;
            BufferedImage gio;
            if (e instanceof Model.Giocatore) {
                try {
                    gio = ImageIO.read(new File(((Giocatore)(e)).getSpritePath()));
                    if (!((Giocatore)(e)).getGoingRight()) {
                        g2d.drawImage(gio, e.getX(), y, e.getEntitysize(), e.getEntitysize(), null);
                    } else {
                        g2d.drawImage(gio, e.getX() + e.getEntitysize(), y, -e.getEntitysize(), e.getEntitysize(), null);
                    }
                    //g2d.drawImage(gio, e.getX(), y, e.getEntitysize(), e.getEntitysize(), null);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }else{
                g2d.setColor(Color.RED);
                g2d.fillRect(e.getX(), y, e.getEntitysize(), e.getEntitysize());
            }

            
        }
    }

    public void setPartita(Partita partita) {
        this.partita = partita;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintLivello(g);
        paintEntita(g);
    }

    @Override
    public void update(Observable o, Object arg) {
        this.repaint();
    }

}