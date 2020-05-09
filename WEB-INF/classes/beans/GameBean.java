package beans;

import javax.sql.*;
import java.sql.*;
import javax.naming.*;

import java.util.Random;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.time.Duration;
import java.time.Instant;

public class GameBean implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String username;
    private int row;
    private int column;
    private String difficulty;
    private Cells[][] cellArray;
    private boolean mineGenerated;
    private boolean gameOver;
    Instant start = Instant.now();
    private Duration timeElapsed;

    private final DataSource dataSource = makeDataSource();

    private DataSource makeDataSource() {
        try {
            InitialContext ctx = new InitialContext();
            return (DataSource) ctx.lookup("java:/comp/env/c3331532_assignment2/c3331532_database");
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    // constructors
    public GameBean() {
    }

    public GameBean(String d, String u) {
        username = u;
        gameOver = false;
        difficulty = d;
        setTableSize();
        cellArray = new Cells[row][column];
        mineGenerated = false;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                cellArray[i][j] = new Cells();
            }
        }
    }

    public void saveGame() throws NamingException, SQLException {
        System.out.println("saveGame() method successfully loaded");


        // step 1
        InitialContext ctx = new InitialContext();                                                                  // used to store application scoped resources
        DataSource ds = (DataSource) ctx.lookup("java:/comp/env/c3331532_assignment2/c3331532_database");           // explore what resources have been defined java:/comp/env/folder/database

        Connection conn = ds.getConnection();                                                                       // connection to database server (pooled connection)
        String query = "SELECT * FROM beanStorage WHERE username = ?";                                              // specify query
        Statement stmt = conn.createStatement();                                                                    // create statement to execute query
        ResultSet rs = stmt.executeQuery(query);                                                                    // execute our statement 


        // step 2
        

        /* PSEUDOCODE

        get a list of all the usernames in the database
        iterate through every username in the database

        if(username already exists){
            UPDATE tableName SET gameBean = serialisedBeanObject WHERE username = this.username
        }else{
            INSERT INTO tableName VALUES(username, serializedBeanObject)
        }

        */
    }

    public void loadGame() throws NamingException, SQLException {
        System.out.println("loadGame() method successfully loaded");

        InitialContext ctx = new InitialContext();                                                                  // used to store application scoped resources
        DataSource ds = (DataSource) ctx.lookup("java:/comp/env/c3331532_assignment2/c3331532_database");           // explore what resources have been defined java:/comp/env/folder/database

        Connection conn = ds.getConnection();                                                                       // connection to database server (pooled connection)
        String query = "SELECT * FROM beanStorage WHERE username = ?";                                              // specify query
        Statement stmt = conn.createStatement();                                                                    // create statement to execute query
        ResultSet rs = stmt.executeQuery(query);                                                                    // execute our statement   

        // result set table (cursor moves around)
        while(rs.next()){                                                                                           // iterate through every row
            rs.getString("username").equals(username);


            /*
            String title = rs.getString("title");                                                                   // inspection needed
            int year = rs.getInt("year");
            String url = rs.getString("url");
            */
            
        }

        /* GOD CODE
        makeDataSource();
        System.out.println("DataSource executed");

        System.out.println(this.username != null ? this.username : "quite dumb");

        String query = "SELECT * FROM games WHERE username = ?";

        statement.setString(1, this.username);

        ResultSet rs = statement.executeQuery();

        if(rs.next()){
            String updateQuery = "UPDATE games SET gameGrid = ? WHERE username = ?";
            PreparedStatement updateStatement = source.getConnection().prepareStatement(updateQuery);

            updateStatement.setString(1, this.serialise());
            updateStatement.setString(2, this.username);
            
            updateStatement.execute();
        }else{
            String updateQuery = "INSERT INTO games(username, gameGrid) VALUES (?, ?)";
            PreparedStatement updateStatement = source.getConnection().prepareStatement(updateQuery);

            updateStatement.setString(1, this.username);
            updateStatement.setString(2, this.serialise());


            updateStatement.execute();
        }

        source.getConnection().close();
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
                Instant end = Instant.now();
                timeElapsed = Duration.between(start, end);
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
        return timeElapsed.toMillis() / 1000;
    }

    public String getUsername(){
        return username;
    }
}