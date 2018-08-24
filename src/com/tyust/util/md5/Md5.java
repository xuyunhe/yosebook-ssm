package com.tyust.util.md5;

import java.security.MessageDigest;

import org.apache.commons.codec.binary.Base64;


public class Md5 {

	/*
	 * 加盐版的md5
	 * 摘要中+了盐：防止抓取摘要后反得出密码（原数据）
	 */
	public static final String salt="com.tyust.yosebook";
	public static String saltMd5(String str){
		try {
			byte[] data=str.getBytes("utf-8");
			MessageDigest md=MessageDigest.getInstance("md5");
			md.update(data);
			md.update(salt.getBytes("utf-8"));
			byte[] md5=md.digest();
			String code=Base64.encodeBase64String(md5);
			return code;
		} catch (Exception e) {		
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/*
	 * 不加盐版的md5
	 */
	public static String md5(String str) {
		try {
			byte[] data=str.getBytes("utf-8");
			MessageDigest md=MessageDigest.getInstance("md5");
			md.update(data);
			byte[] md5=md.digest();
			String code=Base64.encodeBase64String(md5);
			return code;
		} catch (Exception e) {		
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
