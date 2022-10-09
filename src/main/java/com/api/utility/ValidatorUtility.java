package com.api.utility;

import org.springframework.stereotype.Component;

@Component
public class ValidatorUtility {

	/***
	 * Check and validate null string
	 */

	public static String checkString(String firstString, String secondString) {
		String fullString = "";
		if (firstString != null && !firstString.isEmpty()) {
			fullString = fullString + " " + firstString;
		}
		if (secondString != null && !secondString.isEmpty()) {
			fullString = fullString + " " + secondString;
		}
		return fullString;
	}
}
