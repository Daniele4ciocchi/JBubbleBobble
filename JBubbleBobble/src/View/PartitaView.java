package View;

import Model.Acqua;
import Model.Bolla;
import Model.BollaAcqua;
import Model.BollaFulmine;
import Model.BollaSemplice;
import Model.Boulder;
import Model.Entita;
import Model.Fireball;
import Model.Fulmine;
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
    private double scala = 2.0;
    

    public PartitaView() {
        super();
    }

    public void setParameters() {
        int doubleTileSize = partita.getLivello().getTilesize() * 2; // Raddoppia il tileSize
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(36 * doubleTileSize , 26 * doubleTileSize));
        revalidate();
        setBackground(Color.BLACK);
    }

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
                int y = (gridHeight - 1 - row) * partita.getLivello().getTilesize() ;

                
                if (partita.getLivello().getGrid()[row][col].getType() == Tile.TileType.WALL || partita.getLivello().getGrid()[row][col].getType() == Tile.TileType.PLATFORM) {
                    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
                    g2d.drawImage(image, x+5, y+5, partita.getLivello().getTilesize() , partita.getLivello().getTilesize() , null);
                    //g2d.setColor(Color.GRAY);
                    //g2d.fillRect(x+5, y+5, partita.getLivello().getTilesize() , partita.getLivello().getTilesize() );
                    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
                    g2d.drawImage(image, x, y, partita.getLivello().getTilesize() , partita.getLivello().getTilesize() , null);
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
        BufferedImage acqua;
        BufferedImage fulmine;
        BufferedImage fireball;
        BufferedImage specialItem;

        for (Entita e : partita.getEntita().reversed()) {
            int y = (((gridHeight - 1) * partita.getLivello().getTilesize()) - e.getY() - partita.getLivello().getTilesize() ) + 3;
            int x = e.getX()-13;

            if (e instanceof Model.Giocatore) {
                try {
                    giocatore = ImageIO.read(new File(((Giocatore) (e)).getSpritePath()));
                    if (!((Giocatore) (e)).getGoingRight()) {
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
            } else if(e instanceof Model.Bolla){
                // try {
                //     bolla = ImageIO.read(new File(((Bolla)(e)).getSpritePath()));
                //     if (!((Bolla)(e)).getGoingRight()) {
                //         g2d.drawImage(bolla, x, y, e.getEntitysize(), e.getEntitysize(), null);
                //     } else {
                //         g2d.drawImage(bolla, x + e.getEntitysize(), y, -e.getEntitysize(), e.getEntitysize(), null);
                //     }
                //     //g2d.drawImage(gio, x, y, e.getEntitysize(), e.getEntitysize(), null);
                // } catch (IOException ioException) {
                //     ioException.printStackTrace();
                // }

                if (((Bolla)(e)).getNemico() != null){
                    try {
                        bolla = ImageIO.read(new File(((Bolla)(e)).getNemico().getSpritePath()));
                        if (!((Bolla)(e)).getGoingRight()) {
                            g2d.drawImage(bolla, x, y, e.getEntitysize(), e.getEntitysize(), null);
                        } else {
                            g2d.drawImage(bolla, x + e.getEntitysize(), y, -e.getEntitysize(), e.getEntitysize(), null);
                        }
                        g2d.drawImage(bolla, x, y, doubleEntitySize, doubleEntitySize, null);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    //g2d.setColor(Color.GRAY);
                    // g2d.fillOval(x, y,doubleEntitySize, doubleEntitySize);
                } else if (e instanceof BollaSemplice){
                    try {
                        bolla = ImageIO.read(new File(((BollaSemplice)(e)).getSpritePath()));
                        if (!((Bolla)(e)).getGoingRight()) {
                            g2d.drawImage(bolla, x, y, doubleEntitySize, doubleEntitySize, null);
                        } else {
                            g2d.drawImage(bolla, x + doubleEntitySize, y, -doubleEntitySize, doubleEntitySize, null);
                        }
                        
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                } else if( e instanceof Model.BollaAcqua){
                    try {
                        bolla = ImageIO.read(new File(((BollaAcqua)(e)).getSpritePath()));
                        g2d.drawImage(bolla, x, y, doubleEntitySize, doubleEntitySize, null);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }else if( e instanceof Model.BollaFulmine){
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
                //g2d.setColor(Color.GREEN);
                //g2d.fillOval(x, y,doubleEntitySize, doubleEntitySize);
            } else if(e instanceof Model.Nemico){
                try {
                    nemico = ImageIO.read(new File(((Nemico)(e)).getSpritePath()));
                    if (!((Nemico)(e)).getGoingRight()) {
                        g2d.drawImage(nemico, x, y, doubleEntitySize, doubleEntitySize, null);
                    } else {
                        g2d.drawImage(nemico, x + doubleEntitySize, y, -doubleEntitySize, doubleEntitySize, null);
                    }
                    //g2d.drawImage(gio, x, y, e.getEntitysize(), e.getEntitysize(), null);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            } else if(e instanceof Model.Item){
                try {
                    //System.out.println(((PointItem)(e)).getSpritePath());
                    drop = ImageIO.read(new File(((Item)(e)).getSpritePath()));
                    //drop = ImageIO.read(new File("JBubbleBobble/src/resources/sprites/items/image_68.png"));
                    g2d.drawImage(drop, x + doubleEntitySize, y, -doubleEntitySize, doubleEntitySize, null);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            } else if(e instanceof Model.Acqua){
                try {
                    acqua = ImageIO.read(new File(((Acqua)e).getSpritePath()));
                    for (Acqua.Goccia goccia : ((Acqua)(e)).getGocce()){
                        y = (((gridHeight - 1) * partita.getLivello().getTilesize()) - goccia.getY() - partita.getLivello().getTilesize() );
                        g2d.drawImage(acqua, goccia.getX(), y+25, 10, 10, null);
                    }
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            } else if(e instanceof Model.Fulmine){
                try {
                    fulmine = ImageIO.read(new File(((Fulmine)e).getSpritePath()));
                    y = (((gridHeight - 1) * partita.getLivello().getTilesize()) - e.getY() - partita.getLivello().getTilesize() );
                        g2d.drawImage(fulmine, e.getX(), y+25, Entita.getEntitysize(), Entita.getEntitysize(), null);
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        }
        for (Entita e : partita.getEntitaMorte()){
            int y = (((gridHeight - 1) * partita.getLivello().getTilesize()) - e.getY() - partita.getLivello().getTilesize() );
            int x = e.getX()-10;
            if (e instanceof Model.Bolla && ((Bolla)(e)).getNemico() != null){
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

    public void paintElement(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        BufferedImage vite;

        
        Font f = GameView.getFont().deriveFont(30f);
        g2d.setFont(f);
        g2d.setColor(Color.YELLOW);
        if (partita.getLivello().getLevelNum() != 104)g2d.drawString("" + partita.getLivello().getLevelNum(), 10, 2*partita.getLivello().getTilesize());

        for (int i = 0; i < ((Giocatore)partita.getEntita().getFirst()).getLife(); i++) {
            try {
                vite = ImageIO.read(new File(("JBubbleBobble" + File.separator + "src" + File.separator + "resources" + File.separator + "sprites" + File.separator + "items" + File.separator + "image_life.png")));
                g2d.drawImage(vite, i * Entita.getEntitysize(), 25*partita.getLivello().getTilesize(), Entita.getEntitysize(), Entita.getEntitysize(), null);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

        BufferedImage pink_candy;
        BufferedImage yellow_candy;
        BufferedImage blue_candy;
        BufferedImage pink_ring;
        BufferedImage yellow_ring;
        BufferedImage blue_ring;
        BufferedImage sneaker;

        String baseSpritePath = "JBubbleBobble" + File.separator + "src" + File.separator + "resources" + File.separator + "sprites" + File.separator + "items" + File.separator;

        try {
            pink_candy = ImageIO.read(new File(( baseSpritePath + "pink_candy.png")));
            yellow_candy = ImageIO.read(new File(( baseSpritePath + "yellow_candy.png")));
            blue_candy = ImageIO.read(new File(( baseSpritePath + "blue_candy.png")));
            pink_ring = ImageIO.read(new File(( baseSpritePath + "pink_ring.png")));
            yellow_ring = ImageIO.read(new File(( baseSpritePath + "blue_ring.png")));
            blue_ring = ImageIO.read(new File(( baseSpritePath + "red_ring.png")));
            sneaker = ImageIO.read(new File(( baseSpritePath + "sneaker.png")));

            for (int i = 28; i< 35; i++){ 
                g2d.setColor(Color.BLACK);
                g2d.fillOval(i * Entita.getEntitysize(), 25*partita.getLivello().getTilesize(), 17, 17);
                
            }
            g2d.drawImage(pink_candy, 28 * Entita.getEntitysize(), 25*partita.getLivello().getTilesize(), Entita.getEntitysize(), Entita.getEntitysize(), null);
            g2d.drawImage(yellow_candy, 29 * Entita.getEntitysize(), 25*partita.getLivello().getTilesize(), Entita.getEntitysize(), Entita.getEntitysize(), null);
            g2d.drawImage(blue_candy, 30 * Entita.getEntitysize(), 25*partita.getLivello().getTilesize(), Entita.getEntitysize(), Entita.getEntitysize(), null);
            g2d.drawImage(pink_ring, 31 * Entita.getEntitysize(), 25*partita.getLivello().getTilesize(), Entita.getEntitysize(), Entita.getEntitysize(), null);
            g2d.drawImage(yellow_ring, 32 * Entita.getEntitysize(), 25*partita.getLivello().getTilesize(), Entita.getEntitysize(), Entita.getEntitysize(), null);
            g2d.drawImage(blue_ring, 33 * Entita.getEntitysize(), 25*partita.getLivello().getTilesize(), Entita.getEntitysize(), Entita.getEntitysize(), null);
            g2d.drawImage(sneaker, 34 * Entita.getEntitysize(), 25*partita.getLivello().getTilesize(), Entita.getEntitysize(), Entita.getEntitysize(), null);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        
    }

    public void setPartita(Partita partita) {
        this.partita = partita;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.scale(scala, scala);
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
