package com.cyws.tank.command.handle;

import java.io.IOException;

import com.cyws.tank.utils.common.StringUtils;
import com.cyws.tank.utils.log.LogManager;
import com.cyws.tank.utils.po.HttpCommandPo;
import com.cyws.tank.utils.po.HttpResponsePo;
import com.cyws.tank.utils.serializer.FastJsonSerializer;
import com.cyws.tank.web.controller.HttpServerCommandController;
import com.cyws.tank.websocket.client.CommmandClient;

public class HandleCloudCommand {
	private FastJsonSerializer  fastJsonSerializer=new FastJsonSerializer();
	private HttpResponsePo httpResponsePo=new HttpResponsePo();
	public String DecodeCloudCommand(String str){
		LogManager.getInstance().PrintGetHttpCommandLog(str);// 打印数据信息
		if(str!=null && !"".equals(str) && StringUtils.isJSONValid2(str)){			
	        HttpCommandPo httpCommandPo= fastJsonSerializer.toObject(str, HttpCommandPo.class);
	        if(httpCommandPo!=null){
	        	System.out.println("========HandleCloudCommand处理=========");
	            String equipmet_id=httpCommandPo.getEquipmet_id();
	            String command_type=httpCommandPo.getCommand_type();
	            String command_content=httpCommandPo.getCommand_content();
	            System.out.println(equipmet_id.trim()+"   "+ command_type.trim()+"    "+ command_content.trim());
	            if(!StringUtils.strIsNullOrEmpty(equipmet_id)&& !StringUtils.strIsNullOrEmpty(command_type)&& !StringUtils.strIsNullOrEmpty(command_content)){        	
	            	httpResponsePo.setResult(0);
	            	httpResponsePo.setMessage("succeed");
	            	HttpServerCommandController httpServerCommandController=new HttpServerCommandController();
	            	httpServerCommandController.HandleSendCommandToDevice(equipmet_id.trim(), command_type.trim(), command_content.trim());
	            	if(CommmandClient.session!=null){
	            		 try {
							CommmandClient.session.getBasicRemote().sendText("1");
						} catch (IOException e) {
							e.printStackTrace();
						}
	            	 }
	            }else{
	            	httpResponsePo.setResult(2);
	            	httpResponsePo.setMessage("Incorrect parameter contents");	            	
	            }
	        } else {
	        	httpResponsePo.setResult(1);
            	httpResponsePo.setMessage("Wrong way to get an interface");
	        }
        }else{
        	httpResponsePo.setResult(1);
        	httpResponsePo.setMessage("Wrong way to get an interface");
        }
        String str_eventdatajson=fastJsonSerializer.toJSON(httpResponsePo);
		return str_eventdatajson;
	}
}
