import Model.Giocatore;
import Model.Nemico;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class JBubbleBobble extends Application{
    //avvio del
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Pannello Principale");

        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 800, 600);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
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
