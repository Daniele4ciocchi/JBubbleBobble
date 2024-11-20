package classiTolte;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class GiocatoreView implements Observer {

    String im1 = "image_85.png";
    String im2 = "image_86.png";
    String path = "src/resources/sprites/bubblun/"; //image_(85-86).png
    private Image sprite;
    private BufferedImage giocatoreSprite;


    public GiocatoreView(){
        try {
            sprite = ImageIO.read(new File(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public BufferedImage getGiocatoreSprite(){
        return giocatoreSprite;
    }






    @Override
    public void update(Observable o, Object arg) {
        //da qui arrivano istruzioni su come si deve comportare il giocatore
    }
}
