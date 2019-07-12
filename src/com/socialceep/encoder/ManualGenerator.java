package com.socialceep.encoder;

public class ManualGenerator {

	public static void main(String[] args) {

		String encoded = PasswordEncoderGenerator.encode("galileo");

		System.out.println(encoded);
	}
}
