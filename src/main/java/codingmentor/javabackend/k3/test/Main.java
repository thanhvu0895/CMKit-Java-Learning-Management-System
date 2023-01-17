package codingmentor.javabackend.k3.test;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

import codingmentor.javabackend.k3.Utils.PBKDF2Hasher;
import codingmentor.javabackend.k3.Utils.RandomUtils;

public class Main {
	public static void main(String[] args) throws IOException, URISyntaxException  {
		String password = RandomUtils.unique64();
    	PBKDF2Hasher hasher = new PBKDF2Hasher();
    	String hashedPass = hasher.hash(password.toCharArray());
    	System.out.println(hashedPass);
    	System.out.println(hasher.checkPassword(password.toCharArray(), hashedPass));
    	File f = new File("MailLog.txt");
    	String path = f.getAbsolutePath();
		FileWriter fw = new FileWriter(path);
		BufferedWriter writer = new BufferedWriter(fw);
		writer.write("WHY?");
		writer.close();
//    	writer.write(emailBody);
//    	writer.close();
	}
}
