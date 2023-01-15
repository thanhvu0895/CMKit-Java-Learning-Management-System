package codingmentor.javabackend.k3.repository.Impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import codingmentor.javabackend.k3.mapper.UserMapper;
import codingmentor.javabackend.k3.model.User;
import codingmentor.javabackend.k3.repository.AbstractRepository;
import codingmentor.javabackend.k3.repository.UserRepository;

public class UserRepositoryImpl extends AbstractRepository<User> implements UserRepository {
	private static UserRepository repository = null;
	
	// Mapper will map data from result set to the domain object
    private final UserMapper mapper;
    
    private UserRepositoryImpl() {
    	mapper = new UserMapper();
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
            return (results.next()) ? mapper.map(results) : null;
    	});
    }
    
    /**
     * {@inheritDoc}
     *
     * @param User: user object
     * returns nothing
     */

	@Override
	public boolean insert(User user) {
		return executeUpdate(connection -> {
			final String query = "INSERT INTO users(email, admin, first_name, last_name, set_up, password_digest)"
					+ "VALUES (?, ?, ?, ?, ?, ?);";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, user.getEmail());
			statement.setBoolean(2, user.isAdmin());
			statement.setString(3, user.getFirst_name());
			statement.setString(4, user.getLast_name());
			statement.setString(5, user.getPassword_digest());
			System.out.println(statement);
			return statement.executeUpdate();
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
			 return (results.next() && results.getString("email").equals(email)) ? new User() : null;
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
            return (results.next()) ? mapper.map(results) : null;
    	});
	}

	@Override
	public boolean updateUser(String first_name, String last_name, String preferred_name, boolean admin, boolean disabled, int id) {
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
			 return statement.executeUpdate();
		}) != 0;
	}

	@Override
	public boolean deleteUser(int id) {
		return executeUpdate(connection -> {
			 final String query = "DELETE FROM users WHERE id = ?;";
			 PreparedStatement statement = connection.prepareStatement(query);
			 statement.setInt(1, id);
			 System.out.println(statement);
			 return statement.executeUpdate();
		}) != 0;
	}
	
	@Override
	public boolean updatePreferredNameById(String preferred_name, int id) {
		// TODO Auto-generated method stub
		return executeUpdate(connection -> {
			 final String query = "UPDATE users SET preferred_name = ? WHERE id = ?;";
			 PreparedStatement statement = connection.prepareStatement(query);
			 statement.setString(1, preferred_name);
			 statement.setInt(2, id);
			 System.out.println(statement);
			 return statement.executeUpdate();
		}) != 0;
	}

	@Override
	public boolean updatePassword(String new_password, User user) {
		return executeUpdate(connection -> {
			 final String query = "UPDATE users SET password_digest = ? WHERE id = ?;";
			 PreparedStatement statement = connection.prepareStatement(query);
			 statement.setString(1, new_password);
			 statement.setInt(2, user.getId());
			 System.out.println(statement);
			 return statement.executeUpdate();
		}) != 0;
	}
}
