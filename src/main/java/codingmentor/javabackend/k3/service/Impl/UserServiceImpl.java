package codingmentor.javabackend.k3.service.Impl;

import java.util.List;

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

	@Override
	public List<User> getUsers() {
		return userRepository.getUsers();
	}

	@Override
	public User findUserById(int id) {
		return userRepository.findUserById(id);
	}

	@Override
	public boolean updateUser(String first_name, String last_name, String preferred_name, boolean admin,
			boolean disabled, int id) {
		return userRepository.updateUser(first_name, last_name, preferred_name, admin, disabled, id);
	}

	@Override
	public boolean deleteUser(int id) {
		return userRepository.deleteUser(id);
	}
	
	@Override
	public boolean updatePreferredNameById(String preferred_name, int id) {
		return userRepository.updatePreferredNameById(preferred_name, id);
	}

	@Override
	public boolean updatePassword(String new_password, User user) {
		return userRepository.updatePassword(new_password, user);
	}
	
}
