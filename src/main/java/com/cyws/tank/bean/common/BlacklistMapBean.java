package com.cyws.tank.bean.common;

import java.util.ArrayList;
import java.util.List;

public class BlacklistMapBean {
	private static List<String> l=new ArrayList<String>();
	static {
		l.add("139.196.92.92");
		l.add("61.144.248.73");
	}
	public static boolean  getExist(String ip){
		return l.contains(ip);
	}

}
