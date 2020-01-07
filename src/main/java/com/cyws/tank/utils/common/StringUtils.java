package com.cyws.tank.utils.common;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public class StringUtils {
	private static byte[] lock = new byte[0];
	 
	// 位数，默认是8位
	private final static long w = 100000000;
 
	public static String createID() {
		long r = 0;
		synchronized (lock) {
			r = (long) ((Math.random() + 1) * w);
		}
 
		return System.currentTimeMillis() + String.valueOf(r).substring(1);
	}
	
	/* 处理httpServer接口参数转换 */
	public static Map<String,String> formData2Dic(String formData ) {
        Map<String,String> result = new HashMap<>();
        if(formData== null || formData.trim().length() == 0) {
            return result;
        }
        final String[] items = formData.split("&");
        Arrays.stream(items).forEach(item ->{
            final String[] keyAndVal = item.split("=");
            if( keyAndVal.length == 2) {
                try{
                    final String key = URLDecoder.decode( keyAndVal[0],"utf8");
                    final String val = URLDecoder.decode( keyAndVal[1],"utf8");
                    result.put(key,val);
                }catch (UnsupportedEncodingException e) {}
            }
        });
        return result;
    }
	
	public static boolean strIsNullOrEmpty(String s) {
        return (null == s || s.trim().length() < 1);
    }
		
	public final static boolean isJSONValid2(String jsonInString ) {
        try {
        	 JSONObject jsonStr= JSONObject.parseObject(jsonInString);
            return true;
        } catch (Exception  e) {
            return false;
        }
    }
}
