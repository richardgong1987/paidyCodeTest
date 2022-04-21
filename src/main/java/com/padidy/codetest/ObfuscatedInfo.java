package com.padidy.codetest;

import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

public class ObfuscatedInfo {
	public static void main(String[] args) {
		{
			String s = obfuscatedEmail("local-part@domain-name.com");
			System.out.println(s.equals("l*****t@domain-name.com"));
		}
		{
			String s = obfuscatedPhone("+44 123 456 789");
			System.out.println(s.equals("+**-***-**6-789"));
		}


	}

	public static String emailPattern = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";
	public static String phonePattern = "^\\+?[0-9 ]{9,}$";

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
		if (patternMatches(phone, phonePattern)) {
			phone = phone.replaceAll("\\s+", "-");
			StringBuilder retPhone = new StringBuilder();
			int len = phone.length();
			var targetLen = 0;
			for (int j = len - 1; j >= 0; j--) {
				var c = phone.charAt(j) + "";
				boolean isNumber = Pattern.compile("\\d").matcher(c).matches();
				if (isNumber) {
					if (targetLen < 4) {
						retPhone.insert(0, c);
						targetLen++;
					} else {
						retPhone.insert(0, "*");
					}
				} else {
					retPhone.insert(0, c);
				}
			}
			return retPhone.toString();
		}
		return phone;
	}

	private static boolean isBlank(String str) {
		return str == null || str.trim().isEmpty();
	}

	private static boolean patternMatches(String str, String regexPattern) {
		return Pattern.compile(regexPattern).matcher(str).matches();
	}
}
