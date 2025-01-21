package View;

import javax.swing.*;
import java.awt.*;
import Model.Profilo;

public class ProfiloView {
    private Profilo profilo;
    private JFrame frame;

    public ProfiloView(Profilo profilo) {
        this.profilo = profilo;
        frame = new JFrame("Profilo Panel");
        
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setFocusable(true);
        frame.requestFocusInWindow();
        frame.setLocationRelativeTo(null);
        
        this.profilo = profilo;
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(new JLabel("Nickname: " + profilo.getNickname()));
        panel.add(new JLabel("Livello: " + profilo.getLivelloProfilo()));
        panel.add(new JLabel("Partite giocate: " + profilo.getPartite().size()));
        panel.add(new JLabel("Punti totali: " + profilo.getPuntiTotali()));
        panel.add(new JLabel("Vittorie: " + profilo.getVinte()));
        panel.add(new JLabel("Perdite: " + profilo.getPerse()));
        
        
        frame.add(panel);
        frame.setVisible(true);
    }
}