package beans;

import java.io.Serializable;

public class Cells implements Serializable{
    
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
    private boolean mine;
    private boolean visited;
    private boolean flag;
    private int surroundCounter;

    // constructor
    public Cells() {
        visited = false;
        flag = false;
        surroundCounter = 0;
    }

    public boolean isFlagged(){
        return flag;
    }

    public void setMine(){
        mine = true;
    }

    public boolean isMine(){        
        return mine;
    }

    public void surroundCounter(){
        surroundCounter++;
    }

    public int surroundingMines(){
        return surroundCounter;
    }

    public boolean isVisited(){
        //return visited;
        return visited;
    }

    public void setVisited(){
        visited = true;
    }
}