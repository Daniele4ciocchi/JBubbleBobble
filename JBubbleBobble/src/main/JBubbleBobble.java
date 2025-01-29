package main;

import model.*;
import view.MenuView;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.LineBorder;

import controller.MenuController;

public class JBubbleBobble {
    public static JFrame frame;
    private static Font arcadeFont;

    public static void main(String[] args) {
        // Gestione del profilo
        // Profilo.getInstance();
        // try {
        //     Profilo.getInstance().loadProfilo("JBubbleBobble" + File.separator + "src" + File.separator + "profiles.txt");
        // } catch (ClassNotFoundException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // } catch (IOException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }

        // Load Arcade Font
        try {
            arcadeFont = Font.createFont(Font.TRUETYPE_FONT, new File("JBubbleBobble" + File.separator + "src" + File.separator + "resources" + File.separator + "fonts" + File.separator + "ARCADECLASSIC.TTF")).deriveFont(20f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(arcadeFont);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            arcadeFont = new Font("Arial", Font.PLAIN, 24); // Fallback font
        }

        // Creazione del frame
        frame = new JFrame("Esempio di JPanel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setUndecorated(true);
        frame.setShape(new RoundRectangle2D.Double(0, 0, frame.getWidth(), frame.getHeight(), 100, 100)); // Set rounded corners
        frame.setFont(arcadeFont); // 

        MenuView menuview = new MenuView();
        MenuController menucontroller = new MenuController(menuview);
        frame.setContentPane(menucontroller.getview().getPanel());

        // Creazione del pulsante "Esci"
        JButton esciButton = createStyledButton("Esci");
        esciButton.addActionListener(e -> System.exit(0));
        esciButton.setPreferredSize(new Dimension(200,20));
        esciButton.setMaximumSize(new Dimension(200,20));
        esciButton.setMinimumSize(new Dimension(200,20));

        // Aggiunta del pulsante "Esci" al pannello principale
        JPanel panel = menucontroller.getview().getPanel();
        panel.add(Box.createVerticalGlue()); // Add vertical glue to push the button to the bottom
        panel.add(esciButton);
        panel.add(Box.createVerticalStrut(10)); // Add some space below the button

        // Visualizzazione del frame
        frame.setVisible(true);

        // TEST: stampo a terminale tutti i migliori 10 score
        System.out.println(Profilo.getInstance().getBestScores());
    }

    private static JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(Color.YELLOW);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFont(arcadeFont); // Apply arcade font
        return button;
    }
}