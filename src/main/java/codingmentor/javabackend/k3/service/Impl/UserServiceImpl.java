package codingmentor.javabackend.k3.service.Impl;

import codingmentor.javabackend.k3.model.User;
import codingmentor.javabackend.k3.repository.UserRepository;
import codingmentor.javabackend.k3.repository.Impl.UserRepositoryImpl;
import codingmentor.javabackend.k3.service.UserService;

public class UserServiceImpl implements UserService {
	private static UserService userService = null;
	private final UserRepository userRepository;
	
	private UserServiceImpl( ) {
		this.userRepository = UserRepositoryImpl.getInstance();
	}
	
	public static UserService getInstance() {
		if (userService == null) {
			userService = new UserServiceImpl();
		}
		return userService;
	}

	@Override
	public User validateLogin(String email, String password) {
		User user = userRepository.findUserByEmail(email);
		if (user == null) {
			return null;
		}
		
		return user.getPassword_digest().equals(password) ? user : null;
	}

	@Override
	public User findUserByEmail(String email) {
		return userRepository.findUserByEmail(email);
	}

	@Override
	public User register(String email, String password, String firstName, String lastName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
    /**
     * Validate user input
     *
     * @param email: user's email
     * @param password user's password
     * @param first_name user's  name
     * @return true if valid or false
     */
	
	
}
