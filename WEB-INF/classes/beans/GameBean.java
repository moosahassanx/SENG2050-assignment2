package beans;

import java.io.Serializable;

public class GameBean implements Serializable{
    
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    int row;
    int column;
    String time;
    String difficulty;

    //constructors 
    public GameBean() { }

    public GameBean(String d){
        difficulty = d;
        setTableSize();
    }

    public void setDifficulty(String d){
        this.difficulty = d;
        setTableSize();
    }
    
    public String getDifficulty(){
        return this.difficulty;
    }

    public void setTableSize(){
        if(difficulty.equals("Beginner")){
            row = 10;
            column = 10;
        }else if(difficulty.equals("Intermediate")){
            row = 15;
            column = 15;
        }else{
            row = 15;
            column = 15;
        }
    }

    public int getRow(){
        return this.row;
    }

    public int getColumn(){
        return this.column;
    }
}