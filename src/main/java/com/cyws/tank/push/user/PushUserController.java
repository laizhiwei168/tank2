package com.cyws.tank.push.user;

import java.io.IOException;
import java.nio.BufferOverflowException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.xsocket.connection.INonBlockingConnection;

import com.cyws.tank.push.common.PushClientConsts;
import com.cyws.tank.push.data.PushData;
import com.cyws.tank.push.data.RegisterPushData;
import com.cyws.tank.push.data.ResultPushData;
import com.cyws.tank.utils.log.LogManager;
import com.cyws.tank.utils.serializer.FastJsonSerializer;

public class PushUserController {

	private PushUserController(){
    }
    private static PushUserController manager = new PushUserController();
    
    public static PushUserController getInstance(){
        return manager;
    }
    
    
    ExecutorService device_xsocket_exc= Executors.newSingleThreadExecutor();
	public void handleDeviceController(String user, String password,INonBlockingConnection nbc){
		device_xsocket_exc.execute(new  Runnable() {
			public void run() {
				if(user!=null && password!=null){
					int results=PushUserManager.getInstance().addPushUser(user,password, nbc);
			    	// 过滤掉位置报文后，执行命令下发
					String content=results==0?"Authentication success":results==1?"User authentication failed":"Repeat certification";
					ResultPushData resultPushData=new ResultPushData();
					resultPushData.setResult(results+"");
					resultPushData.setContent(content);
					PushData pushData=new PushData();
					pushData.setType(PushClientConsts.msg_id_Push_register);
					pushData.setData(resultPushData);
				    FastJsonSerializer  fastJsonSerializer=new FastJsonSerializer();
				    String json= fastJsonSerializer.toJSON(pushData);
				    try {
						nbc.write(json);
						nbc.flush();
						// 打印日志
						LogManager.getInstance().PrintSentPushUserLog(user,json);//打印log
					} catch (BufferOverflowException | IOException e) {
						
						e.printStackTrace();
					}
				    
				}				  	
			}
		});
	} 
}
