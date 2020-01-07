package com.cyws.tank.manager.po;

public class MgSetEventBean {
	private String EQUIPMENT_ID;
	// 液位
	private Integer LEVELS_TYPE; // 0 - 上限 ， 1 - 下限
	private Double LEVELS_VALUE;
	// 压力
	private Integer PRESSURE_TYPE;
	private Double PRESSURE_VALUE;
	// 真空
	private Integer VACUUM_TYPE;
	private Double VACUUM_VALUE;
	public String getEQUIPMENT_ID() {
		return EQUIPMENT_ID;
	}
	public void setEQUIPMENT_ID(String eQUIPMENT_ID) {
		EQUIPMENT_ID = eQUIPMENT_ID;
	}
	public Integer getLEVELS_TYPE() {
		return LEVELS_TYPE;
	}
	public void setLEVELS_TYPE(Integer lEVELS_TYPE) {
		LEVELS_TYPE = lEVELS_TYPE;
	}
	public Double getLEVELS_VALUE() {
		return LEVELS_VALUE;
	}
	public void setLEVELS_VALUE(Double lEVELS_VALUE) {
		LEVELS_VALUE = lEVELS_VALUE;
	}
	public Integer getPRESSURE_TYPE() {
		return PRESSURE_TYPE;
	}
	public void setPRESSURE_TYPE(Integer pRESSURE_TYPE) {
		PRESSURE_TYPE = pRESSURE_TYPE;
	}
	public Double getPRESSURE_VALUE() {
		return PRESSURE_VALUE;
	}
	public void setPRESSURE_VALUE(Double pRESSURE_VALUE) {
		PRESSURE_VALUE = pRESSURE_VALUE;
	}
	public Integer getVACUUM_TYPE() {
		return VACUUM_TYPE;
	}
	public void setVACUUM_TYPE(Integer vACUUM_TYPE) {
		VACUUM_TYPE = vACUUM_TYPE;
	}
	public Double getVACUUM_VALUE() {
		return VACUUM_VALUE;
	}
	public void setVACUUM_VALUE(Double vACUUM_VALUE) {
		VACUUM_VALUE = vACUUM_VALUE;
	}
	
}
