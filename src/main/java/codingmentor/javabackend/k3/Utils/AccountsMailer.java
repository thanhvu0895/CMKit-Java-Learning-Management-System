package codingmentor.javabackend.k3.Utils;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.http.HttpServletRequest;

import codingmentor.javabackend.k3.model.User;

public class AccountsMailer {
	
	public static void passwordResetEmail(HttpServletRequest request, User user, String token) throws IOException, URISyntaxException{ 
		try {
	    	String baseUrl = request.getRequestURL().substring(0, request.getRequestURL().length() - request.getRequestURI().length()) + request.getContextPath();
	    	String show_use_password_reset_url = baseUrl + UrlUtils.SHOW_USER_PASSWORD_RESET_PATH + "?token=" + token +"&user=" + user.getId();
	    	String emailBody = "<p>\r\n"
	    			+ "    Dear " + user.getPreferred_first_name() + ",\r\n"
	    			+ "<br><br>\r\n"
	    			+ "    Here is the link to reset your Kit password:\r\n"
	    			+ "<br>\r\n"
	    			+ "    <a href=" + show_use_password_reset_url + "> " + show_use_password_reset_url + " </a>\r\n"
	    			+ "<br><br>\r\n"
	    			+ "    If you did not request a password reset, you should disregard this email.\r\n"
	    			+ "<br><br>\r\n"
	    			+ "    Please do not reply to this email, as it was sent by a lazy robot who never checks its inbox.\r\n"
	    			+ "</p>\r\n"
	    			+ "";
    	//TODO: COMMENT THIS PART BEFORE BUILD TO PRODUCTION SERVER
	    	
	    	/* BEGIN COMMENT FOR PRODUCTION */
			// Specify the file name and path here:
			File file = new File("C:\\xampp\\htdocs\\cmkit\\src\\main\\webapp\\log\\MailLog.txt");	 	
			/* This logic is to create the file if the
			 * file is not already present
			 */			
			if(!file.exists()){
				file.createNewFile();
			}	
	    	// Here true is to append the content to file		
			BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
	    	bw.write(emailBody);
	    	// Closing BufferedWriter Stream
	    	bw.close();  	
	    	/* END COMMENT FOR PRODUCTION */
	    	
	      	mail(user.getEmail(), "Kit Password Reset", emailBody);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void inviteUserEmail(HttpServletRequest request, User user, String token) throws IOException, URISyntaxException{
		
		try {
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
	    	//TODO: COMMENT THIS PART BEFORE BUILD TO PRODUCTION SERVER
			// Specify the file name and path here:
			File file = new File("C:\\xampp\\htdocs\\cmkit\\src\\main\\webapp\\log\\MailLog.txt");
		 	
			/* This logic is to create the file if the
			 * file is not already present
			 */
			
			if(!file.exists()){
				file.createNewFile();
			}
	    	// Here true is to append the content to file		
			BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
	    	bw.write(emailBody);
	    	// Closing BufferedWriter Stream
	    	bw.close();
	    	
	    	mail(user.getEmail(), "Kit Account Setup", emailBody);    	
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		properties.put("mail.smtp.port", "587");
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
}