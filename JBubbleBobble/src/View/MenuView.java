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
        panel.setLayout(new GridLayout(3, 1));

        nuovaPartitaButton = new JButton("Nuova Partita");
        continuaPartitaButton = new JButton("Continua Partita");
        visualizzaProfiloButton = new JButton("Visualizza Profilo");

        panel.add(nuovaPartitaButton);
        panel.add(continuaPartitaButton);
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