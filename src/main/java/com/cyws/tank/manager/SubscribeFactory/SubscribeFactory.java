package com.cyws.tank.manager.SubscribeFactory;

import com.cyws.tank.manager.SubscribeNotify.ISubscribeNotify;
import com.cyws.tank.manager.SubscribeNotify.impl.CommandConfigSubscribeNotify;
import com.cyws.tank.manager.SubscribeNotify.impl.LocationInfoSubscribeNotify;
import com.cyws.tank.manager.SubscribeNotify.impl.TransmissionInfoSubscribeNotify;


public class SubscribeFactory {

	public  void pushDataToPlatform(int MsgId, Object objectNode){
		ISubscribeNotify subscribeNotifyResource;
		switch (MsgId) {
		case 0X0001://处理设备注册方法
			subscribeNotifyResource=new CommandConfigSubscribeNotify();
			subscribeNotifyResource.HandleSubscribeNotify(objectNode);
			break;
		case 0X0200://处理设备位置信息插入方法
			subscribeNotifyResource=new LocationInfoSubscribeNotify();
			subscribeNotifyResource.HandleSubscribeNotify(objectNode);
			break;
		case 0X0900://处理设备传感器插入方法
			subscribeNotifyResource=new TransmissionInfoSubscribeNotify();
			subscribeNotifyResource.HandleSubscribeNotify(objectNode);
			break;
		default:
			break;
		}
	}
}
