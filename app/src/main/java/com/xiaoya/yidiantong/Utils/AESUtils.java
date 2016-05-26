package com.xiaoya.yidiantong.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AESUtils {

	private static int[] d = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	private static String[] l = { "a", "b", "c", "d", "e", "f", "g", "h", "i",
			"j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
			"w", "x", "y", "z" };
	private static String sKey = new StringBuilder().append(d[1]).append(l[16])
			.append(l[0]).append(l[25]).toString()
			+ new StringBuilder().append(l[23]).append(l[18]).append(l[22])
					.append(d[2]).toString()
			+ new StringBuilder().append(d[3]).append(l[4]).append(l[3])
					.append(l[2]).toString()
			+ new StringBuilder().append(l[21]).append(l[5]).append(l[16])
					.append(d[7]).toString();

	public static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

	/**
	 * Convert hex string to byte[]
	 * 
	 * @param hexString
	 *            the hex string
	 * @return byte[]
	 */
	public static byte[] hexStringToBytes(String hexString) {
		if (hexString == null || hexString.equals("")) {
			return null;
		}
		hexString = hexString.toUpperCase();
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] d = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
		}
		return d;
	}

	/**
	 * Convert char to byte
	 * 
	 * @param c
	 *            char
	 * @return byte
	 */
	private static byte charToByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}

	public static String decrypt(String paramString){
		try {
			if (sKey == null)
				return null;
			if (sKey.length() != 16)
				return null;
			SecretKeySpec localSecretKeySpec = new SecretKeySpec(
					sKey.getBytes("ASCII"), "AES");
			Cipher localCipher = Cipher.getInstance("AES");
			localCipher.init(2, localSecretKeySpec);
			byte[] byteArr = hexStringToBytes(paramString);
			try {
				String result = new String(localCipher.doFinal(byteArr));
				return result;
			} catch (Exception e) {
				System.out.println(e.toString());
				return null;
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return null;
	}

}
