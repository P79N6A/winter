package com.wuxi;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncodeTest {

	private static final String KEY_MD5 = "MD5"; 
	
	public static void main(String[] args) {
		String string = "hello";
		System.out.println(MD5Encode(string));
	}
	
	public static String MD5Encode(String  inputStr){
		BigInteger integer = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] input = inputStr.getBytes("utf-8");
			md.update(input);
			integer = new BigInteger(md.digest());
			
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return integer.toString(16);
	}
}
