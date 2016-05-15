/*
 * 创建日期 2005-4-8
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
package com.speedy.base.util;

/**
 * @author Administrator
 * 
 *         更改所生成类型注释的模板为 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
public class Encryption {
	public static String decode(String s) {
		String s1 = "";
		DES des = new DES(getKey());
		byte abyte0[] = s.getBytes();
		for (int i = 0; i < abyte0.length / 16; i++) {
			byte abyte1[] = new byte[8];
			for (int j = 0; j <= 7; j++) {
				byte byte0 = (byte) (abyte0[16 * i + 2 * j] - 97);
				byte byte1 = (byte) (abyte0[16 * i + 2 * j + 1] - 97);
				abyte1[j] = (byte) (byte0 * 16 + byte1);
			}

			long l = des.bytes2long(abyte1);
			byte abyte2[] = new byte[8];
			des.long2bytes(des.decrypt(l), abyte2);
			s1 = s1 + new String(abyte2);
		}

		return s1.trim();
	}

	public static String encode(String s) {
		String s1 = "";
		DES des = new DES(getKey());
		byte byte0 = 32;
		byte abyte0[] = s.getBytes();
		int i = abyte0.length;
		int j = i + (8 - i % 8) % 8;
		byte abyte1[] = new byte[j];
		for (int k = 0; k < j; k++)
			if (k <= i - 1)
				abyte1[k] = abyte0[k];
			else
				abyte1[k] = byte0;

		for (int l = 0; l < j / 8; l++) {
			byte abyte2[] = new byte[8];
			for (int i1 = 0; i1 <= 7; i1++)
				abyte2[i1] = abyte1[8 * l + i1];

			long l1 = des.bytes2long(abyte2);
			byte abyte3[] = new byte[8];
			des.long2bytes(des.encrypt(l1), abyte3);
			byte abyte4[] = new byte[16];
			for (int j1 = 0; j1 < 8; j1++) {
				abyte4[2 * j1] = (byte) ((((char) abyte3[j1] & 0xf0) >> 4) + 97);
				abyte4[2 * j1 + 1] = (byte) (((char) abyte3[j1] & 0xf) + 97);
			}

			s1 = s1 + new String(abyte4);
		}
		return s1;
	}

	private static long getKey() {
		return 0x496324baL;
	}

	public static void main(String[] args) {
		String end = Encryption.encode(null);
		System.out.println(end.length() + end);
		String src = Encryption.decode(end);
		System.out.println(src.length() + src);
	}
}
