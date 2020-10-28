package com.root.chceckString;

public class Utils {
	
	public static boolean shortPassword(String password) {
//		String temp = password.toString();
		
		if (password.length() < 6) {
			return true;
		}
		return false;
	}
}
