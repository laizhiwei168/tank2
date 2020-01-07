package com.cyws.tank.websocket.heartbeat;


import com.cyws.tank.websocket.client.CommmandClient;

public class CommmandHeartbeat implements Runnable{
	private long checkDelay = 30;
    private long keepAliveDelay = 30000;//30000
    private long SendTime=0;
    private CommmandClient commmandClient=null;
	public CommmandHeartbeat(CommmandClient commmandClient){
		this.commmandClient=commmandClient;
	}
    
    public void run() {
        while(true){
            if(System.currentTimeMillis()-SendTime>keepAliveDelay){
            	SendTime=System.currentTimeMillis();
                String commandjson="00000";//new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                commmandClient.pushData(commandjson);
            }else{
                try {
                    Thread.sleep(checkDelay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }  
            }  
        }  
    }
}
