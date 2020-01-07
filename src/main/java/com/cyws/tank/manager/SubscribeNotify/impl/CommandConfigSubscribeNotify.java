package com.cyws.tank.manager.SubscribeNotify.impl;

import com.cyws.tank.codec.common.TPMSConsts;
import com.cyws.tank.codec.vo.req.CommonMsg;
import com.cyws.tank.command.cache.CommandCache;
import com.cyws.tank.manager.SubscribeNotify.ISubscribeNotify;
import com.cyws.tank.manager.bll.CommandBll;
import com.cyws.tank.manager.po.Full_commandPo;
import com.cyws.tank.utils.common.DateUtils;


public class CommandConfigSubscribeNotify implements ISubscribeNotify{

	private CommandBll commandBll=new CommandBll();
	@Override
	public void HandleSubscribeNotify(Object deviceData) {
		CommonMsg commonMsg=(CommonMsg) deviceData;
		Full_commandPo full_command=new Full_commandPo();
		
		int commandResult = 0;//变更数据库是否成功字段
		if(commonMsg.getResult()==0){
			//操作配置成功的处理
			commandResult=1;			
		}else{
			//操作配置失败的处理
			commandResult=2;
		}
		if(commonMsg.getUp_msgId()==TPMSConsts.cmd_terminal_param_settings){
			String terminalPhone=commonMsg.getMsgHeader().getTerminalPhone();
			full_command.setFinishTime(DateUtils.getDoDayTime());
			full_command.setStatus(commandResult);
			full_command.setEquipmet_id(terminalPhone);
			full_command.setFlowid(commonMsg.getUp_flowId());
			int i=commandBll.updateCommand(full_command);
			if(i>0){
				String command=commandBll.selectcammandByIDAndFlowid(full_command);
				//CommandCache.getInstance().removeCommandCacheByPhone(terminalPhone, command);
			}
		}		
	}
}
