package Model;

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
            while (myReader.hasNextLine()) { // lettura file
                for (int j = 0; j < 36; j++) {
                    if (myReader.next().equals("0")) grid[i][j] = new Tile(Tile.TileType.EMPTY);
                    else if (myReader.next().equals("1")) grid[i][j] = new Tile(Tile.TileType.WALL);
                    else if (myReader.next().equals("2")) grid[i][j] = new Tile(Tile.TileType.PLATFORM);
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
