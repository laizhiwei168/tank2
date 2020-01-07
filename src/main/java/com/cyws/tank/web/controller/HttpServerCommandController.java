package com.cyws.tank.web.controller;

import org.xsocket.connection.INonBlockingConnection;

import com.cyws.tank.codec.vo.PackageData;
import com.cyws.tank.command.cache.CommandCache;
import com.cyws.tank.command.handle.DeviceCommand;
import com.cyws.tank.manager.bll.CommandBll;
import com.cyws.tank.manager.po.CommandPo;
import com.cyws.tank.socket.common.UserDeviceBean;
import com.cyws.tank.socket.user.DeviceUserManager;
import com.cyws.tank.utils.common.DateUtils;
import com.cyws.tank.utils.common.UUIDUtil;

public class HttpServerCommandController {
	
	public void HandleSendCommandToDevice(String equipmet_id,String command_type,String command_content){
		if(!"".equals(equipmet_id) && !"".equals(command_type) && !"".equals(command_content)){
			String id= UUIDUtil.getUUID();
			boolean isadd=setCommandCache(id, equipmet_id, command_type, command_content);
			if(isadd){
				AddCommandData(id, equipmet_id, command_type, command_content);				
			}
			pushCommandToDevice(equipmet_id);
		}
	}
	
	// 向设备推送命令
	private  void pushCommandToDevice(String equipmet_id){
		UserDeviceBean userDeviceBean=DeviceUserManager.getInstance().getUserDeviceBean(equipmet_id);
		if(userDeviceBean!=null){
			if(userDeviceBean.isConnect()){
				INonBlockingConnection xsocke=userDeviceBean.getXsocke();
				PackageData packageData=userDeviceBean.getPackageData();
				DeviceCommand deviceCommand=new DeviceCommand();
				deviceCommand.sendCacheCommand(packageData, xsocke);
			}
		}
	}
	
	// 缓存设备命令
	private  boolean setCommandCache(String id,String equipmet_id,String command_type,String command_content){
		String idAndtypeAndcontent=id+"="+command_type+"="+command_content;
		System.out.println(equipmet_id+"=========="+idAndtypeAndcontent);
		boolean is_add=CommandCache.getInstance().addCommandCache(equipmet_id, idAndtypeAndcontent);	
		return is_add;
	}
	
	// 存储设备命令到数据库里
	private void AddCommandData(String id,String equipmet_id,String command_type,String command_content){
		CommandBll commandBll=new CommandBll();
		CommandPo command=new CommandPo();
		command.setId(id);
		command.setEquipmet_id(equipmet_id);
		command.setCommand_type(command_type);
		command.setCommand_content(command_content);
		command.setAddTime(DateUtils.getDoDayTime());
		commandBll.insertCommand(command);
	}
}
