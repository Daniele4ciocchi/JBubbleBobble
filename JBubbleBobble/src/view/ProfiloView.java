package view;

import model.Profilo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ProfiloView {
    private Profilo profilo;
    private JFrame frame;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private Point initialClick;
    private Font arcadeFont;
    private JLabel selectedAvatarLabel;

    public ProfiloView(Profilo profilo, List<String> classifica) {
        this.profilo = profilo;
        frame = new JFrame("Profilo Panel");
        
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 500);
        frame.setUndecorated(true); // Remove window decorations
        frame.setFocusable(true);
        frame.requestFocusInWindow();
        frame.setLocationRelativeTo(null);

        // Load Arcade Font
        try {
            arcadeFont = Font.createFont(Font.TRUETYPE_FONT, new File("JBubbleBobble" + File.separator + "src" + File.separator + "resources" + File.separator + "fonts" + File.separator + "ARCADECLASSIC.TTF")).deriveFont(24f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(arcadeFont);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            arcadeFont = new Font("Arial", Font.PLAIN, 24); // Fallback font
        }

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Profilo Panel
        JPanel profiloPanel = new JPanel();
        profiloPanel.setLayout(new BoxLayout(profiloPanel, BoxLayout.Y_AXIS));
        profiloPanel.setBackground(Color.BLACK);
        profiloPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        profiloPanel.add(createCenteredLabel("Nickname: " + profilo.getNickname()));
        profiloPanel.add(createCenteredLabel("Livello: " + profilo.getLivelloProfilo()));
        profiloPanel.add(createCenteredLabel("Partite giocate: " + profilo.getPartite().size()));
        profiloPanel.add(createCenteredLabel("Punti totali: " + profilo.getPuntiTotali()));
        profiloPanel.add(createCenteredLabel("Vittorie: " + profilo.getVinte()));
        profiloPanel.add(createCenteredLabel("Perdite: " + profilo.getPerse()));

        // Avatar Selection Panel
        JPanel avatarPanel = new JPanel();
        avatarPanel.setLayout(new BoxLayout(avatarPanel, BoxLayout.X_AXIS));
        avatarPanel.setBackground(Color.BLACK);
        avatarPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        avatarPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.YELLOW), "Seleziona Avatar", 0, 0, arcadeFont, Color.YELLOW));

        String avatarPath = "JBubbleBobble" + File.separator + "src" + File.separator + "resources" + File.separator + "avatars" + File.separator;
        ButtonGroup avatarGroup = new ButtonGroup();
        JRadioButton avatar1 = createStyledRadioButtonWithImage(avatarPath + "avatar1.png", 1);
        JRadioButton avatar2 = createStyledRadioButtonWithImage(avatarPath + "avatar2.png", 2);
        JRadioButton avatar3 = createStyledRadioButtonWithImage(avatarPath + "avatar3.png", 3);
        JRadioButton avatar4 = createStyledRadioButtonWithImage(avatarPath + "avatar4.png", 4);
        JRadioButton avatar5 = createStyledRadioButtonWithImage(avatarPath + "avatar5.png", 5);

        avatarGroup.add(avatar1);
        avatarGroup.add(avatar2);
        avatarGroup.add(avatar3);
        avatarGroup.add(avatar4);
        avatarGroup.add(avatar5);

        avatarPanel.add(avatar1);
        avatarPanel.add(avatar2);
        avatarPanel.add(avatar3);
        avatarPanel.add(avatar4);
        avatarPanel.add(avatar5);

        profiloPanel.add(avatarPanel);

        // Label to show selected avatar
        selectedAvatarLabel = new JLabel();
        selectedAvatarLabel.setHorizontalAlignment(SwingConstants.CENTER);
        selectedAvatarLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectedAvatarLabel.setPreferredSize(new Dimension(100, 100)); // Set preferred size for the label
        profiloPanel.add(selectedAvatarLabel);

        // Add panels to card layout
        cardPanel.add(profiloPanel, "Profilo");

        // Classifica Panel
        JPanel classificaPanel = new JPanel();
        classificaPanel.setLayout(new BoxLayout(classificaPanel, BoxLayout.Y_AXIS));
        classificaPanel.setBackground(Color.BLACK);
        classificaPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        classificaPanel.add(createCenteredLabel("Classifica:"));
        for (String p : classifica) {
            String[] parts = p.split(":");
            String nickname = parts[0];
            String score = parts[1];
            int avatarIndex = Integer.parseInt(parts[1]); // Assuming the avatar index is stored in the third part

            JPanel entryPanel = new JPanel();
            entryPanel.setLayout(new BoxLayout(entryPanel, BoxLayout.X_AXIS));
            entryPanel.setBackground(Color.BLACK);

            JLabel avatarLabel = new JLabel(new ImageIcon(avatarPath + "avatar" + avatarIndex + ".png"));
            avatarLabel.setPreferredSize(new Dimension(50, 50));

            JLabel nicknameLabel = createCenteredLabel(nickname + ": " + score);
            nicknameLabel.setHorizontalAlignment(SwingConstants.LEFT);

            entryPanel.add(avatarLabel);
            entryPanel.add(Box.createHorizontalStrut(10)); // Add some space between avatar and nickname
            entryPanel.add(nicknameLabel);

            classificaPanel.add(entryPanel);
        }

        cardPanel.add(classificaPanel, "Classifica");

        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.BLACK);
        JLabel titleLabel = createCenteredLabel("Statistiche");
        titleLabel.setFont(arcadeFont.deriveFont(Font.BOLD, 24f));
        titlePanel.add(titleLabel);

        // Control Panel
        JPanel controlPanel = new JPanel();
        controlPanel.setBackground(Color.BLACK);
        JButton profiloButton = createStyledButton("Profilo");
        JButton classificaButton = createStyledButton("Classifica");

        profiloButton.addActionListener(e -> cardLayout.show(cardPanel, "Profilo"));
        classificaButton.addActionListener(e -> cardLayout.show(cardPanel, "Classifica"));

        controlPanel.add(profiloButton);
        controlPanel.add(classificaButton);

        // Close Button Panel
        JPanel closePanel = new JPanel();
        closePanel.setBackground(Color.BLACK);
        closePanel.setLayout(new BoxLayout(closePanel, BoxLayout.Y_AXIS));

        JButton resetButton = createStyledButton("Reset");
        resetButton.setBackground(Color.RED);
        resetButton.setForeground(Color.WHITE);
        resetButton.addActionListener(e -> resetGlobalScores());

        JButton chiudiButton = createStyledButton("Chiudi");
        chiudiButton.addActionListener(e -> frame.dispose());

        closePanel.add(resetButton);
        closePanel.add(Box.createVerticalStrut(10)); // Add some space between buttons
        closePanel.add(chiudiButton);

        // Main Panel
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(titlePanel);
        panel.add(Box.createVerticalStrut(20));
        panel.add(controlPanel);
        panel.add(Box.createVerticalStrut(20));
        panel.add(cardPanel);
        panel.add(Box.createVerticalStrut(20));
        panel.add(closePanel);

        frame.add(panel);
        frame.setShape(new RoundRectangle2D.Double(0, 0, frame.getWidth(), frame.getHeight(), 100, 100));

        // Add mouse listeners for dragging the window
        frame.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
                frame.getComponentAt(initialClick);
            }
        });

        frame.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                // get location of Window
                int thisX = frame.getLocation().x;
                int thisY = frame.getLocation().y;

                // Determine how much the mouse moved since the initial click
                int xMoved = e.getX() - initialClick.x;
                int yMoved = e.getY() - initialClick.y;

                // Move window to this position
                int X = thisX + xMoved;
                int Y = thisY + yMoved;
                frame.setLocation(X, Y);
            }
        });

        frame.setVisible(true);
    }

    private JLabel createCenteredLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.YELLOW);
        label.setBackground(Color.BLACK);
        label.setOpaque(true);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setFont(arcadeFont); // Apply arcade font
        return label;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(Color.YELLOW);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFont(arcadeFont); // Apply arcade font
        return button;
    }

    private JRadioButton createStyledRadioButtonWithImage(String imagePath, int avatarIndex) {
        ImageIcon icon = new ImageIcon(imagePath);
        JRadioButton radioButton = new JRadioButton(icon);
        radioButton.setBackground(Color.BLACK);
        radioButton.setForeground(Color.YELLOW);
        radioButton.setFocusPainted(false);
        radioButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        radioButton.setFont(arcadeFont); // Apply arcade font
        radioButton.setHorizontalTextPosition(SwingConstants.CENTER);
        radioButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        radioButton.addActionListener(new AvatarSelectionListener(avatarIndex, icon));
        return radioButton;
    }

    private void resetGlobalScores() {
        try (FileWriter fw = new FileWriter("global_scores.txt", false)) {
            fw.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class AvatarSelectionListener implements ActionListener {
        private int avatarIndex;
        private ImageIcon avatarIcon;

        public AvatarSelectionListener(int avatarIndex, ImageIcon avatarIcon) {
            this.avatarIndex = avatarIndex;
            this.avatarIcon = avatarIcon;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            profilo.setAvatar(avatarIndex);
            selectedAvatarLabel.setIcon(avatarIcon); // Update the selected avatar label
        }
    }
}