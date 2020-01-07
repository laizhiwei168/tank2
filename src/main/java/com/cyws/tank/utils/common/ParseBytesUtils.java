package com.cyws.tank.utils.common;

import com.cyws.tank.codec.common.TPMSConsts;

public class ParseBytesUtils {
	private static BitOperator bitOperator =new BitOperator();
	private static BCD8421Operater bcd8421Operater=new BCD8421Operater();
	
	public static Double parseDoubleFromBytes(byte[] data, int startIndex, int length) {
		return parseDoubleFromBytes(data, startIndex, length, 0.0);
	}

	private static Double parseDoubleFromBytes(byte[] data, int startIndex, int length, Double defaultVal) {
		try {
			// 字节数大于4,从起始索引开始向后处理4个字节,其余超出部分丢弃
			final int len = length > 4 ? 4 : length;
			byte[] tmp = new byte[len];
			System.arraycopy(data, startIndex, tmp, 0, len);
			return bitOperator.byte2Double(tmp);
		} catch (Exception e) {
			//log.error("解析浮点数出错:{}", e.getMessage());
			e.printStackTrace();
			return defaultVal;
		}
	}
	
	public static int parseIntFromBytes(byte[] data, int startIndex, int length) {
		return parseIntFromBytes(data, startIndex, length, 0);
	}

	private static int parseIntFromBytes(byte[] data, int startIndex, int length, int defaultVal) {
		try {
			// 字节数大于4,从起始索引开始向后处理4个字节,其余超出部分丢弃
			final int len = length > 4 ? 4 : length;
			byte[] tmp = new byte[len];
			System.arraycopy(data, startIndex, tmp, 0, len);
			return bitOperator.byteToInteger(tmp);
		} catch (Exception e) {
			//log.error("解析整数出错:{}", e.getMessage());
			e.printStackTrace();
			return defaultVal;
		}
	}
	
	public static String parseBcdStringFromBytes(byte[] data, int startIndex, int lenth) {
		return parseBcdStringFromBytes(data, startIndex, lenth, null);
	}

	private static String parseBcdStringFromBytes(byte[] data, int startIndex, int lenth, String defaultVal) {
		try {
			byte[] tmp = new byte[lenth];
			System.arraycopy(data, startIndex, tmp, 0, lenth);
			return bcd8421Operater.bcd2String(tmp);
		} catch (Exception e) {
			//log.error("解析BCD(8421码)出错:{}", e.getMessage());
			e.printStackTrace();
			return defaultVal;
		}
	}
	
	public static String parseStringFromBytes(byte[] data, int startIndex, int lenth) {
		return parseStringFromBytes(data, startIndex, lenth, null);
	}
	

	private static String parseStringFromBytes(byte[] data, int startIndex, int lenth, String defaultVal) {
		try {
			byte[] tmp = new byte[lenth];
			System.arraycopy(data, startIndex, tmp, 0, lenth);
			return new String(tmp, TPMSConsts.string_charset);
		} catch (Exception e) {			
			e.printStackTrace();
			return defaultVal;
		}
	}
}
