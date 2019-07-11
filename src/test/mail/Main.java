package test.mail;

import javax.mail.MessagingException;

public class Main {

	public static void main(String[] args) {
		
		String body = JwtGenerator.createJwtTest("aprohenza@gmail.com", "registration request", 600000);

		try {
			MailManager.enviarConGMail("aprohenza@gmail.com", "mail desde java", body);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}	

}
