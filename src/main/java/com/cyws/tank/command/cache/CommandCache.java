package com.cyws.tank.command.cache;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;



public class CommandCache {
	private CommandCache(){
    }
    private static CommandCache manager = new CommandCache();
    
    public static CommandCache getInstance(){
        return manager;
    }
        
    private final  Map<String, Set<String>> commandMap = new  ConcurrentHashMap<String, Set<String>>();
    
    public boolean addCommandCache(String terminalPhone,String idAndtypeAndcontent){
    	boolean is_add=false;
    	Set<String> set_cammandBean=commandMap.get(terminalPhone);
		 if(set_cammandBean!=null){
			 if(!set_cammandBean.contains(idAndtypeAndcontent)){
				 System.out.println("刷进缓存命令："+idAndtypeAndcontent);
				 set_cammandBean.add(idAndtypeAndcontent);	
				 is_add=true;
			 }
		 }else{
			 set_cammandBean=new LinkedHashSet<String>();
			 set_cammandBean.add(idAndtypeAndcontent);
			 commandMap.put(terminalPhone, set_cammandBean);
			 is_add=true;
		 }
		 return is_add;
   }
    
    public String getCommandCache(String terminalPhone){
    	String value="";
    	if(terminalPhone!=null&&!"".equals(terminalPhone)){
	    	Set<String> lst_command=commandMap.get(terminalPhone);
	    	if(lst_command!=null&&lst_command.size()>0){
	    		 value= lst_command.iterator().next();
	    		 lst_command.remove(value);
	    	}
    	}
    	return value;
    }
    
    public  void  removeCommandCacheByPhone(String terminalPhone,String command){
    	Set<String> lst_command=commandMap.get(terminalPhone);
    	if(lst_command!=null&&lst_command.size()>0){
    		lst_command.remove(command);
    	}
    }
    
}
