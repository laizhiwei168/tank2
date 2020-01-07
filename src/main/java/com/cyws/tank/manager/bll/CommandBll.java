package com.cyws.tank.manager.bll;

import java.util.List;
import java.util.Map;

import com.cyws.tank.manager.dal.CommandDal;
import com.cyws.tank.manager.po.CommandPo;
import com.cyws.tank.manager.po.Full_commandPo;

public class CommandBll {
	private CommandDal commandDal=new CommandDal();
	// 获取3天的命令
	public List<Map<String, Object>> selectcammandBy3day(){		 
		return commandDal.selectcammandBy3day();
	}
	// 把命令参数的流水号设置到命令里
	public int updateCommandtoframSeq(Full_commandPo full_command){
		return commandDal.updateCommandtoframSeq(full_command);
	}
	// 处理命令下发成功
	public int updateCommand(Full_commandPo full_command){
		return commandDal.updateCommand(full_command);
	}
	
	// 新增一条命令数据
	public int insertCommand(CommandPo command){
		return commandDal.insertCommand(command);
	}
	
	public String selectcammandByIDAndFlowid(Full_commandPo full_command){
		String idAndtypeAndcontent="";
		List<Map<String, Object>> ls_gx_cammand =commandDal.selectcammandByIDAndFlowid(full_command);
		if(!ls_gx_cammand.isEmpty()){
			Map<String, Object> map=ls_gx_cammand.get(0);
			String id=map.get("id").toString().trim();
			String imeiNo=map.get("equipmet_id").toString().trim();
			String command_type=map.get("command_type").toString().trim();
			String command_content=map.get("command_content").toString().trim();
		    idAndtypeAndcontent=id+"="+command_type+"="+command_content;
		}
		return idAndtypeAndcontent;
	}
}
