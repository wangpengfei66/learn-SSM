package com.kaishengit.util;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class AESUtils {

	//使用同一key加密解密
	//根据key值进行加密
	//根据key值进行解密
	
	private static Key key = null;
	static{
		//使用key生成器生成一个固定的key
		KeyGenerator keyGenerator;
		try {
			keyGenerator = KeyGenerator.getInstance("AES");
			keyGenerator.init(128, new SecureRandom(Config.get("key.source").getBytes()));
			SecretKey secretKey = keyGenerator.generateKey();
			byte[] keyByte = secretKey.getEncoded();
			key = new SecretKeySpec(keyByte, "AES");
		
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * AES加密函数
	 * @param str
	 * @return
	 */
	public static String encode(String str) {
		String res = null;
		try {
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte [] result = cipher.doFinal(str.getBytes());
			res = Base64.encodeBase64String(result);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return res;
	}
	/**
	 * AES解密函数
	 * @param str
	 * @return
	 */
	public static String decode(String str){
		String decode = null;
		try {
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte [] result = cipher.doFinal(Base64.decodeBase64(str));
			decode = new String(result);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return decode;
	}
	/*public static void main(String[] args) {
		String res = AESUtils.encode("123");
		System.out.println("res :" + res);
		String encode = AESUtils.decode(res);
		System.out.println("encode:" + encode);
				
	}*/
	
}
