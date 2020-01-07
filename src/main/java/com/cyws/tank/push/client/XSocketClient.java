package com.cyws.tank.push.client;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.xsocket.connection.INonBlockingConnection;
import org.xsocket.connection.NonBlockingConnection;


public class XSocketClient {

	private static String IP ; 
	private static int PORT ; 
	private INonBlockingConnection nbc;
	public XSocketClient(String ip,int port){
		this.IP=ip;
		this.PORT=port;
	}
	
	protected void doConnect() {
		
		try {
			nbc = new NonBlockingConnection(IP, PORT, new XSocketClientHandler());
			//设置编码格式  
	        nbc.setEncoding("UTF-8");  
	        //设置是否自动清空缓存  
	        nbc.setAutoflush(true); 
	        ExecutorService ext= Executors.newSingleThreadExecutor();
	        try {
	        	ext.execute(new Runnable() {
				@Override
				public void run() {
					while (true) {	        	
			        	if(nbc.isOpen()){
			        	}else{
			        		doConnect();
			        		return;
			        	}
			        	try {
							Thread.currentThread().sleep(2000);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
					}
				}
	        	});
	        } catch (Exception e) {
		    }finally{		    	
		    	ext.shutdown();//结束线程 
		    }
	        
		} catch (IOException e) {
			e.printStackTrace();
			try {
				Thread.currentThread().sleep(2000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			doConnect();
		} 
		
	}
	
	public void sendData(String bresponse ) throws Exception {
		//nbc.write(bresponse+"\r\n"); 添加换行符操作
		nbc.write(bresponse);
	}
	
	public static void main(String[] args) {
		XSocketClient x=new XSocketClient("10.43.10.46", 8091);		
		x.doConnect();
		for(int i=0;i<10;i++){
			try {
				x.sendData("123123123");
				Thread.currentThread().sleep(4000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
}
