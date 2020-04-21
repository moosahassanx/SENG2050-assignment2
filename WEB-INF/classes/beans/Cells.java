package beans;

import java.io.Serializable;

public class Cells implements Serializable{
    
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public Cells() {
        //
    }

    public boolean isFlagged(){
        return true;    // temporary value
    }

    public boolean isMine(){
        return true;    // temporary value
    }

    public int surroundingMines(){
        return 0;       // temporary value
    }
    /*
    [00][01][02][03]
    [10][11][12][]
    [2][][][]
    */

    // opened / clicked
    public boolean state(){
        return true;    // temporary value
    }
}