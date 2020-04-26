package beans;

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
            }else{
                cellArray[x][y].setVisited();
                
                // above
                if(checkBorder(x, y+1)){
                    return;
                }else{
                    if(cellArray[x][y+1].isMine()){
                        cellArray[x][y].surroundCounter();
                    }
                }
                
                // below
                if(checkBorder(x, y-1)){
                    return;
                }else{
                    if(cellArray[x][y-1].isMine()){
                        cellArray[x][y].surroundCounter();
                    }
                }
                
                // left
                if(checkBorder(x-1, y)){
                    return;
                }else{
                    if(cellArray[x-1][y].isMine()){
                        cellArray[x][y].surroundCounter();
                    }
                }
                
                // right
                if(checkBorder(x+1, y)){
                    return;
                }else{
                    if(cellArray[x+1][y].isMine()){
                        cellArray[x][y].surroundCounter();
                    }
                }
                
                // top-right
                if(checkBorder(x+1, y+1)){
                    return;
                }else{
                    if(cellArray[x+1][y+1].isMine()){
                        cellArray[x][y].surroundCounter();
                    }
                }
                
                // top-left
                if(checkBorder(x-1, y+1)){
                    return;
                }else{
                    if(cellArray[x-1][y+1].isMine()){
                        cellArray[x][y].surroundCounter();
                    }
                }
                
                // bottom-left
                if(checkBorder(x-1, y-1)){
                    return;
                }else{
                    if(cellArray[x-1][y-1].isMine()){
                        cellArray[x][y].surroundCounter();
                    }
                }
                
                // bottom-right
                if(checkBorder(x+1, y-1)){
                    return;
                }else{
                    if(cellArray[x+1][y-1].isMine()){
                        cellArray[x][y].surroundCounter();
                    }
                }
            }
        }
    }

    private boolean checkBorder(int x, int y) {
        if ((x < 0) || (y < 0) || (x > row-1) || (y > column-1)) {
            return false;
        }
        return true;
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