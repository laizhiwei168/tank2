package com.cyws.tank.push.service;

import org.xsocket.connection.INonBlockingConnection;

import com.cyws.tank.push.common.PushClientConsts;
import com.cyws.tank.push.data.PushData;
import com.cyws.tank.push.data.RegisterPushData;
import com.cyws.tank.push.user.PushUserController;
import com.cyws.tank.utils.log.LogManager;
import com.cyws.tank.utils.serializer.FastJsonSerializer;

public class PushProtocolServiceImpl {

	private FastJsonSerializer  fastJsonSerializer=new FastJsonSerializer();
	public Object dealWithData(final String json,INonBlockingConnection nbc) {
		
	    System.out.println(json.indexOf("{"));
		if(json.indexOf("{")!=0){
			LogManager.getInstance().PrintSentPushUserLog("pushUser_error",json);//打印log
			System.out.println("数据格式有问题！！！");
			return null;
		}
		
		LogManager.getInstance().PrintSentPushUserLog("pushUser",json);//打印log
		
		String resp_json=null;		
		try {
			PushData pushData= fastJsonSerializer.toObject(json, PushData.class);
			if(pushData!=null&& pushData.getType()!=null && !"".equals(pushData.getType())){
				//System.out.println(pushData.getType()+"   "+pushData.getData());
				switch (pushData.getType()) {
				case PushClientConsts.cmd__id_Push_heart_beat:
					
					break;
				case PushClientConsts.cmd__id_Push_register:						
					RegisterPushData registerPushData= fastJsonSerializer.toObject(pushData.getData().toString(), RegisterPushData.class);
					PushUserController.getInstance().handleDeviceController(registerPushData.getUser(), registerPushData.getPassword(), nbc);
					break;
				case PushClientConsts.cmd__id_Push_data:
					
					break;
				case PushClientConsts.cmd__id_Push_event:
					
					break;

				default:
					break;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return resp_json;
		
	}
	
}
