package com.cyws.tank.push.user;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.xsocket.connection.INonBlockingConnection;

import com.cyws.tank.bean.common.PushUserMapBean;
import com.cyws.tank.push.data.PushUserBean;
import com.cyws.tank.socket.common.UserDeviceBean;

public class PushUserManager {
	private PushUserManager(){
    }
    private static PushUserManager manager = new PushUserManager();
    
    public static PushUserManager getInstance(){
        return manager;
    }
    
    private final Map<String, String> socketTagMap=new ConcurrentHashMap<String, String>();
    private final ConcurrentMap<String, PushUserBean> deviceMap = new ConcurrentHashMap<>();
    
    public int addPushUser(String user, String password,INonBlockingConnection nbc){
    	String xsockeId =nbc.getId(); 
    	if(!socketTagMap.containsKey(xsockeId)){//判断标记map里是否存在对应的xsockeId
    		// 调用知否有推送数据客户登记接口
    		String userId=HandlePushUserRegister(user,password);
    		if(userId!=null){
	    		socketTagMap.put(xsockeId, userId);
	    		if(deviceMap.containsKey(userId)){//处理已登记的客户
	    			PushUserBean oldPushUserBean= deviceMap.get(userId);
	    			oldPushUserBean.setConnect(true);
	    			oldPushUserBean.setXsockeId(xsockeId);
	    			oldPushUserBean.setXsocke(nbc);
	    			oldPushUserBean.setUserName(user);
	    		}else{
	    			PushUserBean pushUserBean= new PushUserBean();
	    			pushUserBean.setConnect(true);
	    			pushUserBean.setXsockeId(xsockeId);
	    			pushUserBean.setXsocke(nbc);
	    			pushUserBean.setUserName(user);
	    			deviceMap.put(userId, pushUserBean);    			
	    		}
	    		return 0;
    		}else{
    			return 1;
    		}    		
    	}
    	return 2;
    }
    
    private String HandlePushUserRegister(String user, String password){
    	String key=user+"_"+password;
    	String userId=PushUserMapBean.getConstantMap().get(key)==null?null:PushUserMapBean.getConstantMap().get(key).toString();
    	return userId;
    }
    
    public PushUserBean getPushUserBean(String userId){
    	PushUserBean oldPushUserBean= deviceMap.get(userId);
    	return oldPushUserBean;
    }
    
    // 断开连接操作
    public boolean removePushUser(String xsockeId) {
		String userId=socketTagMap.remove(xsockeId);
		if(userId!=null){
			PushUserBean pushUserBean= deviceMap.get(userId);
			if(pushUserBean!=null){
				pushUserBean.setConnect(false);
				return true;
			}
		}
	 return false;
    }
    
    // 统计在线人数
    public int getOnlineCount() {
        return socketTagMap.size();
    }
        
}
