package beans;

import javax.sql.*;
import java.sql.*;
import javax.naming.*;

import java.io.Serializable;
import java.util.Random;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
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
    private long start;
    private long end;
    private long duration;

    // constructors
    public GameBean() {
        new Thread();
    }

    public GameBean(String d, String u) {
        username = u;
        gameOver = false;
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

    public void loadGame() throws NamingException, SQLException {
        System.out.println("loadGame() method successfully loaded");

        // connection
        InitialContext ctx = new InitialContext();                                                                  // used to store application scoped resources
        DataSource ds = (DataSource) ctx.lookup("java:/comp/env/c3331532_assignment2/c3331532_database");           // explore what resources have been defined java:/comp/env/folder/database
        Connection conn = ds.getConnection();                                                                       // connection to database server (pooled connection)

        String query = "SELECT * FROM beanStorage WHERE username = ?";                                              // specify query
        Statement stmt = conn.createStatement();                                                                    // create statement to execute query
        PreparedStatement ss2 = conn.prepareStatement(query);
        ss2.setString(1, username);
        
        ResultSet rs = stmt.executeQuery(query);                                                                    // execute our statement   
        System.out.println("666666");

        // result set table (cursor moves around)
        if(rs.next()){                                                                                           // iterate through every row
            System.out.println("7777777");
            rs.getString("username").equals(username);
            System.out.println("8888888");
            
        }

        




        /*
        this.username = username;
        
        System.out.println("111111111111111111111");

        DataSource source = (DataSource) new InitialContext().lookup("java:/comp/env/jdbc/SENG2050");

        System.out.println("222222222222222222222");

        String query = "SELECT * FROM games WHERE username = ?";

        PreparedStatement statement = source.getConnection().prepareStatement(query);
        System.out.println("333333333333333333333");
        
        statement.setString(1, username);

        ResultSet rs = statement.executeQuery();
        System.out.println("444444444444444444444");
        if (rs.next())
        {
            this.deserialise(rs.getString("gameGrid"));
            gridWidth = gridHeight = grid.length;
            source.getConnection().close();
            return true;
        }
        source.getConnection().close();
        System.out.println("555555555555555555555");

        return false;
        */
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
    
    public long getTimeElapsed() {
        return duration;
    }

    public String getUsername(){
        return username;
    }
}