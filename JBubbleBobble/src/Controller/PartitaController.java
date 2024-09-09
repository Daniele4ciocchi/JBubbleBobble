package Controller;

import Model.Partita;
import View.PartitaView;

public class PartitaController {
    private Partita model;
    private PartitaView view;

    private static PartitaController inCorso; //istanza attuale della partita attuale

//    private Partita model = Partita.getInstance();

//    public static PartitaController getInstance(){
//        if (INSTANCE == null){
//            INSTANCE = new PartitaController();
//        }
//        return INSTANCE;
//    }

    public PartitaController(Partita model, PartitaView view){
        this.model = model;
        this.view = view;
    }

    //metodi di gestione della partita
    public void start(){
        model.addEntita(model.getGiocatore());
        //TODO: aggiungere spawn nemici
//        model.addEntita(model.getLivello().getEnemySpawn());

        model.getLivello().setLevelNum(1);
        model.getLivello().costruisciGrid();
    }

    public void update() {
    }

    public void stop(){}

    public void pause(){}

    public void restart(){
        this.stop();
        this.start();
    }

    public static PartitaController getInstance(){return inCorso;}
    }
}


