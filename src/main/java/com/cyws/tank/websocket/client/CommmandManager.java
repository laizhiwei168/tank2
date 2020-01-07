package com.cyws.tank.websocket.client;


import javax.websocket.ClientEndpoint;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

import com.cyws.tank.command.handle.HandleCloudCommand;

@ClientEndpoint
public class CommmandManager {

	@OnOpen
    public void onOpen(Session session) {
        System.out.println("CommmandManager  Connected to endpoint: " + session.getBasicRemote());
    }

    @OnMessage
    public void onMessage(String message) {
    	 //System.out.println("commandManager接收到来自服务器发送的定位信息的数据："+message);        	
        //处理平台下发命令jsion数据
    	 if(!"00000".equals(message)&&!"1111".equals(message)){
    		 HandleCloudCommand handleCloudCommand=new HandleCloudCommand();
    	     handleCloudCommand.DecodeCloudCommand(message);
    	 }        
    }
    
    @OnClose
    public void OnClose(Session session) {
    	System.out.println("断开command连接");
    	CommmandClient.session=null;    	
    }

    @OnError
    public void onError(Throwable t) {
    	System.out.println("错误啦！！！！！！！！！！！！！！！！！");
        t.printStackTrace();
    }
}
