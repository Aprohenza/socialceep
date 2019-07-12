package com.socialceep.mailing;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailManager {

	public static void enviarConGMail(String destinatario, String asunto, String cuerpo) throws MessagingException {
		// Esto es lo que va delante de @gmail.com en tu cuenta de correo. Es el
		// remitente tambi�n.

		Properties props = new Properties();

		// Nombre del host de correo, es smtp.gmail.com
		props.setProperty("mail.smtp.host", "smtp.gmail.com");

		// TLS si esta disponible
		props.setProperty("mail.smtp.starttls.enable", "true");

		// Puerto de gmail para envio de correos
		props.setProperty("mail.smtp.port", "587");

		// Nombre del usuario
		props.setProperty("mail.smtp.user", "aprohenza");

		// Si requiere o no usuario y password para conectarse.
		props.setProperty("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(props);

		// Para obtener un log de salida m�s extenso
		session.setDebug(true);

		MimeMessage message = new MimeMessage(session);

		message.setFrom(new InternetAddress("aprohenza@gmail.com"));
		message.addRecipients(Message.RecipientType.TO, destinatario); // Se podr�an a�adir varios de la misma manera
		message.setSubject(asunto);
		message.setText(cuerpo, "ISO-8859-1", "html");
		

		Transport transport = session.getTransport("smtp");
		transport.connect("aprohenza@gmail.com", "cajafuerte1");
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();

	}

}
