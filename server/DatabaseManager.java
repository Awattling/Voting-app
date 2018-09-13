package server;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

public class DatabaseManager {
	protected static String databaseLocal = "jdbc:mysql://localhost:3306/voting_app"; 
	protected static String databaseUser = "root"; 
	protected static String databasePass = "password"; 
	protected static Connection con = null; 
	protected boolean databaseToggle = true; 
	
	public DatabaseManager(){
		initilizeDatabase();
	}
	
	public void initilizeDatabase(){
		try {
			 // Registering the SQL driver // 
		    Class.forName("com.mysql.jdbc.Driver");
		    // Connecting to database // 
		    con = DriverManager.getConnection(databaseLocal, databaseUser, databasePass);
			System.out.println("[DatabaseManager] Database connected");
			// Getting database metadata for table checks // 
			DatabaseMetaData dbmd = con.getMetaData();
			
			
			// Checking if polls table exists and creating if it does not // 
			ResultSet tables = dbmd.getTables(null, null, "polls", null); 
			if(tables.next()){
				System.out.println("[DatabaseManager] polls table found in database");
			}else{
				System.out.println("[DatabaseManager] polls table was not found in database. Creating Table");
				String sql = "CREATE TABLE POLLS " +
	                   "(id INTEGER not NULL, " +
	                   " active DATETIME, " + 
	                   " inActive DATETIME, " + 
	                   " question CHAR(255), " + 
	                   " PRIMARY KEY ( id ))";
				Statement stmt = (Statement) con.createStatement();
				stmt.executeUpdate(sql);
			}
			// Checking if options table exists and creating if it does not // 
			tables = dbmd.getTables(null, null, "options", null); 
			if(tables.next()){
				System.out.println("[DatabaseManager] options table found in database");
			}else{
				System.out.println("[DatabaseManager] options table was not found in database. Creating Table");
				String sql = "CREATE TABLE options " +
	                   "(id INTEGER not NULL, " +
	                   " name CHAR(255), " + 
	                   " votes INTEGER DEFAULT 0, " + 
	                   " pollID INTEGER not Null, " +
	                   " FOREIGN KEY (pollID) REFERENCES polls(id),"+
	                   " PRIMARY KEY ( id ))";
				Statement stmt = (Statement) con.createStatement();
				stmt.executeUpdate(sql);
			}
			// Checking if people table exists and creating if it does not // 
			tables = dbmd.getTables(null, null, "people", null); 
			if(tables.next()){
				System.out.println("[DatabaseManager] people table found in database");
			}else{
				System.out.println("[DatabaseManager] people table was not found in database. Creating Table");
				String sql = "CREATE TABLE people " +
	                   "(id INTEGER not NULL, " +
	                   " fname CHAR(255), " +
	                   " lname CHAR(255), " +
	                   " address1 CHAR(255), " +
	                   " address2 CHAR(255), " +
	                   " postal CHAR(255), " +
	                   " city CHAR(255), " +
	                   " state CHAR(255), " +
	                   " country CHAR(255), " +
	                   " idLegal INTEGER, " +
	                   " pollID INTEGER not Null, " +
	                   " FOREIGN KEY (pollID) REFERENCES polls(id),"+
	                   " PRIMARY KEY ( id ))";
				Statement stmt = (Statement) con.createStatement();
				stmt.executeUpdate(sql);
			}
		}catch(Exception e){
			System.out.println("[DatabaseManager] Something went wrong initilizing database");
			System.out.println("[DatabaseManager] Running without database enabled and default poll information");
			try {
				con.close();
			} catch (SQLException e1) {
				// Nothing to worry about. Error closing database connector// 
			}
			con = null;
			databaseToggle = !databaseToggle; 
		}
		
	}

	public void toggle() {
		// TODO Auto-generated method stub
		
	}
}