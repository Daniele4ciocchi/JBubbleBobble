package main;

import Model.Giocatore;
import Model.Nemico;

public class JBubbleBobble {
    public static void main(String[] args) throws InterruptedException {

        //TODO: implementare JavaFX nella view del gioco
        Nemico n = new Nemico(Nemico.TipologiaNemico.ZENCHAN);
        System.out.println(n.getTipologia().getNome() + n.getTipologia().getSalto());
        Giocatore giocatore = Giocatore.getInstance();
        while(true){
            //controllo della posizione del giocatore cliccando i relativi tasti
            Thread.sleep(2000);
            System.out.println(giocatore.getPosX());
            giocatore.moveRight();
        }
    }
}
