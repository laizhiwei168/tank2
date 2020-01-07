package com.cyws.tank.codec.vo.req;

import com.cyws.tank.codec.vo.PackageData;

public class CommonMsg extends PackageData{
	// 对应配置帧的序号
	private Integer up_flowId; 
	// 对应配置帧的消息ID
	private Integer up_msgId;
	// 配置结果返回     {00H – 配置正常 , 01H – 配置失败  , 02H - 信息有误}
	private Integer result;
	
	public CommonMsg(PackageData packageData) {
		this.checkSum = packageData.getCheckSum();
		this.msgBodyBytes = packageData.getMsgBodyBytes();
		this.msgHeader = packageData.getMsgHeader();
	}

	public Integer getUp_flowId() {
		return up_flowId;
	}

	public void setUp_flowId(Integer up_flowId) {
		this.up_flowId = up_flowId;
	}

	public Integer getUp_msgId() {
		return up_msgId;
	}

	public void setUp_msgId(Integer up_msgId) {
		this.up_msgId = up_msgId;
	}

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}
}
