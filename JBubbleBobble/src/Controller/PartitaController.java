package Controller;

import Model.Partita;
import View.PartitaView;

public class PartitaController {

    private static PartitaController INSTANCE;

    private PartitaView view = PartitaView.getInstance();
    private Partita model = Partita.getInstance();

    public static PartitaController getInstance(){
        if (INSTANCE == null){
            INSTANCE = new PartitaController();
        }
        return INSTANCE;
    }

    public PartitaController() {

    }


    //metodi di gestione della partita
    public void start(){
        model.gioca();
    }

    public void stop(){}

    public void pause(){}

    public void restart(){
        this.stop();
        this.start();
    }


    }
}


