package com.cyws.tank.bean.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.cyws.tank.utils.common.PropertiesUtil;

public class ConstantBean {
	private  static Map<String, Object> ConstantMap=new HashMap<String, Object>();
	static {
		Properties props= PropertiesUtil.GetProperties("/resources/constant.properties");
		for(Object key : props.keySet()){
			ConstantMap.put(key.toString(), props.get(key));
		}
	}
	
	public static Map getConstantMap(){
		return ConstantMap;
	}
}
