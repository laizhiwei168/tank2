package com.cyws.tank.socket.common;

import org.xsocket.connection.INonBlockingConnection;

import com.cyws.tank.codec.vo.PackageData;

public class UserDeviceBean {
	// 处理设备是否还连接服务
	private boolean isConnect=true; 
	
	// xsocket
	private String xsockeId;
	private INonBlockingConnection xsocke;
	
	// 报文架构
	private PackageData packageData;

	// 流水号
	private int flowId;
		
	
	public String getXsockeId() {
		return xsockeId;
	}
	public void setXsockeId(String xsockeId) {
		this.xsockeId = xsockeId;
	}
	public INonBlockingConnection getXsocke() {
		return xsocke;
	}
	public void setXsocke(INonBlockingConnection xsocke) {
		this.xsocke = xsocke;
	}
	public PackageData getPackageData() {
		packageData.getMsgHeader().setFlowId(getFlowId(packageData.getMsgHeader().getFlowId()));
		return packageData;
	}
	public void setPackageData(PackageData packageData) {
		this.packageData = packageData;
	}
	public int getFlowId() {
		return flowId;
	}
	public void setFlowId(int flowId) {
		this.flowId = flowId;
	}
	public boolean isConnect() {
		return isConnect;
	}
	public void setConnect(boolean isConnect) {
		this.isConnect = isConnect;
	}
	
	//获取流水号
    private  int getFlowId(int currentFlowId){
    	if (currentFlowId >= 0xffff)
			currentFlowId = 0;
		return ++currentFlowId;
    } 
	
}
