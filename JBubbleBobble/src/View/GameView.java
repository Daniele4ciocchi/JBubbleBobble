package View;

import Model.Tile;

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

    public void drawLevel(Tile[][] level, String imagePath) {
        // Rimuoviamo la vecchia griglia dei tile senza rimuovere altri componenti
        Component[] components = panel.getComponents();
        for (Component comp : components) {
            if (comp instanceof JLabel && comp.getName() != null && comp.getName().equals("TileGrid")) {
                panel.remove(comp);
            }
        }

        // Carichiamo l'immagine del tile
        ImageIcon tileIcon = new ImageIcon(imagePath);
        Image tileImage = tileIcon.getImage(); // Otteniamo l'immagine
        int tileWidth = tileImage.getWidth(null); // Larghezza del singolo tile
        int tileHeight = tileImage.getHeight(null); // Altezza del singolo tile

        // Crea un JLabel per l'immagine di sfondo e configuralo con un layout Grid
        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setName("TileGrid");
        backgroundLabel.setLayout(new GridLayout(level.length, level[0].length));

        // Cicla sulla griglia di tile e aggiungi l'immagine corretta per ogni tile
        for (int i = 0; i < level.length; i++) {
            for (int j = 0; j < level[i].length; j++) {
                JLabel tileLabel = new JLabel();

                // Crea una nuova immagine per ogni tile, posizionata in base al tipo
                if (level[i][j].getType() == Tile.TileType.WALL) {
                    tileLabel.setIcon(new ImageIcon(tileImage)); // Imposta l'immagine per il muro
                } else if (level[i][j].getType() == Tile.TileType.PLATFORM) {
                    tileLabel.setIcon(new ImageIcon(tileImage)); // Imposta l'immagine per la piattaforma
                } else {
                    tileLabel.setIcon(new ImageIcon(tileImage)); // Usa l'immagine di base per altri tipi
                }

                // Impostiamo le dimensioni per fare in modo che ogni tile occupi lo spazio giusto
                tileLabel.setPreferredSize(new Dimension(tileWidth, tileHeight));
                tileLabel.setHorizontalAlignment(SwingConstants.CENTER);
                tileLabel.setVerticalAlignment(SwingConstants.CENTER);

                // Aggiungi il tile all'etichetta di sfondo
                backgroundLabel.add(tileLabel);
            }
        }

        // Aggiungi l'etichetta di sfondo al pannello
        panel.add(backgroundLabel, BorderLayout.CENTER);

        // Rende il pannello valido e lo ridisegna
        panel.revalidate();
        panel.repaint();
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
