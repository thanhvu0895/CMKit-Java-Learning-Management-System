package codingmentor.javabackend.k3.Utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.http.HttpServletRequest;

import codingmentor.javabackend.k3.model.User;

public class AccountsMailer {
	
	
	public static void invite_user_email(HttpServletRequest request, User user, String token) throws IOException, URISyntaxException{
		BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\xampp\\htdocs\\cmkit\\src\\main\\webapp\\log\\MailLog.txt"));
    	String servletPath = UrlUtils.putIdInPath(UrlUtils.SHOW_USER_INVITE_PATH, user.getId());
    	String baseUrl = request.getRequestURL().substring(0, request.getRequestURL().length() - request.getRequestURI().length()) + request.getContextPath();
    	String show_user_invite_url = baseUrl + servletPath + "?token=" + token;
    	String emailBody = "<p>\r\n"
    			+ "    Dear " + user.getEmail() +",\r\n"
    			+ "<br><br>\r\n"
    			+ "    You have been invited to create an account on the Kit platform. To create your account, please follow the link below:\r\n"
    			+ "<br>\r\n"
    			+ "    <a href=" + show_user_invite_url + "> " + show_user_invite_url + " </a>\r\n"
    			+ "<br><br>\r\n"
    			+ "    You will be prompted for your name and asked to create a password. Once you have set up your account, you can log in using your email (" + user.getEmail() +") and password at " + baseUrl + ".\r\n"
    			+ "<br><br>\r\n"
    			+ "    If you have any trouble setting up an account or have a question, please contact your professor.\r\n"
    			+ "<br><br>\r\n"
    			+ "    Please do not reply to this email, as it was sent by a lazy robot who never checks its inbox.\r\n"
    			+ "</p>\r\n"
    			+ "\r\n"
    			+ "";
    	mail(user.getEmail(), "Kit Account Setup", emailBody);
    	writer.append(emailBody);
    	writer.close();
	}
	
	public static void mail(String to, String subject, String body) {
		// Recipient's email ID needs to be mentioned.
		String from = "thanh.vu15@kzoo.edu";

		// Assuming you are sending email from gmail smtp
		String host = "email-smtp.us-east-1.amazonaws.com";

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.put("mail.smtp.auth", true);
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "25");
		properties.put("mail.smtp.ssl.trust", host);
		// Get the Session object.// and pass username and password
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("AKIAQJEQ4YBALLTVKEJ5", "BAFv6bh/Th97fSyFC3oFtW7DX/R6jGErcC+HzRkSNtcP");
			}
		});
		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Set Subject: header field
			message.setSubject(subject);

			// Send the actual HTML message.
			message.setContent(body, "text/html");

			System.out.println("sending...");
			// Send message
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws URISyntaxException, IOException {
    	URL url = ClassLoader.getSystemResource("MailLog.txt");
		File f = new File(url.toURI().getPath());
		FileWriter fw = new FileWriter(f.getAbsoluteFile());
		BufferedWriter writer = new BufferedWriter(fw);
		String emailBody = "TESTTODOABV";
    	writer.write(emailBody);
    	writer.close();
//		// Recipient's email ID needs to be mentioned.
//		String to = "hongkhanhvu94@gmail.com";
//
//		// Sender's email ID needs to be mentioned
//		String from = "thanh.vu15@kzoo.edu";
//
//		// Assuming you are sending email from gmail smtp
//		String host = "email-smtp.us-east-1.amazonaws.com";
//
//		// Get system properties
//		Properties properties = System.getProperties();
//
//		// Setup mail server
//		properties.put("mail.smtp.auth", true);
//		properties.put("mail.smtp.starttls.enable", "true");
//		properties.put("mail.smtp.host", host);
//		properties.put("mail.smtp.port", "25");
//		properties.put("mail.smtp.ssl.trust", host);
//
//		// Get the Session object.// and pass username and password
//		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
//
//			protected PasswordAuthentication getPasswordAuthentication() {
//
//				return new PasswordAuthentication("AKIAQJEQ4YBALLTVKEJ5", "BAFv6bh/Th97fSyFC3oFtW7DX/R6jGErcC+HzRkSNtcP");
//
//			}
//		});
//
//		try {
//			// Create a default MimeMessage object.
//			MimeMessage message = new MimeMessage(session);
//
//			// Set From: header field of the header.
//			message.setFrom(new InternetAddress(from));
//
//			// Set To: header field of the header.
//			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//
//			// Set Subject: header field
//			message.setSubject("Hello World!!");
//
//			// Send the actual HTML message.
//			message.setContent("<h1>This is actual message embedded in HTML tags</h1>", "text/html");
//
//			System.out.println("sending...");
//
//			// Send message
//			Transport.send(message);
//			System.out.println("Sent message successfully....");
//		} catch (MessagingException mex) {
//			mex.printStackTrace();
//		}
	}
}