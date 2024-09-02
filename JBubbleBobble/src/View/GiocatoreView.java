package View;

import Model.Giocatore;

import javax.swing.*;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

public class GiocatoreView extends JFrame implements Observer {
    //la view estende JFrame che a quanto pare ha un metodo addKeyListener()
    //che permette di aggiungere un KeyListener al JFrame, solo così si possono avere in input i tasti

    //TODO: sinceramente credo sia tutto da implementare da zero, questo è codice scritto a caso
    private Giocatore model;
    private JPanel panel;

    public GiocatoreView() {
        setTitle("Gioco");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setFocusable(true);
        panel.requestFocusInWindow();
        add(panel);

        setVisible(true);
    }


    public void addKeyListener(KeyListener listener) {
        panel.addKeyListener(listener);
    }

    public void initialize() {
        panel.setFocusable(true);
        panel.requestFocusInWindow();
    }

    public JPanel getPanel() {
        return panel;
    }


    @Override
    public void update(Observable o, Object arg) {
        //da qui arrivano istruzioni su come si deve comportare il giocatore
    }
}
