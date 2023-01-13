package codingmentor.javabackend.k3.repository;

import java.util.List;

import codingmentor.javabackend.k3.model.User;

public interface UserRepository {
	/**
	 * Insert data of user to database
	 *
	 * @param user data will insert to database
	 * true if successful or false
	 */
	boolean insert(User user);
    
	/**
     * Check if user exists by email 
     *
     * @param email: users's email
     * @return true if user existed or false
     */
    boolean existedByEmail(String email);
    
	/**
	 * Find user by email
	 *
	 * @param email: user's email
	 * @return a user instance if found or null
	 */
    User findUserByEmail(String email);
    
	/**
	 * Find user by email
	 *
	 * @param id: user's id
	 * @return a user instance if found or null
	 */
    User findUserById(int id);
    
    /**
	 * Find list of users
	 *
	 * @return a list of all users if found or null
	 */
    List<User> getUsers();
    
    /**
	 * Update users
	 *
	 * @return boolean if update successful or else false
	 */
    boolean updateUser(String first_name, String last_name, String preferred_name, boolean admin, boolean disabled, int id);
    
    
}
