package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuView {
    private JPanel panel;
    private JButton nuovaPartitaButton;
    private JButton continuaPartitaButton;
    private JButton visualizzaProfiloButton;
    private JTextField passwordField;

    public MenuView() {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Menu");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        nuovaPartitaButton = new JButton("Nuova Partita");
        continuaPartitaButton = new JButton("Continua Partita");
        visualizzaProfiloButton = new JButton("Visualizza Profilo");
        passwordField = new JTextField(20);

        nuovaPartitaButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        continuaPartitaButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        visualizzaProfiloButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);

        nuovaPartitaButton.setBackground(Color.GREEN);

        panel.add(Box.createVerticalStrut(20));
        panel.add(label);
        panel.add(Box.createVerticalStrut(20));
        panel.add(nuovaPartitaButton);
        panel.add(Box.createVerticalStrut(20));
        panel.add(continuaPartitaButton);
        panel.add(Box.createVerticalStrut(20));
        panel.add(passwordField);
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

    public String getPasswordInput() {
        return passwordField.getText();
    }
}