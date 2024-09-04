package Controller;

import Model.Partita;
import View.PartitaView;

public class PartitaController {

    private Partita partita = Partita.getInstance();
    private PartitaView view = partita.getView();

    public PartitaController() {

    }

    public void start(){

    }
}
