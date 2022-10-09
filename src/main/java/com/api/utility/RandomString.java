package com.api.utility;

import java.security.SecureRandom;
import java.util.Random;

public class RandomString {

	@SuppressWarnings("unused")
	private static final Random generator = new Random(); 

	static final String SOURCE = "0123456789";
	
	static SecureRandom secureRnd = new SecureRandom();

	public static String randomString(int length) {
		
		StringBuilder sb = new StringBuilder(length); 
		for (int i = 0; i < length; i++) 
			sb.append(SOURCE.charAt(secureRnd.nextInt(SOURCE.length()))); 
		
		return sb.toString();
		
	}
		
}
