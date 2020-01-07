package com.cyws.tank.manager.po;

/**
 * 触发事件信息记录实体类
 * @author lzw
 *
 */
public class EquipmetEventPo {
	private String equipmetNo;
	private String clientId;
	private Integer eventTypeId;
	private String referenceValue;
	private String currentValue;
	private String eventContent;
	private String pushMobiles;
	private String pushEmail;
	private boolean isSee;
	
	public String getEquipmetNo() {
		return equipmetNo;
	}
	public void setEquipmetNo(String equipmetNo) {
		this.equipmetNo = equipmetNo;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	
	public Integer getEventTypeId() {
		return eventTypeId;
	}
	public void setEventTypeId(Integer eventTypeId) {
		this.eventTypeId = eventTypeId;
	}
	public String getReferenceValue() {
		return referenceValue;
	}
	public void setReferenceValue(String referenceValue) {
		this.referenceValue = referenceValue;
	}
	public String getCurrentValue() {
		return currentValue;
	}
	public void setCurrentValue(String currentValue) {
		this.currentValue = currentValue;
	}
	public String getEventContent() {
		return eventContent;
	}
	public void setEventContent(String eventContent) {
		this.eventContent = eventContent;
	}
	public String getPushMobiles() {
		return pushMobiles;
	}
	public void setPushMobiles(String pushMobiles) {
		this.pushMobiles = pushMobiles;
	}
	public String getPushEmail() {
		return pushEmail;
	}
	public void setPushEmail(String pushEmail) {
		this.pushEmail = pushEmail;
	}
	public boolean isSee() {
		return isSee;
	}
	public void setSee(boolean isSee) {
		this.isSee = isSee;
	}
	
}
