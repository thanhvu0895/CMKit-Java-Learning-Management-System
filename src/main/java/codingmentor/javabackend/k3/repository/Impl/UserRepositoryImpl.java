package codingmentor.javabackend.k3.repository.Impl;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;

import java.util.List;

import codingmentor.javabackend.k3.Utils.PBKDF2Hasher;
import codingmentor.javabackend.k3.Utils.RandomUtils;
import codingmentor.javabackend.k3.mapper.UserMapper;
import codingmentor.javabackend.k3.model.User;
import codingmentor.javabackend.k3.repository.AbstractRepository;
import codingmentor.javabackend.k3.repository.UserRepository;

public class UserRepositoryImpl extends AbstractRepository<User> implements UserRepository {
	private static UserRepository repository = null;
	private PBKDF2Hasher hasher = null;
	// Mapper will map data from result set to the domain object
    private final UserMapper mapper;
    
    
    private UserRepositoryImpl() {
    	mapper = new UserMapper();
    	hasher = new PBKDF2Hasher();
    }
    
    public static UserRepository getInstance() {
    	if (repository == null) {
    		repository = new UserRepositoryImpl();
    	}
    	return repository;
    }
    
    /**
     * {@inheritDoc}
     *
     * @param email: user's email
     * @return A User with email or null
     */
    
    @Override
    public User findUserByEmail(String email) { 
		return executeQuerySingle(connection -> {

    		// Query to find user by email
		final String query = "SELECT * FROM users WHERE email = ? LIMIT 1;";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, email);
        ResultSet results = statement.executeQuery();
        System.out.println(statement);
        User user = (results.next()) ? mapper.map(results) : null;
        close(connection, statement, results);
        return user;
    	});
    }
    
    private void close (Connection connection, Statement ps, ResultSet rs) {
    	try {
    		if (rs != null) {
    			rs.close();
    		}
    		if (ps != null) {
    			ps.close();
    		}
    		
    		if (connection != null) {
    			connection.close();
    		}

    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    }
 
  	/*
  	 * # IMPORTANT: Accounts get a random password when they are first created. #
  	 * # This password cannot actually be used as the set_up boolean is false, and
  	 * # using a default password would be dumb # if set_up ever stopped working
  	 */
	/**
	 * {@inheritDoc}
	 * 
	 * @param email: user's email
	 * @param admin: where user is admin
	 * @return true if user is created and false if not
	 */
    
	@Override
	public boolean createUserSendInvite(String email, boolean admin) {
		return executeUpdate(connection -> {
			final String query = "INSERT INTO users(email, admin, password_digest)"
					+ "VALUES (?, ?, ?);";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, email);
			statement.setBoolean(2, admin);
			String password_digest = hasher.hash(RandomUtils.unique64().toCharArray());
			statement.setString(3, password_digest);
			System.out.println(statement);
			int result = statement.executeUpdate();
    		if (statement != null) {
    			statement.close();
    		}    		
    		if (connection != null) {
    			connection.close();
    		}
			return result;
		}) != 0;
	}

	
	/**
	 * {@inheritDoc}
	 * 
	 * @param email: user's email
	 * @return 
	 */
	@Override
	public boolean existedByEmail(String email) {
		return executeQuerySingle(connection -> {
			 final String query = "SELECT email FROM users WHERE email = ?  LIMIT 1;";
			 PreparedStatement statement = connection.prepareStatement(query);
			 statement.setString(1, email);
			 ResultSet results = statement.executeQuery();
			 System.out.println(statement);
			 User user = (results.next() && results.getString("email").equals(email)) ? new User() : null;
			 close(connection, statement, results);
			 return user;
		}) != null;
	}
	
	
	/**
	 * {@inheritDoc}
	 * 
	 * @return list of all users
	 */

	@Override
	public List<User> getUsers() {
		return executeQuery(connection -> {
			final String query = "SELECT * FROM users";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet results = statement.executeQuery();
			System.out.println(statement);
			List<User> usersList = new ArrayList<>();
			while(results.next()) {
				usersList.add(mapper.map(results));
			}
			close(connection, statement, results);
			return usersList;
		});
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param id: user's id
	 * @return 
	 */
	@Override
	public User findUserById(int id) {
		return executeQuerySingle(connection -> {

    		// Query to find user by email
    		final String query = "SELECT * FROM users WHERE id = ?  LIMIT 1;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet results = statement.executeQuery();
            System.out.println(statement);
            User user = (results.next()) ? mapper.map(results) : null;
            close(connection, statement, results);
            return user;
    	});
	}

	@Override
	public boolean updateUserEditAdmin(String first_name, String last_name, String preferred_name, boolean admin, boolean disabled, int id) {
		return executeUpdate(connection -> {
			final String query = "UPDATE users SET first_name = ?, last_name = ?, preferred_name = ?, admin = ?, disabled = ? WHERE id = ?;";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, first_name);
			statement.setString(2, last_name);
			statement.setString(3, preferred_name);
			statement.setBoolean(4, admin);
			statement.setBoolean(5, disabled);
			statement.setInt(6, id);
			System.out.println(statement);
			int result = statement.executeUpdate();
		 
			 if (statement != null) {
				 statement.close();
    		}
    		
    		if (connection != null) {
    			connection.close();
    		}
			 
			 
			 return result;
		}) != 0;
	}

	
	
	@Override
	public boolean deleteUser(int id) {
		return executeUpdate(connection -> {
			 final String query = " UPDATE users SET deleted = 1, set_up = 0 WHERE id = ?;";
			 PreparedStatement statement = connection.prepareStatement(query);
			 statement.setInt(1, id);
			 System.out.println(statement);
			 int result = statement.executeUpdate();
			 if (statement != null) {
				 statement.close();
			 }
 		
			 if (connection != null) {
				 connection.close();
			 }
 		
			 return result;
		}) != 0;
	}
	
	
	@Override
	public boolean recoverUser(int id) {
		return executeUpdate(connection -> {
			 final String query = " UPDATE users SET deleted = 0 WHERE id = ?;";
			 PreparedStatement statement = connection.prepareStatement(query);
			 statement.setInt(1, id);
			 System.out.println(statement);
			 int result = statement.executeUpdate();
			 if (statement != null) {
				 statement.close();
			 }
 		
			 if (connection != null) {
				 connection.close();
			 }
 		
			 return result;
		}) != 0;
	}
	
	
	@Override
	public boolean updatePreferredNameById(String preferred_name, int id) {
		return executeUpdate(connection -> {
			 final String query = "UPDATE users SET preferred_name = ? WHERE id = ?;";
			 PreparedStatement statement = connection.prepareStatement(query);
			 statement.setString(1, preferred_name);
			 statement.setInt(2, id);
			 System.out.println(statement);
			 int result = statement.executeUpdate();
			 if (statement != null) {
				 statement.close();
			 }
    		
			 if (connection != null) {
				 connection.close();
			 }
    		
			 return result;
		}) != 0;
	}

	@Override
	public boolean updatePassword(String new_password, User user) {
		return executeUpdate(connection -> {
			 final String query = "UPDATE users SET password_digest = ? WHERE id = ?;";
			 PreparedStatement statement = connection.prepareStatement(query);
			 String password_digest = hasher.hash(new_password.toCharArray());
			 statement.setString(1, password_digest);
			 statement.setInt(2, user.getId());
			 System.out.println(statement);
			 int result = statement.executeUpdate();
			 if (statement != null) {
				 statement.close();
			 }
    		
			 if (connection != null) {
				 connection.close();
			 }
    		
			 return result;
		}) != 0;
	}

	@Override
	public boolean updateResetDigest(int userid, String reset_digest) {
		return executeUpdate(connection -> {
			 final String query = " UPDATE users SET reset_digest = ? WHERE id = ?;";
			 PreparedStatement statement = connection.prepareStatement(query);
			 statement.setString(1, reset_digest);
			 statement.setInt(2, userid);
			 System.out.println(statement);
			 int result = statement.executeUpdate();
			 if (statement != null) {
				 statement.close();
			 }
    		
			 if (connection != null) {
				 connection.close();
			 }
    		
			 return result;
		}) != 0;
	}
	
	
	@Override
	public boolean updateResetExpires(int userid, LocalDateTime reset_expires) {
		return executeUpdate(connection -> {
			 final String query = " UPDATE users SET reset_expires = ?, deleted = 0 WHERE id = ?;";
			 PreparedStatement statement = connection.prepareStatement(query);
			 statement.setTimestamp(1, Timestamp.from(reset_expires.toInstant(ZoneOffset.of("-05:00"))));
			 statement.setInt(2, userid);
			 System.out.println(statement);
			 int result = statement.executeUpdate();
			 if (statement != null) {
				 statement.close();
			 }
    		
			 if (connection != null) {
				 connection.close();
			 }
    		
			 return result;
		}) != 0;
	}
	
	
	

	@Override
	public boolean updateUserInviteParams(int userid, String first_name, String last_name, String preferred_name, String password) {
		return executeUpdate(connection -> {
			 final String query = " UPDATE users SET first_name = ?, last_name = ?, preferred_name = ?, password_digest = ? WHERE id = ?;";
			 
			 PreparedStatement statement = connection.prepareStatement(query);
			 statement.setString(1, first_name);
			 statement.setString(2, last_name);
			 statement.setString(3, preferred_name);
			 String password_digest = hasher.hash(password.toCharArray());
			 statement.setString(4, password_digest);
			 statement.setInt(5, userid);
			 System.out.println(statement);
			 int result = statement.executeUpdate();
			 
			 if (statement != null) {
				 statement.close();
			 }
   		
			 if (connection != null) {
				 connection.close();
			 }
   		
			 return result;
		}) != 0;
	}

	@Override
	public boolean updateSetUpUser(int userid) {
		return executeUpdate(connection -> {
			 final String query = " UPDATE users SET set_up = 1 WHERE id = ?;";
			 PreparedStatement statement = connection.prepareStatement(query);
			 statement.setInt(1, userid);
			 System.out.println(statement);
			 int result = statement.executeUpdate();
			 if (statement != null) {
				 statement.close();
			 }
  		
			 if (connection != null) {
				 connection.close();
			 }
  		
			 return result;
		}) != 0;
	}
}
