package codingmentor.javabackend.k3.test;

import codingmentor.javabackend.k3.Utils.PBKDF2Hasher;
import codingmentor.javabackend.k3.Utils.RandomUtils;

public class Main {
	public static void main(String[] args) {
		
		String password = RandomUtils.unique64();
    	PBKDF2Hasher hasher = new PBKDF2Hasher();
    	String hashedPass = hasher.hash(password.toCharArray());
    	System.out.println(hashedPass);
    	System.out.println(hasher.checkPassword(password.toCharArray(), hashedPass));
	}
}
