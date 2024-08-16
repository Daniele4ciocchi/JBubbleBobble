
import java.util.ArrayList;

class Profilo {
    private String nickname; // nome utente, deciso nel costruttore
    private Image avatar; // 
    private ArrayList<Partita> partite;
    private int livello;

    public Profilo(String n, Image a){
        nickname = n;
        avatar = a;
    }


    public String getNickname(){
        return nickname;
    }

    public Image getAvatar(){
        return avatar;
    }

    public ArrayList<Partita> getPartite(){
        return partite;
    }

    public int getVinte(){
        return partite.stream().filter(x -> x.isVinta()).count();
    }

    public int getPerse(){
        return partite.stream().filter(x -> x.isVinta() == false).count();
    }

    public int getLivello(){
        return livello;
    }
}
