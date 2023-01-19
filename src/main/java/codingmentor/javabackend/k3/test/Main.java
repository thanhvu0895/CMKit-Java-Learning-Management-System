package codingmentor.javabackend.k3.test;



import java.io.IOException;
import java.net.URISyntaxException;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import codingmentor.javabackend.k3.Utils.PBKDF2Hasher;
import codingmentor.javabackend.k3.Utils.RandomUtils;
import codingmentor.javabackend.k3.repository.UserRepository;
import codingmentor.javabackend.k3.repository.Impl.UserRepositoryImpl;

public class Main {
	public static void main(String[] args) throws IOException, URISyntaxException  {
		String password = "1234";
    	PBKDF2Hasher hasher = new PBKDF2Hasher();
    	String hashedPass = hasher.hash(password.toCharArray());
    	System.out.println(hashedPass);
    	System.out.println(hasher.checkPassword(password.toCharArray(), hashedPass));
    	
    	String emailBody = "<p>\r\n"
    			+ "    Dear,\r\n"
    			+ "<br><br>\r\n"
    			+ "    You have been invited to create an account on the Kit platform. To create your account, please follow the link below:\r\n"
    			+ "<br>\r\n"
    			+ "    <a href=>  </a>\r\n"
    			+ "<br><br>\r\n"
    			+ "    You will be prompted for your name and asked to create a password. Once you have set up your account, you can log in using your email () and password at .\r\n"
    			+ "<br><br>\r\n"
    			+ "    If you have any trouble setting up an account or have a question, please contact your professor.\r\n"
    			+ "<br><br>\r\n"
    			+ "    Please do not reply to this email, as it was sent by a lazy robot who never checks its inbox.\r\n"
    			+ "</p>\r\n"
    			+ "\r\n"
    			+ "";
    	System.out.println("Body of email: " + emailBody);
    	UserRepository userRepository = UserRepositoryImpl.getInstance();
    	userRepository.findUserById(123124809);
	}
}
