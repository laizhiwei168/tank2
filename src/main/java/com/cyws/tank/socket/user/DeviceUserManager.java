package com.cyws.tank.socket.user;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.xsocket.connection.INonBlockingConnection;

import com.cyws.tank.codec.vo.PackageData;
import com.cyws.tank.socket.common.UserDeviceBean;



public class DeviceUserManager {
	private DeviceUserManager(){
    }
    private static DeviceUserManager manager = new DeviceUserManager();
    
    public static DeviceUserManager getInstance(){
        return manager;
    }
    
    private final Map<String, String> socketTagMap=new ConcurrentHashMap<String, String>();
    private final ConcurrentMap<String, UserDeviceBean> deviceMap = new ConcurrentHashMap<>();
    
    
    public int addDeviceUser(PackageData packageData,INonBlockingConnection nbc){
    	String xsockeId =nbc.getId(); 
    	INonBlockingConnection xsocke= nbc;
    	String terminalPhone=packageData.getMsgHeader().getTerminalPhone();
    	if(!socketTagMap.containsKey(xsockeId)){//判断标记map里是否存在对应的xsockeId
    		socketTagMap.put(xsockeId, terminalPhone);
    		if(deviceMap.containsKey(terminalPhone)){//处理已登记号的设备
    			UserDeviceBean olduserDeviceBean= deviceMap.get(terminalPhone);
    			olduserDeviceBean.setConnect(true);
    			olduserDeviceBean.setXsocke(xsocke);
    			return 3;
    		}else{
    			UserDeviceBean userDeviceBean=new UserDeviceBean(); 
    			userDeviceBean.setConnect(true);
    			userDeviceBean.setXsocke(xsocke);
    			userDeviceBean.setPackageData(packageData);
    			deviceMap.put(terminalPhone, userDeviceBean);
    		}
    		return 1;
    	}
    	return 0;
    }
    
    public UserDeviceBean getUserDeviceBean(String terminalPhone){
    	UserDeviceBean olduserDeviceBean= deviceMap.get(terminalPhone);
    	return olduserDeviceBean;
    }
    
    // 断开连接操作
    public boolean removeDeviceUser(String xsockeId) {
		String deviceId=socketTagMap.remove(xsockeId);
		if(deviceId!=null){
			UserDeviceBean userDeviceBean= deviceMap.get(deviceId);
			if(userDeviceBean!=null){
				userDeviceBean.setConnect(false);
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
