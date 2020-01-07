package com.cyws.tank.push.client;


public class XSocketClientManager {
	private static  XSocketClientManager clientManager=new XSocketClientManager( );
	private XSocketClientManager(){		
	}
	public static XSocketClientManager getClientManager(){
		return clientManager;
	}
	
	private String ip;
	private Integer port;
	private XSocketClient client;
	public void connection(String ip,Integer port){
		this.ip=ip;
		this.port=port;
		XSocketClient client = new XSocketClient(ip,port);
		client.doConnect();
		this.client=client;
	}
	
	public void pushData(String str){
		try {
			if(client!=null){
			this.client.sendData(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
