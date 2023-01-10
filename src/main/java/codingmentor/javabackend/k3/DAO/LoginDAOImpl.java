package codingmentor.javabackend.k3.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import codingmentor.javabackend.k3.connection.DBConnect;
import codingmentor.javabackend.k3.model.User;
public class LoginDAOImpl {
	
    public boolean validate(User user) throws ClassNotFoundException {
        try {
        	// Open connection to database;
        	Connection conn = DBConnect.getConnection();
        	String sql = "SELECT * FROM users where email = ? and password_digest = ?";
        	
        	// Query database
            PreparedStatement stmt = conn.prepareStatement(sql); 
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getPassword_digest());          
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
            	return true;     
            }      
            conn.close(); // close dbconnection to save resources
            
        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
        return false;
    }
    
    
    public String getFirstNameFromUser(User user) throws ClassNotFoundException {
    	String result = "";
    	String sql = "SELECT first_name FROM users where email = ?";
    	try {
        	Connection conn = DBConnect.getConnection();
        	// Query database
            PreparedStatement stmt = conn.prepareStatement(sql); 
            stmt.setString(1, user.getEmail());

            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
            	return rs.getString(1); 
            };
            conn.close(); // close dbconnection to save resources
        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
        return result;
    }
    
    
    public boolean isAdmin(User user) throws ClassNotFoundException {
    	String sql = "SELECT admin FROM users where email = ?";
    	try {
        	Connection conn = DBConnect.getConnection();
        	// Query database
            PreparedStatement stmt = conn.prepareStatement(sql); 
            stmt.setString(1, user.getEmail());

            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
            	return rs.getBoolean(1); 
            };
            conn.close(); // close dbconnection to save resources
        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
        return false;
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