package codingmentor.javabackend.k3.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {
	private static final String URL = "jdbc:mysql://kit-database-do-user-12351930-0.b.db.ondigitalocean.com:25060/db_kit";
    private static final String USER_NAME = "doadmin";
    private static final String PASSWORD = "AVNS_3gPXYc_UWokHhj9cFQL";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL Driver Not Found!");
        } catch (SQLException e) {
        	System.out.println("DB not found!");
        	e.printStackTrace();
        }
        return null;
    }
}
