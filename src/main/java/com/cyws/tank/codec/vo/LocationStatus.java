package com.cyws.tank.codec.vo;

/**状态位定义*/
public class LocationStatus {
	private boolean is_ACC;//ACC是否开关
	private boolean is_location;//是否定位成功
	private boolean is_lat;//true是南纬为负，false是北纬为正
	private boolean is_log;//true是西经为正，false是东经为负
	private boolean is_operationState;//运营状态
	private boolean is_loglatSecrecy;//是否经过经纬度保密
	private boolean is_oilWay;//车辆油路是否正常
	private boolean is_electricityWay;//车辆电路是否正常
	private boolean is_doorLock;//车门锁状态
	
	
	public boolean isIs_ACC() {
		return is_ACC;
	}
	public void setIs_ACC(boolean is_ACC) {
		this.is_ACC = is_ACC;
	}
	public boolean isIslocation() {
		return is_location;
	}
	public void setIslocation(boolean is_location) {
		this.is_location = is_location;
	}
	public boolean isIs_lat() {
		return is_lat;
	}
	public void setIs_lat(boolean is_lat) {
		this.is_lat = is_lat;
	}
	public boolean isIs_log() {
		return is_log;
	}
	public void setIs_log(boolean is_log) {
		this.is_log = is_log;
	}
	public boolean isIs_operationState() {
		return is_operationState;
	}
	public void setIs_operationState(boolean is_operationState) {
		this.is_operationState = is_operationState;
	}
	public boolean isIs_loglatSecrecy() {
		return is_loglatSecrecy;
	}
	public void setIs_loglatSecrecy(boolean is_loglatSecrecy) {
		this.is_loglatSecrecy = is_loglatSecrecy;
	}
	public boolean isIs_oilWay() {
		return is_oilWay;
	}
	public void setIs_oilWay(boolean is_oilWay) {
		this.is_oilWay = is_oilWay;
	}
	public boolean isIs_electricityWay() {
		return is_electricityWay;
	}
	public void setIs_electricityWay(boolean is_electricityWay) {
		this.is_electricityWay = is_electricityWay;
	}
	public boolean isIs_doorLock() {
		return is_doorLock;
	}
	public void setIs_doorLock(boolean is_doorLock) {
		this.is_doorLock = is_doorLock;
	}	
		
	@Override
	public String toString() {
		return "LocationStatus [is_ACC=" + is_ACC + ", is_location=" + is_location
				+ ", is_lat=" + is_lat + ", is_log=" + is_log + ", is_operationState=" + is_operationState + ", is_loglatSecrecy="
				+ is_loglatSecrecy + ", is_oilWay=" + is_oilWay + ", is_electricityWay=" + is_electricityWay +  ", is_doorLock=" + is_doorLock +"]";
	}
}
