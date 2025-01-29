package view;

import javax.imageio.ImageIO;
import javax.swing.*;

import controller.MenuController;
import model.Bolla;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MenuView {
    public static JFrame frame;
    private JPanel panel;
    private JButton nuovaPartitaButton;
    private JButton continuaPartitaButton;
    private JButton visualizzaProfiloButton;
    private JTextField passwordField;
    private JLabel usernameLabel;
    private JTextField usernameField;
    private static Font arcadeFont;

    public MenuView() {
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
        frame.setShape(new RoundRectangle2D.Double(0, 0, frame.getWidth(), frame.getHeight(), 50, 50)); // Set rounded corners
        frame.setFont(arcadeFont); // 


        // Visualizzazione del frame
        frame.setVisible(true);

        

        // Creazione del pulsante "Esci"
        JButton esciButton = createStyledButton("Esci");
        esciButton.addActionListener(e -> System.exit(0));
        esciButton.setPreferredSize(new Dimension(200,20));
        esciButton.setMaximumSize(new Dimension(200,20));
        esciButton.setMinimumSize(new Dimension(200,20));
        
        
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setFont(GameView.getFont());
        panel.setBackground(Color.BLACK);

        Font font = GameView.getFont();

        BufferedImage icon = null;

        panel.setFont(font);

        try {
            icon = ImageIO.read(new File("JBubbleBobble" + File.separator + "src" + File.separator + "resources" + File.separator + "sprites"+ File.separator + "misc" + File.separator + "image_21.png"));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        JLabel label = new JLabel(new ImageIcon(icon));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        nuovaPartitaButton = new JButton("Nuova Partita");
        continuaPartitaButton = new JButton("Continua Partita");
        visualizzaProfiloButton = new JButton("Visualizza Profilo");

        usernameLabel = new JLabel("username");
        usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        usernameLabel.setPreferredSize(new Dimension(200,20));
        usernameLabel.setMaximumSize(new Dimension(200,20));
        usernameLabel.setMinimumSize(new Dimension(200,20));
        usernameLabel.setFont(font);
        usernameLabel.setBackground(Color.BLACK);
        usernameLabel.setForeground(Color.YELLOW);

        String lastNickname = "";
        try (BufferedReader br = new BufferedReader(new FileReader("global_scores.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
            lastNickname = line.split(":")[0];
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        usernameField = new JTextField(lastNickname,2);

        usernameField.setPreferredSize(new Dimension(200,20));
        usernameField.setMaximumSize(new Dimension(200,20));
        usernameField.setMinimumSize(new Dimension(200,20));
        usernameField.setFont(font);
        usernameField.setBackground(Color.BLACK);
        usernameField.setForeground(Color.YELLOW);
        usernameField.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        

        passwordField = new JTextField(2);
        //passwordField.setHorizontalAlignment(JTextField.CENTER);
        passwordField.setPreferredSize(new Dimension(200,20));
        passwordField.setMaximumSize(new Dimension(200,20));
        passwordField.setMinimumSize(new Dimension(200,20));
        passwordField.setFont(font);
        passwordField.setBackground(Color.BLACK);
        passwordField.setForeground(Color.YELLOW);
        passwordField.setBorder(BorderFactory.createLineBorder(Color.YELLOW));

        nuovaPartitaButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        nuovaPartitaButton.setFont(font);
        nuovaPartitaButton.setBackground(Color.YELLOW);
        nuovaPartitaButton.setForeground(Color.BLACK);
        nuovaPartitaButton.setPreferredSize(new Dimension(200, 20));
        nuovaPartitaButton.setMaximumSize(new Dimension(200, 20));
        nuovaPartitaButton.setMinimumSize(new Dimension(200, 20));

        continuaPartitaButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        continuaPartitaButton.setFont(font);
        continuaPartitaButton.setBackground(Color.YELLOW);
        continuaPartitaButton.setForeground(Color.BLACK);
        continuaPartitaButton.setPreferredSize(new Dimension(200, 20));
        continuaPartitaButton.setMaximumSize(new Dimension(200, 20));
        continuaPartitaButton.setMinimumSize(new Dimension(200, 20));

        visualizzaProfiloButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        visualizzaProfiloButton.setFont(font);
        visualizzaProfiloButton.setBackground(Color.YELLOW);
        visualizzaProfiloButton.setForeground(Color.BLACK);
        visualizzaProfiloButton.setPreferredSize(new Dimension(200, 20));
        visualizzaProfiloButton.setMaximumSize(new Dimension(200, 20));
        visualizzaProfiloButton.setMinimumSize(new Dimension(200, 20));

        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordField.setFont(font);

        //nuovaPartitaButton.setBackground(Color.GREEN);
        
        panel.add(Box.createVerticalStrut(20));
        panel.add(label);
        panel.add(Box.createVerticalStrut(20));
        panel.add(nuovaPartitaButton);
        panel.add(Box.createVerticalStrut(20));
        panel.add(continuaPartitaButton);
        panel.add(passwordField);

        panel.add(Box.createVerticalStrut(20));
        panel.add(visualizzaProfiloButton);
        panel.add(Box.createVerticalStrut(20));
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(Box.createVerticalGlue()); // Add vertical glue to push the button to the bottom
         // Add some space below the button


        // simpatika gif :)
        ImageIcon gifIcon = new ImageIcon("JBubbleBobble" + File.separator + "src" + File.separator + "resources" + File.separator + "sprites" + File.separator + "misc" + File.separator + "animated.gif");
        JLabel gifLabel = new JLabel(gifIcon);
        gifLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        gifLabel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        panel.add(Box.createVerticalGlue());
        panel.add(gifLabel);
        gifLabel.setPreferredSize(new Dimension(100, 100));
        gifLabel.setMaximumSize(new Dimension(100, 100));
        gifLabel.setMinimumSize(new Dimension(100, 100));
        gifLabel.setIcon(new ImageIcon(gifIcon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
        
        panel.add(esciButton);
        
        frame.add(panel);
    }   

    public JPanel getPanel() {
        return panel;
    }


    public void addNuovaPartitaListener(ActionListener listener) {nuovaPartitaButton.addActionListener(listener);}
    public void addContinuaPartitaListener(ActionListener listener) {continuaPartitaButton.addActionListener(listener);}
    public void addVisualizzaProfiloListener(ActionListener listener) {visualizzaProfiloButton.addActionListener(listener);}

    public String getUsernameInput() {
        return usernameField.getText();
    }
    public String getPasswordInput() {
        return passwordField.getText();
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