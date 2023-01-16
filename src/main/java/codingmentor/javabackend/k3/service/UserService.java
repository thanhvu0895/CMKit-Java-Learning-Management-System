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

}
