package com.cyws.tank.command.common;

public class ParameterConfigurationBean {

	private String IP1; // 0x0013 主IP
	private String portNumber1; // 0x0018 主端口 
	private String remoteUpgradeIP; //0x001A 远程升级IP 
	private String remoteUpgradePortNumber; // 0x001B 远程升级端口 	
	private String MaxPressure; // 0x0023 最大压力值KPa	
	private String MaxLiquidLeve; // 0x0024 最大液位值mmH2O 
	private String  fullLoad; // 0x0025 满载填充率（90-99）
	private String TankType; // 0x0026 储罐类型 0立罐/1卧罐
	private String sleepTime; // 0x0027 休眠时间min      DWORD
	private String PressureRange; // 0x002A 压力量程KPa       DWORD
	private String LiquidLeveRange; // 0x002B 液位量程KPa       DWORD
	private String referenceVoltage; // 0x0031  温度参考电压mv*10     DWORD
	private String referenceG; // 0x0032  温度放大器G值*100       DWORD
	private String standardVoltage;//0x0033  温度电源基准电压mv*10  DWORD
	private String temperatureA;// 0x0034  温度公式A值*10000       DWORD
	private String temperatureB;// 0x0035  温度公式B值       DWORD
	private String tankDiameter;// 0x0036  储罐直径（mm）  DWORD
	private String tankLength; // 0x0037 储罐长度（mm）  DWORD
	private String tankMedium; // 0x0038 储存介质  DWORD
	private String remoteUpgradeSleepTime; // 0x00000039 远程升级命令
	private String restart;// 0x0000003A  uint8  0-256  分钟 X分钟后重启设备
	private String workingTime;//0x0000003B uint8 （0-24） ,（0-24） ;  开始工作时间 , 结束工作时间 
	
	
	
	//=============黄工添加的==============
	
	private String  blockSet; //0x000000c1标段设定 c1
	private String  timeStart;//0x000000c2 时间段起点 C2
	private String  timeEnd;//0x000000c3 时间段终点c3
	private String  minutes;//0x000000c4 分钟数 c4
	
	
	
	public String getBlockSet() {
		return blockSet;
	}
	public void setBlockSet(String blockSet) {
		this.blockSet = blockSet;
	}
	public String getTimeStart() {
		return timeStart;
	}
	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}
	public String getTimeEnd() {
		return timeEnd;
	}
	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}
	public String getMinutes() {
		return minutes;
	}
	public void setMinutes(String minutes) {
		this.minutes = minutes;
	}
	public String getIP1() {
		return IP1;
	}
	public void setIP1(String iP1) {
		IP1 = iP1;
	}
	public String getPortNumber1() {
		return portNumber1;
	}
	public void setPortNumber1(String portNumber1) {
		this.portNumber1 = portNumber1;
	}
	public String getRemoteUpgradeIP() {
		return remoteUpgradeIP;
	}
	public void setRemoteUpgradeIP(String remoteUpgradeIP) {
		this.remoteUpgradeIP = remoteUpgradeIP;
	}
	public String getRemoteUpgradePortNumber() {
		return remoteUpgradePortNumber;
	}
	public void setRemoteUpgradePortNumber(String remoteUpgradePortNumber) {
		this.remoteUpgradePortNumber = remoteUpgradePortNumber;
	}
	public String getMaxPressure() {
		return MaxPressure;
	}
	public void setMaxPressure(String maxPressure) {
		MaxPressure = maxPressure;
	}
	public String getMaxLiquidLeve() {
		return MaxLiquidLeve;
	}
	public void setMaxLiquidLeve(String maxLiquidLeve) {
		MaxLiquidLeve = maxLiquidLeve;
	}
	public String getFullLoad() {
		return fullLoad;
	}
	public void setFullLoad(String fullLoad) {
		this.fullLoad = fullLoad;
	}
	public String getTankType() {
		return TankType;
	}
	public void setTankType(String tankType) {
		TankType = tankType;
	}
	public String getSleepTime() {
		return sleepTime;
	}
	public void setSleepTime(String sleepTime) {
		this.sleepTime = sleepTime;
	}
	public String getPressureRange() {
		return PressureRange;
	}
	public void setPressureRange(String pressureRange) {
		PressureRange = pressureRange;
	}
	public String getLiquidLeveRange() {
		return LiquidLeveRange;
	}
	public void setLiquidLeveRange(String liquidLeveRange) {
		LiquidLeveRange = liquidLeveRange;
	}
	public String getReferenceVoltage() {
		return referenceVoltage;
	}
	public void setReferenceVoltage(String referenceVoltage) {
		this.referenceVoltage = referenceVoltage;
	}
	public String getReferenceG() {
		return referenceG;
	}
	public void setReferenceG(String referenceG) {
		this.referenceG = referenceG;
	}
	public String getStandardVoltage() {
		return standardVoltage;
	}
	public void setStandardVoltage(String standardVoltage) {
		this.standardVoltage = standardVoltage;
	}
	public String getTemperatureA() {
		return temperatureA;
	}
	public void setTemperatureA(String temperatureA) {
		this.temperatureA = temperatureA;
	}
	public String getTemperatureB() {
		return temperatureB;
	}
	public void setTemperatureB(String temperatureB) {
		this.temperatureB = temperatureB;
	}
	public String getTankDiameter() {
		return tankDiameter;
	}
	public void setTankDiameter(String tankDiameter) {
		this.tankDiameter = tankDiameter;
	}
	public String getTankLength() {
		return tankLength;
	}
	public void setTankLength(String tankLength) {
		this.tankLength = tankLength;
	}
	public String getTankMedium() {
		return tankMedium;
	}
	public void setTankMedium(String tankMedium) {
		this.tankMedium = tankMedium;
	}
	public String getRemoteUpgradeSleepTime() {
		return remoteUpgradeSleepTime;
	}
	public void setRemoteUpgradeSleepTime(String remoteUpgradeSleepTime) {
		this.remoteUpgradeSleepTime = remoteUpgradeSleepTime;
	}
	public String getRestart() {
		return restart;
	}
	public void setRestart(String restart) {
		this.restart = restart;
	}
	public String getWorkingTime() {
		return workingTime;
	}
	public void setWorkingTime(String workingTime) {
		this.workingTime = workingTime;
	}
	
}
