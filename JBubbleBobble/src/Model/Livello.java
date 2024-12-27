package Model;

import java.sql.Array;
import java.util.ArrayList;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class Livello {

    private int levelNum = 1;
    private Tile[][] grid;


    public Livello(int livello){
            this.levelNum = livello;
            costruisciGrid();
    }

    public int getLevelNum() {
        return levelNum;
    }
    public Tile[][] getGrid() {
        return grid;
    }


    public void changeLevel() {
        this.levelNum++;
        costruisciGrid();
    }

    public void changeLevel(int levelNum) {
        this.levelNum = levelNum;
        costruisciGrid();
    }

    //TODO: da rivedere
    public void costruisciGrid() {
        grid = new Tile[26][36];
        int i = 0;
        try {
            File f = new File("JBubbleBobble" + File.separator + "src" + File.separator + "Model" + File.separator + "data" + File.separator + "levels" + File.separator + levelNum + ".txt");
            Scanner myReader = new Scanner(f);
            while (myReader.hasNextLine()) {
                String nextToken = myReader.next();
                if (i >= 26){
                    break;
                }
                for (int j = 0; j < 36; j++) {

                    grid[25-i][j] = new Tile(switch (nextToken.charAt(j)) {
                        case '0' -> Tile.TileType.EMPTY;
                        case '1' -> Tile.TileType.WALL;
                        case '2' -> Tile.TileType.PLATFORM;

                        case '3' -> Tile.TileType.PLAYER_SPAWN;
                        case '5' -> Tile.TileType.POWERUP_SPAWN;

                        case 'Z' -> Tile.TileType.ZENCHAN_SPAWN;
                        case 'B' -> Tile.TileType.BANEBOU_SPAWN;
                        case 'M' -> Tile.TileType.MIGHTA_SPAWN;
                        case 'H' -> Tile.TileType.HIDEGON_SPAWN;
                        case 'P' -> Tile.TileType.PULPULSPAWN;
                        case 'O' -> Tile.TileType.MONSTA_SPAWN;

                        default -> throw new IllegalArgumentException("Token non valido: " + nextToken);
                    }, i, j);
                }
                i++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("file mancante");
        }
    }

    //TODO: da completare
    public void applyGravity(Entita e){
        e.setVelocitaY(e.getGravita() + e.getVelocitaY());
        int newY = e.getY() + e.getVelocitaY();

        // Controllo collisione con il terreno o piattaforme
        if (grid[e.getX()][newY + e.getHeight()].getType().isWalkable()) {
            e.setPosizione(e.getX(),newY);
        } else {
            // Se colpisce il terreno, ferma la caduta
            e.setPosizione(e.getX(),(newY / 32) * 32);
            e.setVelocitaY(0);
        }
    }

    private boolean isOnGround(Entita e) {
        // Check if the entity is on the ground
        return grid[e.getX()][e.getY()].getType().isWalkable();
    }

    public String getTilePath() {
        String tilePath = "JBubbleBobble" + File.separator + "src" + File.separator + "resources" + File.separator + "blocks" + File.separator + "normal blocks";

        return tilePath + "block_" + levelNum + ".png";
    }


}
