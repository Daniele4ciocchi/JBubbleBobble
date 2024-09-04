package View;

import Controller.PartitaController;
import Model.Partita;

public class PartitaView {

    private static PartitaView INSTANCE;
    private PartitaController controller = PartitaController.getInstance();

    public static PartitaView getInstance(){
        if (INSTANCE == null){
            INSTANCE = new PartitaView();
        }
        return INSTANCE;
    }

    private PartitaView(){

    }

    public paint(){
        giocatoreview.paintGiocatore();

    }
}
