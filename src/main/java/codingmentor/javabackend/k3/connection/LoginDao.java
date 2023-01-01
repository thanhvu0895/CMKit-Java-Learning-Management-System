package codingmentor.javabackend.k3.connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import codingmentor.javabackend.k3.bean.LoginBean;
public class LoginDao {
    public boolean validate(LoginBean loginbean) throws ClassNotFoundException {
        boolean status = false;
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (
            Connection connection = DriverManager.getConnection("jdbc:mysql://kit-database-do-user-12351930-0.b.db.ondigitalocean.com:25060/db_kit?useSSL=false", "doadmin", "AVNS_3gPXYc_UWokHhj9cFQL");
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM users where email = ? and password_digest = ?")) {
            stmt.setString(1, loginbean.getEmail());
            stmt.setString(2, loginbean.getPassword_digest());          
            System.out.println(stmt);
            ResultSet rs = stmt.executeQuery();
            status = rs.next();
        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
        return status;
    }
    private void printSQLException(SQLException ex) {
         for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}