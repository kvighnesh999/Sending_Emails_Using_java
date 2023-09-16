package com.vighnesh;

import java.io.File;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class App {
	public static void main(String[] args) {
		
		System.out.println("preparing to send message ...");
		String message = "hello i am vighnesh";
		String subject = "Email : Confirmation";
		String to = "kvighnesh999@gmail.com";
		String from = "kvighnesh123@gmail.com";
		
		sendEmail(message,subject,to,from);
//		sendAttach(message,subject,to,from);
	}

	//Attachment function
	private static void sendAttach(String message, String subject, String to, String from) {

		String host="smtp.gmail.com";
		Properties properties = System.getProperties();
		System.out.println("PROPERTIES "+properties);
		
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port","465");
		properties.put("mail.smtp.ssl.enable","true");
		properties.put("mail.smtp.auth","true");
		
		//step1: load session
		Session session=Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {				
				return new PasswordAuthentication("kvighnesh123@gmail.com", "hvlnlcnovdnhddap");
			}
			
		});
		
		session.setDebug(true);
		//step2: compose email
		MimeMessage m = new MimeMessage(session);
		try {
			m.setFrom(from);
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			m.setSubject(subject);
			String path="C:\\Users\\user\\Desktop\\logo.png";
		
			
			MimeMultipart mimeMultipart = new MimeMultipart();
			MimeBodyPart textMime = new MimeBodyPart();
			MimeBodyPart fileMime = new MimeBodyPart();
		
		try {
			
			textMime.setText(message);
			File file=new File(path);
			fileMime.attachFile(file);
			
			mimeMultipart.addBodyPart(textMime);
			mimeMultipart.addBodyPart(fileMime);
		
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		m.setContent(mimeMultipart);
	
		//step3 : send 
		Transport.send(m);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Sent success...................");
	}

	//send text mail function
	private static void sendEmail(String message, String subject, String to, String from) {
		
		String host="smtp.gmail.com";
		
		Properties properties = System.getProperties();
		System.out.println("PROPERTIES "+properties);
		
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port","465");
		properties.put("mail.smtp.ssl.enable","true");
		properties.put("mail.smtp.auth","true");
		
		//step1: load session
		Session session=Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {				
				return new PasswordAuthentication("kvighnesh123@gmail.com", "hvlnlcnovdnhddap");
			}
		});
		
		session.setDebug(true);
		
		//step2 : compose
		MimeMessage m = new MimeMessage(session);
		try {
			m.setFrom(from);
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			m.setSubject(subject);
			m.setText(message);
			//step3 : send
			Transport.send(m);
			System.out.println("Sent success...................");
		
		}catch (Exception e) {
			e.printStackTrace();
		}	
	}
}

