package codingmentor.javabackend.k3.repository;

import codingmentor.javabackend.k3.model.User;

public interface UserRepository {
	/**
	 * Insert data of user to database
	 *
	 * @param user data will insert to database
	 * @return true if insert successful or false
	 */
	void insert(User user);
    
	/**
     * Check if user exists by email 
     *
     * @param username player username
     * @return true if player existed or false
     */
    boolean existedByEmail(String email);
    
	/**
	 * Find player by username
	 *
	 * @param username player username
	 * @return a player instance if found or null
	 */
    User findUserByEmail(String email);
}
