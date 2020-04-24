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

    private int rowClicked;
    private int colClicked;


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

    public void testCell(String c){
        int rowA = Integer.parseInt(c);
        
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

/* JOSH GAME MECHANICS
    public void tableMaker(int x)
    {
        cells = new cellBeans[x][x];
        do{
            for (int i = 0; i < x; i++)
            {
                for(int j = 0; j < x; j++)
                {
                    double willItBomb = Math.random();
                    if (willItBomb < 0.4)
                    {
                        cells[i][j].setHasBomb(true);
                        bombTotal++;
                    }
                    else
                    {
                        cells[i][j].setHasBomb(false);
                    }
                    //Have a grid, manually allocate the first 100 as mines, then shuffle the table
                }
            }
        } while(bombTotal <= ((x*x)/4));
            //Creating the numbers that show how many bombs are in the vincinity of obsceninty in my eyes
            
            for(int i = 0; i < x; i++)
            {
                for (int j = 0; j < x; i++)
                {
                    checkBombs(i, j);
                }
            }
        }

    public int checkBombs(int i, int j)
    {
        if (i < 0 || j < 0 || i >= heightWidth || j >= heightWidth)
        {
            return 0;
        }
        int surBombTotal = 0;
        for (int k = -1; k < 2; k++)
        {
            for (int l = -1; l < 2; l++)
            {
                if ((k+i) < 0 || (l+j) < 0 || (k+i) >= heightWidth || (l+j) >= heightWidth)
                {
                    continue;
                }
                else
                {
                    surBombTotal++;
                }
            }
        }
        cells[i][j].setSurBombs(surBombTotal);
        return surBombTotal;
    }

    public void openDaSpaces(int i, int j)
    {
        if (i < 0 || j < 0 || i >= heightWidth || j >= heightWidth)
        {
            return;
        }
        if (cells[i][j].getIsOpen())
        {
            return;
        }
        cells[i][j].setIsOpen(true);
        if (checkBombs(i,j) != 0)
        {
            return;
        }
        openDaSpaces((i-1), (j-1));
        openDaSpaces((i), (j-1));
        openDaSpaces((i+1), (j-1));
        openDaSpaces((i-1), (j));
        openDaSpaces((i+1), (j));
        openDaSpaces((i+1), (j+1));
        openDaSpaces((i-1), (j+1));
        openDaSpaces((i), (j+1));
    }
*/