package com.socialceep.mailing;

import javax.mail.MessagingException;

public class Main {

	public static void main(String[] args) {
		
		String body = JwtGenerator.createJwtToUserRegistration("Pepe", " Perez Perez", "aprohenza@gmail.com", "estaesmipassword", "MALE");

		try {
			MailManager.enviarConGMail("aprohenza@gmail.com", "MAIL DE PRUEBA DESDE SOCIAL CEEP", body);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}	

}
