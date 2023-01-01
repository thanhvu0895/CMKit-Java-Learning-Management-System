package codingmentor.javabackend.k3.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
	 private static final String URL = "jdbc:mysql://kit-database-do-user-12351930-0.b.db.ondigitalocean.com:25060/db_kit?useSSL=false";
	 private static final String USERNAME = "doadmin"; // mysql username
	 private static final String PASSWORD = "AVNS_3gPXYc_UWokHhj9cFQL"; //mysql password
	
	 public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		}  catch (ClassNotFoundException e) {
            System.out.println("Driver not Found!");
            e.printStackTrace();
        } catch (SQLException e) {
        	System.out.println("DB not found");
        	e.printStackTrace();
        }
		return conn;
	}
	 
	 public static boolean isConnectionValid(Connection connection) {
		 try {
			 if (connection != null && !connection.isClosed()) {
	            // Running a simple validation query
	            connection.prepareStatement("SELECT 1");
	            return true;
	        }
        }
        catch (SQLException e) {
            // log some useful data here
        }
        return false;
    }
	
	 
	public static void main(String[] args) {
		System.out.println(isConnectionValid(getConnection()));
	}
}
