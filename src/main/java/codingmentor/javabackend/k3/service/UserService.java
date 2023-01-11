package codingmentor.javabackend.k3.service;

import codingmentor.javabackend.k3.model.User;

public interface UserService {

	/**
	 * Sign in method to get a user from database by username and password
	 *
	 * @param email => user's email 
	 * @param password user's password
	 * @return an user if found or null
	 */
	User validateLogin(String email, String password);
	
	/**
	 * 
	 * @param email user's email
	 * @return an user if found or null
	 */
	User findUserByEmail(String email);
	
	
    /**
     * Sign up method to add new player to database if not existed
     *
     * @param username player username
     * @param password player password
     * @param name     player name
     * @return a Player instance if user input is valid and not duplicate or null
     */
    User register(String email, String password, String firstName, String lastName);
}
