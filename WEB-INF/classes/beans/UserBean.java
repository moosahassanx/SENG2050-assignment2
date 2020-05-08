// package as beans
package beans;

// database usage
import javax.sql.*;
import java.sql.*;
import javax.naming.*;
import javax.naming.InitialContext;

import java.io.Serializable;

public class UserBean implements Serializable{
    
    /**
	 *
	 */
	private static final long serialVersionUID = 1L;
    String username;
    
    private final DataSource dataSource = makeDataSource();
    
    private DataSource makeDataSource(){
        try{
            InitialContext ctx = new InitialContext();
            return (DataSource) ctx.lookup("java:/comp/env/c3331532_assignment2/c3331532_database");
        }catch (NamingException e){
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }
    public Connection getConnection() throws SQLException{
        return dataSource.getConnection();
    }

    // constructors
    public UserBean() { }

    public UserBean(final String u) throws SQLException {
        this.username = u;

        getConnection();
        
        /*
        // STEP1: load the jdbc driver
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").getDeclaredConstructor().newInstance();
            System.out.println("STEP1: jdbc driver has successfully loaded");
        }catch(Exception e){
            System.out.println("ERROR: The mssql jdbc driver could not be found");
            return;
        }

        // STEP2: create a connection to the database
        String url = "jdbc:sqlserver://localhost:1433;databaseName=c3331532_database;user=dbuser;password=dbuser";
        Connection connection = null;

        try{
            // open the connection with the drivermanager class
            connection = DriverManager.getConnection(url);
            System.out.println("STEP2: connected to the database");
        }catch(SQLException e){
            System.out.print("ERROR: ");
            System.out.println(e.getMessage());
            return;
        }
        */
    }

    // mutator
    public void setUsername(final String u) {
        this.username = u;
    }

    // accessor
    public String getUsername(){
        return username;
    }
}