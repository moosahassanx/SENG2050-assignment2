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


    //constructors 
    public GameBean() { }

    public GameBean(String d){
        difficulty = d;
        setTableSize();
        cellArray = new Cells[row][column];
    }

    // game methods
    public void setTableSize(){
        if(difficulty.equals("Beginner")){
            row = 10;
            column = 10;
        }else if(difficulty.equals("Intermediate")){
            row = 15;
            column = 15;
        }else{
            row = 20;
            column = 20;
        }
    }

    public void testCell(){
        /*
        if(cellSelected.isMine()){
            - pop-up box saying you fucked up
            - redirect to newgame.jsp
            - delete game from session
        }else{
            - cellSelected.surroundingMines()
        }
        */
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
}