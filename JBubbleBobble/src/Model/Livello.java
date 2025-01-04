package Model;

import java.sql.Array;
import java.util.ArrayList;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class Livello {

    private int levelNum = 1;
    private int tilesize = 16;
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


    public String getTilePath() {
        return "JBubbleBobble" + File.separator + "src" 
        + File.separator + "resources" 
        + File.separator + "blocks" 
        + File.separator + "normal blocks" 
        + File.separator + "block_" 
        + levelNum + ".png";
    }

    public int getTilesize() {
        return tilesize;
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
                        case '3' -> Tile.TileType.TP_ENTRY;

                        case '4' -> Tile.TileType.POWERUP_SPAWN;

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


    public boolean isWalkable(int x, int y) {
        int tileX = x / tilesize;
        int tileY = y / tilesize;
        return grid[tileY][tileX].getType().isWalkable();
    }

    public boolean isSolid(int x, int y) {

        int tileX = x / tilesize;
        int tileY = y / tilesize;
        return grid[tileY][tileX].getType().isSolid();
    }

    public boolean isEmpty(int x, int y) {
        int tileX = x / tilesize;
        int tileY = y / tilesize;
        return !(grid[tileY][tileX].getType().isSolid() || grid[tileY][tileX].getType().isWalkable());
    }

    public boolean isTPEntry(int x, int y) {
        int tileX = x / tilesize;
        int tileY = y / tilesize;
        return grid[tileY][tileX].getType() == Tile.TileType.TP_ENTRY;
    }
    

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < 26; i++) {
            s += i + " ";
            for (int j = 0; j < 36; j++) {
                s += grid[i][j].getType().toString() + " ";
            }
            s += "\n";
        }
        return s;
    }

}
