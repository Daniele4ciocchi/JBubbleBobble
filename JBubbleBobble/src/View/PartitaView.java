package View;

import Model.Bolla;
import Model.BollaSemplice;
import Model.Entita;
import Model.Giocatore;
import Model.Item;
import Model.Nemico;
import Model.Partita;
import Model.Tile;
import Model.PointItem;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class PartitaView extends JPanel implements Observer {

    private Partita partita;
    private BufferedImage image;

    

    public PartitaView() {
        super();
    }

    public void setParameters() {
        int doubleTileSize = partita.getLivello().getTilesize() * 2; // Raddoppia il tileSize
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(36 * doubleTileSize, 26 * doubleTileSize));
        setBackground(Color.BLACK);
    }

    public void paintLivello(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        int gridHeight = partita.getLivello().getGrid().length;
        int gridWidth = partita.getLivello().getGrid()[0].length;
         // Raddoppia il tileSize

        try {
            image = ImageIO.read(new File(partita.getLivello().getTilePath()));
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Errore nel caricamento delle immagini!");
        }

        for (int row = 0; row < gridHeight; row++) {
            for (int col = 0; col < gridWidth; col++) {
                int x = col * partita.getLivello().getTilesize() ;
                int y = (gridHeight - 1 - row) * partita.getLivello().getTilesize() ;

                if (partita.getLivello().getGrid()[row][col].getType() == Tile.TileType.WALL || partita.getLivello().getGrid()[row][col].getType() == Tile.TileType.PLATFORM) {
                    g2d.drawImage(image, x, y, partita.getLivello().getTilesize() , partita.getLivello().getTilesize() , null);
                } else {
                    g2d.setColor(Color.BLACK);
                    g2d.fillRect(x, y, partita.getLivello().getTilesize() , partita.getLivello().getTilesize() );
                }
            }
        }
    }

    public void paintEntita(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        int gridHeight = partita.getLivello().getGrid().length;
        int doubleEntitySize = Entita.getEntitysize() * 2;
        BufferedImage giocatore;
        BufferedImage bolla;
        BufferedImage nemico;
        BufferedImage drop;

        for (Entita e : partita.getEntita().reversed()) {
            int y = (((gridHeight - 1) * partita.getLivello().getTilesize()) - e.getY() - partita.getLivello().getTilesize() );
            if (e instanceof Model.Giocatore) {
                try {
                    giocatore = ImageIO.read(new File(((Giocatore) (e)).getSpritePath()));
                    if (!((Giocatore) (e)).getGoingRight()) {
                        g2d.drawImage(giocatore, e.getX(), y, doubleEntitySize, doubleEntitySize, null);
                    } else {
                        g2d.drawImage(giocatore, e.getX() + doubleEntitySize, y, - doubleEntitySize, doubleEntitySize, null);
                    }
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

                if (((Bolla)(e)).getNemico() != null){
                    try {
                        bolla = ImageIO.read(new File(((Bolla)(e)).getNemico().getSpritePath()));
                        if (!((Bolla)(e)).getGoingRight()) {
                            g2d.drawImage(bolla, e.getX(), y, e.getEntitysize(), e.getEntitysize(), null);
                        } else {
                            g2d.drawImage(bolla, e.getX() + e.getEntitysize(), y, -e.getEntitysize(), e.getEntitysize(), null);
                        }
                        g2d.drawImage(bolla, e.getX(), y, doubleEntitySize, doubleEntitySize, null);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    //g2d.setColor(Color.GRAY);
                    // g2d.fillOval(e.getX(), y,doubleEntitySize, doubleEntitySize);
                } else{
                    try {
                        bolla = ImageIO.read(new File(((BollaSemplice)(e)).getSpritePath()));
                        if (!((Bolla)(e)).getGoingRight()) {
                            g2d.drawImage(bolla, e.getX(), y, doubleEntitySize, doubleEntitySize, null);
                        } else {
                            g2d.drawImage(bolla, e.getX() + doubleEntitySize, y, -doubleEntitySize, doubleEntitySize, null);
                        }
                        //g2d.drawImage(gio, e.getX(), y, e.getEntitysize(), e.getEntitysize(), null);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
                //g2d.setColor(Color.GREEN);
                //g2d.fillOval(e.getX(), y,doubleEntitySize, doubleEntitySize);
            } else if(e instanceof Model.Nemico){
                try {
                    nemico = ImageIO.read(new File(((Nemico)(e)).getSpritePath()));
                    if (!((Nemico)(e)).getGoingRight()) {
                        g2d.drawImage(nemico, e.getX(), y, doubleEntitySize, doubleEntitySize, null);
                    } else {
                        g2d.drawImage(nemico, e.getX() + doubleEntitySize, y, -doubleEntitySize, doubleEntitySize, null);
                    }
                    //g2d.drawImage(gio, e.getX(), y, e.getEntitysize(), e.getEntitysize(), null);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            } else if(e instanceof Model.PointItem){
                try {
                    //System.out.println(((PointItem)(e)).getSpritePath());
                    drop = ImageIO.read(new File(((Item)(e)).getSpritePath()));
                    //drop = ImageIO.read(new File("JBubbleBobble/src/resources/sprites/items/image_68.png"));
                    g2d.drawImage(drop, e.getX() + doubleEntitySize, y, -doubleEntitySize, doubleEntitySize, null);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
    }

    public void paintElement(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        BufferedImage vite;

        
        Font f = GameView.getFont().deriveFont(30f);
        g2d.setFont(f);
        g2d.setColor(Color.YELLOW);
        g2d.drawString("" + partita.getLivello().getLevelNum(), 10, 2*partita.getLivello().getTilesize());

        for (int i = 0; i < ((Giocatore)partita.getEntita().getFirst()).getLife(); i++) {
            try {
                vite = ImageIO.read(new File(("JBubbleBobble" + File.separator + "src" + File.separator + "resources" + File.separator + "sprites" + File.separator + "items" + File.separator + "image_life.png")));
                g2d.drawImage(vite, i * Entita.getEntitysize(), 25*partita.getLivello().getTilesize(), Entita.getEntitysize(), Entita.getEntitysize(), null);
            } catch (IOException ioException) {
                ioException.printStackTrace();
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
        paintElement(g);
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
}
