package codingmentor.javabackend.k3.test;



import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.TimeZone;
import codingmentor.javabackend.k3.Utils.PBKDF2Hasher;
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
	    LocalDateTime now = LocalDateTime.now();    
	    System.out.println(now.toInstant(ZoneOffset.of("-00:00")));
	    
	  
	    int offsetInSeconds = TimeZone.getTimeZone("EST").getRawOffset() / 1000;

	    ZoneOffset zoneOffset = ZoneOffset.ofTotalSeconds(offsetInSeconds);

	    String zoneOffsetString = zoneOffset.toString();
	    System.out.println(zoneOffsetString);
	     //  LocalDateTime to Timestamp
        Timestamp timestamp = Timestamp.valueOf("2023-01-19 19:36:23.770611");
        System.out.println(timestamp);
        LocalDateTime localDateTime = timestamp.toLocalDateTime();
        System.out.println(localDateTime);  // 2019-06-14T15:50:36.068076300
     
        
        
	}
	
	public static Date convertLocalDateTimeToDateUsingInstant(LocalDateTime dateToConvert) {
	    return java.util.Date
	      .from(dateToConvert.atZone(ZoneId.systemDefault())
	      .toInstant());
	}
}
