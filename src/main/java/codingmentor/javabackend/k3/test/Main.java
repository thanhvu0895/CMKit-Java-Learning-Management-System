package codingmentor.javabackend.k3.test;

import codingmentor.javabackend.k3.DAO.LoginDAOImpl;
import codingmentor.javabackend.k3.model.User;
import codingmentor.javabackend.k3.repository.UserRepository;
import codingmentor.javabackend.k3.repository.Impl.UserRepositoryImpl;

public class Main {
	public static void main(String[] args) {
		User user = new User("test@1234", "1234");
		LoginDAOImpl login = new LoginDAOImpl();
		UserRepository userR = UserRepositoryImpl.getInstance();
		try {
			System.out.println(login.validate(user));
			System.out.println(login.getFirstNameFromUser(user));
			System.out.println(login.isAdmin(user));
			System.out.println(userR.existedByEmail("test@12345"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
