package ar.edu.utn.frba.dds.modelo.entidades.notificador;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class NotificacionMail implements EstrategiaNotificacion {
	
	@Override
	public void enviarNotificacion(Notificacion notificacion) {
		try (InputStream input = NotificacionMail.class
				.getClassLoader()
				.getResourceAsStream("tokens.properties")) {
			
			Properties props = new Properties();
			props.load(input);
			
			String username = props.getProperty("usernameMail");
			String password = props.getProperty("passwordMail");
			String from = "joabruno@frba.utn.edu.ar";
			Authenticator authenticator = new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			};
			
			Session session = Session.getInstance(props, authenticator);
			
			try {
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(from));
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(notificacion.getReceptor().getMail()));
				message.setSubject("PruebaMail");
				message.setText(notificacion.getMensaje());
				
				Transport.send(message);
				
				System.out.println("Mensaje enviado correctamente.");
			} catch (
					MessagingException e) {
				e.printStackTrace();
			}
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}

