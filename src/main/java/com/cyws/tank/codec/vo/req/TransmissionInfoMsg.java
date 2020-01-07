package com.cyws.tank.codec.vo.req;

import com.cyws.tank.codec.vo.ExtensionBaseData;
import com.cyws.tank.codec.vo.ExtensionTankData;

public class TransmissionInfoMsg extends ExtensionTankData {	
	// 时间
	private String dateTime;
	// 传感器个数
	private int sensorNumber;
	// 掉电检测ID   
	private int powerDownDetectorId;
	// 掉电检测字节
	private int powerDownDetectorChar;
	// 掉电检测传感器状态  
	private int powerDownDetectorType;
	// 门开关ID 
	private int doorId;	
	// 门字节数 
	private int doorChar;	
	// 门状态 
	private int doorType;
	// 电量id 
	private int batteryId;
	// 电量字节数 
	private int batteryChar;
	// 电量 
	private int battery;
	// 传感器ID 
	private int sensorId;	
	// 电压字节数 
	private int voltageChar;
	// 传感器测量值
	private int sensorValue;
	
	
	public TransmissionInfoMsg(ExtensionBaseData extensionBaseData){		
		super(extensionBaseData);
	}
	
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public int getSensorNumber() {
		return sensorNumber;
	}
	public void setSensorNumber(int sensorNumber) {
		this.sensorNumber = sensorNumber;
	}
	public int getPowerDownDetectorId() {
		return powerDownDetectorId;
	}
	public void setPowerDownDetectorId(int powerDownDetectorId) {
		this.powerDownDetectorId = powerDownDetectorId;
	}
	public int getPowerDownDetectorChar() {
		return powerDownDetectorChar;
	}
	public void setPowerDownDetectorChar(int powerDownDetectorChar) {
		this.powerDownDetectorChar = powerDownDetectorChar;
	}
	public int getPowerDownDetectorType() {
		return powerDownDetectorType;
	}
	public void setPowerDownDetectorType(int powerDownDetectorType) {
		this.powerDownDetectorType = powerDownDetectorType;
	}
	public int getDoorId() {
		return doorId;
	}
	public void setDoorId(int doorId) {
		this.doorId = doorId;
	}
	public int getDoorChar() {
		return doorChar;
	}
	public void setDoorChar(int doorChar) {
		this.doorChar = doorChar;
	}
	public int getDoorType() {
		return doorType;
	}
	public void setDoorType(int doorType) {
		this.doorType = doorType;
	}
	public int getBatteryId() {
		return batteryId;
	}
	public void setBatteryId(int batteryId) {
		this.batteryId = batteryId;
	}
	public int getBatteryChar() {
		return batteryChar;
	}
	public void setBatteryChar(int batteryChar) {
		this.batteryChar = batteryChar;
	}
	public int getBattery() {
		return battery;
	}
	public void setBattery(int battery) {
		this.battery = battery;
	}
	public int getSensorId() {
		return sensorId;
	}
	public void setSensorId(int sensorId) {
		this.sensorId = sensorId;
	}
	public int getVoltageChar() {
		return voltageChar;
	}
	public void setVoltageChar(int voltageChar) {
		this.voltageChar = voltageChar;
	}
	public int getSensorValue() {
		return sensorValue;
	}
	public void setSensorValue(int sensorValue) {
		this.sensorValue = sensorValue;
	}
		
}
