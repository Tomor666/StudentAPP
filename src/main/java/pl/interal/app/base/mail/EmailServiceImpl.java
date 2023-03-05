package pl.interal.app.base.mail;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;


@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

	@Value("${spring.mail.username:someRandom}")
	private String username;

	@Value("${spring.mail.password:someRandom}")
	private String password;

	@Override
	public void sendAndEmail(String reciever, String textMsg) {
		final Properties properties = setEmailProperties();
		final Session session = createSession(properties);
		Message message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(reciever));
			message.setSubject("Mail Subject");

			MimeBodyPart mimeBodyPart = new MimeBodyPart();
			mimeBodyPart.setContent(textMsg, "text/html; charset=utf-8");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(mimeBodyPart);
			message.setContent(multipart);
			Transport.send(message);
		} catch (MessagingException e) {
			log.error("Something went wrong during reciever send. Error : {}", e.getCause().getMessage());
		}
	}


	private Session createSession(Properties prop) {
		return Session.getInstance(prop, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
	}


	private Properties setEmailProperties() {

		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "465");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.socketFactory.port", "465");
		prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		return prop;

	}
}
