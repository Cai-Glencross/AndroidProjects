package hu.cai.ait.minesweeper;

/**
 * Created by caiglencross on 3/6/17.
 */

public class Cell {

    private static int bombsNotFound=5;


    private boolean isBomb;
    private boolean isShown;
    private boolean isFlagged;
    public int x;
    public int y;
    private int numBombs;

    public Cell(int theX, int theY){
        isBomb = false;
        isShown = false;
        isFlagged = false;

        x= theX;
        y = theY;

        numBombs = 0;

    }

    public void makeBomb(){isBomb = true;}

    public void flag(){
        isFlagged = true;
        bombsNotFound--;
    }

    public void show(){isShown = true;}

    public boolean isBomb(){return isBomb;}

    public boolean isFlagged(){return isFlagged;}

    public boolean isShown(){return isShown;}

    public void addBomb(){numBombs++;}

    public int getNumBombs(){return numBombs;}

    public static int getBombsNotFound(){
        return bombsNotFound;
    }

    public static void setBombsNotFound(int bombs){
        bombsNotFound = bombs;
    }



}
