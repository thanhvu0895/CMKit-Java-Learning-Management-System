package codingmentor.javabackend.k3.test;

import codingmentor.javabackend.k3.DAO.LoginDAOImpl;
import codingmentor.javabackend.k3.model.User;
import codingmentor.javabackend.k3.repository.UserRepository;
import codingmentor.javabackend.k3.repository.Impl.UserRepositoryImpl;

public class Main {
	public static void main(String[] args) {
		LoginDAOImpl login = new LoginDAOImpl();
		UserRepository userR = UserRepositoryImpl.getInstance();
		
	}
}
