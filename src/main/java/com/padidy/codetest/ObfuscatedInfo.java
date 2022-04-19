package com.padidy.codetest;

import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

public class ObfuscatedInfo {
	public static void main(String[] args) {
		String s = obfuscatedEmail("local-part@domain-name.com");
		System.out.println(s.equals("l*****t@domain-name.com"));
	}

	public static String emailPattern = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";

	public static String obfuscatedEmail(String email) {
		if (isBlank(email)) {
			return email;
		}
		email = email.trim();

		if (patternMatches(email, emailPattern)) {
			String[] emailInfo = email.split("@");
			String emailName = emailInfo[0].toLowerCase();
			String emailDomain = emailInfo[1];
			if (emailName.length() > 5) {
				var firtChar = emailName.charAt(0) + "";
				var lastChar = emailName.charAt(emailName.length() - 1) + "";

				return firtChar + "*****" + lastChar + "@" + emailDomain;
			}
			return "*****@" + emailDomain;
		}
		return email;
	}

	public static String obfuscatedPhone(String phone) {
		if (isBlank(phone)) {
			return phone;
		}
		phone = phone.trim();
		return "";
	}

	private static boolean isBlank(String str) {
		return str == null || str.trim().isEmpty();
	}

	private static boolean patternMatches(String str, String regexPattern) {
		return Pattern.compile(regexPattern).matcher(str).matches();
	}
}
