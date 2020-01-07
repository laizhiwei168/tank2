package com.cyws.tank.manager.po;

public class CommandPo {
	private String id;//
	private String equipmet_id;//
	private String command_title;
	private String command_type;//
	private String command_content;//
	private String addTime;//
	private Integer status;//
	private Integer header;//
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEquipmet_id() {
		return equipmet_id;
	}
	public void setEquipmet_id(String equipmet_id) {
		this.equipmet_id = equipmet_id;
	}
	public String getCommand_title() {
		return command_title;
	}
	public void setCommand_title(String command_title) {
		this.command_title = command_title;
	}
	public String getCommand_type() {
		return command_type;
	}
	public void setCommand_type(String command_type) {
		this.command_type = command_type;
	}
	public String getCommand_content() {
		return command_content;
	}
	public void setCommand_content(String command_content) {
		this.command_content = command_content;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getHeader() {
		return header;
	}
	public void setHeader(Integer header) {
		this.header = header;
	}
		
}
