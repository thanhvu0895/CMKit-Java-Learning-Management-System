package codingmentor.javabackend.k3.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DBConnect {
	 private static final String URL = "jdbc:mysql://kit-database-do-user-12351930-0.b.db.ondigitalocean.com:25060/db_kit?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=America/New_York";
	 private static final String USERNAME = "doadmin"; // mysql username
	 private static final String PASSWORD = "Colen321!"; //mysql password
	 private static Logger logger = LogManager.getLogger("codingmentor");

	 public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		}  catch (ClassNotFoundException e) {
			logger.error("Driver not Found!", e.getMessage());
            //System.out.println("Driver not Found!");
            //logger.error(e.getMessage());
        } catch (SQLException e) {
			logger.error("DB not found", e.getMessage());
        	//System.out.println("DB not found");
        	//logger.error(e.getMessage());
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
	        logger.error(e.getMessage());
        	//logger.error(e.getMessage());
            // log some useful data here
        }
        return false;
    }

	public static void main(String[] args) {
        logger.info(isConnectionValid(getConnection()));
		//System.out.println(isConnectionValid(getConnection()));
	}
}
