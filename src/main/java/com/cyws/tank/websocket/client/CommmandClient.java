package com.cyws.tank.websocket.client;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;



public class CommmandClient {
    public static Session session=null;
    private  boolean isRun=false;
    private ExecutorService CommmandExc= Executors.newSingleThreadExecutor();
    private String wsUrl="";
    // 这是启动websocket连接，重连等
    public void runCommmandConnect(String wsUrl){
    	CommmandExc.execute(new  Runnable() {
			public void run() {	
				createCommmandConnect(wsUrl);
			}
    	});
    }
    
    private void createCommmandConnect(String wsUrl){
    	System.out.println("====createCommmandConnect===="); 
    	boolean isOpen=true;
		try {		
		if(wsUrl!=null && "".equals(this.wsUrl)){
			this.wsUrl=wsUrl;
		}
		WebSocketContainer container = ContainerProvider.getWebSocketContainer();
		String uri=this.wsUrl;//"ws://localhost:8080/websocketservice/websocket";
        session = container.connectToServer(CommmandManager.class, new URI(uri)); 
        isRun=true;
        while (isOpen) {
    		try {
    			session.isOpen();
    			//System.out.println(session.isOpen()+"---------"+wsUrl);
				Thread.sleep(5000);
			} catch (Exception e) {
				isOpen=false;
				e.printStackTrace();
			}
		}
		} catch (Exception e) {
			isOpen=false;
			e.printStackTrace();
		}
    }
    
    public void pushData(String commandjson){
    	//System.out.println("----------"+commandjson);
		if(session!=null){
    		try {
    			System.out.println("============"+commandjson);
				session.getBasicRemote().sendText(commandjson);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{			
			if(isRun){
				System.out.println("-------重连websocket--------");
				try {
					Thread.sleep(60000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				isRun=false;
			    runCommmandConnect(null);
			}			
		}		
    }
    
    
}
