package codingmentor.javabackend.k3.service;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class SendHTMLEmail {

	public static void main(String[] args) {
		// Recipient's email ID needs to be mentioned.
		String to = "hongkhanhvu94@gmail.com";

		// Sender's email ID needs to be mentioned
		String from = "nhatthanh310895@gmail.com";

		// Assuming you are sending email from gmail smtp
		String host = "smtp.office365.com";

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

				return new PasswordAuthentication("nhatthanh310895@gmail.com", "Codingmentor2022");

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
			message.setSubject("Hello World!!");

			// Send the actual HTML message.
			message.setContent("<h1>This is actual message embedded in HTML tags</h1>", "text/html");

			System.out.println("sending...");

			// Send message
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}

	}

}