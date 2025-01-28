package view;

import javax.imageio.ImageIO;
import javax.swing.*;

import model.Bolla;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MenuView {
    private JPanel panel;
    private JButton nuovaPartitaButton;
    private JButton continuaPartitaButton;
    private JButton visualizzaProfiloButton;
    private JTextField passwordField;

    public MenuView() {
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

        passwordField = new JTextField(2);
        //passwordField.setHorizontalAlignment(JTextField.CENTER);
        passwordField.setPreferredSize(new Dimension(100,20));
        passwordField.setMaximumSize(new Dimension(100,20));
        passwordField.setMinimumSize(new Dimension(100,20));
        passwordField.setFont(font);
        passwordField.setBackground(Color.BLACK);
        passwordField.setForeground(Color.YELLOW);

        nuovaPartitaButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        nuovaPartitaButton.setFont(font);
        nuovaPartitaButton.setBackground(Color.YELLOW);
        nuovaPartitaButton.setForeground(Color.BLACK);

        continuaPartitaButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        continuaPartitaButton.setFont(font);
        continuaPartitaButton.setBackground(Color.YELLOW);
        continuaPartitaButton.setForeground(Color.BLACK);

        visualizzaProfiloButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        visualizzaProfiloButton.setFont(font);
        visualizzaProfiloButton.setBackground(Color.YELLOW);
        visualizzaProfiloButton.setForeground(Color.BLACK);

        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordField.setFont(font);

        //nuovaPartitaButton.setBackground(Color.GREEN);

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
    }   

    public JPanel getPanel() {
        return panel;
    }


    public void addNuovaPartitaListener(ActionListener listener) {nuovaPartitaButton.addActionListener(listener);}
    public void addContinuaPartitaListener(ActionListener listener) {continuaPartitaButton.addActionListener(listener);}
    public void addVisualizzaProfiloListener(ActionListener listener) {visualizzaProfiloButton.addActionListener(listener);}

    public String getPasswordInput() {
        return passwordField.getText();
    }
}