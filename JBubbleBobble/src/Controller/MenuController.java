package Controller;

import Model.Menu;
import Model.Partita;
import View.PartitaView;

public class MenuController {

    private MenuView view;
    private Menu model;

    public MenuController() {
        this.model = new Menu();
        this.view = new MenuView(this);
    }

    public PartitaController nuovaPartita() {
//        Partita pmodel = new Partita();
        Partita m = model.nuovaPartita();
        PartitaView v = new PartitaView();
        return new PartitaController(m,v);
    }

}
