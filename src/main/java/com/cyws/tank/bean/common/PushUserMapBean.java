package com.cyws.tank.bean.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.cyws.tank.utils.common.PropertiesUtil;

public class PushUserMapBean {
	private  static Map<String, Object> pushUserMap=new HashMap<String, Object>();
	static {
		Properties props= PropertiesUtil.GetProperties("/resources/pushUser.properties");
		for(Object key : props.keySet()){
			pushUserMap.put(key.toString(), props.get(key));
		}
	}
	
	public static Map getConstantMap(){
		return pushUserMap;
	}
}
