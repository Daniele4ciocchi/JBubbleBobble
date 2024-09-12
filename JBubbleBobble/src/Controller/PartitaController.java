package Controller;

import Model.Entita;
import Model.Nemico;
import Model.Partita;
import Model.Tile;
import View.PartitaView;

public class PartitaController {
    private Partita model;
    private PartitaView view;

    private static PartitaController inCorso; //istanza attuale della partita attuale


    public PartitaController(Partita model, PartitaView view){
        this.model = model;
        this.view = view;
    }

    //metodi di gestione della partita
    public void start(){
        model.getLivello().setLevelNum(1);
        model.getLivello().costruisciGrid();
        model.addEntita(model.getGiocatore());
        for (Tile t : model.getLivello().getEnemySpawns()){
            model.addEntita(new Nemico(
                    Nemico.TipologiaNemico.valueOf(
                            t.getType().toString().replace("_SPAWN", "")
                    ), t.getX(), t.getY()));
        }
        //TODO: [AVVIO DELLA VIEW]
        update();
    }


    public void update() {
        while (model.getGiocatore().getHp() > 0){
            checkCollision();


            // TODO: sleep di 16.66666ms
        }
    }

    // scritto da quel ritardato di chatgpt
    public void checkCollision() {
        for (Entita entita : model.getEntita()) {
            if (entita instanceof Nemico && isColliding(model.getGiocatore(), entita)) {
                handleCollision( (Nemico) entita);
            }
        }
    }

    private boolean isColliding(Entita entita1, Entita entita2) {
        return entita1.getPosX() < entita2.getPosX() + entita2.getWidth() &&
                entita1.getPosX() + entita1.getWidth() > entita2.getPosX() &&
                entita1.getPosY() < entita2.getPosY() + entita2.getHeight() &&
                entita1.getPosY() + entita1.getHeight() > entita2.getPosY();
    }

    private void handleCollision( Nemico nemico) {
        // Logica per gestire la collisione tra il giocatore e il nemico
        model.getGiocatore().setHp(model.getGiocatore().getHp() - 1); // Esempio: decrementa la vita del giocatore
        System.out.println("Collisione rilevata tra il giocatore e un nemico!");
    }
    // fine della roba scritta dal ritardato


    public void stop(){}

    public void pause(){}

    public void restart(){
        this.stop();
        this.start();
    }

    public static PartitaController getInstance(){return inCorso;}
    public Partita getModel(){return model;}
    public PartitaView getView(){return view;}
}


