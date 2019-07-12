package com.socialceep.mailing;

import java.util.Date;

import com.socialceep.encoder.PasswordEncoderGenerator;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtGenerator {
	
	private static final String SECRET_KEY = "habshdbahsdbhajsdvhbvh";
	
	public static String createJwtToUserRegistration(String userNameRegistration, String userLastNameRegistration,
			String userEmailRegistration, String userPasswordRegistration, String userGenderRegistration) {

		// The JWT signature algorithm we will be using to sign the token
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		
		long expMillis = nowMillis + 86400000; // 24 horas
		Date expireTime = new Date(expMillis);

		

		
		// Let's set the JWT Claims
		JwtBuilder builder = Jwts.builder()
								.setIssuedAt(now)
								.setSubject("Registration token request.")
								.setExpiration(expireTime)
								.claim("name", userNameRegistration)
								.claim("lastname", userLastNameRegistration)
								.claim("email", userEmailRegistration)
								.claim("password", PasswordEncoderGenerator.encode(userPasswordRegistration))
								.claim("gender", userGenderRegistration)
								.signWith(signatureAlgorithm, SECRET_KEY);

		

		// Builds the JWT and serializes it to a compact, URL-safe string
		return builder.compact();
	}

}
