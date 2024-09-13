package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuView {
    private JPanel panel;
    private JButton nuovaPartitaButton;
    private JButton continuaPartitaButton;
    private JButton visualizzaProfiloButton;

    public MenuView() {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(43,51,57));

        ImageIcon titleIcon = new ImageIcon("src/resources/sprites/misc/image_21.png");

        JLabel label = new JLabel(titleIcon);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);


        nuovaPartitaButton = new JButton("Nuova Partita");
        continuaPartitaButton = new JButton("Continua Partita");
        visualizzaProfiloButton = new JButton("Visualizza Profilo");

        nuovaPartitaButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        continuaPartitaButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        visualizzaProfiloButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        nuovaPartitaButton.setBackground(Color.GREEN);

        panel.add(Box.createVerticalStrut(20));
        panel.add(label);
        panel.add(Box.createVerticalStrut(20));
        panel.add(nuovaPartitaButton);
        panel.add(Box.createVerticalStrut(20));
        panel.add(continuaPartitaButton);
        panel.add(Box.createVerticalStrut(20));
        panel.add(visualizzaProfiloButton);
    }

    public JPanel getPanel() {
        return panel;
    }

    public void addNuovaPartitaListener(ActionListener listener) {
        nuovaPartitaButton.addActionListener(listener);
    }

    public void addContinuaPartitaListener(ActionListener listener) {
        continuaPartitaButton.addActionListener(listener);
    }

    public void addVisualizzaProfiloListener(ActionListener listener) {
        visualizzaProfiloButton.addActionListener(listener);
    }
}