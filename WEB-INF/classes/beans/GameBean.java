package beans;

import java.sql.*;

import java.util.Random;
import java.io.Serializable;

public class GameBean implements Serializable{
    
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private int row;
    private int column;
    private String time;
    private String difficulty;
    private Cells[][] cellArray;
    private UserBean UserObject;
    private boolean mineGenerated;
    private boolean gameOver;

    //constructors 
    public GameBean() { }

    public GameBean(String d){
        gameOver = false;
        difficulty = d;
        setTableSize();
        cellArray = new Cells[row][column];
        mineGenerated = false;

        for(int i = 0; i < row; i++){
            for(int j = 0; j < column; j++){
                cellArray[i][j] = new Cells();
            }
        }
    }

    // game methods
    public void setTableSize(){
        if(difficulty.equals("Beginner")){
            row = 10;
            column = 10;
        }else if(difficulty.equals("Intermediate")){
            row = 15;
            column = 15;
        }else{                      // Advanced
            row = 20;
            column = 20;
        }
    }

    private int getRandomNumberInRange(int min, int max) {
		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}

    public void setMines(){
        if(mineGenerated){
            return;
        }

        // USE THIS ONE BEFORE SUBMITTING!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //int rand = getRandomNumberInRange((row*column)/4, row*column);

        /*
        // TESTING 1
        int rand = getRandomNumberInRange(1, 15);

        Random random = new Random();
        while (rand > 0) {
            int i = getRandomNumberInRange(0, row-1);
            int j = getRandomNumberInRange(0, column-1);
            
            if (!cellArray[i][j].isMine()) {
                cellArray[i][j].setMine();
                rand--;
            }
        }
        */

        // TESTING 2
        cellArray[1][1].setMine();
        cellArray[0][5].setMine();
        
        mineGenerated = true;
    }

    public void testCell(int x, int y){
        setMines();

        if(cellArray[x][y].isVisited()){
            return;
        }else{
            if(cellArray[x][y].isFlagged()){
                return;
            }else if(cellArray[x][y].isMine()){
                gameOver = true;
                return;
            }else{
                cellArray[x][y].setVisited();
                
                // down
                if(x+1 >= column){
                    return;
                }else{
                    if(cellArray[x+1][y].isMine()){
                        cellArray[x][y].surroundCounter();
                    }
                }

                // up
                if(x-1 < 0){
                    return;
                }else{
                    if(cellArray[x-1][y].isMine()){
                        cellArray[x][y].surroundCounter();
                    }
                }

                // left
                if(y-1 < 0){
                    return;
                }else{
                    if(cellArray[x][y-1].isMine()){
                        cellArray[x][y].surroundCounter();
                    }
                }

                // right
                if(y+1 >= row){
                    return;
                }else{
                    if(cellArray[x][y+1].isMine()){
                        cellArray[x][y].surroundCounter();
                    }
                }

                // down left
                if((y-1 < 0) || (x+1 >= column)){
                    return;
                }else{
                    if(cellArray[x+1][y-1].isMine()){
                        cellArray[x][y].surroundCounter();
                    }
                }

                // down right
                if((x+1 >= column) || (y+1 >= row)){
                    return;
                }else{
                    if(cellArray[x+1][y+1].isMine()){
                        cellArray[x][y].surroundCounter();
                    }
                }

                // up right
                if((x-1 < 0) || (y+1 >= row)){
                    return;
                }else{
                    if(cellArray[x-1][y+1].isMine()){
                        cellArray[x][y].surroundCounter();
                    }
                }

                // up left
                if((x-1 < 0) || (y-1 < 0)){
                    return;
                }else{
                    if(cellArray[x-1][y-1].isMine()){
                        cellArray[x][y].surroundCounter();
                    }
                }

                if(cellArray[x][y].surroundingMines() == 0){
                    recursiveTestCell(x+1, y);       // down
                    recursiveTestCell(x-1, y);       // up
                    recursiveTestCell(x, y-1);       // left
                    recursiveTestCell(x, y+1);       // right
                    recursiveTestCell(x+1, y-1);     // down left
                    recursiveTestCell(x+1, y+1);     // down right
                    recursiveTestCell(x-1, y+1);     // up right
                    recursiveTestCell(x-1, y-1);     // up left
                }
            }
        }
    }

    public void recursiveTestCell(int x, int y){
        if(cellArray[x][y].isVisited()){
            return;
        }else{
            if(cellArray[x][y].isFlagged()){
                return;
            }else if(cellArray[x][y].isMine()){
                gameOver = true;
                return;
            }else{
                cellArray[x][y].setVisited();
                
                // down
                if(x+1 >= column){
                    return;
                }else{
                    if(cellArray[x+1][y].isMine()){
                        cellArray[x][y].surroundCounter();
                    }
                }

                // up
                if(x-1 < 0){
                    return;
                }else{
                    if(cellArray[x-1][y].isMine()){
                        cellArray[x][y].surroundCounter();
                    }
                }

                // left
                if(y-1 < 0){
                    return;
                }else{
                    if(cellArray[x][y-1].isMine()){
                        cellArray[x][y].surroundCounter();
                    }
                }

                // right
                if(y+1 >= row){
                    return;
                }else{
                    if(cellArray[x][y+1].isMine()){
                        cellArray[x][y].surroundCounter();
                    }
                }

                // down left
                if((y-1 < 0) || (x+1 >= column)){
                    return;
                }else{
                    if(cellArray[x+1][y-1].isMine()){
                        cellArray[x][y].surroundCounter();
                    }
                }

                // down right
                if((x+1 >= column) || (y+1 >= row)){
                    return;
                }else{
                    if(cellArray[x+1][y+1].isMine()){
                        cellArray[x][y].surroundCounter();
                    }
                }

                // up right
                if((x-1 < 0) || (y+1 >= row)){
                    return;
                }else{
                    if(cellArray[x-1][y+1].isMine()){
                        cellArray[x][y].surroundCounter();
                    }
                }

                // up left
                if((x-1 < 0) || (y-1 < 0)){
                    return;
                }else{
                    if(cellArray[x-1][y-1].isMine()){
                        cellArray[x][y].surroundCounter();
                    }
                }

                if(cellArray[x][y].surroundingMines() == 0){
                    recursiveTestCell(x, y+1);       // right
                    recursiveTestCell(x+1, y);       // down
                    recursiveTestCell(x-1, y);       // up
                    recursiveTestCell(x, y-1);       // left
                    recursiveTestCell(x+1, y-1);     // down left
                    recursiveTestCell(x+1, y+1);     // down right
                    recursiveTestCell(x-1, y+1);     // up right
                    recursiveTestCell(x-1, y-1);     // up left
                }
            }
        }
    }

    // mutators
    public void setDifficulty(String d){
        this.difficulty = d;
        setTableSize();
    }
    
    // accessors
    public String getDifficulty(){
        return this.difficulty;
    }

    public int getRow(){
        return this.row;
    }

    public int getColumn(){
        return this.column;
    }

    public Cells[][] getArray(){
        return cellArray;
    }

    public boolean getWin(){
        return gameOver;
    }
}