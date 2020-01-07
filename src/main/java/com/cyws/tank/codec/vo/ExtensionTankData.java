package com.cyws.tank.codec.vo;

/**
 * 第三层协议结构
 * @author lzw
 *
 */
public class ExtensionTankData extends ExtensionBaseData{	
	// 命令字
	protected int orderChar;
	// 目的字
	protected int goalChar;
	// 流水号
	protected int flowId;
	// 消息体长度
	protected int msgBodyLength;
	
	public ExtensionTankData(ExtensionBaseData extensionBaseData){
		this.extensionType=extensionBaseData.getExtensionType();
		this.checkSum=extensionBaseData.getCheckSum();
		this.versions=extensionBaseData.getVersions();
		this.manufacturers=extensionBaseData.getManufacturers();
		this.peripheralType=extensionBaseData.getPeripheralType();
		this.orderType=extensionBaseData.getOrderType();
		this.msgExtensionBodyBytes=extensionBaseData.getMsgExtensionBodyBytes();
		this.packageData=extensionBaseData.getPackageData();
	}
	
	public int getOrderChar() {
		return orderChar;
	}
	public void setOrderChar(int orderChar) {
		this.orderChar = orderChar;
	}
	public int getGoalChar() {
		return goalChar;
	}
	public void setGoalChar(int goalChar) {
		this.goalChar = goalChar;
	}
	public int getFlowId() {
		return flowId;
	}
	public void setFlowId(int flowId) {
		this.flowId = flowId;
	}
	public int getMsgBodyLength() {
		return msgBodyLength;
	}
	public void setMsgBodyLength(int msgBodyLength) {
		this.msgBodyLength = msgBodyLength;
	}	
		
}
