package test.mail;

import java.util.Date;

import com.formbean.encoder.PasswordEncoderGenerator;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtGenerator {

	public static String createJwtTest(String email, String subject, long timeToExpireMillis) {

		// The JWT signature algorithm we will be using to sign the token
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		
		long expMillis = nowMillis + timeToExpireMillis;
		Date expireTime = new Date(expMillis);

		

		String SECRET_KEY = "habshdbahsdbhajsdvhbvh";
		// Let's set the JWT Claims
		JwtBuilder builder = Jwts.builder()
								.setIssuedAt(now)
								.setSubject(subject)
								.setExpiration(expireTime)
								.claim("email", email)
								.signWith(signatureAlgorithm, SECRET_KEY);

		

		// Builds the JWT and serializes it to a compact, URL-safe string
		return builder.compact();
	}
	
	public static String createJwtToUserRegistration(String userNameRegistration, String userLastNameRegistration,
			String userEmailRegistration, String userPasswordRegistration, String userGenderRegistration) {

		// The JWT signature algorithm we will be using to sign the token
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		
		long expMillis = nowMillis + 86400000; // 24 horas
		Date expireTime = new Date(expMillis);

		

		String SECRET_KEY = "habshdbahsdbhajsdvhbvh";
		// Let's set the JWT Claims
		JwtBuilder builder = Jwts.builder()
								.setIssuedAt(now)
								.setSubject("Reggistration token request.")
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
