package com.cyws.tank.utils.log;

import com.cyws.tank.thread.pool.LogThreadPool;
import com.cyws.tank.utils.common.Utilty;


public class LogManager {
	private LogManager(){
    }
    private static LogManager manager = new LogManager();
    
    public static LogManager getInstance(){
        return manager;
    }
    private  Utilty util= new Utilty();
    //设备获取报文log
    public void PrintGainDeviceLog(String deviceId,byte[] bt){
    	LogThreadPool.init().execute(new  Runnable() {
			public void run() {
				int length=bt.length;
				String data_str=util.toStringByte(bt,0,length-1);				
				DataLogUtil.getLog_Exception().SaveGainDeviceMessage(deviceId.trim(),data_str);
			}
    	});
    }
    
    //设备log
    public void PrintSentDeviceLog(String deviceid,byte[] bt){
    	LogThreadPool.init().execute(new  Runnable() {
			public void run() {
				String data_str=util.toStringByte(bt,0,bt.length-1);
				DataLogUtil.getLog_Exception().SaveSentDeviceMessage(deviceid.trim(),data_str);
			}
    	});
    }
    
    // 获取命令的log
    public void PrintGetHttpCommandLog(String data){
    	LogThreadPool.init().execute(new  Runnable() {
			public void run() {
				DataLogUtil.getLog_Exception().SaveGetHttpCommandMessage(data);
			}
    	});
    }
    
    // 推送json
    public void PrintPushJsonLog(String data){
    	LogThreadPool.init().execute(new  Runnable() {
			public void run() {
				DataLogUtil.getLog_Exception().SavePushJsonMessage(data);
			}
    	});
    }
    
    
    // 获取客户数据log
    public void PrintGainPushUserLog(String userId,String json){
    	LogThreadPool.init().execute(new  Runnable() {
			public void run() {			
				DataLogUtil.getLog_Exception().SaveGainPushUserMessage(userId.trim(),json);
			}
    	});
    }
    
    //发送客户数据log
    public void PrintSentPushUserLog(String userId,String json){
    	LogThreadPool.init().execute(new  Runnable() {
			public void run() {
				DataLogUtil.getLog_Exception().SaveSentPushUserMessage(userId.trim(),json);
			}
    	});
    }
}
