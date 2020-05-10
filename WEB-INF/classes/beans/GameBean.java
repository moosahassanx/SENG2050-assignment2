package beans;

import javax.sql.*;
import java.sql.*;
import javax.naming.*;

import java.io.Serializable;
import java.util.Random;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.Instant;

public class GameBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private String username;
    private int row;
    private int column;
    private String difficulty;
    private Cells[][] cellArray;
    private boolean mineGenerated;
    private boolean gameOver;
    private boolean win;
    private long start;
    private long end;
    private long duration;
    private int numberOfMines;
    private int unopened;

    // constructors
    public GameBean() {
        new Thread();
    }

    public GameBean(String d, String u) {
        win = false;
        numberOfMines = 0;
        username = u;
        gameOver = false;
        win = false;
        difficulty = d;
        setTableSize();
        cellArray = new Cells[row][column];
        mineGenerated = false;
        start = Instant.now().toEpochMilli();
        
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                cellArray[i][j] = new Cells();
            }
        }
    }

    public void saveGame(byte[] gameOrWhatever) throws NamingException, SQLException, IOException {

        System.out.println("saveGame() method successfully loaded");

        // connection
        InitialContext ctx = new InitialContext();                                                                  // used to store application scoped resources
        DataSource ds = (DataSource) ctx.lookup("java:/comp/env/c3331532_assignment2/c3331532_database");           // explore what resources have been defined java:/comp/env/folder/database
        Connection conn = ds.getConnection();                                                                       // connection to database server (pooled connection)
        
        // querying and updating database
        String query = "INSERT INTO beanStorage (username, gameBean) VALUES (?, ?)";
        PreparedStatement ss2 = conn.prepareStatement(query);
        ss2.setString(1, username);
        ss2.setBytes(2, gameOrWhatever);
        ss2.executeUpdate();

        System.out.println("USERNAME: " + username + " and its GameBean has been stored");
    }

    public void loadGame(String u) throws NamingException, SQLException, IOException, ClassNotFoundException {
        System.out.println("loadGame() method successfully loaded");

        // connection
        InitialContext ctx = new InitialContext();                                                                  // used to store application scoped resources
        DataSource ds = (DataSource) ctx.lookup("java:/comp/env/c3331532_assignment2/c3331532_database");           // explore what resources have been defined java:/comp/env/folder/database
        Connection conn = ds.getConnection();                                                                       // connection to database server (pooled connection)
        System.out.println("successfully connected");

        // querying database
        String query = "SELECT * FROM beanStorage WHERE username = ?";
        PreparedStatement ss2 = conn.prepareStatement(query);
        ss2.setString(1, u);
        ResultSet rs = ss2.executeQuery();
        System.out.println("SELECT * FROM beanStorage WHERE username = " + u);

        if(rs.next()){
            // storing byte from sql as a separate byte
            byte[] loadByte = rs.getBytes("gameBean");

            // deserialising the loadByte object
            ByteArrayInputStream bs = new ByteArrayInputStream(loadByte);
            ObjectInputStream os = new ObjectInputStream(bs);

            try{
                // making a new object and storing the same data onto it
                GameBean loadGame = (GameBean) os.readObject();

                // set attributes to this object from the new object
                this.username = loadGame.getUsername();
                this.row = loadGame.getRow();
                this.column = loadGame.getColumn();
                this.difficulty = loadGame.getDifficulty();
                this.cellArray = loadGame.getArray();
                this.mineGenerated = loadGame.getMineGenerated();
                this.gameOver = loadGame.getLose();
                this.start = loadGame.getStart();
                this.end = loadGame.getEnd();
                this.duration = loadGame.getTimeElapsed();

            }catch(NotSerializableException e){
                System.out.println("SERVLET WRITE OBJECT ERROR: " + e);
            }

            // updating database
            String queryUpdate = "UPDATE beanStorage SET gameBean = ? WHERE username ?";
            PreparedStatement ss3 = conn.prepareStatement(queryUpdate);
            ss3.setBytes(1, loadByte);
            ss3.setString(2, u);
            ss3.executeQuery();
            System.out.println("UPDATE beanStorage SET ... successful");

            // closing
            ds.getConnection().close();
        }

        ds.getConnection().close();

    }

	// game methods
    public void setTableSize(){
        if(difficulty.equals("Beginner")){
            row = 10;
            column = 10;
            unopened = 100;
        }else if(difficulty.equals("Intermediate")){
            row = 15;
            column = 15;
            unopened = 225;
        }else{                      // Advanced
            row = 20;
            column = 20;
            unopened = 400;
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

        // SUBMISSION TESTING
        //int rand = getRandomNumberInRange((row*column)/4, row*column);

        
        // TESTING 1
        int rand = getRandomNumberInRange(1, 20);

        numberOfMines = rand;

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

    public void makeFlagged(int x, int y){
        if(cellArray[x][y].isFlagged()){
            cellArray[x][y].removeFlag();
        }else{
            cellArray[x][y].setFlagged();
        }
    }

    public void testCell(int x, int y){
        setMines();

        if(cellArray[x][y].isVisited()){
            return;
        }else{
            if(cellArray[x][y].isFlagged()){
                return;
            }else if(cellArray[x][y].isMine()){
                end = Instant.now().toEpochMilli();
                duration = start - end;
                gameOver = true;
                return;
            }else{
                
                // top
                if(x+1 >= row){
                    //
                }else{
                    if(cellArray[x+1][y].isMine()){
                        cellArray[x][y].surroundCounter();
                    }
                }

                // bottom
                if(x-1 < 0){
                    //
                }else{
                    if(cellArray[x-1][y].isMine()){
                        cellArray[x][y].surroundCounter();
                    }
                }

                // right
                if(y-1 < 0){
                    //
                }else{
                    if(cellArray[x][y-1].isMine()){
                        cellArray[x][y].surroundCounter();
                    }
                }

                // left
                if(y+1 >= column){
                    //
                }else{
                    if(cellArray[x][y+1].isMine()){
                        cellArray[x][y].surroundCounter();
                    }
                }

                // bottom right
                if(x-1 < 0 || y-1 < 0){
                    //
                }else{
                    if(cellArray[x-1][y-1].isMine()){
                        cellArray[x][y].surroundCounter();
                    }
                }
                

                // bottom left
                if(x-1 < 0 || y+1 >= column){
                    //
                }else{
                    if(cellArray[x-1][y+1].isMine()){
                        cellArray[x][y].surroundCounter();
                    }
                }
                

                // top left
                if(x+1 >= row || y+1 >= column){
                    //
                }else{
                    if(cellArray[x+1][y+1].isMine()){
                        cellArray[x][y].surroundCounter();
                    }
                }
                

                // top right
                if(x+1 >= row || y-1 < 0){
                    //
                }else{
                    if(cellArray[x+1][y-1].isMine()){
                        cellArray[x][y].surroundCounter();
                    }
                }
                
                // mark the cell as visited
                cellArray[x][y].setVisited();
                unopened--;
                checkWin();
                
            }
        }
    }

    public void checkWin(){
        if(numberOfMines == unopened){
            end = Instant.now().toEpochMilli();
            duration = start - end;
            win = true;
        }
    }
    
    // not necessary but yo 
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

    public boolean getLose(){
        return gameOver;
    }

    public boolean getWin(){
        return win;
    }

    public long getStart(){
        return start;
    }

    public long getEnd(){
        return end;
    }
    
    public long getTimeElapsed() {
        return -(duration/1000);
    }

    public String getUsername(){
        return username;
    }

    public boolean getMineGenerated(){
        return mineGenerated;
    }
}