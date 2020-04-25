package beans;

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

    public void setMines(){
        if(mineGenerated){
            return;
        }

        // define the range 
        int max = row*column; 
        int min = (row*column) / 4; 
        int range = max - min + 1; 

        // generate random numbers within 1/4 of total cells to total number of cells
        for (int i = 0; i < max; i++) {
            // rand = number of mines in the field
            int rand = (int)(Math.random() * range) + min;
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