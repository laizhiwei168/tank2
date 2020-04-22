package com.cyws.tank.codec.vo;

public class ExtensionInitiativeData  extends ExtensionTankData {

	// 上传时间
	private String dateTime;
	
	// 传感器个数
	private int sensorNumber;
	
	// 掉电检测传感器状态
	//private int powerDownDetection;
	// 掉电检测  f0
	private int powerDownDetection;
	
	// 门开关状态 f1
	private int doorOnOff;
	
	// 电池电量 f2
	private int battery;
	
	// 压力1 01
	private int pressure1;
	
	// 液位  02
	private int liquidLevel;
	
	// 压力2 03
	private int pressure2;
	
	// 气体泄漏  04
	private int gasReveal;
	
	// 温度  05
	private int temperature;
	
	// 气体泄漏  06
	private int gasReveal2;
	
	// 压力3 07
	private int pressure3;
	
	// 液位2  08
	private int liquidLevel2;
	
	// 温度2  09
	private int temperature2;

	// 加臭次数 11
	private long addSmellyNumber;
	
	// 气化后压力
	private int A1;
	// BOG压力
	private int A2;
	// 气化前压力
	private int A3;
	// 调压前压力
	private int A4;
	// 调压后压力
	private int A5;
	// 仪表风压力
	private int A6;
	
	//1#储罐环温
	private int B1;
	//2#储罐环温
	private int B2;
	//BOG温度
	private int B3;
	//调压前温度
	private int B4;
	//调压后温度
	private int B5;
	//气化区环温
	private int B6;
	//卸车区环温
	private int B7;
	
	//2#储罐探头
	private int C1;
	//储罐增压区探头浓度
	private int C2;
	//气化区探头
	private int C3;
	//灌装区探头浓度
	private int C4;
	//卸车区探头
	private int C5;
	
	/**1 流量计*/
	private boolean is_workFlowAddUp_8;
	private boolean is_standardFlowAddUp_8;
	//工况流量累计 D1
	private long workFlowAddUp;	
	//标况流量累计 D2
	private long standardFlowAddUp;	
	//工况计瞬时流量 D3
	private int workFlow;	
	//标况计瞬时流量 D4
	private int standardFlow;	
	// 温度 D5
	private int workTemperature;	
	// 压力 D6
	private int workPressure;	
	//剩余流量  D7
	private int residueFlow;
	
	/**2 流量计*/
	private boolean is_workFlowAddUp2_8;
	private boolean is_standardFlowAddUp2_8;
	//工况流量累计2  D1
	private long workFlowAddUp2;	
	//标况流量累计2  D2
	private long standardFlowAddUp2;	
	//工况计瞬时流量2  D3
	private int workFlow2;	
	//标况计瞬时流量2  D4
	private int standardFlow2;	
	// 温度2 D5
	private int workTemperature2;	
	// 压力2 D6
	private int workPressure2;	
	//剩余流量2  D7
	private int residueFlow2;
	
	//开度
	private int aperture;
	
	// 3#储罐压力  D9
	private int tankPressure3;
	// 3#储罐液位  D8
	private int liquidLevel3;
	// 4#储罐压力  E9
	private int tankPressure4;
	// 4#储罐液位  E8
	private int liquidLevel4;
	
	//======黄工新添加的=====
	/**3 流量计*/
	private boolean is_workFlowAddUp3_8;
	private boolean is_standardFlowAddUp3_8;
	//工况流量累计3  51
	private long workFlowAddUp3;	
	//标况流量累计3  52
	private long standardFlowAddUp3;	
	//工况计瞬时流量3  53
	private int workFlow3;	
	//标况计瞬时流量3  54
	private int standardFlow3;	
	// 温度3 55
	private int workTemperature3;	
	// 压力3 56
	private int workPressure3;
	
	/**4 流量计*/
	private boolean is_workFlowAddUp4_8;
	private boolean is_standardFlowAddUp4_8;
	//工况流量累计4  5A
	private long workFlowAddUp4;	
	//标况流量累计4  5B
	private long standardFlowAddUp4;	
	//工况计瞬时流量4 5C
	private int workFlow4;	
	//标况计瞬时流量4  5D
	private int standardFlow4;	
	// 温度4 5E
	private int workTemperature4;	
	// 压力4 5F
	private int workPressure4;

	public long getAddSmellyNumber() {
		return addSmellyNumber;
	}

	public void setAddSmellyNumber(long addSmellyNumber) {
		this.addSmellyNumber = addSmellyNumber;
	}

	public boolean isIs_workFlowAddUp3_8() {
		return is_workFlowAddUp3_8;
	}

	public void setIs_workFlowAddUp3_8(boolean is_workFlowAddUp3_8) {
		this.is_workFlowAddUp3_8 = is_workFlowAddUp3_8;
	}

	public boolean isIs_standardFlowAddUp3_8() {
		return is_standardFlowAddUp3_8;
	}

	public void setIs_standardFlowAddUp3_8(boolean is_standardFlowAddUp3_8) {
		this.is_standardFlowAddUp3_8 = is_standardFlowAddUp3_8;
	}

	public long getWorkFlowAddUp3() {
		return workFlowAddUp3;
	}

	public void setWorkFlowAddUp3(long workFlowAddUp3) {
		this.workFlowAddUp3 = workFlowAddUp3;
	}

	public long getStandardFlowAddUp3() {
		return standardFlowAddUp3;
	}

	public void setStandardFlowAddUp3(long standardFlowAddUp3) {
		this.standardFlowAddUp3 = standardFlowAddUp3;
	}

	public int getWorkFlow3() {
		return workFlow3;
	}

	public void setWorkFlow3(int workFlow3) {
		this.workFlow3 = workFlow3;
	}

	public int getStandardFlow3() {
		return standardFlow3;
	}

	public void setStandardFlow3(int standardFlow3) {
		this.standardFlow3 = standardFlow3;
	}

	public int getWorkTemperature3() {
		return workTemperature3;
	}

	public void setWorkTemperature3(int workTemperature3) {
		this.workTemperature3 = workTemperature3;
	}

	public int getWorkPressure3() {
		return workPressure3;
	}

	public void setWorkPressure3(int workPressure3) {
		this.workPressure3 = workPressure3;
	}

	public boolean isIs_workFlowAddUp4_8() {
		return is_workFlowAddUp4_8;
	}

	public void setIs_workFlowAddUp4_8(boolean is_workFlowAddUp4_8) {
		this.is_workFlowAddUp4_8 = is_workFlowAddUp4_8;
	}

	public boolean isIs_standardFlowAddUp4_8() {
		return is_standardFlowAddUp4_8;
	}

	public void setIs_standardFlowAddUp4_8(boolean is_standardFlowAddUp4_8) {
		this.is_standardFlowAddUp4_8 = is_standardFlowAddUp4_8;
	}

	public long getWorkFlowAddUp4() {
		return workFlowAddUp4;
	}

	public void setWorkFlowAddUp4(long workFlowAddUp4) {
		this.workFlowAddUp4 = workFlowAddUp4;
	}

	public long getStandardFlowAddUp4() {
		return standardFlowAddUp4;
	}

	public void setStandardFlowAddUp4(long standardFlowAddUp4) {
		this.standardFlowAddUp4 = standardFlowAddUp4;
	}

	public int getWorkFlow4() {
		return workFlow4;
	}

	public void setWorkFlow4(int workFlow4) {
		this.workFlow4 = workFlow4;
	}

	public int getStandardFlow4() {
		return standardFlow4;
	}

	public void setStandardFlow4(int standardFlow4) {
		this.standardFlow4 = standardFlow4;
	}

	public int getWorkTemperature4() {
		return workTemperature4;
	}

	public void setWorkTemperature4(int workTemperature4) {
		this.workTemperature4 = workTemperature4;
	}

	public int getWorkPressure4() {
		return workPressure4;
	}

	public void setWorkPressure4(int workPressure4) {
		this.workPressure4 = workPressure4;
	}

	public int getTankPressure3() {
		return tankPressure3;
	}

	public void setTankPressure3(int tankPressure3) {
		this.tankPressure3 = tankPressure3;
	}

	public int getLiquidLevel3() {
		return liquidLevel3;
	}

	public void setLiquidLevel3(int liquidLevel3) {
		this.liquidLevel3 = liquidLevel3;
	}

	public int getTankPressure4() {
		return tankPressure4;
	}

	public void setTankPressure4(int tankPressure4) {
		this.tankPressure4 = tankPressure4;
	}

	public int getLiquidLevel4() {
		return liquidLevel4;
	}

	public void setLiquidLevel4(int liquidLevel4) {
		this.liquidLevel4 = liquidLevel4;
	}

	public int getA1() {
		return A1;
	}

	public void setA1(int a1) {
		A1 = a1;
	}

	public int getA2() {
		return A2;
	}

	public void setA2(int a2) {
		A2 = a2;
	}

	public int getA3() {
		return A3;
	}

	public void setA3(int a3) {
		A3 = a3;
	}

	public int getA4() {
		return A4;
	}

	public void setA4(int a4) {
		A4 = a4;
	}

	public int getA5() {
		return A5;
	}

	public void setA5(int a5) {
		A5 = a5;
	}

	public int getA6() {
		return A6;
	}

	public void setA6(int a6) {
		A6 = a6;
	}

	public int getB1() {
		return B1;
	}

	public void setB1(int b1) {
		B1 = b1;
	}

	public int getB2() {
		return B2;
	}

	public void setB2(int b2) {
		B2 = b2;
	}

	public int getB3() {
		return B3;
	}

	public void setB3(int b3) {
		B3 = b3;
	}

	public int getB4() {
		return B4;
	}

	public void setB4(int b4) {
		B4 = b4;
	}

	public int getB5() {
		return B5;
	}

	public void setB5(int b5) {
		B5 = b5;
	}

	public int getB6() {
		return B6;
	}

	public void setB6(int b6) {
		B6 = b6;
	}

	public int getB7() {
		return B7;
	}

	public void setB7(int b7) {
		B7 = b7;
	}

	public int getC1() {
		return C1;
	}

	public void setC1(int c1) {
		C1 = c1;
	}

	public int getC2() {
		return C2;
	}

	public void setC2(int c2) {
		C2 = c2;
	}

	public int getC3() {
		return C3;
	}

	public void setC3(int c3) {
		C3 = c3;
	}

	public int getC4() {
		return C4;
	}

	public void setC4(int c4) {
		C4 = c4;
	}

	public int getC5() {
		return C5;
	}

	public void setC5(int c5) {
		C5 = c5;
	}

	public boolean isIs_workFlowAddUp_8() {
		return is_workFlowAddUp_8;
	}

	public void setIs_workFlowAddUp_8(boolean is_workFlowAddUp_8) {
		this.is_workFlowAddUp_8 = is_workFlowAddUp_8;
	}

	public boolean isIs_standardFlowAddUp_8() {
		return is_standardFlowAddUp_8;
	}

	public void setIs_standardFlowAddUp_8(boolean is_standardFlowAddUp_8) {
		this.is_standardFlowAddUp_8 = is_standardFlowAddUp_8;
	}

	public boolean isIs_workFlowAddUp2_8() {
		return is_workFlowAddUp2_8;
	}

	public void setIs_workFlowAddUp2_8(boolean is_workFlowAddUp2_8) {
		this.is_workFlowAddUp2_8 = is_workFlowAddUp2_8;
	}

	public boolean isIs_standardFlowAddUp2_8() {
		return is_standardFlowAddUp2_8;
	}

	public void setIs_standardFlowAddUp2_8(boolean is_standardFlowAddUp2_8) {
		this.is_standardFlowAddUp2_8 = is_standardFlowAddUp2_8;
	}

	public int getGasReveal2() {
		return gasReveal2;
	}

	public void setGasReveal2(int gasReveal2) {
		this.gasReveal2 = gasReveal2;
	}

	public int getPressure3() {
		return pressure3;
	}

	public void setPressure3(int pressure3) {
		this.pressure3 = pressure3;
	}

	public int getLiquidLevel2() {
		return liquidLevel2;
	}

	public void setLiquidLevel2(int liquidLevel2) {
		this.liquidLevel2 = liquidLevel2;
	}

	public int getTemperature2() {
		return temperature2;
	}

	public void setTemperature2(int temperature2) {
		this.temperature2 = temperature2;
	}

	public long getWorkFlowAddUp() {
		return workFlowAddUp;
	}

	public void setWorkFlowAddUp(long workFlowAddUp) {
		this.workFlowAddUp = workFlowAddUp;
	}

	public long getStandardFlowAddUp() {
		return standardFlowAddUp;
	}

	public void setStandardFlowAddUp(long standardFlowAddUp) {
		this.standardFlowAddUp = standardFlowAddUp;
	}

	public int getWorkFlow() {
		return workFlow;
	}

	public void setWorkFlow(int workFlow) {
		this.workFlow = workFlow;
	}

	public int getStandardFlow() {
		return standardFlow;
	}

	public void setStandardFlow(int standardFlow) {
		this.standardFlow = standardFlow;
	}

	public int getWorkTemperature() {
		return workTemperature;
	}

	public void setWorkTemperature(int workTemperature) {
		this.workTemperature = workTemperature;
	}

	public int getWorkPressure() {
		return workPressure;
	}

	public void setWorkPressure(int workPressure) {
		this.workPressure = workPressure;
	}

	public int getResidueFlow() {
		return residueFlow;
	}

	public void setResidueFlow(int residueFlow) {
		this.residueFlow = residueFlow;
	}

	public long getWorkFlowAddUp2() {
		return workFlowAddUp2;
	}

	public void setWorkFlowAddUp2(long workFlowAddUp2) {
		this.workFlowAddUp2 = workFlowAddUp2;
	}

	public long getStandardFlowAddUp2() {
		return standardFlowAddUp2;
	}

	public void setStandardFlowAddUp2(long standardFlowAddUp2) {
		this.standardFlowAddUp2 = standardFlowAddUp2;
	}

	public int getWorkFlow2() {
		return workFlow2;
	}

	public void setWorkFlow2(int workFlow2) {
		this.workFlow2 = workFlow2;
	}

	public int getStandardFlow2() {
		return standardFlow2;
	}

	public void setStandardFlow2(int standardFlow2) {
		this.standardFlow2 = standardFlow2;
	}

	public int getWorkTemperature2() {
		return workTemperature2;
	}

	public void setWorkTemperature2(int workTemperature2) {
		this.workTemperature2 = workTemperature2;
	}

	public int getWorkPressure2() {
		return workPressure2;
	}

	public void setWorkPressure2(int workPressure2) {
		this.workPressure2 = workPressure2;
	}

	public int getResidueFlow2() {
		return residueFlow2;
	}

	public void setResidueFlow2(int residueFlow2) {
		this.residueFlow2 = residueFlow2;
	}

	public int getAperture() {
		return aperture;
	}

	public void setAperture(int aperture) {
		this.aperture = aperture;
	}

	public ExtensionInitiativeData(ExtensionBaseData extensionBaseData){
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
	
	public int getBattery() {
		return battery;
	}

	public void setBattery(int battery) {
		this.battery = battery;
	}

	public int getPressure1() {
		return pressure1;
	}

	public void setPressure1(int pressure1) {
		this.pressure1 = pressure1;
	}

	public int getLiquidLevel() {
		return liquidLevel;
	}

	public void setLiquidLevel(int liquidLevel) {
		this.liquidLevel = liquidLevel;
	}

	public int getPressure2() {
		return pressure2;
	}

	public void setPressure2(int pressure2) {
		this.pressure2 = pressure2;
	}

	public int getGasReveal() {
		return gasReveal;
	}

	public void setGasReveal(int gasReveal) {
		this.gasReveal = gasReveal;
	}

	public int getTemperature() {
		return temperature;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}

	public int getPowerDownDetection() {
		return powerDownDetection;
	}

	public void setPowerDownDetection(int powerDownDetection) {
		this.powerDownDetection = powerDownDetection;
	}

	public int getDoorOnOff() {
		return doorOnOff;
	}

	public void setDoorOnOff(int doorOnOff) {
		this.doorOnOff = doorOnOff;
	}
	
	@Override
	public String toString() {
		return "ExtensionInitiativeData [dateTime=" + dateTime + ", powerDownDetection=" + powerDownDetection
				+ ", temperature=" + temperature + ", doorOnOff=" + doorOnOff + ", battery=" + battery + ", liquidLevel="
				+ liquidLevel + ", gasReveal=" + gasReveal + ", pressure1=" + pressure1 +  ", pressure2=" + pressure2 +"]";
	}
}
