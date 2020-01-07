package com.cyws.tank.codec.vo.resp;

public class InitiativeInfoMsgBody {
	public static final byte success = 0x76;
	public static final byte failure = 0x78;
	
	// 版本
	private int versions;
	// 厂商编号 
	private int manufacturers;
	// 外设类型 
	private int peripheralType;
	// 命令类型 
	private int orderType;
	
	// 命令字
	private int orderChar;
	// 目的字
	private int goalChar;
	// 流水号
	private int flowId;
	// 结果
	private byte replyCode;
	// 内容
	private String content;
	
    public void setTargetInfo(int orderChar,int goalChar,int flowId,byte replyCode,String content){
    	this.orderChar=orderChar;
    	this.goalChar=goalChar;
    	this.flowId=flowId;
    	this.replyCode=replyCode;
    	this.content=content;
    }
    
    public void setExtensionInfo(int versions,int manufacturers,int peripheralType,int orderType){
    	this.versions=versions;
    	this.manufacturers=manufacturers;
    	this.peripheralType=peripheralType;
    	this.orderType=orderType;
    }

	public int getVersions() {
		return versions;
	}

	public void setVersions(int versions) {
		this.versions = versions;
	}

	public int getManufacturers() {
		return manufacturers;
	}

	public void setManufacturers(int manufacturers) {
		this.manufacturers = manufacturers;
	}

	public int getPeripheralType() {
		return peripheralType;
	}

	public void setPeripheralType(int peripheralType) {
		this.peripheralType = peripheralType;
	}

	public int getOrderType() {
		return orderType;
	}

	public void setOrderType(int orderType) {
		this.orderType = orderType;
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

	public byte getReplyCode() {
		return replyCode;
	}

	public void setReplyCode(byte replyCode) {
		this.replyCode = replyCode;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public static byte getSuccess() {
		return success;
	}

	public static byte getFailure() {
		return failure;
	}   
    
	
}
