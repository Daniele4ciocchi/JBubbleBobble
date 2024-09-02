package main;

import Model.Giocatore;
import Model.Nemico;

public class Main {
    public static void main(String[] args) throws InterruptedException {
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
