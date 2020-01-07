package com.cyws.tank.command.tool;

import com.cyws.tank.command.common.ParameterConfigurationBean;


public class AssemblyCommandTool {
	public ParameterConfigurationBean  HandleAssemblyCommand(String[] l_command_content,String[] l_command_type){
		ParameterConfigurationBean pc_po =new ParameterConfigurationBean();
		int commandNumber=l_command_type.length;
		int commandContent=l_command_content.length;
		if(commandNumber>0){
		  for(int i=0;i<commandNumber;i++){
			  String type=l_command_type[i];
			  String content="";
			  if(commandContent>0){//处理查询命令
				  content=l_command_content[i];
			  }			  
			  AssemblyCommand(content, type, pc_po);
		  }
		}
		
		return pc_po;
	}
	
	private void AssemblyCommand(String command_content,String command_type,ParameterConfigurationBean pc_po){
		switch (command_type) {
		case "00000013":
			//ip1
			pc_po.setIP1(command_content);
			break;
		case "00000018":
			//端口
			pc_po.setPortNumber1(command_content);
			break;
		case "0000001A":
			//
			pc_po.setRemoteUpgradeIP(command_content);
			break;
		case "0000001B":
			pc_po.setRemoteUpgradePortNumber(command_content);
			break;
		case "00000023":
			//
			pc_po.setMaxPressure(command_content);
			break;
		case "00000024":
			//位置信息上报时间间隔
			pc_po.setMaxLiquidLeve(command_content);
			break;
		case "00000025":
			//附加数据上报时间间隔
			pc_po.setFullLoad(command_content);
			break;
		case "00000026":
			//复位终端
			pc_po.setTankType(command_content);
			break;
		case "00000027":
			//设置定位模式
			pc_po.setSleepTime(command_content);
			break;
		case "0000002A":
			pc_po.setPressureRange(command_content);
			break;
		case "0000002B":
			//处理冷箱特殊的修改频率
			pc_po.setLiquidLeveRange(command_content);
			break;
		case "00000031":
			//处理碰撞时间窗口
			pc_po.setReferenceVoltage(command_content);
			break;
		case "00000032":
			//处理碰撞震动阀值
			pc_po.setReferenceG(command_content);
			break;
		case "00000033":
			//处理碰撞稳定阀值
			pc_po.setStandardVoltage(command_content);
			break;
		case "00000034":
			//处理碰撞碰撞阀值
			pc_po.setTemperatureA(command_content);
			break;
		case "00000035":
			//处理碰撞
			pc_po.setTemperatureB(command_content);
			break;
		case "00000036":
			pc_po.setTankDiameter(command_content);
			break;
		case "00000037":
			pc_po.setTankLength(command_content);
			break;
		case "00000038":
			pc_po.setTankMedium(command_content);
			break;		
		case "00000039":
			pc_po.setRemoteUpgradeSleepTime(command_content);
			break;
		case "0000003A":
			pc_po.setRestart(command_content);
			break;
		case "0000003B":
			pc_po.setWorkingTime(command_content);
			break;
			
			//===========黄工===========
			
		case "000000C1":
			pc_po.setBlockSet(command_content);
			break;
		case "000000C2":
			pc_po.setTimeStart(command_content);
			break;
		case "000000C3":
			pc_po.setTimeEnd(command_content);
			break;
		case "000000C4":
			pc_po.setMinutes(command_content);
			break;
			
		}	
		
	}
}
