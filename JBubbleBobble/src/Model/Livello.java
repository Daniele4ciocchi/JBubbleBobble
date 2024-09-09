package Model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class Livello {
    private static Livello instance;
    private Tile[][] grid;
    private int levelNum;

    // COSTRUTTORE DELLA CLASSE
    private Livello() {}

    public static Livello getInstance() {
        if (instance == null) {
            instance = new Livello();
        }
        return instance;
    }

    public int getLevelNum() {
        return levelNum;
    }
    public Tile[][] getGrid() {
        return grid;
    }



    public void costruisciGrid() {
        grid = new Tile[26][36];
        int i = 0;
        try {
            File f = new File("/data/levels/" + Integer.toString(levelNum) + ".txt");
            Scanner myReader = new Scanner(f);
            while (myReader.hasNextLine()) {
                String nextToken = myReader.next();// lettura file
                for (int j = 0; j < 36; j++) {
                    grid[i][j] = new Tile(switch (nextToken) {
                        case "0" -> Tile.TileType.EMPTY;
                        case "1" -> Tile.TileType.WALL;
                        case "2" -> Tile.TileType.PLATFORM;

                        case "3" -> Tile.TileType.PLAYERSPAWN;
                        case "5" -> Tile.TileType.POWERUPSPAWN;

                        case "Z" -> Tile.TileType.ZENCHAN_SPAWN;
                        case "B" -> Tile.TileType.BANEBOU_SPAWN;
                        case "M" -> Tile.TileType.MIGHTA_SPAWN;
                        case "H" -> Tile.TileType.HIDEGON_SPAWN;
                        case "P" -> Tile.TileType.PULPULSPAWN;
                        case "O" -> Tile.TileType.MONSTA_SPAWN;

                        default -> throw new IllegalArgumentException("Token non valido: " + nextToken);
                    });
                }
                myReader.nextLine();
                i++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("file mancante");
        }
    }

    public Tile getTile(int x, int y) {
        return grid[x][y];
    }

    public void setLevelNum(int levelNum) {this.levelNum = levelNum;}

    public void nextLevelNumber() {
        levelNum++;
        costruisciGrid();
    }

    public void resetLevelNumber() {
        levelNum = 1;
        costruisciGrid();
    }
}
