package view;

import javax.swing.*;
import model.Profilo;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.RoundRectangle2D;
import java.util.List;

public class ProfiloView {
    private Profilo profilo;
    private JFrame frame;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private Point initialClick;

    public ProfiloView(Profilo profilo, List<String> classifica) {
        this.profilo = profilo;
        frame = new JFrame("Profilo Panel");
        
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setUndecorated(true); // Remove window decorations
        frame.setFocusable(true);
        frame.requestFocusInWindow();
        frame.setLocationRelativeTo(null);

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

        // Classifica Panel
        JPanel classificaPanel = new JPanel();
        classificaPanel.setLayout(new BoxLayout(classificaPanel, BoxLayout.Y_AXIS));
        classificaPanel.setBackground(Color.BLACK);
        classificaPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        classificaPanel.add(createCenteredLabel("Classifica:"));
        for (String p : classifica) {
            classificaPanel.add(createCenteredLabel(p));
        }

        // Add panels to card layout
        cardPanel.add(profiloPanel, "Profilo");
        cardPanel.add(classificaPanel, "Classifica");

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
        JButton chiudiButton = createStyledButton("Chiudi");
        chiudiButton.addActionListener(e -> frame.dispose());
        closePanel.add(chiudiButton);

        frame.setLayout(new BorderLayout());
        frame.add(controlPanel, BorderLayout.NORTH);
        frame.add(cardPanel, BorderLayout.CENTER);
        frame.add(closePanel, BorderLayout.SOUTH);
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
        return label;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(Color.YELLOW);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        return button;
    }
}