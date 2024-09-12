package main;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JBubbleBobble {
    public static JFrame frame;

    public static void main(String[] args) {
        // Creazione del frame
        frame = new JFrame("Esempio di JPanel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        /*
        // Creazione del pannello
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Creazione dell'etichetta
        JLabel label = new JLabel("Testo iniziale", SwingConstants.CENTER);
        panel.add(label, BorderLayout.CENTER);

        // Creazione del pulsante
        JButton button = new JButton("Cambia testo");
        panel.add(button, BorderLayout.SOUTH);

        // Aggiunta dell' ActionListener al pulsante
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText("Testo cambiato!");
            }
        });

        // Aggiunta del pannello al frame
        frame.add(panel);

        // Visualizzazione del frame
        frame.setVisible(true);
        */
    }
}
