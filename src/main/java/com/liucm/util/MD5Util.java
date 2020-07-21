package com.liucm.util;

import javax.swing.JOptionPane;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {
	
	private static final String salt = "agefdkyspv";
	
	public static String md5(String src) {
		return DigestUtils.md5Hex(src);
	}
	
	
	public static String getToken(String pswd) {
		String str = ""+salt.charAt(0)+salt.charAt(2) +salt.charAt(9)+salt.charAt(4) +salt.charAt(7)+ pswd +salt.charAt(5)+salt.charAt(6) +salt.charAt(3)+ salt.charAt(8)+salt.charAt(1);
		return md5(str);
	}
	public static boolean verifyToken(String token) {
		if(getToken(token.substring(0, 8)).equals(token.substring(9))) {
			return true;
		}
		System.out.println("error token:"+token);
		return false;
	}
	
	public static void main(String[] args){
		String str = JOptionPane.showInputDialog(null, "输入日期", "提示", JOptionPane.INFORMATION_MESSAGE);
		String token = getToken(str);
		JOptionPane.showMessageDialog(null, token, "Token", JOptionPane.INFORMATION_MESSAGE);
	}
	
}
