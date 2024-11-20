package classiTolte;

import View.Menu;
import Model.Partita;
import View.MenuView;
import View.PartitaView;
import main.JBubbleBobble;
import View.ProfiloView;

public class MenuController {

    private MenuView view;
    private Menu model;

    public MenuController() {
        this.model = new Menu();
        this.view = new MenuView();
        initView();
    }

    private void initView() {
        view.addNuovaPartitaListener(e -> nuovaPartita());
        view.addContinuaPartitaListener(e -> continuaPartita("password"));
        view.addVisualizzaProfiloListener(e -> getProfiloStats());
    }

    public void nuovaPartita() {
        PartitaController partitaController = new PartitaController(new Partita(), new PartitaView());
        JBubbleBobble.frame.setContentPane(partitaController.getView().getPanel());
        JBubbleBobble.frame.revalidate();
    }

    public void continuaPartita(String password) {
        model.continuaPartita(password);
        PartitaController partitaController = new PartitaController(new Partita(), new PartitaView());
        JBubbleBobble.frame.setContentPane(partitaController.getView().getPanel());
        JBubbleBobble.frame.revalidate();
    }

    public void getProfiloStats() {
        ProfiloView profiloView = new ProfiloView(model.getProfilo());
        profiloView.createProfiloFrame();


    }

    public MenuView getView() {return view;}
    public Menu getModel() {return model;}

}
