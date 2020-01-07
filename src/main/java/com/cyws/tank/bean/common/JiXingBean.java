package com.cyws.tank.bean.common;

public class JiXingBean {

	// 获取token
	public static final String url1="http://47.105.47.32:9526/oauth/token";
	public static final String userName="gzzj";
	public static final String userPass="0ca175b9-c0f7-26a8-31d8-95e269332461";
	
	public static final String grant_type_gain="password";
	public static final String scope="all";
	public static final String username="zjny2018";
	public static final String password="6619e14d0ccec";
	// 刷新token
	public static final String grant_type_refresh="refresh_token";
	
	// 推送数据
	public static final String push_url="http://47.105.47.32:9526/api/sensor/dingli/tank";
	
	
	public static  String token=null;
	public static  String refresh_token=null;
	
}
