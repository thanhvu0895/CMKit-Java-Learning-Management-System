package codingmentor.javabackend.k3.repository.Impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
    		final String query = "SELECT email, admin, first_name, password_digest FROM users WHERE email = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            ResultSet results = statement.executeQuery();
            
            return (results.next()) ? mapper.map(results) : null;
    	});
    }

	@Override
	public void insert(User user) {
		executeUpdate(connection -> {
			final String query = "INSERT INTO users(email, admin, first_name, last_name, set_up, password_digest)"
					+ "VALUES (?, ?, ?, ?, ?, ?);";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, user.getEmail());
			statement.setBoolean(2, user.isAdmin());
			statement.setString(3, user.getFirst_name());
			statement.setString(4, user.getLast_name());
			statement.setString(4, user.getPassword_digest());
			return statement.executeUpdate();
		});
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
			 final String query = "SELECT email FROM users WHERE email = ?;";
			 PreparedStatement statement = connection.prepareStatement(query);
			 statement.setString(1, email);
			 ResultSet results = statement.executeQuery();
			 return (results.next() && results.getString("email").equals(email)) ? new User() : null;
		}) != null;
	}
}
