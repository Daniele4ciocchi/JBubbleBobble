package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import model.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

/**
 * Classe che rappresenta la vista della partita.
 */
public class PartitaView extends JPanel implements Observer {

    private Partita partita;
    private BufferedImage image;
    private double scala = 2.0;

    /**
     * Costruttore della classe PartitaView.
     */
    public PartitaView() {super();}

    /**
     * Metodo che imposta i parametri della vista della partita.
     */
    public void setParameters() {
        int doubleTileSize = partita.getLivello().getTilesize() * 2; // Raddoppia il tileSize
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(36 * doubleTileSize , 26 * doubleTileSize));
        revalidate();
        setBackground(Color.BLACK);
    }

    /**
     * Metodo che disegna il livello.
     * @param g il contesto grafico su cui disegnare il livello
     */
    public void paintLivello(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        int gridHeight = partita.getLivello().getGrid().length;
        int gridWidth = partita.getLivello().getGrid()[0].length;

        try {
            image = ImageIO.read(new File(partita.getLivello().getTilePath()));
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Errore nel caricamento delle immagini!");
        }
        g2d.setBackground(Color.BLACK);
        for (int row = gridHeight -1; row >= 0; row--) {
            for (int col = 0; col < gridWidth; col++) {
                int x = col * partita.getLivello().getTilesize() ;
                int y = (gridHeight - 1 - row) * partita.getLivello().getTilesize() ;

                g2d.setColor(Color.BLACK);
                g2d.fillRect(x, y, partita.getLivello().getTilesize() , partita.getLivello().getTilesize() );
            }
        }
        for (int row = gridHeight -1; row >= 0; row--) {
            for (int col = 0; col < gridWidth; col++) {
                int x = col * partita.getLivello().getTilesize() ;
                int y = (gridHeight - 1 - row) * partita.getLivello().getTilesize();

                if (partita.getLivello().getGrid()[row][col].getType() == Tile.TileType.WALL || partita.getLivello().getGrid()[row][col].getType() == Tile.TileType.PLATFORM) {
                    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
                    g2d.drawImage(image, x+5, y+5, partita.getLivello().getTilesize() , partita.getLivello().getTilesize() , null);
                    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
                    g2d.drawImage(image, x, y, partita.getLivello().getTilesize() , partita.getLivello().getTilesize() , null);
                } 
            }
        }
    }

    /**
     * Metodo che disegna le entità.
     * @param g il contesto grafico su cui disegnare le entità
     */
    public void paintEntita(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        int gridHeight = partita.getLivello().getGrid().length;
        int doubleEntitySize = Entita.getEntitysize() * 2;
        BufferedImage giocatore;
        BufferedImage bolla;
        BufferedImage nemico;
        BufferedImage drop;
        BufferedImage acqua;
        BufferedImage fulmine;
        BufferedImage fireball;

        for (Entita e : partita.getEntita().reversed()) {
            int y = (((gridHeight - 1) * partita.getLivello().getTilesize()) - e.getY() - partita.getLivello().getTilesize() ) + 3;
            int x = e.getX()-13;

            if (e instanceof model.Giocatore) {
                try {
                    giocatore = ImageIO.read(new File(((Giocatore) (e)).getSpritePath()));
                    if (!((Giocatore) (e)).isGoingRight()) {
                        if (((Giocatore) (e)).getInvincibilita() % 2 == 1) {
                            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
                            g2d.drawImage(giocatore, x, y, doubleEntitySize, doubleEntitySize, null);
                            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
                        }else {
                            g2d.drawImage(giocatore, x, y, doubleEntitySize, doubleEntitySize, null);
                        }
                    } else {
                        if (((Giocatore) (e)).getInvincibilita() % 2 == 1) {
                            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
                            g2d.drawImage(giocatore, x + doubleEntitySize, y, - doubleEntitySize, doubleEntitySize, null);
                            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
                        }else {
                            g2d.drawImage(giocatore, x + doubleEntitySize, y, - doubleEntitySize, doubleEntitySize, null);
                        }
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            } else if(e instanceof model.Bolla){
                if (((Bolla)(e)).getNemico() != null){
                    try {
                        bolla = ImageIO.read(new File(((Bolla)(e)).getNemico().getSpritePath()));
                        if (!((Bolla)(e)).isGoingRight()) {
                            g2d.drawImage(bolla, x, y, e.getEntitysize(), e.getEntitysize(), null);
                        } else {
                            g2d.drawImage(bolla, x + e.getEntitysize(), y, -e.getEntitysize(), e.getEntitysize(), null);
                        }
                        g2d.drawImage(bolla, x, y, doubleEntitySize, doubleEntitySize, null);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                } else if (e instanceof BollaSemplice){
                    try {
                        bolla = ImageIO.read(new File(((BollaSemplice)(e)).getSpritePath()));
                        if (!((Bolla)(e)).isGoingRight()) {
                            g2d.drawImage(bolla, x, y, doubleEntitySize, doubleEntitySize, null);
                        } else {
                            g2d.drawImage(bolla, x + doubleEntitySize, y, -doubleEntitySize, doubleEntitySize, null);
                        }
                        
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                } else if( e instanceof model.BollaAcqua){
                    try {
                        bolla = ImageIO.read(new File(((BollaAcqua)(e)).getSpritePath()));
                        g2d.drawImage(bolla, x, y, doubleEntitySize, doubleEntitySize, null);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }else if( e instanceof model.BollaFulmine){
                    try {
                        bolla = ImageIO.read(new File(((BollaFulmine)(e)).getSpritePath()));
                        g2d.drawImage(bolla, x, y, doubleEntitySize, doubleEntitySize, null);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                } else if (e instanceof Fireball){
                    try {
                        fireball = ImageIO.read(new File(((Fireball)(e)).getSpritePath()));
                        g2d.drawImage(fireball, x, y, doubleEntitySize, doubleEntitySize, null);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
                else if (e instanceof Boulder){
                    try {
                        fireball = ImageIO.read(new File(((Boulder)(e)).getSpritePath()));
                        g2d.drawImage(fireball, x, y, doubleEntitySize, doubleEntitySize, null);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            } else if(e instanceof model.Nemico){
                try {
                    nemico = ImageIO.read(new File(((Nemico)(e)).getSpritePath()));
                    if (!((Nemico)(e)).isGoingRight()) {
                        g2d.drawImage(nemico, x, y, doubleEntitySize, doubleEntitySize, null);
                    } else {
                        g2d.drawImage(nemico, x + doubleEntitySize, y, -doubleEntitySize, doubleEntitySize, null);
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            } else if(e instanceof model.Item){
                try {
                    drop = ImageIO.read(new File(((Item)(e)).getSpritePath()));
                    g2d.drawImage(drop, x + doubleEntitySize, y, -doubleEntitySize, doubleEntitySize, null);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            } else if(e instanceof model.Acqua){
                try {
                    acqua = ImageIO.read(new File(((Acqua)e).getSpritePath()));
                    for (Acqua.Goccia goccia : ((Acqua)(e)).getGocce()){
                        y = (((gridHeight - 1) * partita.getLivello().getTilesize()) - goccia.getY() - partita.getLivello().getTilesize() );
                        g2d.drawImage(acqua, goccia.getX(), y+25, 10, 10, null);
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } else if(e instanceof model.Fulmine){
                try {
                    fulmine = ImageIO.read(new File(((Fulmine)e).getSpritePath()));
                    y = (((gridHeight - 1) * partita.getLivello().getTilesize()) - e.getY() - partita.getLivello().getTilesize() );
                        g2d.drawImage(fulmine, e.getX(), y+25, Entita.getEntitysize(), Entita.getEntitysize(), null);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        for (Entita e : partita.getEntitaMorte()){
            int y = (((gridHeight - 1) * partita.getLivello().getTilesize()) - e.getY() - partita.getLivello().getTilesize() );
            int x = e.getX()-10;
            if (e instanceof model.Bolla && ((Bolla)(e)).getNemico() != null){
                if (((Bolla)(e)).getNemico().isDead() && ((Bolla)(e)).getNemico().getDeathCounter() > 0){
                    try {
                        nemico = ImageIO.read(new File(((Bolla)(e)).getNemico().getSpritePath()));
                        g2d.drawImage(nemico, x, y, doubleEntitySize, doubleEntitySize, null);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * Metodo che disegna gli elementi.
     * @param g il contesto grafico su cui disegnare gli elementi
     */
    public void paintElement(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        BufferedImage vite;

        Font f = GameView.getFont().deriveFont(30f);
        g2d.setFont(f);
        g2d.setColor(Color.YELLOW);
        if (partita.getLivello().getLevelNum() != 104 && partita.getLivello().getLevelNum() != 105)g2d.drawString("" + partita.getLivello().getLevelNum(), 10, 2*partita.getLivello().getTilesize());

        for (int i = 0; i < ((Giocatore)partita.getEntita().getFirst()).getLife(); i++) {
            try {
                vite = ImageIO.read(new File(("JBubbleBobble" + File.separator + "src" + File.separator + "resources" + File.separator + "sprites" + File.separator + "items" + File.separator + "image_life.png")));
                g2d.drawImage(vite, i * Entita.getEntitysize(), 25*partita.getLivello().getTilesize(), Entita.getEntitysize(), Entita.getEntitysize(), null);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }        
    }

    /**
     * Metodo che restituisce la partita.
     * @return la partita
     */
    public void setPartita(Partita partita) {this.partita = partita;}

    /**
     * Metodo che disegna la partita.
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.scale(scala, scala);
        super.paintComponent(g);
        paintLivello(g);
        paintEntita(g);
        paintElement(g);

    }

    /**
     * Metodo che aggiorna la vista con il pattern Observe Observable
     */
    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
}
