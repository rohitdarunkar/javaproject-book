package ListUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUtils {
	public static Connection con;
	
	public static Connection getConnection() {
		
		try{
			
			Class.forName("com.mysql.cj.jdbc.Driver");
		    Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/demodb","root","");
		
	        return con;
		    
		} catch(Exception e) {
			
			System.out.println("Connection Error: " + e.getMessage());
			
		    return null;
		    
		}
		
	}	
	
	public static void close(Connection con) {
		
		try {
			
			con.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
	}
	
    public static void printSQLException(SQLException e) {
		
		System.out.println(e);
		
	}public static ResultSet getResultFromSqlQuery(String SqlQueryString) {
        //Creating Resultset object
        ResultSet rs = null;
        try {
            //Checking whether the connection is null or null
            if (con == null) {
                getConnection();
            }
            //Querying the query
            rs = con.createStatement().executeQuery(SqlQueryString);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return rs;
    }

    //Creating universal method to query for inserting or updating information in mysql database
    public static int insertUpdateFromSqlQuery(String SqlQueryString) {
        int i = 2;
        try {
            //Checking whether the connection is null or null
            if (con == null) {
                getConnection();
            }
            //Querying the query
            i = con.createStatement().executeUpdate(SqlQueryString);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return i;
    }
}
	


