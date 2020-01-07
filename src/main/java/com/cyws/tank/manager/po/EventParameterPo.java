package com.cyws.tank.manager.po;

public class EventParameterPo {
	private String defaultValue;
	private boolean isSMS;
	private String mobiles;
	private boolean isEmail;
	private String emails;
	
	public String getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	public boolean isSMS() {
		return isSMS;
	}
	public void setSMS(boolean isSMS) {
		this.isSMS = isSMS;
	}
	public String getMobiles() {
		return mobiles;
	}
	public void setMobiles(String mobiles) {
		this.mobiles = mobiles;
	}
	public boolean isEmail() {
		return isEmail;
	}
	public void setEmail(boolean isEmail) {
		this.isEmail = isEmail;
	}
	public String getEmails() {
		return emails;
	}
	public void setEmails(String emails) {
		this.emails = emails;
	}
	
}
