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

    // constructor
    public Cells() {
        visited = false;
        flag = false;
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

    public int surroundingMines(){
        return 8;       // temporary value
    }

    public boolean isVisited(){
        //return visited;
        return visited;
    }

    public void setVisited(){
        visited = true;
    }
}