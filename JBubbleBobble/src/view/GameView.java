package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.WindowAdapter;
import java.io.File;
import java.io.IOException;

/**
 * Classe che rappresenta la vista del gioco.
 */
public class GameView  {
    private JFrame frame = new JFrame("Bubble Bobble MVC Game");
    private PartitaView partita ;
    private TopPanel topPanel;
    private JLabel scoreLabel;
    private JButton pauseButton;
    private JButton exitButton;

    private static Font customFont;

    /**
     * Costruttore della classe GameView.
     */
    public GameView() {
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1152, 920);
        frame.setVisible(true);
        frame.setFocusable(true);
        frame.requestFocusInWindow();
        frame.setLocationRelativeTo(null);
        
        try {
            customFont = Font.createFont(Font.PLAIN, new File("JBubbleBobble" + File.separator + "src" + File.separator + "resources" + File.separator + "fonts" + File.separator + "ARCADECLASSIC.TTF"));
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            customFont = new Font("Serif", Font.PLAIN, 24); // Fallback font
        }
    }

    /**
     * Metodo che aggiunge il pannello della partita alla finestra.
     * @param partita il pannello della partita
     */
    public void addPartitaPanel(PartitaView partita) {
        this.partita = partita;
        frame.add(partita, BorderLayout.CENTER);
    }

    /**
     * Metodo che aggiunge il pannello superiore alla finestra.
     * @param TopPanel il pannello superiore
     */
    public void addTopPanel(TopPanel TopPanel) {
        this.topPanel = TopPanel;
        frame.add(TopPanel, BorderLayout.NORTH);
    }
    
    /**
     * Metodo che aggiunge un keylistener 
     * @param  keyAdapter il keyAdapter da aggiungere
     */
    public void addKeyListener(KeyAdapter keyAdapter) {frame.addKeyListener(keyAdapter);}

    /**
     * Metodo che ritorna il Panel della partita   
     * @param partita il pannello della partita
     */
    public PartitaView getPanel() { return partita;}

    /**
     * metodo che ritorna il topPanel
     * @return il topPanel
     */
    public TopPanel getTopPanel() { return topPanel;}

    /**
     * Metodo che ritorna il frame
     * @return il frame
     */
    public JFrame getFrame() {return frame;}

    /**
     * Metodo che ritorna il font personalizzato
     * @return il font personalizzato
     */
    public static Font getFont() {return customFont;}
    
    /**
     * Metodo che aggiorna lo score nel pannello superiore.
     * @param score il punteggio da aggiornare
     */
    public void updateScore(int score) {scoreLabel.setText("Score: " + score);}

    /**
     * Metodo che aggiunge un WindowListener alla finestra.
     * @param windowAdapter il WindowListener da aggiungere
     */
    public void addWindowListener(WindowAdapter windowAdapter) {
        frame.addWindowListener(windowAdapter);
    }
}
