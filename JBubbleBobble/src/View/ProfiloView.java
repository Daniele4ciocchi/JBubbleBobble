package View;

import javax.swing.*;
import java.awt.*;
import Model.Profilo;

public class ProfiloView extends JPanel {
    private Profilo profilo;

    public ProfiloView(Profilo profilo) {
        this.profilo = profilo;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Nickname: " + profilo.getNickname()));
        add(new JLabel("Livello: " + profilo.getLivello()));
        add(new JLabel("Partite giocate: " + profilo.getPartite().size()));
        add(new JLabel("Punti totali: " + profilo.getPuntiTotali()));
        add(new JLabel("Vittorie: " + profilo.getVinte()));
        add(new JLabel("Perdite: " + profilo.getPerse()));
    }


    /*
    // main di test creato dall'AI
    public static void main(String[] args) {
        JFrame frame = new JFrame("Profilo Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Profilo profilo = Profilo.getInstance();
        ProfiloView panel = new ProfiloView(profilo);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }

     */
}