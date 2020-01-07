package com.cyws.tank.command.handle;

import org.xsocket.connection.INonBlockingConnection;

import com.cyws.tank.codec.service.TerminalMsgProcessService;
import com.cyws.tank.codec.vo.PackageData;
import com.cyws.tank.command.cache.CommandCache;
import com.cyws.tank.command.common.ParameterConfigurationBean;
import com.cyws.tank.command.tool.AssemblyCommandTool;
import com.cyws.tank.command.tool.EncodeCommand;
import com.cyws.tank.manager.bll.CommandBll;
import com.cyws.tank.manager.po.Full_commandPo;
import com.cyws.tank.utils.common.Utilty;
import com.cyws.tank.utils.log.LogManager;


public class DeviceCommand {
	private TerminalMsgProcessService msgProcessService=new TerminalMsgProcessService();
	public void sendCacheCommand(PackageData packageData,INonBlockingConnection nbc){
		Full_commandPo full_command=new Full_commandPo();
		ParameterConfigurationBean pc_po=null;
		String terminalPhone=packageData.getMsgHeader().getTerminalPhone();
		String idAndtypeAndcontent=CommandCache.getInstance().getCommandCache(terminalPhone);
		if(!"".equals(idAndtypeAndcontent.trim())){
			String[] strs=idAndtypeAndcontent.split("=");
			if(1<strs.length && strs.length<=3){
				String id=strs[0];
				String command_type=strs[1];
				String command_content=strs[2];
				if(!"".equals(command_type)){//这是防止非法命令，内容为空是查询命令 PlatformConversionCommand
					String[] l_command_type=command_type.split(",");
					String[] l_command_content=command_content.split(",");
					AssemblyCommandTool assemblyCommandTool=new AssemblyCommandTool();
					pc_po=assemblyCommandTool.HandleAssemblyCommand(l_command_content, l_command_type);
				}
				full_command.setId(id);
			}
		}
		
		if(pc_po!=null){
			Integer flowId=pushDeviceCommand(packageData, nbc, pc_po);
			if(flowId!=null){//处理业务
				full_command.setFlowid(flowId);
				CommandBll commandBll=new CommandBll();
				commandBll.updateCommandtoframSeq(full_command);
			}
		}
	}
	
	private Integer pushDeviceCommand(PackageData packageData,INonBlockingConnection nbc,ParameterConfigurationBean pc_po){
		Integer flowId=null;
		try { 
			 Utilty util=new  Utilty();
			 //获取组装命令报文
			 EncodeCommand encodeCommand=new EncodeCommand();
			 String commandBody=encodeCommand.HandleEncodeCommand(pc_po);			
			 if("00".equals(commandBody)){//过滤掉无效的命令下发
				return null; 
			 }
			 System.out.println("===commandBody:"+commandBody);
			 byte[] body_bt= util.hexStrToByteArray(commandBody);
			 byte[] msgHeaderAndBody= this.msgProcessService.processParamSettingMsg(packageData, body_bt);			
			 nbc.write(msgHeaderAndBody);
			 flowId= getFlowId(packageData.getMsgHeader().getFlowId());
			 // 记录命令报文
			 LogManager.getInstance().PrintSentDeviceLog(packageData.getMsgHeader().getTerminalPhone(),msgHeaderAndBody);//记录应答报文
		} catch (Exception e) {
			e.printStackTrace();
		}		 
		return flowId;
	}
	
	//获取流水号
    private  int getFlowId(int currentFlowId){
    	if (currentFlowId >= 0xffff)
			currentFlowId = 0;
		return ++currentFlowId;
    } 
	
}
