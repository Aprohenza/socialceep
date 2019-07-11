package com.formbean.encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncoderGenerator {

	public static String encode(String password) {
		MessageDigest md = null;
		StringBuffer encoded = new StringBuffer();
		
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}		

		byte[] hash = md.digest(password.getBytes());

		for (byte b : hash) {
			encoded.append(String.format("%02x", b));
		}
		
		return encoded.toString();	
	}
}