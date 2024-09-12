package Controller;

import Model.Menu;
import Model.Partita;
import View.MenuView;
import View.PartitaView;

public class MenuController {

    private MenuView view;
    private Menu model;

    public MenuController() {
        this.model = new Menu();
        this.view = new MenuView(this);
    }

    public PartitaController nuovaPartita() {

        Partita m = new Partita();
        PartitaView v = new PartitaView();
        return new PartitaController(m,v);
    }

    public PartitaController continuaPartita(String password){
        model.continuaPartita(password);
        Partita mx = new Partita();
        PartitaView vx = new PartitaView();
        return new PartitaController(mx, vx);
    }

    public void getProfilo(){

    }


}
