package hu.cai.ait.minesweeper;

import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

import hu.cai.ait.minesweeper.View.GameView;

/**
 * Created by caiglencross on 3/6/17.
 */

public class MineModel {
    private static MineModel instance = null;
    public final int numCols = 5;
    public final int numRows = 5;
    public static final short WON = 1;
    public static final short LOST = -1;
    public static final short PLAYING = 0;

    public static short status = PLAYING;

    public static boolean flag = false;



    private MineModel(){
        placeBombs(5);
    }

    private Cell[][] cellArray = new Cell[numRows][numCols];



    public static MineModel getInstance(){
        if (instance == null){
            instance = new MineModel();
        }

        return instance;
    }

    public void placeBombs(int numBombs){
        Random generator = new Random();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                cellArray[i][j] = new Cell(j,i);
            }
        }
        while (numBombs>0){
            int randomX = generator.nextInt(numRows);
            int randomY = generator.nextInt(numRows);
            if (!cellArray[randomY][randomX].isBomb()) {
                cellArray[randomY][randomX].makeBomb();
                numBombs--;
                for(Cell c: getNeighbors(cellArray[randomY][randomX])){
                    c.addBomb();
                }
            }
        }

    }

    public ArrayList<Cell> getNeighbors(Cell cell) {
        int[][] vecs = {{0, 1}, {1, 0}, {1, 1}, {-1, 0}, {0, -1}, {-1, -1}, {-1, 1}, {1, -1}};
        ArrayList<Cell> neighbors = new ArrayList<Cell>();
        for (int[] vec : vecs) {
            int xPrime = cell.x + vec[0];
            int yPrime = cell.y + vec[1];
            if (isInRange(xPrime, yPrime)) {
                neighbors.add(cellArray[yPrime][xPrime]);
            }
        }
        return neighbors;
    }



    public boolean isInRange(int x, int y){
        if (x >= 0 && x < numCols  && y >= 0 && y < numRows){
            return true;
        }
        return false;

    }

    public void revealCell(Cell c){
        if(c.isBomb()){
            lose();
        }else if(c.getNumBombs()==0){
            revealAdjacents(c);
        }else{
            c.show();
        }
    }

    public void flagCell(Cell c){
        if(!c.isBomb()){
            lose();
        }else{
            c.flag();
            MainActivity.updateCounter();
            if(c.getBombsNotFound() == 0){
                win();
            }
        }
    }

    protected void revealAdjacents(Cell cell) {
        Queue<Cell> queue = new LinkedList();

        queue.add(cell);
        while (!queue.isEmpty()) {
            Cell c = queue.remove();

            c.show();

            if (c.getNumBombs()==0){
                for (Cell neighbor:getNeighbors(c)){
                    if (!neighbor.isShown()) {
                        queue.add(neighbor);
                    }
                }
            }
        }

    }

    public Cell getCell(int x, int y){
        return cellArray[y][x];
    }

    public void resetModel(){
        status = PLAYING;
        instance = new MineModel();
        Cell.setBombsNotFound(5);
        MainActivity.resetClock();
        MainActivity.updateCounter();
    }

    public void lose(){
        status = LOST;
    }

    public void win() {status = WON; MainActivity.stopTimer();}

    public void changeFlag(){
        flag = !flag;
    }




}
