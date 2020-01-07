package com.cyws.tank.manager.controller;

import java.util.List;
import java.util.Map;

import com.cyws.tank.command.cache.CommandCache;
import com.cyws.tank.manager.bll.CommandBll;


public class CommandCacheController {

	private CommandBll commandBll=new CommandBll();
	private CommandCache commandCache=CommandCache.getInstance();
	public void handlecomman2Cache(){
		List<Map<String, Object>> lst_map=commandBll.selectcammandBy3day();
		if(lst_map.isEmpty()){
			return;
		}
		for(Map<String, Object> map:lst_map){
			String id=map.get("id").toString().trim();
			String imeiNo=map.get("equipmet_id").toString().trim();
			String command_type="";
			if(map.get("command_type")!=null){
		    command_type=map.get("command_type").toString().trim();
			}
			String command_content="";
			if(map.get("command_content")!=null){
			command_content=map.get("command_content").toString().trim();
			}
			if(!"".equals(command_type)&&!"".equals(command_content)){
				String idAndtypeAndcontent=id+"="+command_type+"="+command_content;
				System.out.println(imeiNo+"=========="+idAndtypeAndcontent);
				commandCache.addCommandCache(imeiNo, idAndtypeAndcontent);	
			}
		}
	}		
}
